public class DistanceVector {
    public static void main(String[] args) {
        int n = 4;

        int[][] cost = {
            {0, 2, 999, 1},
            {2, 0, 3, 2},
            {999, 3, 0, 4},
            {1, 2, 4, 0}
        };

        int[][] dist = new int[n][n];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                dist[i][j] = cost[i][j];

        for(int k=0;k<n;k++)
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    if(dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        System.out.println("Routing Table:");
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++)
                System.out.print(dist[i][j] + " ");
            System.out.println();
        }
    }
}