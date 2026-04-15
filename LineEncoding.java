import java.util.*;

public class LineEncoding {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter binary data: ");
        String data = sc.next();

        System.out.println("NRZ-L  : " + nrzl(data));
        System.out.println("NRZ-I  : " + nrzi(data));
        System.out.println("Manchester: " + manchester(data));
    }

    // NRZ-L
    static String nrzl(String data) {
        String res = "";
        for (char b : data.toCharArray())
            res += (b == '1') ? "H " : "L ";
        return res;
    }

    // NRZ-I
    static String nrzi(String data) {
        String res = "";
        char level = 'L';
        for (char b : data.toCharArray()) {
            if (b == '1') level = (level == 'L') ? 'H' : 'L';
            res += level + " ";
        }
        return res;
    }

    // Manchester
    static String manchester(String data) {
        String res = "";
        for (char b : data.toCharArray())
            res += (b == '1') ? "LH " : "HL ";
        return res;
    }
}