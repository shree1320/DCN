import java.io.*;
import java.net.*;

public class HttpGetExample {
    public static void main(String[] args) {
        try {
            // URL to request
            URL url = new URL("https://www.example.com");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine).append("\n");
            in.close();

            // Print response
            System.out.println("Server Response:\n" + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
