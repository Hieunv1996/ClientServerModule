package chatmodule;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Hieu Nguyen on 3/20/2017.
 */
public class RecieveMessage extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private String target;

    public RecieveMessage(Socket socket, BufferedReader reader, String target) {
        this.socket = socket;
        this.reader = reader;
        this.target = target;
    }

    @Override
    public void run() {
        StringBuilder message = new StringBuilder();
        try {
            while (true) {
                message.append(reader.readLine());
                if (message.length() != 0) {
                    System.out.println(target+": "+message);
                    message.setLength(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Connection is lost!");
        }
    }
}
