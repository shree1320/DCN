import java.util.Scanner;
public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data, div, temp;
        int i, j;

        System.out.print("Enter data: ");
        data = sc.next();
        System.out.print("Enter divisor: ");
        div = sc.next();

        temp = data + "0".repeat(div.length() - 1);
        char[] t = temp.toCharArray();
        char[] d = div.toCharArray();

        for (i = 0; i <= t.length - d.length; i++)
            if (t[i] == '1')
                for (j = 0; j < d.length; j++)
                    t[i + j] = (t[i + j] == d[j]) ? '0' : '1';

        System.out.print("CRC: ");
        for (i = t.length - d.length + 1; i < t.length; i++)
            System.out.print(t[i]);

        System.out.println("\nCodeword: " + data + temp.substring(temp.length() - div.length() + 1));
    }
}
