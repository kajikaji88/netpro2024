import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AchiMuiteHoiServer extends JFrame implements ActionListener {
    private static final String[] JANKEN_CHOICES = {"goo", "cyoki", "paa"};
    private static final String[] DIRECTIONS = {"↑", "↓", "←", "→"};
    private static final int SERVER_PORT = 5050;

    private PrintWriter out;
    private BufferedReader in;
    private JLabel instructionLabel;
    private JLabel resultLabel;
    private JButton[] jankenButtons;
    private JButton[] directionButtons;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private String clientJanken;
    private String clientDirection;
    private String serverJankenResult;

    public AchiMuiteHoiServer() {
        setTitle("あっち向いてホイ - サーバー");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        instructionLabel = new JLabel("クライアントを待っています...", SwingConstants.CENTER);
        resultLabel = new JLabel("", SwingConstants.CENTER);

        JPanel jankenPanel = new JPanel();
        jankenPanel.setLayout(new GridLayout(1, 3));
        jankenButtons = new JButton[3];
        for (int i = 0; i < 3; i++) {
            String imagePath = "/images/" + JANKEN_CHOICES[i].toLowerCase() + ".jpg";
            // 画像のリソースがnullでないことを確認
            if (getClass().getResource(imagePath) != null) {
                jankenButtons[i] = new JButton(new ImageIcon(getClass().getResource(imagePath)));
            } else {
                System.err.println("画像ファイルが見つかりません: " + imagePath);
                jankenButtons[i] = new JButton(JANKEN_CHOICES[i]);
            }            
            jankenButtons[i].setActionCommand(JANKEN_CHOICES[i]);
            jankenButtons[i].addActionListener(this);
            jankenButtons[i].setEnabled(false);
            jankenPanel.add(jankenButtons[i]);
        }

        JPanel directionPanel = new JPanel();
        directionPanel.setLayout(new GridLayout(1, 4));
        directionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            directionButtons[i] = new JButton(DIRECTIONS[i]);
            directionButtons[i].addActionListener(this);
            directionButtons[i].setEnabled(false);
            directionPanel.add(directionButtons[i]);
        }

        add(instructionLabel, BorderLayout.NORTH);
        add(jankenPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
        add(directionPanel, BorderLayout.SOUTH);

        setVisible(true);

        startServer();
    }

    private void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                while (true) {
                    clientSocket = serverSocket.accept();
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new PrintWriter(clientSocket.getOutputStream(), true);

                    System.out.println("クライアントが接続されました: " + clientSocket.getInetAddress());

                    out.println("START");
                    processClientConnection();

                    // Close the client connection after each game
                    clientSocket.close();
                }
            } catch (IOException e) {
                handleConnectionError();
            }
        }).start();
    }

    private void processClientConnection() {
        instructionLabel.setText("クライアントが接続されました。じゃんけんをしてください");

        try {
            while (true) {
                String clientMessage = in.readLine();
                if (clientMessage == null) break;

                if (instructionLabel.getText().contains("じゃんけん")) {
                    clientJanken = clientMessage;
                    instructionLabel.setText("サーバーの手を選んでください");
                    enableButtons(jankenButtons, true);
                    enableButtons(directionButtons, false);
                } else if (instructionLabel.getText().contains("方向")) {
                    clientDirection = clientMessage;
                    instructionLabel.setText("サーバーの方向を選んでください");
                    enableButtons(jankenButtons, false);
                    enableButtons(directionButtons, true);
                }
            }
        } catch (IOException e) {
            handleConnectionError();
        }
    }

    private void processJanken(String serverHand) {
        enableButtons(jankenButtons, false);

        System.out.println("サーバーの手: " + serverHand);

        String result = determineJankenWinner(clientJanken, serverHand);
        serverJankenResult = result;
        resultLabel.setText("じゃんけんの結果: " + result);
        out.println("結果: " + result);
        out.println("サーバーの手: " + serverHand);
        System.out.println("じゃんけんの結果: " + result);

        if (result.equals("引き分け")) {
            instructionLabel.setText("引き分けです。もう一度じゃんけんをしてください");
            out.println("引き分け");
            enableButtons(jankenButtons, false);
            enableButtons(directionButtons, false);
        } else {
            instructionLabel.setText("クライアントが方向を選ぶのを待っています");
            out.println("じゃんけん結果完了");
            enableButtons(jankenButtons, false);
            enableButtons(directionButtons, false);
        }
    }

    private void processHoi(String serverDirection) {
        enableButtons(directionButtons, false);

        out.println(serverDirection);
        System.out.println("サーバーの向き: " + serverDirection);

        String result = determineHoiWinner(serverJankenResult, clientDirection, serverDirection);
        resultLabel.setText("あっち向いてホイの結果: " + result);
        out.println("方向結果: " + result);
        System.out.println("あっち向いてホイの結果: " + result);

        if (result.equals("クライアントの勝利") || result.equals("サーバーの勝利") || result.equals("引き分け") || result.equals("続行")) {
            instructionLabel.setText("じゃんけんをしてください");
            if(result.equals("クライアントの勝利") || result.equals("サーバーの勝利")){
            out.println("ゲーム終了");
            }else{
                out.println("続行");
            }
            resetGame();
            enableButtons(jankenButtons, false);
            enableButtons(directionButtons, false);
        }
    }

    private void resetGame() {
        instructionLabel.setText("クライアントが接続されました。じゃんけんをしてください");
        enableButtons(jankenButtons, true);
        enableButtons(directionButtons, false);
    }

    private void handleConnectionError() {
        resultLabel.setText("クライアントとの接続に失敗しました。");
        instructionLabel.setText("");
        enableButtons(jankenButtons, false);
        enableButtons(directionButtons, false);
        System.out.println("クライアントとの接続に失敗しました。");
    }

    private static String determineJankenWinner(String clientHand, String serverHand) {
        if (clientHand.equals(serverHand)) {
            return "引き分け";
        }
        if ((clientHand.equals("goo") && serverHand.equals("cyoki")) ||
                (clientHand.equals("cyoki") && serverHand.equals("paa")) ||
                (clientHand.equals("paa") && serverHand.equals("goo"))) {
            return "クライアント勝ち";
        }
        
        return "サーバー勝ち";
    }

    private static String determineHoiWinner(String jankenResult, String clientHoi, String serverHoi) {
        if (clientHoi.equals(serverHoi)) {
            return jankenResult.contains("クライアント勝ち") ? "クライアントの勝利" : "サーバーの勝利";
        }
        return "続行";
    }

    private void enableButtons(JButton[] buttons, boolean enable) {
        for (JButton button : buttons) {
            button.setEnabled(enable);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String actionCommand = clickedButton.getActionCommand();

        if (instructionLabel.getText().contains("サーバーの手を選んでください")) {
            processJanken(actionCommand);
            enableButtons(jankenButtons, false);  // サーバーのボタンを無効にする
        } else if (instructionLabel.getText().contains("サーバーの方向を選んでください")) {
            processHoi(actionCommand);
            enableButtons(directionButtons, false);  // サーバーのボタンを無効にする
        }
    }

    public static void main(String[] args) {
        new AchiMuiteHoiServer();
    }
}