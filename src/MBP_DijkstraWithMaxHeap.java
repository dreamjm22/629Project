import java.util.*;

public class MBP_DijkstraWithMaxHeap {
    private List<Integer> status=new ArrayList<Integer>();
    private  List<Double> bw=new ArrayList<Double>();

    public List<Double> FindMBP(Graph graph, int s, int t, List<Integer> path){
        MaxHeap Heap = new MaxHeap();
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
            Heap.insert(v,bw.get(v-1));
        }
        while(Heap.D.size()>0 && status.get(t-1)!=2){
            int max_bw_v=Heap.maximum();
            status.set(max_bw_v-1,2);
            Heap.delete(max_bw_v);
            for(int i=0;i<graph.Graph.get(max_bw_v).NumofEdges;i++){
                double weight=graph.Graph.get(max_bw_v).edges.get(i).weight;
                int w=graph.Graph.get(max_bw_v).edges.get(i).V1==graph.Graph.get(max_bw_v).V
                        ?graph.Graph.get(max_bw_v).edges.get(i).V2
                        :graph.Graph.get(max_bw_v).edges.get(i).V1;
                if(status.get(w-1)==0){
                    status.set(w-1,1);
                    path.set(w-1,max_bw_v);
                    bw.set(w-1,Math.min(bw.get(max_bw_v-1),weight));
                    Heap.insert(w,bw.get(w-1));
                }
                else if(status.get(w-1)==1 && bw.get(w-1)<Math.min(bw.get(max_bw_v-1),weight)){
                    Heap.delete(w);
                    bw.set(w-1,Math.min(bw.get(max_bw_v-1),weight));
                    path.set(w-1,max_bw_v);
                    Heap.insert(w,bw.get(w-1));
                }
            }
        }


        return bw;
    }
}
