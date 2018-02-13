import java.time.chrono.MinguoChronology;
import java.util.*;
import java.lang.Math.*;

public class Graph{
    public HashMap<Integer,GraphVertix> Graph = new HashMap<Integer,GraphVertix>();//edit to hash
    public int NumOfVertix;

    public class GraphVertix {
        public int V;
        public List<Edge> edges;
        public int NumofEdges;
    }


    public void addVertix(int V){
        GraphVertix Vertix = new GraphVertix();
        Vertix.V=V;
        Vertix.edges=new ArrayList<Edge>();
        NumOfVertix++;
        Graph.put(V,Vertix);
    }
    public void addEdge(int V1, int V2, double weight){
        Edge e=new Edge();
        e.V1=V1;
        e.V2=V2;
        e.weight=weight;
        Graph.get(V1).edges.add(e);
        Graph.get(V1).NumofEdges++;
        Graph.get(V2).edges.add(e);
        Graph.get(V2).NumofEdges++;


    }
    public boolean IsConnected(int V1, int V2){
        if(V1==V2)
            return true;
        for(int i =0;i<Graph.get(V1).NumofEdges;i++){
            if(Graph.get(V1).edges.get(i).V1==V2 || Graph.get(V1).edges.get(i).V2==V2)
                return true;
        }

        return false;
    }


}



