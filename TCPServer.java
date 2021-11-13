import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class TCPServer {

    static int counter = 0;

    public static void writeMessage(String message, boolean increaseCounter, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        if (increaseCounter) counter++;
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9090);
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        while(true) {
            try {
                Socket socket = serverSocket.accept();

                System.out.println("LOG: Accepting connections");

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while(true) {

                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("CLIENT: " + msgFromClient);
                    counter = 0;

                    // znajdź najwiekszą liczbę, która podniesiona do kwadratu będzie mniejsza niż liczba wysłana klientowi
                    if("191616".equals(msgFromClient)) {
                        writeMessage("12345", true, bufferedWriter);
                    } else {
                        writeMessage("191616", false, bufferedWriter);
                    }

                    // wyślij liczbe z której trzeba usunąć wszystkie piątki
                    msgFromClient = bufferedReader.readLine();
                    System.out.println("CLIENT: " + msgFromClient);
                    if("111".equals(msgFromClient)) {
                        writeMessage("5437654", true, bufferedWriter);
                    }

                    // wyślij port klienta
                    msgFromClient = bufferedReader.readLine();
                    System.out.println("CLIENT: " + msgFromClient);
                    if("43764".equals(msgFromClient)) {
                        writeMessage("wyslij port", true, bufferedWriter);
                    }

                    // wyślij 3 kolejne liczby ktore muszą być dodane
                    msgFromClient = bufferedReader.readLine();
                    System.out.println("CLIENT: " + msgFromClient);
                    if(socket.getPort() == Integer.parseInt(msgFromClient)) {
                        writeMessage("4", true, bufferedWriter);
                        writeMessage("5", false, bufferedWriter);
                        writeMessage("6", false, bufferedWriter);
                    }

                    // kolokwium ukończone!
                    msgFromClient = bufferedReader.readLine();
                    System.out.println("CLIENT: " + msgFromClient);
                    if("15".equals(msgFromClient)) {
                        writeMessage("brawo!!", true, bufferedWriter);
                    }

                    if(counter == 5) {
                        writeMessage("zaliczyles kolosa!!", false, bufferedWriter);
                    } else {
                        writeMessage(" nie zaliczyles kolosa!!", false, bufferedWriter);
                    }
                    break;

//                    if(msgFromClient.equalsIgnoreCase("BYE")) break;
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
