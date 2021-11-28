import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class ClientUDP99 {
    public static void main(String[] args) throws IOException {
        byte[]doWyslania = {0x08, 0x54, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03, 0x77, 0x77, 0x77, 0x02, 0x77, 0x70, 0x02, 0x70, 0x6c, 0x00, 0x00,  0x01, 0x00, 0x01};
        String dnsName = "localhost";
        InetAddress dnsAddress = InetAddress.getByName(dnsName);
        int dnsPort = 10053;
        DatagramPacket packetToSend = new DatagramPacket(
                doWyslania,
                doWyslania.length,
                dnsAddress,
                dnsPort
        );
        DatagramSocket socket = new DatagramSocket();
        socket.send(packetToSend);
        DatagramPacket packetToReceive = new DatagramPacket(
                new byte[1400],
                1400
        );
        socket.receive(packetToReceive);
        System.out.println("Odebrano od: " + packetToReceive.getAddress().toString() +":" + packetToReceive.getPort());
        String response = new String(packetToReceive.getData(), 0, packetToReceive.getLength());
        System.out.println("Dane: " + response + " !!!");
        for(int i =0; i < packetToReceive.getLength(); i++) {
            System.out.print((packetToReceive.getData()[i]<0?256+packetToReceive.getData()[i]:packetToReceive.getData()[i]) + " ");
        }
    }
}
