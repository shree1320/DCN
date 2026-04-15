import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;

public class RSA_AWT extends Frame implements ActionListener {
    TextField input; TextArea output; Button run;
    BigInteger p=new BigInteger("61"), q=new BigInteger("53");
    BigInteger n=p.multiply(q);
    BigInteger phi=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    BigInteger e=new BigInteger("17");
    BigInteger d=e.modInverse(phi);

    public RSA_AWT(){
        setLayout(new FlowLayout());
        add(new Label("Enter number message:"));
        input=new TextField(10); add(input);
        run=new Button("Encrypt/Decrypt"); add(run);
        run.addActionListener(this);
        output=new TextArea(5,40); add(output);
        setSize(500,200); setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        BigInteger msg=new BigInteger(input.getText());
        BigInteger cipher=msg.modPow(e,n);
        BigInteger plain=cipher.modPow(d,n);
        output.setText("Public key: ("+e+","+n+")\n"+
                       "Private key: ("+d+","+n+")\n"+
                       "Encrypted: "+cipher+"\n"+
                       "Decrypted: "+plain);
    }

    public static void main(String[] args){ new RSA_AWT(); }
}
