import java.util.*;

public class IPClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter IP Address: ");
        String ip = sc.nextLine();

        String parts[] = ip.split("\\.");
        int firstOctet = Integer.parseInt(parts[0]);

        if (firstOctet >= 1 && firstOctet <= 126) {
            System.out.println("Class A");
            System.out.println("Subnet Mask: 255.0.0.0");
        } 
        else if (firstOctet <= 191) {
            System.out.println("Class B");
            System.out.println("Subnet Mask: 255.255.0.0");
        } 
        else if (firstOctet <= 223) {
            System.out.println("Class C");
            System.out.println("Subnet Mask: 255.255.255.0");
        } 
        else if (firstOctet <= 239) {
            System.out.println("Class D");
        } 
        else {
            System.out.println("Class E");
        }
    }
}