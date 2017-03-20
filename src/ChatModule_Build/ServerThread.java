import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Hieu Nguyen on 3/20/2017.
 */
public class ServerThread extends Thread{
    private int clientNumber;
    private Socket socketOfServer;

    public ServerThread(Socket socketOfServer, int clientNumber) {
        this.clientNumber = clientNumber;
        this.socketOfServer = socketOfServer;
        log("New connection with client #" + this.clientNumber + " at " + socketOfServer);
    }

    private void log(String message) {
        System.out.println(message);
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        StringBuilder message = new StringBuilder();
        try {

            BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

            new RecieveMessage(socketOfServer,is,"Client").start();
            while (true) {
                    message.append(sc.nextLine());
                    os.write(message.toString());
                    os.newLine();
                    os.flush();
                    message.setLength(0);
            }
        } catch (IOException e) {
            log("Lost connection with client #" + this.clientNumber + " at " + socketOfServer);
        }
    }
}

