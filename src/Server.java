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

            out.write(buffer);

            client.close();
        }
    }
}
