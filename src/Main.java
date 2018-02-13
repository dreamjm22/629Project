import java.util.*;
//import org.jgrapht.*;
//import org.jgrapht.alg.*;
//import org.jgrapht.graph.*;


public class Main {
    private static int NumOfVertices = 5000;
    private static float WeightBound = 1000.0f;
    private static int Degree = 6;



    public static void main(String[] args){
        Graph graph1=new Graph(),graph2=new Graph();
        long T1,T2;
        int start,end;
        List<Double> bw1, bw2, bw3, bw4, bw5, bw6 ,bw7,bw8;
        List<Integer> path1=new ArrayList<Integer>(),path2=new ArrayList<Integer>(),path3=new ArrayList<Integer>(),path4=new ArrayList<Integer>(),path5=new ArrayList<Integer>(),path6=new ArrayList<Integer>();
        List<Integer> path7=new ArrayList<Integer>(),path8=new ArrayList<Integer>();
        MBP_Dijkstra MBP_Dijkstra=new MBP_Dijkstra();
        MBP_Kruskal MBP_Kruskal = new MBP_Kruskal();
        MBP_DijkstraWithMaxHeap MBP_DijkstraWithMaxHeap = new MBP_DijkstraWithMaxHeap();
        //Initialization for source and target
        System.out.println("========================================\n");
        System.out.println("Number of Vertice: "+Integer.toString(NumOfVertices)+"\n");
        Random rand = new Random();
        start = rand.nextInt(NumOfVertices) + 1;
        end = rand.nextInt(NumOfVertices) + 1;
        while(end == start)
            end = rand.nextInt(NumOfVertices) + 1;
        System.out.println("Start: "+Integer.toString(start)+", end: "+Integer.toString(end)+"\n");
        System.out.println("=======================================\n\n\n");
        //initialization for graph
        prepare(graph1,start,end);
        init_graph1(graph1);

        prepare(graph2,start,end);
        init_graph2(graph2);

        /*
        outputgraph(graph1);
        System.out.println("=============================================");
        outputgraph(graph2);
        */





        System.out.println("=================sparse graph======================\n");
        System.out.println("Result for different algorithms");
        T1=System.currentTimeMillis();
        bw1=MBP_Dijkstra.FindMBP(graph1, start, end, path1);
        T2=System.currentTimeMillis();
        System.out.println("Max-Bandwidth-Path-Using Dijkstra: ");
        System.out.println("Running time: "+String.valueOf(T2-T1)+"ms");
        System.out.println("Max-Bandwidth: "+String.valueOf(bw1.get(end-1)));

        T1=System.currentTimeMillis();
        bw7= MBP_DijkstraWithMaxHeap.FindMBP(graph1, start, end, path7);
        T2=System.currentTimeMillis();
        System.out.println("\nMax-Bandwidth-Path-Using Dijkstra with a heap: ");
        System.out.println("Running time: "+String.valueOf(T2-T1)+"ms");
        System.out.println("Max-Bandwidth: "+String.valueOf(bw7.get(end-1)));

        T1=System.currentTimeMillis();
        bw3 = MBP_Kruskal.FindMBP(graph1, start, end, path3);
        T2=System.currentTimeMillis();
        System.out.println("\nMax-Bandwidth-Path-Using Kruskal: ");
        System.out.println("Running time: "+String.valueOf(T2-T1)+"ms");
        System.out.println("Max-Bandwidth: "+String.valueOf(bw3.get(start-1)));




        MBP_Dijkstra=new MBP_Dijkstra();
        MBP_Kruskal = new MBP_Kruskal();
        MBP_DijkstraWithMaxHeap = new MBP_DijkstraWithMaxHeap();

        System.out.println("\n=================dense graph======================\n");
        System.out.println("Result for different algorithms\n");
        T1=System.currentTimeMillis();
        bw4=MBP_Dijkstra.FindMBP(graph2, start, end, path4);
        T2=System.currentTimeMillis();
        System.out.println("Max-Bandwidth-Path-Using Dijkstra: ");
        System.out.println("Running time: "+String.valueOf(T2-T1)+"ms");
        System.out.println("Max-Bandwidth: "+String.valueOf(bw4.get(end-1)));

        T1=System.currentTimeMillis();
        bw5= MBP_DijkstraWithMaxHeap.FindMBP(graph2, start, end, path8);
        T2=System.currentTimeMillis();
        System.out.println("\nMax-Bandwidth-Path-Using Dijkstra with a heap: ");
        System.out.println("Running time: "+String.valueOf(T2-T1)+"ms");
        System.out.println("Max-Bandwidth: "+String.valueOf(bw5.get(end-1)));

        T1=System.currentTimeMillis();
        bw6 = MBP_Kruskal.FindMBP(graph2, start, end, path6);
        T2=System.currentTimeMillis();
        System.out.println("\nMax-Bandwidth-Path-Using Kruskal: ");
        System.out.println("Running time: "+String.valueOf(T2-T1)+"ms");
        System.out.println("Max-Bandwidth: "+String.valueOf(bw6.get(start-1)));

/*
        System.out.println("****************1******************");
        OutputResult(path1,start,end,bw1);
        System.out.println("****************2******************");
        OutputResult(path2,start,end,bw2);

        System.out.println("****************3******************");
        OutputResult(path3,start,end,bw3);
        //OutputPath(path3,start,end);
*/

    }


    private static void outputgraph(Graph graph){
        for(int i=1;i<graph.NumOfVertix+1;i++){
            System.out.println(graph.Graph.get(i).V);
            for(int j=0;j<graph.Graph.get(i).NumofEdges;j++){
                if(graph.Graph.get(i).edges.get(j).V1!=graph.Graph.get(i).V)
                    System.out.println(graph.Graph.get(i).edges.get(j).V1);
                else
                    System.out.println(graph.Graph.get(i).edges.get(j).V2);

            }
            System.out.println("------------------------");

        }
    }

    private static void init_graph1 (Graph graph){
        int i,count,ran_num1,ran_num2;
        int[] v_degree=new int[NumOfVertices+1];
        for(i=1;i<=NumOfVertices;i++){
            v_degree[i]=2;
        }
        Random rand = new Random(System.currentTimeMillis());
        count=0;
        while(count<(Degree*NumOfVertices/2)){
            ran_num1=rand.nextInt(NumOfVertices)+1;
            ran_num2=rand.nextInt(NumOfVertices)+1;

            if(!graph.IsConnected(ran_num1,ran_num2)){
                graph.addEdge(ran_num1,ran_num2,Math.random()*WeightBound);
                v_degree[ran_num1]++;
                v_degree[ran_num2]++;
                count++;
            }
        }
    }

    private static void init_graph2 (Graph graph) {
        int i,j,ran_num;
        Random rand = new Random();

        for (i=1;i<NumOfVertices;i++){
            for(j=i+2;j<=NumOfVertices;j++){
                ran_num=rand.nextInt(5)+1;
                if(ran_num==2) {
                    graph.addEdge(i, j,Math.random()*WeightBound);
                }
            }
        }
    }

    private static void prepare(Graph graph, int s, int t){
        int i;
        for (i=1;i<=NumOfVertices;i++){
            graph.addVertix(i);
        }
        for(i=1;i<NumOfVertices;i++){
            if(!graph.IsConnected(i,i+1)){
                graph.addEdge(i, i+1,Math.random()*WeightBound);
            }
        }

    }

    private static void OutputPath(List<Integer> path, Integer s, Integer t){
        System.out.println(t);
        int temp=t;
        while(!path.get(temp-1).equals(s)){

            temp=path.get(temp-1);
            System.out.println(temp);

        }
        System.out.println(s);
    }

    private static void OutputResult(List<Integer> path, Integer s, Integer t,List<Double> bw){
        System.out.println(t);
        System.out.println(bw.get(t-1));

        int temp =t;
        while(!path.get(temp-1).equals(s)) {

            temp=path.get(temp-1);
            System.out.println(String.valueOf(temp));
            System.out.println(bw.get(temp-1));
        }
        System.out.println(s);

    }
}
