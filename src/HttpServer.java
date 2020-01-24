import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started");

            while (true) {
            Socket socket = serverSocket.accept();

            try (BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream())
            ) {

                while (!bufferedReader.ready()) ;

                System.out.println();

                while (bufferedReader.ready()) {

                    System.out.println(bufferedReader.readLine());
                }

                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-Type: text/html; charset=utf-8");
                    writer.println();
                    writer.println("<h1>Hello, would you like to have something to drink with me?</h1>");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
