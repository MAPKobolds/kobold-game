package org.uniba.kobold.socket;

import java.io.*;
import java.net.Socket;

public class HttpPage {

    private final Socket socket;

    public HttpPage(Socket socket) {
        this.socket = socket;
    }

    public void renderPage(String filePath) throws IOException {
        File index = new File(filePath);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new FileReader(index));

        // print HTTP headers
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html");
        printWriter.println("Content-Length: " + index.length());
        printWriter.println("\r\n");

        String line = reader.readLine();
        while (line != null) {
            printWriter.println(line);
            line = reader.readLine();
        }

        reader.close();
        printWriter.close();
    }

}
