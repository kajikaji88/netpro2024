package kadai06;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastServer {
    public static void main(String[] argv)
    throws Exception {
// 5100番ポートを監視するUDPソケットを生成

InetAddress multicastAddress = InetAddress.getByName("239.0.0.1");
int port = 5100;

try(MulticastSocket socket = new MulticastSocket(port);){
socket.joinGroup(multicastAddress);

System.out.println("Server started. Waiting for commands...");

// 受け付けるデータバッファとUDPパケットを作成
byte receiveBuffer[] = new byte[1024];
DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

while (true) {
    // UDPパケットを受信
    socket.receive(receivePacket);

    // 受信したデータを標準出力へ出力
    System.out.println
            (new String(receivePacket.getData(),
                    0, receivePacket.getLength()));
}
}
}
}
