import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer = new byte[1400];

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public void sendThenReceive() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("pierwsza liczba");
                String one_messageToSend = scanner.nextLine();
                buffer = one_messageToSend.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                System.out.println("druga liczba");
                String two_messageToSend = scanner.nextLine();
                buffer = two_messageToSend.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                System.out.println("trzecia liczba");
                String three_messageToSend = scanner.nextLine();
                buffer = three_messageToSend.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                datagramSocket.receive(datagramPacket);
                String one_messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("The server says you said NWD: " + one_messageFromServer);

// ZADANIE 2
                System.out.println("usun cyfre 4 z tej liczby");
                String four_messageToSend = "3456";
                byte[] four_buffer = four_messageToSend.getBytes();
                for(int i = 0; i < four_buffer.length; i++) { System.out.println("buffer: " + four_buffer[i]);}
                datagramPacket = new DatagramPacket(four_buffer, four_buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                datagramSocket.receive(datagramPacket);
                String two_messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("The server says you said: cyfra 4 usunieta " + two_messageFromServer);
// ZADANIE 3

                System.out.println("podaj liczbe k. k > x^6 ale k < (x+1)^6");
                String five_messageToSend = "15627";
                buffer = five_messageToSend.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                datagramSocket.receive(datagramPacket);
                String six_messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("liczba powinna byc 5, a jest: " + six_messageFromServer);

// SKLEJ STRINGI
                System.out.println("pierwszy teskt do sklejenia");
                String seven_messageToSend = scanner.nextLine();
                buffer = seven_messageToSend.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                System.out.println("drugi teskt do sklejenia");
                String eight_messageToSend = scanner.nextLine();
                buffer = eight_messageToSend.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                System.out.println("drugi teskt do sklejenia");
                String nine_messageToSend = scanner.nextLine();
                buffer = nine_messageToSend.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                datagramSocket.receive(datagramPacket);
                String nine_messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("sklejony string: " + nine_messageFromServer);

            } catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Client client = new Client(datagramSocket, inetAddress);
        System.out.println("Send datagram packet to a server.");
        client.sendThenReceive();
    }
}
