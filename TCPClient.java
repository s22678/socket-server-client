import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {

        Socket socket = null;

        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 9090);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            // Scanner scanner = new Scanner(System.in);

            while(true) {

                String msgToSend = "191616";

                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String firstVal = bufferedReader.readLine();

                Long i = 1l;
                while(true) {
                    double tmp = Math.pow(i, 2);
//                    System.out.println(i);
                    if(tmp > Long.parseLong(firstVal)) {
                        System.out.println("firstVal: " + firstVal + "  tmp: " + tmp);
                        break;
                    } else {
                        ++i;
                    }
                }
                System.out.println(i-1);

                bufferedWriter.write(String.valueOf(i-1));
                bufferedWriter.newLine();
                bufferedWriter.flush();

                StringBuilder sb = new StringBuilder();
                String secondVal = bufferedReader.readLine();
                System.out.println("2nd: " + secondVal);
                for(int j = 0; j < secondVal.length(); j++) {
                    if(secondVal.charAt(j) != '5') sb.append(secondVal.charAt(j));
                }

                bufferedWriter.write(sb.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String thirdVal = bufferedReader.readLine();
                System.out.println("3rd: " + thirdVal);

                bufferedWriter.write(  String.valueOf(socket.getLocalPort()));
                bufferedWriter.newLine();
                bufferedWriter.flush();

                Integer fourth = Integer.parseInt(bufferedReader.readLine());
                System.out.println("4th: " + fourth);
                Integer fifthVal = Integer.parseInt(bufferedReader.readLine());
                System.out.println("5th: " + fifthVal);
                Integer sixth = Integer.parseInt(bufferedReader.readLine());
                System.out.println("6th: " + sixth);

                bufferedWriter.write(String.valueOf(fourth + fifthVal + sixth));
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String seventh = bufferedReader.readLine();
                System.out.println("7th: " + seventh);

                String eighth = bufferedReader.readLine();
                System.out.println("8th: " + eighth);

//                System.out.println("RESPONSE: " + bufferedReader.readLine());
//                System.out.println("getLocalPort: " + socket.getLocalPort());
//                System.out.println("getLocalAddress: " + socket.getLocalAddress());
//                System.out.println("getInetAddress: " + socket.getInetAddress());
//                System.out.println("getPort: " + socket.getPort());

//                if(msgToSend.equalsIgnoreCase("BYE")) break;
                break;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                if (bufferedReader != null) bufferedReader.close();
                if (bufferedWriter != null) bufferedWriter.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (outputStreamWriter != null) outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
