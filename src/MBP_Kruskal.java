import java.util.*;
import java.lang.*;


public class MBP_Kruskal {
    private Graph T = new Graph();

    private int find(int v, List<Integer> VertexList){
        int w=v,u;
        Queue<Integer> q = new LinkedList<Integer>();
        while(VertexList.get(w-1)!=0){
            q.add(w);
            w=VertexList.get(w-1);
        }
        while(!q.isEmpty()){
            u=q.poll();
            VertexList.set(u-1,w);
        }
        return w;
    }

    private void addEdge(Graph T, Edge edge,List<Integer> VertexList, Integer[][] visited,int r1, int r2){
        int v1=edge.V1;
        int v2=edge.V2;
        if(T.Graph.get(v1)==null && T.Graph.get(v2)==null){
            T.addVertix(v1);
            T.addVertix(v2);
            T.addEdge(v1,v2,edge.weight);
            VertexList.set(v2-1,v1);
        }
        else if(T.Graph.get(v1)==null){
            T.addVertix(v1);
            T.addEdge(v1,v2,edge.weight);
            VertexList.set(v1-1,r2);
        }
        else if(T.Graph.get(v2)==null){
            T.addVertix(v2);
            T.addEdge(v1,v2,edge.weight);
            VertexList.set(v2-1,r1);
        }
        else{
            T.addEdge(v1,v2,edge.weight);
            VertexList.set(r2-1,r1);
        }
        visited[v1-1][v2-1] = 0;
        visited[v2-1][v1-1] = 0;
    }

    private void Kruskal(Graph graph, Integer[][] visited){
        List<Edge> SortedEdge = new ArrayList<Edge>();
        List<Integer> VertexList = new ArrayList<Integer>();

        for(int i=1;i<=graph.NumOfVertix;i++){
            //VertexList.add(graph.Graph.get(i).V);
            VertexList.add(0);
            //System.out.println(i+"+"+VertexList.get(i-1));
            for(int j=0;j<graph.Graph.get(i).NumofEdges;j++){
                SortedEdge.add(graph.Graph.get(i).edges.get(j));
            }
        }
        Collections.sort(SortedEdge, Edge.EdgeComparator);
        int count=0;
        for(int i=0;i<SortedEdge.size();i++){
            Edge e = SortedEdge.get(i);
            int r1=find(e.V1,VertexList),r2=find(e.V2,VertexList);
            if(r1!=r2) {
                addEdge(T, e, VertexList, visited,r1,r2);
                count++;
                if(count==4999) {
                    break;
                }
            }

        }
    }


    private void getPath(int s, int t,List<Integer>path,Integer mark,Integer[][] visited){

        if(mark == 1)
            return;
        int i=0;
        int v1= Integer.MIN_VALUE,v2 = Integer.MIN_VALUE;
        while (i<T.Graph.get(s).NumofEdges){
            v1 = T.Graph.get(s).edges.get(i).V1;
            v2 = T.Graph.get(s).edges.get(i).V2;
            if(visited[v1-1][v2-1] == 1) {
                i++;
                if(i==T.Graph.get(s).NumofEdges)
                    return;
            }
            else {
                visited[v1-1][v2-1] = 1;
                visited[v2-1][v1-1] = 1;
                break;
            }
        }
        if(v1!=s){
            if(v1==t)
                mark=1;
            path.set(v1-1,s);
            getPath(v1,t,path,mark,visited);
        }
        else{
            if(v2==t)
                mark=1;
            path.set(v2-1,s);
            getPath(v2,t,path,mark,visited);
        }
        getPath(s,t,path,mark,visited);
    }

    private List<Double> getbw(int s, int t, List<Integer> path){
        List<Double> bw= new ArrayList<Double>();
        for(int i=0;i<T.NumOfVertix;i++){
            bw.add(0.0);
        }
        int temp1=t, temp2;
        double max_bandwidth=Double.MAX_VALUE;
        while(temp1 != s){

            temp2=path.get(temp1-1);
            for(int i=0;i<T.Graph.get(temp1).NumofEdges;i++){
                if(T.Graph.get(temp1).edges.get(i).V1==temp2 || T.Graph.get(temp1).edges.get(i).V2==temp2){
                    max_bandwidth = Math.min(T.Graph.get(temp1).edges.get(i).weight,max_bandwidth);
                    bw.set(temp1-1,max_bandwidth);
                    break;
                }
            }
            temp1=temp2;
        }
        bw.set(s-1,max_bandwidth);
        return bw;
    }

    public List<Double> FindMBP(Graph graph, int s, int t, List<Integer> path){
        for(int i=0;i<graph.NumOfVertix;i++){
            path.add(i+1);
        }
        Integer[][] visited = new Integer[graph.NumOfVertix][graph.NumOfVertix];
        Kruskal(graph,visited);
        Integer mark = 0;
        getPath(s,t,path,mark,visited);
        return getbw(s,t,path);
    }

}
