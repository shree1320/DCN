import java.util.*;

public class TokenBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int bucketSize = 10, tokens = 0, rate = 3;

        System.out.println("Enter number of packets:");
        int n = sc.nextInt();
        int[] packets = new int[n];
        System.out.println("Enter packet sizes:");
        for(int i=0;i<n;i++) packets[i]=sc.nextInt();

        for(int p:packets){
            tokens = Math.min(bucketSize, tokens + rate); // add tokens
            System.out.println("Tokens available: "+tokens);

            if(p <= tokens){
                tokens -= p;
                System.out.println("Packet of size "+p+" sent, remaining tokens: "+tokens+"\n");
            } else {
                System.out.println("Packet of size "+p+" dropped (not enough tokens)\n");
            }
        }
    }
}
