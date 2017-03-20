package com.hieunv.ltht;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by HieuNguyen on 02/25/2017.
 */
public class ServerSide {
    public static void main(String args[]) throws IOException {

        ServerSocket listener = null;

        int clientNumber = 0;

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
                new ServiceThread(socketOfServer, clientNumber++).start();
            }
        } finally {
            listener.close();
        }

    }

    private static void log(String message) {
        System.out.println(message);
    }

    private static class ServiceThread extends Thread {

        private int clientNumber;
        private Socket socketOfServer;

        public ServiceThread(Socket socketOfServer, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;
            log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
        }

//        private String giaiPTB1(double a1,double b1,double c1,double a2,double b2, double c2){
//            String rs = "";
//            double D,Dx,Dy;
//            D=a1*b2-a2*b1; Dx=c1*b2-c2*b1; Dy=a1*c2-a2*c1;
//            if(D == 0){
//                if(Dx == 0 && Dy == 0){
//                    rs = "PT bac nhat co vo so nghiem!";
//                }else{
//                    rs = "PT bac nhat vo nghiem!";
//                }
//            }else{
//                rs = "PT bac nhat co nghiem duy nhat (x,y) = (" + (Dx/D)+","+(Dy/D)+")";
//            }
//            return rs;
//        }
//
//
//        private String giaiPTB2(double a, double b, double c){
//            String result = "";
//            if(a == 0){
//                if(b == 0){
//                    if(c == 0){
//                        result += "Phuong trinh bac nhat(a = 0) co vo so nghiem!";
//                    }else{
//                        result += "Phuong trinh bac nhat(a = 0) vo nghiem!";
//                    }
//                }else{
//                    result += "Phuong trinh bac nhat(a = 0) co nghiem: x = " + (-c/b);
//                }
//            }else{
//                double delta = b*b - a*c*4;
//                if(delta < 0){
//                    result += "Phuong trinh bac hai vo nhgiem!";
//                }else if(delta == 0){
//                    result += "Phuong trinh bac hai co ngiem kep x1 = x2 = " + (-b/(2*a));
//                }else{
//                    result += "Phuong trinh bac hai co 2 nghiem phan biet: x1 = " + (-b + Math.sqrt(delta))/(2*a) + ", x2 = " + (-b - Math.sqrt(delta))/(2*a);
//                }
//            }
//            return  result;
//        }

        @Override
        public void run() {
            Scanner sc = new Scanner(System.in);
            StringBuilder message = new StringBuilder();
            try {

                BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

                while (true) {
                    String line = is.readLine();
                    if(line == null) continue;
                    System.out.println("Client: "+line);
                    if(sc.hasNext()){
                        message.append(sc.nextLine());
                        os.write(message.toString());
                        os.newLine();
                        os.flush();
                        message.setLength(0);
                    }
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


}
