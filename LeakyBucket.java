import java.util.*;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int bucketSize = 10, outRate = 3, filled = 0;

        System.out.println("Enter number of packets:");
        int n = sc.nextInt();
        int[] packets = new int[n];
        System.out.println("Enter packet sizes:");
        for(int i=0;i<n;i++) packets[i]=sc.nextInt();

        for(int p:packets){
            System.out.println("Incoming packet size: "+p);
            if(p+filled > bucketSize)
                System.out.println("Bucket overflow! Packet discarded.");
            else {
                filled += p;
                System.out.println("Bucket filled: "+filled);
            }
            filled = Math.max(0, filled - outRate);
            System.out.println("After leaking: "+filled+"\n");
        }
    }
}
