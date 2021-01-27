package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8081);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ждем клиента");
            Socket client = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            Thread readerThread = new com.company.ReaderThread(dataInputStream, client);
            readerThread.start();
            while (!client.isClosed()) {
                String msg = scanner.nextLine();
                dataOutputStream.writeUTF(msg);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
