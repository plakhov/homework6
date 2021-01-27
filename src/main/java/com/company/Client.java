package com.company;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket client = new Socket("localhost", 8081);
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        Thread thread = new ReaderThread(dataInputStream, client);
        thread.start();
        while (!client.isClosed()) {
            dataOutputStream.writeUTF(scanner.nextLine());
        }
    }
}
