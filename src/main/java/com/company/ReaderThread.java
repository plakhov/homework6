package com.company;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReaderThread extends Thread {
    private final DataInputStream dataInputStream;
    private final Socket socket;

    public ReaderThread(DataInputStream dataInputStream, Socket socket) {
        this.dataInputStream = dataInputStream;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                String msg = dataInputStream.readUTF();
                System.out.println(msg);
                if ("/end".equals(msg)) {
                    socket.close();
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println();
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
