import java.awt.*;
import java.awt.event.*;

public class DijkstraShort extends Frame implements ActionListener {
    TextField srcField;
    TextArea result;
    Button run;

    int[][] graph = {
        {0, 4, 0, 0, 0, 0, 0, 8, 0},
        {4, 0, 8, 0, 0, 0, 0, 11, 0},
        {0, 8, 0, 7, 0, 4, 0, 0, 2},
        {0, 0, 7, 0, 9,14, 0, 0, 0},
        {0, 0, 0, 9, 0,10, 0, 0, 0},
        {0, 0, 4,14,10, 0, 2, 0, 0},
        {0, 0, 0, 0, 0, 2, 0, 1, 6},
        {8,11, 0, 0, 0, 0, 1, 0, 7},
        {0, 0, 2, 0, 0, 0, 6, 7, 0}
    };

    public DijkstraShort() {
        setLayout(new FlowLayout());
        add(new Label("Source (0-8):"));
        srcField = new TextField(3); add(srcField);
        run = new Button("Run"); add(run);
        run.addActionListener(this);
        result = new TextArea(10,40); add(result);
        setSize(500,300); setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int src = Integer.parseInt(srcField.getText());
        int[] dist = dijkstra(graph, src);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<dist.length;i++)
            sb.append("From "+src+" to "+i+" = "+dist[i]+"\n");
        result.setText(sb.toString());
    }

    int[] dijkstra(int[][] g, int src) {
        int V=g.length, dist[]=new int[V]; boolean[] vis=new boolean[V];
        for(int i=0;i<V;i++) dist[i]=9999;
        dist[src]=0;
        for(int c=0;c<V-1;c++){
            int u=minDist(dist,vis); vis[u]=true;
            for(int v=0;v<V;v++)
                if(!vis[v] && g[u][v]!=0 && dist[u]+g[u][v]<dist[v])
                    dist[v]=dist[u]+g[u][v];
        }
        return dist;
    }

    int minDist(int[] d, boolean[] v){
        int min=9999, idx=-1;
        for(int i=0;i<d.length;i++)
            if(!v[i] && d[i]<=min){min=d[i]; idx=i;}
        return idx;
    }

    public static void main(String[] args){ new DijkstraShort(); }
}
