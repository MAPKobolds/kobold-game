package org.uniba.kobold.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class PostPage {

    private static final Logger log = LoggerFactory.getLogger(PostPage.class);

    static public String getPostPage () throws IOException {
        String endpoint = "/posts";
        Socket socket = new Socket("jsonplaceholder.typicode.com", 80);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("GET " + endpoint + " HTTP/1.1");
        out.println("Host: " + "jsonplaceholder.typicode.com");
        out.println("Connection: close");
        out.println();

        // Lettura richiesta
        String line;
        String result = "";
        while ((line = in.readLine()) != null) {
            result = result.concat(line);
        }

        //getter rudimentale dell'array contenuto nella richiesta
        return PostPage.buildPage(result.split("\\[")[3]);
    }

    private static String buildPage(String content) {
        return "<!DOCTYPE html><br>" +
                "<html lang=\"en\"><br>" +
                "<head><br>" +
                "    <meta charset=\"UTF-8\"><br>" +
                "    <title>Socket request</title><br>" +
                "</head><br>" +
                "<body><br>" +
                "    Ecco il risultato della richiesta con socket client:<br>" +
                "    <br><br><br>" +
                "    <code><br>" + content + "<br></code><br>" +
                "        <br>" +
                "<br>" +
                "</body><br>" +
                "</html>";
    }

}
