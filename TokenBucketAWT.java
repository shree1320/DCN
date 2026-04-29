import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TokenBucketAWT extends Frame implements ActionListener {
    TextField tf; TextArea ta; Button add, run;
    int bucketSize=10, tokens=0, rate=3;
    List<Integer> pkts=new ArrayList<>();

    TokenBucketAWT(){
        setLayout(new FlowLayout());
        add(new Label("Packet:")); tf=new TextField(5); add(tf);
        add=new Button("Add"); run=new Button("Run"); add(add); add(run);
        ta=new TextArea(12,40); add(ta);
        add.addActionListener(this); run.addActionListener(this);
        setSize(450,300); setVisible(true);
        addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==add){
            try{
                pkts.add(Integer.parseInt(tf.getText()));
                ta.append("Packet added: "+tf.getText()+"\n");
                tf.setText("");
            } catch(Exception ex){ ta.append("Invalid!\n"); }
        } else {
            ta.append("\n--- Simulation ---\n"); tokens=0;
            for(int p:pkts){
                tokens=Math.min(bucketSize,tokens+rate); // refill tokens
                ta.append("Incoming packet size: "+p+"\n");
                if(p<=tokens){
                    tokens-=p;
                    ta.append("Packet sent, tokens left: "+tokens+"\n\n");
                } else {
                    ta.append("Not enough tokens! Packet discarded.\n\n");
                }
            }
        }
    }

    public static void main(String[] a){ new TokenBucketAWT(); }
}
