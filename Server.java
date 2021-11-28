import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Server {

    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[1400];

    public Server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void receiveThenSend() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress inetAddress =  datagramPacket.getAddress();
                System.out.println("IP address: " + inetAddress.toString());
                int port = datagramPacket.getPort();

// ZADANIE 1 - NWD
                // get 1st number
                String one_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                String parsed_one_messageFromClient = one_messageFromClient.replaceAll("\r", "").replaceAll("\n", "");
                System.out.println("parsed Message 1 from client " + parsed_one_messageFromClient);
                Integer one_int = Integer.parseInt(parsed_one_messageFromClient);
                System.out.println("Message 1 from client " + one_int);

                // get 2nd number
                datagramSocket.receive(datagramPacket);
                String two_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                String parsed_two_messageFromClient = two_messageFromClient.replaceAll("\r", "").replaceAll("\n", "");
                Integer two_int = Integer.parseInt(parsed_two_messageFromClient);
                System.out.println("Message 2 from client " + two_int);

                // get 3rd number
                datagramSocket.receive(datagramPacket);
                String three_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                String parsed_three_messageFromClient = three_messageFromClient.replaceAll("\r", "").replaceAll("\n", "");
                Integer three_int = Integer.parseInt(parsed_three_messageFromClient);
                System.out.println("Message 3 from client " + three_int);

                // get NWD
                int nwd_one = NWD.NWD_2(one_int, two_int);
                int nwd_final = NWD.NWD_2(nwd_one, three_int);
                System.out.println("NWD " + nwd_final);

                // parse NWD int -> String -> byte[] packet
                String one_send = Integer.toString(nwd_final);
                byte[] one_buffer = one_send.getBytes();
                
                // send the udp packet with NWD
                datagramPacket = new DatagramPacket(one_buffer, one_buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);


// ZADANIE 2 - usun cyfre
                // usun cyfre 4 z liczby
                datagramSocket.receive(datagramPacket);
                String four_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println(four_messageFromClient);
                String parsed_four_messageFromClient = four_messageFromClient.replaceAll("\r", "").replaceAll("\n", "");
                System.out.println("usun cyfre 4 z tej liczby " + parsed_four_messageFromClient);
                String four_response = scanner.nextLine();
                byte[] four_buffer = four_response.getBytes();

                // wyslij pakiet z usunietymi cyframi 4
                datagramPacket = new DatagramPacket(four_buffer, four_buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

// ZADANIE 3 - nowa liczba podniesiona do potegi nie moze byc wieksza niz odebrana liczba
                // odbierz liczbe
                datagramSocket.receive(datagramPacket);
                String five_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                String parsed_five_messageFromClient = five_messageFromClient.replaceAll("\r", "").replaceAll("\n", "");
                Long five_long = Long.parseLong(parsed_five_messageFromClient);
                System.out.println("x podniesione do 6 potegi nie moze byc wieksze niz ta liczba " + five_long);

                // wyznacz liczbe
                Long i = 1l;
                while(true) {
                    double tmp = Math.pow(i, 6);
//                    System.out.println(i);
                    if(tmp > five_long) {
                        System.out.println("five_long: " + five_long + "  tmp: " + tmp);
                        break;
                    } else {
                        ++i;
                    }
                }
                System.out.println("i-1: " + (i-1));
                
                // parse najwieksza potega mniejsza od x, int -> String -> byte[] packet
                String five_send = String.valueOf(i-1);
                byte[] five_buffer = five_send.getBytes();
                
                // send the udp packet with najwieksza liczba mniejsza od k gdy podniesiona do potegi 6
                datagramPacket = new DatagramPacket(five_buffer, five_buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

// sklej stringi
                // odbierz liczbe
                datagramSocket.receive(datagramPacket);
                String six_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                String parsed_six_messageFromClient = six_messageFromClient.replaceAll("\r", "").replaceAll("\n", "");
                System.out.println("pierwszy string do sklejenia " + parsed_six_messageFromClient);
                // powtórz to co powyżej tyle razy ile jest stringów...

                datagramSocket.receive(datagramPacket);
                String seven_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                String parsed_seven_messageFromClient = seven_messageFromClient.replaceAll("\r", "").replaceAll("\n", "");
                System.out.println("drugi string do sklejenia " + parsed_seven_messageFromClient);

                datagramSocket.receive(datagramPacket);
                String eight_messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                String parsed_eight_messageFromClient = eight_messageFromClient.replaceAll("\r", "").replaceAll("\n", "");
                System.out.println("trzeci string do sklejenia " + parsed_eight_messageFromClient);

                String a = "a";
                String b = "b";
                String c = "c";
                String six_send = parsed_six_messageFromClient+parsed_seven_messageFromClient+parsed_eight_messageFromClient;
                byte[] six_buffer = six_send.getBytes();
                
                // send the udp packet with najwieksza liczba mniejsza od k gdy podniesiona do potegi 6
                datagramPacket = new DatagramPacket(six_buffer, six_buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);

                scanner.close();
            } catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        Server server = new Server(datagramSocket);
        server.receiveThenSend();
    }
}
