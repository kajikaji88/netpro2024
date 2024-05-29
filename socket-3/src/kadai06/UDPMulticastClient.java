package kadai06;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPMulticastClient {
    public static void main(String[] argv)
    throws Exception {
// UDPパケットを送信する先となるブロードキャストアドレス (5100番ポート) 
//ネットワーク部が全てビットがたっているものが
//ブロードキャストアドレス　
/*
192.168.1.255」は自分のネットワーク環境に合わせて書き変える必要がある。
ホストが所属しているネットワークが192.168.0.xxx/255.255.255.0なら「192.168.0.255」と指定する。
 */
InetSocketAddress remoteAddress = new InetSocketAddress("239.0.0.1", 5100);


String str= "HELLO";
// UDPパケットに含めるデータ
byte[] sendBuffer = str.getBytes();

// UDPパケット
DatagramPacket sendPacket =
        new DatagramPacket(sendBuffer, sendBuffer.length, remoteAddress);

// DatagramSocketインスタンスを生成して、UDPパケットを送信
try( DatagramSocket socket = new DatagramSocket()){
    socket.send(sendPacket);
    System.out.println("Sent:"+str);
    }
}
}
