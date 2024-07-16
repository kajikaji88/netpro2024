package practise2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PrimeClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 5050;

        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("サーバに接続しました。");

            String input;
            while (true) {
                System.out.print("数を入力してください（終了は'q'または'exit'）：");
                input = scanner.nextLine();
                out.println(input);

                if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("exit")) {
                    System.out.println("クライアントを終了します。");
                    break;
                }

                String response = in.readLine();
                System.out.println(response);
            }
        } catch (UnknownHostException e) {
            System.err.println("ホストに接続できません: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/Oエラーが発生しました: " + e.getMessage());
        }
    }
}
