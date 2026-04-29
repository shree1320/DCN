import java.io.*;
import java.net.*;

public class TCPFileServer {
    public static void main(String[] args) {
        int port = 5000;
        String filePath = "sample.txt"; // file to send

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream os = socket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();

            System.out.println("File sent successfully.");
            bis.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
