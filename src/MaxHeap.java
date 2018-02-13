import java.util.*;

public class MaxHeap {
    Integer[] H = new Integer[5000];
    List<HeapNode> D = new ArrayList<HeapNode>();

    public int maximum(){
        return (D.get(0).V);
    }

    public void insert(int v, double bw){
        HeapNode node = new HeapNode();
        node.V=v;
        node.weight=bw;
        D.add(node);
        int i=D.size();
        H[v-1]=i-1;
        while(i>1 && D.get(i-1).weight>D.get(i/2-1).weight){
            HeapNode temp =D.get(i/2-1);
            H[D.get(i/2-1).V-1]=i-1;
            H[D.get(i-1).V-1]=i/2-1;
            D.set(i/2-1,D.get(i-1));
            D.set(i-1,temp);
            i=i/2;
        }
    }

    public void delete(int v){
        int i=H[v-1]+1;
        H[D.get(D.size()-1).V-1]=i-1;
        H[v-1]=-1;
        D.set(i-1, D.get(D.size()-1));
        D.remove(D.size()-1);
        while(i*2<=D.size() && (D.get(i-1).weight<D.get(i*2-1).weight||(i*2<D.size() && D.get(i-1).weight<D.get(i*2).weight))){
            if(i*2<D.size() && D.get(i*2-1).weight<D.get(i*2).weight){
                HeapNode node = D.get(i * 2);
                H[D.get(i-1).V-1]=i*2;
                H[D.get(i*2).V-1]=i-1;
                D.set(i * 2, D.get(i - 1));
                D.set(i - 1, node);
                i = i * 2 + 1;
            }
            else {
                HeapNode node = D.get(i * 2-1);
                H[D.get(i-1).V-1]=i*2-1;
                H[D.get(i*2-1).V-1]=i-1;
                D.set(i * 2-1, D.get(i - 1));
                D.set(i - 1, node);
                i = i * 2;
            }
        }
    }

    public void output(){
        for(int i=0;i<D.size();i++){
            System.out.println(D.get(i).V + " + " + D.get(H[D.get(i).V-1]).V);
        }
    }
}
