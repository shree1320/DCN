import java.util.Scanner;

public class ChannelCapacity {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Bandwidth (Hz): ");
        double B = sc.nextDouble();

        System.out.print("Enter Signal to Noise Ratio (SNR): ");
        double SNR = sc.nextDouble();

        System.out.print("Enter number of signal levels (L): ");
        int L = sc.nextInt();

        // Shannon Capacity
        double shannon = B * (Math.log(1 + SNR) / Math.log(2));

        // Nyquist Capacity
        double nyquist = 2 * B * (Math.log(L) / Math.log(2));

        System.out.println("\nShannon Channel Capacity = " + shannon + " bps");
        System.out.println("Nyquist Channel Capacity = " + nyquist + " bps");

        sc.close();
    }
}
