package com.hieunv.ltht;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by HieuNguyen on 02/25/2017.
 */
public class ClientSide {
    public static void main(String[] args) {

        String serverHost = "localhost";
        int port = 80;
        Socket socketOfClient = null;
        BufferedWriter os = null;
        BufferedReader is = null;

        Scanner sc = new Scanner(System.in);


        try {
            try {
                System.out.println("Enter serverHost: ");
                serverHost = sc.nextLine();
                System.out.println("Enter port: ");
                port = sc.nextInt();
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
            while (true) {
                System.out.println("\nMENU");
                System.out.println("1.Giai PTB2");
                System.out.println("2.Giai PT bac nhat hai an");
                System.out.println("3.Exit");
                System.out.println("Your choice: ");
                int ch;
                do {
                    ch = sc.nextInt();
                    if (ch < 1 || ch > 3) {
                        System.out.println("Try again: ");
                    }
                } while (ch < 1 || ch > 3);
                if (ch == 1) {
                    System.out.println("Enter value a,b,c follow syntax \"a b c\" : ");
                    sc.nextLine();
                    String s = sc.nextLine();
                    os.write("1 " + s);
                    os.newLine();
                    os.flush();
                } else if (ch == 2) {
                    System.out.println("Enter value a1,b1,c1,a2,b2,c2 follow syntax \"a1 b1 c1 a2 b2 c2\" : ");
                    sc.nextLine();
                    String s = sc.nextLine();
                    os.write("2 " + s);
                    os.newLine();
                    os.flush();
                } else {
                    os.write("EXIT");
                    os.flush();
                    System.out.println("Connect is lost!");
                    break;
                }
                System.out.println(is.readLine());
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
