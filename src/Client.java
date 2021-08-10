import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        InetAddress destAddr;
        int port;

        if (args.length == 2) {
            destAddr = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
        } else {
            destAddr = InetAddress.getLoopbackAddress();
            port = 10017;
        }

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a message (0 to quit): ");
            String userInput = input.nextLine();

            if (userInput.equals("0")) break;

            byte[] buffer = userInput.getBytes(StandardCharsets.ISO_8859_1);

            Socket socket = new Socket(destAddr, port);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(buffer);
            buffer = new byte[1024];
            in.read(buffer);

            String stringIn = new String(buffer);
            System.out.println("\n" + stringIn);
        }
    }
}
