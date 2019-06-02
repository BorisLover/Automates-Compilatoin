/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphes;
import gestautomates.CharEtEtat;
import gestautomates.Etats;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTabbedPane;
import org.graphstream.graph.*;
//import org.graphstream.graph.Graph;
//import org.graphstream.graph.implementations.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;


/**
 *
 * @author KENFACK TAGUEZEM BORIS
 */
public class Graphstreams {
    private SingleGraph graph;
   // public static void main(String args[]) {
        
        //Object[] styleSheet={};
        //graph.addAttribute("name", 1,2,3,4);
        //graph.
        //Node e1=graph.getNode("1");
        ///e1.addAttribute("ui.style", "shape:circle;fill-color: hidden; text-alignment: center;");
        //e1.addAttribute("ui.class", "shape:circle;fill-color: yellow; text-alignment: center;");
        //e1.addAttribute("ui.class","1");
        //System.out.println(e1);
        //graph.addAttribute("ui.stylesheet", StyleSheet);
        //new Graphstreams();
        
    //},JTabbedPane afn
    public Graphstreams(List lEtats,String title){
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	graph = new SingleGraph(title);
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.setStrict(false);
        graph.setAutoCreate( true );
        int i=0;
        for(Object etat:lEtats){
            Etats et=(Etats)etat;
            for(Object suivs:et.gettabSuiv()){
                if(graph.getEdge(i+"") == null){
                    CharEtEtat suiv=(CharEtEtat)suivs;
                    if(suiv.qs>=0){
                        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh "+suiv.car);
                        graph.addEdge(i+"", et.getqc()+"", suiv.getEtat()+"", true);
                        Edge edg=graph.getEdge(i+"");
                        edg.addAttribute("ui.label", suiv.getCar());
                        i++;
                    }
                }
            }
            
        }
        
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
            String q=node.getAttribute("ui.label");
            //q.addAttribute("ui.style", "shape:circle;fill-color: hidden; text-alignment: center;");
            System.out.println(q);
        }
        /*for (Edge edge : graph.getEdgeSet()) {
            c
        }*/

        //explore(graph.getNode("1"));
            //Viewer view=new Viewer(graph,Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
            //graph.display();
            
    }
    public SingleGraph getGraph(){
        return graph;
    }
    public void explore(Node source) {
            Iterator<? extends Node> k = source.getBreadthFirstIterator();

            while (k.hasNext()) {
                Node next = k.next();
                next.setAttribute("ui.class", "marked");
                sleep();
            }
    }
    protected void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
    protected String styleSheet =
            "node {" +
            "	fill-color: black;size:14px;text-size: 15;" +
            "}" +
            "node.marked {" +
            "	fill-color: white;" +
            "}"+
            "edge {"+
            "  fill-color: gray;text-padding: 17px;shape: cubic-curve;text-size: 20;text-color:red;"+
            "}" +
            "node.initial {" +
            "	fill-color: red;" +
            "}"+ 
            "node.final {" +
            "	fill-color: green;" +
            "}"+ 
            "edge.way {" +
            "	fill-color: yellow;" +
            "}";
    
    
}
