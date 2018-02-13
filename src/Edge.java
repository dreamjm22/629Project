import java.time.chrono.MinguoChronology;
import java.util.*;

public class Edge implements Comparable<Edge>{
    public int V1,V2;
    public double weight;


    public int compareTo(Edge e){
        if (e.weight - this.weight>=0)
            return 1;
        else
            return -1;
    }

    public static Comparator<Edge> EdgeComparator = new Comparator<Edge>(){
        public int compare(Edge e1, Edge e2){
            return e1.compareTo(e2);
        }
    };

}
