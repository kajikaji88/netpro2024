import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AchiMuiteHoiClient extends JFrame implements ActionListener {
    private static final String[] JANKEN_CHOICES = {"goo", "cyoki", "paa"};
    private static final String[] DIRECTIONS = {"↑", "↓", "←", "→"};
    private static final String SERVER_ADDRESS = "localhost"; // またはグローバルIPアドレス
    private static final int SERVER_PORT = 5050;

    private PrintWriter out;
    private BufferedReader in;
    private JLabel instructionLabel;
    private JLabel resultLabel;
    private JButton[] jankenButtons;
    private JButton[] directionButtons;

    private boolean jankenPhase; // true: じゃんけんフェーズ, false: あっち向いてホイフェーズ
    private boolean awaitingResponse; // サーバーからの応答待ち

    public AchiMuiteHoiClient() {
        setTitle("あっち向いてホイ - クライアント");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        instructionLabel = new JLabel("じゃんけんをしてください", SwingConstants.CENTER);
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
            //jankenButtons[i].setEnabled(false); // Initially disable direction buttons

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

        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        processServerMessage(message);
                    }
                } catch (IOException e) {
                    handleConnectionError();
                }
            }).start();
        } catch (IOException e) {
            handleConnectionError();
        }
    }

    private void handleConnectionError() {
        resultLabel.setText("サーバーへの接続に失敗しました。");
        instructionLabel.setText("");
        for (JButton button : jankenButtons) {
            button.setEnabled(false);
        }
        for (JButton button : directionButtons) {
            button.setEnabled(false);
        }
    }

    private void processServerMessage(String message) {
        if (message.equals("START_JANKEN")) {
            jankenPhase = true;
            instructionLabel.setText("じゃんけんをしてください");
            enableButtons(jankenButtons, true);
            enableButtons(directionButtons, false);
            awaitingResponse = false;
        } else if (message.equals("じゃんけん結果完了")) {
            jankenPhase = false;
            instructionLabel.setText("方向を選んでください");
            enableButtons(jankenButtons, false); // ここを false に設定
            enableButtons(directionButtons, true); // ここを true に設定
            awaitingResponse = false;
        } else if (message.startsWith("結果:")) {
            resultLabel.setText(message);
            if (message.contains("引き分け")) { // あいこの場合はじゃんけんを再度行う
                instructionLabel.setText("あいこです。じゃんけんをしてください");
                enableButtons(jankenButtons, true);
                enableButtons(directionButtons, false);
                awaitingResponse = false;
            } else {
                instructionLabel.setText("方向を選んでください");
                enableButtons(jankenButtons, false);
                enableButtons(directionButtons, true);
                awaitingResponse = false;
            }
        } else if (message.startsWith("方向結果:")) {
            resultLabel.setText(message);
            instructionLabel.setText("じゃんけんをしてください");
            enableButtons(jankenButtons, true);
            enableButtons(directionButtons, false);
            awaitingResponse = false;
        } else {
            resultLabel.setText(message);
            enableButtons(jankenButtons, true);
            enableButtons(directionButtons, false);
            awaitingResponse = false;
        }

        // コンソールに結果を出力
        System.out.println(message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String actionCommand = clickedButton.getActionCommand();

        if (jankenPhase) {
            enableButtons(jankenButtons, false);
            enableButtons(directionButtons, false);
            instructionLabel.setText("相手の動きを待っています...");
            awaitingResponse = true;
            out.println(actionCommand);
        } else {
            enableButtons(jankenButtons, false);
            enableButtons(directionButtons, false);
            instructionLabel.setText("相手の動きを待っています...");
            awaitingResponse = true;
            out.println(actionCommand);
        }
    }

    private void enableButtons(JButton[] buttons, boolean enable) {
        for (JButton button : buttons) {
            button.setEnabled(enable);
        }
    }

    public static void main(String[] args) {
        new AchiMuiteHoiClient();
    }
}