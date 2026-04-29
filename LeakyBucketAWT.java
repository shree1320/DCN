import java.awt.*;
import java.awt.event.*;

public class LeakyBucketAWT extends Frame implements ActionListener {
    TextField packetField;
    TextArea outputArea;
    Button addButton, runButton;
    int bucketSize = 10, outRate = 3, filled = 0;
    java.util.List<Integer> packets = new java.util.ArrayList<>();

    public LeakyBucketAWT() {
        setLayout(new FlowLayout());

        Label lbl = new Label("Enter packet size:");
        add(lbl);

        packetField = new TextField(10);
        add(packetField);

        addButton = new Button("Add Packet");
        addButton.addActionListener(this);
        add(addButton);

        runButton = new Button("Run Simulation");
        runButton.addActionListener(this);
        add(runButton);

        outputArea = new TextArea(15, 40);
        add(outputArea);

        setTitle("Leaky Bucket Simulation");
        setSize(500, 400);
        setVisible(true);

        // Close window properly
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                int p = Integer.parseInt(packetField.getText());
                packets.add(p);
                outputArea.append("Packet added: " + p + "\n");
                packetField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid input!\n");
            }
        } else if (e.getSource() == runButton) {
            outputArea.append("\n--- Simulation Start ---\n");
            filled = 0;
            for (int p : packets) {
                outputArea.append("Incoming packet size: " + p + "\n");
                if (p + filled > bucketSize) {
                    outputArea.append("Bucket overflow! Packet discarded.\n");
                } else {
                    filled += p;
                    outputArea.append("Bucket filled: " + filled + "\n");
                }
                filled = Math.max(0, filled - outRate);
                outputArea.append("After leaking: " + filled + "\n\n");
            }
            outputArea.append("--- Simulation End ---\n");
        }
    }

    public static void main(String[] args) {
        new LeakyBucketAWT();
    }
}
