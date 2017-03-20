import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Hieu Nguyen on 3/20/2017.
 */
public class Server{
    public static void main(String args[]) throws IOException {

        ServerSocket listener = null;

        int clientNumber = 1;

        try {
            listener = new ServerSocket(7777);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }

        System.out.println("Server is waiting to accept user...");
        try {
            while (true) {
                Socket socketOfServer = listener.accept();
                new ServerThread(socketOfServer, clientNumber++).start();
            }
        } finally {
            listener.close();
        }

    }
}
