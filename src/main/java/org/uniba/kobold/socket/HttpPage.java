package org.uniba.kobold.socket;

import java.io.*;
import java.net.Socket;

public class HttpPage {

    private final Socket socket;

    public HttpPage(Socket socket) {
        this.socket = socket;
    }

    public void renderPage(File file) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new FileReader(file));

        initPage(printWriter, file.length());

        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            printWriter.println(line);
            line = reader.readLine();
        }

        reader.close();
        printWriter.close();
    }

    public void renderPage(String htmlPage) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

        initPage(printWriter, htmlPage.length());
        printWriter.println(htmlPage);

        printWriter.close();
    }

    public void initPage(PrintWriter printWriter, long contentLength) {
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html");
        printWriter.println("Content-Length: " + contentLength);
        printWriter.println("\r\n");
    }

}
