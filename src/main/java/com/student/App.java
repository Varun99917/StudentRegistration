
package com.student;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {

            String response;

            if (exchange.getRequestMethod().equals("POST")) {

                exchange.getRequestBody().close();

                response = "<html><body style='font-family:Arial;text-align:center;padding:50px;'>"
                        + "<h1 style='color:green;'>Registration Successful!</h1>"
                        + "<p>Student registration completed successfully.</p>"
                        + "<a href='/'>Go Back</a>"
                        + "</body></html>";

            } else {

                InputStream input =
                        App.class.getResourceAsStream("/index.html");

                if (input == null) {
                    response = "HTML file not found!";
                } else {
                    response = new String(
                            input.readAllBytes(),
                            StandardCharsets.UTF_8);
                    input.close();
                }
            }

            byte[] data = response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set(
                    "Content-Type", "text/html; charset=UTF-8");

            exchange.sendResponseHeaders(200, data.length);

            exchange.getResponseBody().write(data);

            exchange.close();
        });

        server.start();

        System.out.println("Student Registration Server Started!");
        System.out.println("Open http://localhost:8080");
    }
}
