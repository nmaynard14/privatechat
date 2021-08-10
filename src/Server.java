import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) throws Exception {
        int port;

        if (args.length > 1)
            throw new IllegalArgumentException("Parameter: <Port>");
        else if (args.length == 1)
            port = Integer.parseInt(args[0]);
        else
            port = 10017;

        ServerSocket server = new ServerSocket(port);

        while (true) {
            Socket client = server.accept();

            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();

            byte[] buffer = new byte[1024];

            in.read(buffer);

            System.out.print("Echo: ");
            for (int i = 0; buffer[i] != 0 && i < buffer.length; i++) {
                System.out.print((char)(buffer[i] & 0xFF));
            }
            System.out.print("\n");

            out.write(buffer);

            client.close();
        }
    }
}
