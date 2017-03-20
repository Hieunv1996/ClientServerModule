package chatmodule;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Hieu Nguyen on 3/20/2017.
 */
public class Client {
    public static void main(String[] args) {
        final String serverHost = "localhost";
        final int port = 7777;
        Socket socketOfClient = null;
        BufferedWriter os = null;
        BufferedReader is = null;
        StringBuilder message = new StringBuilder();

        Scanner sc = new Scanner(System.in);


        try {
            try {
//                System.out.println("Enter serverHost: ");
//                serverHost = sc.nextLine();
//                System.out.println("Enter port: ");
//                port = sc.nextInt();
                socketOfClient = new Socket(serverHost, port);
            } catch (IOException e) {
                System.out.println("Error when connect to serverHost, try again");
            }
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
            return;
        }
        System.out.println("Connect to " + serverHost + ":" + port + " success!");
        try {
            new RecieveMessage(socketOfClient,is,"Server").start();
            while (true) {
                    message.append(sc.nextLine());
                    os.write(message.toString());
                    os.newLine();
                    os.flush();
                    message.setLength(0);
            }
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("Lost connect! Please check your connection.");
        }
    }
}
