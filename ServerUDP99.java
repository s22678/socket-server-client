import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ServerUDP99 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(10053);
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
