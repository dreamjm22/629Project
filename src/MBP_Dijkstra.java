import java.time.chrono.MinguoChronology;
import java.util.*;

public class MBP_Dijkstra {
    private List<Integer> status=new ArrayList<Integer>();
    private List<Double> bw=new ArrayList<Double>();

    public List<Double> FindMBP(Graph graph, int s, int t, List<Integer> path){
        for(int i=0;i<graph.NumOfVertix;i++){
            status.add(0);
            path.add(i);
            bw.add(0.0);
        }
        status.set(s-1,2);
        for(int i=0; i< graph.Graph.get(s).NumofEdges;i++){
            int v=graph.Graph.get(s).edges.get(i).V1==graph.Graph.get(s).V
                    ?graph.Graph.get(s).edges.get(i).V2
                    :graph.Graph.get(s).edges.get(i).V1;
            status.set(v-1,1);
            path.set(v-1,s);
            bw.set(v-1,graph.Graph.get(s).edges.get(i).weight);
        }
        while(status.get(t-1)!=2){
            double max_bw=0.0;
            int max_bw_v=-1;
            for(int i=0;i<bw.size();i++){
                if(bw.get(i)>max_bw && status.get(i)==1) {
                    max_bw_v = i + 1;
                    max_bw=bw.get(i);
                }
            }
            status.set(max_bw_v-1,2);
            for(int i=0;i<graph.Graph.get(max_bw_v).NumofEdges;i++){
                double weight=graph.Graph.get(max_bw_v).edges.get(i).weight;
                int w=graph.Graph.get(max_bw_v).edges.get(i).V1==graph.Graph.get(max_bw_v).V
                        ?graph.Graph.get(max_bw_v).edges.get(i).V2
                        :graph.Graph.get(max_bw_v).edges.get(i).V1;
                if(status.get(w-1)==0){
                    status.set(w-1,1);
                    path.set(w-1,max_bw_v);
                    bw.set(w-1,Math.min(bw.get(max_bw_v-1),weight));

                }
                else if(status.get(w-1)==1 && bw.get(w-1)<Math.min(bw.get(max_bw_v-1),weight)){
                    bw.set(w-1,Math.min(bw.get(max_bw_v-1),weight));

                    path.set(w-1,max_bw_v);
                }
            }
        }
        return bw;
    }
}
