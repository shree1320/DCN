import java.io.*;
import java.net.*;

public class TCPFileClient {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int port = 5000;
        String savePath = "received.txt"; // file to save

        try (Socket socket = new Socket(serverHost, port)) {
            System.out.println("Connected to server.");

            InputStream is = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(savePath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();

            System.out.println("File received and saved as " + savePath);
            bos.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
