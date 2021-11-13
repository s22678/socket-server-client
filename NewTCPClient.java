import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NewTCPClient {

    public static void main(String[] args) throws IOException {
        Socket socket = null;

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        Scanner scanner = new Scanner(System.in);

        try {
            socket = new Socket("localhost", 9090);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true) {

                bufferedWriter.write("191616");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println(bufferedReader.readLine());



            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null) socket.close();
            if(bufferedReader != null) bufferedReader.close();
            if(bufferedWriter != null) bufferedWriter.close();
        }
    }
}
