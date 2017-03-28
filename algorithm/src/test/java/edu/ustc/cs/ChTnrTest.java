package edu.ustc.cs;

import edu.ustc.cs.alg.CH;
import edu.ustc.cs.alg.ChTnr;
import edu.ustc.cs.model.edge.Edge;
import edu.ustc.cs.model.edge.WeightEdge;
import org.jgrapht.DirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by zyb on 2017/3/13.
 */
public class ChTnrTest {
    WeightedGraph<Integer, Edge> graph;
    ChTnr<Integer,Edge> chTnr;

    @Before
    public void before() throws Exception {
        graph = new DefaultDirectedWeightedGraph<Integer, Edge>(WeightEdge.class);
        File input = new File("F:\\java\\algs4-data\\algs4-data\\mediumEWD.txt");
        Scanner in = new Scanner(input);
        int num = in.nextInt();
        for(int i = 0; i < num; i++){           //添加顶点
            graph.addVertex(i);
        }
        in.nextInt();
        while (in.hasNext()){  //录入边
            int v = in.nextInt();
            int w = in.nextInt();
            double weight = in.nextDouble();
            WeightEdge<Integer> edge = new WeightEdge<Integer>(v,w,weight);
            graph.addEdge(v,w,edge);
        }
        in.close();
        chTnr = new ChTnr<Integer, Edge>((AbstractBaseGraph<Integer, Edge>) graph);
        System.out.println("init method run ...");
        chTnr.init();
        System.out.println("init method end ...");
    }

    @Test
    public void test(){
        if(graph instanceof DirectedGraph){
            System.out.println(((DirectedGraph) graph).outgoingEdgesOf(7));
        } else{
            System.out.println(graph.edgesOf(7));
        }
    }

    @Test
    public void testFindForwardAccessNode(){
        Set list = chTnr.findForwardAccessNode(8);
        System.out.println(chTnr.getTransNodes());
        System.out.println(list);
    }
    @Test
    public void testFindBackwardAccessNode(){
        Set list = chTnr.findBackwardAccessNode(2);
        System.out.println(list);
    }

    @Test
    public void testQuery(){
        System.out.println(chTnr.getPathVertex(4, 2));
    }

}