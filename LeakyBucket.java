import java.util.*;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Bucket parameters
        System.out.print("Enter bucket capacity: ");
        int bucketCapacity = sc.nextInt();

        System.out.print("Enter output rate (packets per second): ");
        int outputRate = sc.nextInt();

        System.out.print("Enter number of seconds to simulate: ");
        int time = sc.nextInt();

        // Input traffic (packets arriving each second)
        int[] incoming = new int[time];
        System.out.println("Enter incoming packets per second:");
        for (int i = 0; i < time; i++) {
            incoming[i] = sc.nextInt();
        }

        int bucketSize = 0; // current packets in bucket

        System.out.println("\n--- Leaky Bucket Simulation ---");
        for (int t = 0; t < time; t++) {
            System.out.println("Second " + (t + 1) + ":");

            // Add incoming packets
            bucketSize += incoming[t];
            System.out.println("  Incoming: " + incoming[t]);

            // Check overflow
            if (bucketSize > bucketCapacity) {
                int dropped = bucketSize - bucketCapacity;
                bucketSize = bucketCapacity;
                System.out.println("  Dropped: " + dropped);
            } else {
                System.out.println("  Dropped: 0");
            }

            // Leak packets at output rate
            int sent = Math.min(bucketSize, outputRate);
            bucketSize -= sent;
            System.out.println("  Sent: " + sent);
            System.out.println("  Remaining in bucket: " + bucketSize);
        }

        sc.close();
    }
}
