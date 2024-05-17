import java.net.InetAddress;
import java.net.UnknownHostException;

public class CheckLocalAddreess {
    public static void main(String[] args) {
        try {
            // ローカルホストのInetAddressオブジェクトを取得
            InetAddress localHost = InetAddress.getLocalHost();
            
            // ホスト名を表示
            System.out.println("Host name is: " + localHost.getHostName());
            
            // IPアドレスを表示
            System.out.println("IP address is: " + localHost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}