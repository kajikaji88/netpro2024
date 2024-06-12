import java.net.InetAddress;
import java.net.UnknownHostException;

public class CheckLocalAddreess {
    public static void main(String[] args) {
        try {
            // ローカルホストのInetAddressオブジェクトを取得
            InetAddress addr= InetAddress.getByName("8.8.8.8");
            
            // ホスト名を表示
            System.out.println("Host name is: " + addr.getHostName());
            
            // IPアドレスを表示
            System.out.println("IP address is: " + addr.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}