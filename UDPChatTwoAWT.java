import java.awt.*;
import java.awt.event.*;
import java.net.*;

class ChatFrame extends Frame implements ActionListener {
    TextArea chatArea;
    TextField inputField;
    Button sendButton;
    DatagramSocket socket;
    int localPort, remotePort;
    InetAddress remoteHost;
    String userName;

    ChatFrame(String userName, int localPort, String remoteHost, int remotePort) {
        super("UDP Chat - " + userName);
        this.userName = userName;
        this.localPort = localPort;
        this.remotePort = remotePort;

        chatArea = new TextArea();
        chatArea.setEditable(false);
        inputField = new TextField();
        sendButton = new Button("Send");

        Panel bottom = new Panel(new BorderLayout());
        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(sendButton, BorderLayout.EAST);

        add(chatArea, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);

        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        try {
            socket = new DatagramSocket(localPort);
            this.remoteHost = InetAddress.getByName(remoteHost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Receiver thread
        Thread receiver = new Thread(() -> {
            byte[] buffer = new byte[1024];
            while (true) {
                try {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    chatArea.append("Friend: " + msg + "\n");
                } catch (Exception e) {
                    break;
                }
            }
        });
        receiver.start();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                socket.close();
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String msg = inputField.getText();
            byte[] data = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, remoteHost, remotePort);
            socket.send(packet);
            chatArea.append(userName + ": " + msg + "\n");
            inputField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class UDPChatTwoAWT {
    public static void main(String[] args) {
        // Create two chat windows in one run
        new ChatFrame("User A", 5000, "localhost", 6000);
        new ChatFrame("User B", 6000, "localhost", 5000);
    }
}
