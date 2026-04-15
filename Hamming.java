import java.util.Scanner;
public class Hamming {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] h = new int[7];

        System.out.println("Enter 4 data bits:");
        h[2]=sc.nextInt(); h[4]=sc.nextInt();
        h[5]=sc.nextInt(); h[6]=sc.nextInt();

        h[0]=h[2]^h[4]^h[6];
        h[1]=h[2]^h[5]^h[6];
        h[3]=h[4]^h[5]^h[6];

        System.out.print("Code: ");
        for(int i=0;i<7;i++) System.out.print(h[i]+" ");

        System.out.println("\nEnter received code:");
        for(int i=0;i<7;i++) h[i]=sc.nextInt();

        int e=(h[3]^h[4]^h[5]^h[6])*4+(h[1]^h[2]^h[5]^h[6])*2+(h[0]^h[2]^h[4]^h[6]);

        if(e!=0){ System.out.println("Error at position: "+e); h[e-1]^=1; }
        else System.out.println("No error");

        System.out.print("Corrected Code: ");
        for(int i=0;i<7;i++) System.out.print(h[i]+" ");
    }
}
