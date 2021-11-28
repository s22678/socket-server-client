import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class KolosDwaSerwer {
    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[1400];

    public KolosDwaSerwer(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void receiveThenSend() {
        while(true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress inetAddress =  datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                System.out.println("IP address: " + inetAddress.toString() + "   port: " + port );


// ZADANIE
                String one_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("parsed Message 1 from client " + one_messageFromClient);
                String bez_dziewiatek = one_messageFromClient.replaceAll("9", "");
                System.out.println("bez_dziewiatek " + bez_dziewiatek);
                bez_dziewiatek += "\n";
                byte[] one_buffer = bez_dziewiatek.getBytes();

                datagramPacket = new DatagramPacket(one_buffer, one_buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

// ZADANIE 2
                datagramSocket.receive(datagramPacket);
                String two_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("x podniesione do 2 potegi nie moze byc wieksze niz ta liczba: " + two_messageFromClient);
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < two_messageFromClient.length(); j++) {
                    if(two_messageFromClient.charAt(j) != '\n') sb.append(two_messageFromClient.charAt(j));
                }

                String parsed_two_messageFromClient = sb.toString();
                Long five_long = Long.parseLong(parsed_two_messageFromClient);


                Long i = 1l;
                while(true) {
                    double tmp = Math.pow(i, 2);
//                    System.out.println(i);
                    if(tmp > five_long) {
                        System.out.println("five_long: " + five_long + "  tmp: " + tmp);
                        break;
                    } else {
                        ++i;
                    }
                }
                System.out.println("i-1: " + (i-1));


                String two = String.valueOf(i-1);
                String two_send = two+"\n";
                byte[] two_buffer = two_send.getBytes();
                datagramPacket = new DatagramPacket(two_buffer, two_buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

// ODBIERZ
                // 1 liczba
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String two_one_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("ODPOWIEDZ1 " + two_one_messageFromClient);
                // 1 liczba
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String two_two_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("ODZPOWIEDZ2" + two_two_messageFromClient);


// ZADANIE 3
                // 1 liczba
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String three_one_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("pierwsza liczba do dodania: " + three_one_messageFromClient);

                sb = new StringBuilder();
                for(int j = 0; j < two_messageFromClient.length(); j++) {
                    if(two_messageFromClient.charAt(j) != '\n') sb.append(two_messageFromClient.charAt(j));
                }
                Integer one_int = Integer.parseInt(sb.toString());

                // 2 liczba
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String three_two_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("druga liczba do dodania: " + three_two_messageFromClient);
                sb = new StringBuilder();
                for(int j = 0; j < two_messageFromClient.length(); j++) {
                    if(two_messageFromClient.charAt(j) != '\n') sb.append(two_messageFromClient.charAt(j));
                }
                Integer two_int = Integer.parseInt(sb.toString());

                // 3 liczba
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String three_three_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("trzecia liczba do dodania: " + three_three_messageFromClient);
                sb = new StringBuilder();
                for(int j = 0; j < two_messageFromClient.length(); j++) {
                    if(two_messageFromClient.charAt(j) != '\n') sb.append(two_messageFromClient.charAt(j));
                }
                Integer three_int = Integer.parseInt(sb.toString());

                // 4 liczba
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String three_four_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("trzecia liczba do dodania: " + three_three_messageFromClient);
                sb = new StringBuilder();
                for(int j = 0; j < two_messageFromClient.length(); j++) {
                    if(two_messageFromClient.charAt(j) != '\n') sb.append(two_messageFromClient.charAt(j));
                }
                Integer four_int = Integer.parseInt(sb.toString());
                Integer result = one_int + two_int + three_int + four_int;

                String threee_res = result.toString();

                threee_res = threee_res+"\n";
                byte[] three_buffer = threee_res.getBytes();

                // send the udp packet with NWD
                datagramPacket = new DatagramPacket(three_buffer, three_buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

// ODBIERZ FINALNA FLAGE

                // FLAGA
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String four_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("finalna flaga: " + four_messageFromClient);

            } catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(10053);
        KolosDwaSerwer server = new KolosDwaSerwer(datagramSocket);
        server.receiveThenSend();
    }
}
