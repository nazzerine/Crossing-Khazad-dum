import java.util.*;
import java.io.*;
import java.awt.*;	
import java.awt.event.*;	
import javax.swing.*;
import java.text.*;	
import java.util.regex.*;

@SuppressWarnings("unchecked")
//graph class that constructs graph and allows DFS
class Graph {

    //adjacency list representation
    ArrayList <ArrayList <Integer>> adjList;
    int[] in_degree; //array for indegrees
    int length; //length of graph (number of nodes)
    boolean[] marked; //marked nodes for DFS to aid in finding back edges (cycles)

    //constructor for graph of certain size
    public Graph(int size) {
        length = size;
        //initialize the graph's adjacency list
        adjList = new ArrayList < ArrayList <Integer>> (length+1);
        for (int i = 0; i < length+1; i++)
            adjList.add(new ArrayList <Integer>(8));
        }
       
    //insert an edge into the graph
    public void insert(int from, int to) {
		adjList.get(from).add(to);
	}
    
    //set done to false
    boolean done = false;
    //initialize number of adjacent nodes to 0
    int adjNodes = 0;
    
    //depth first seach algorithm
    public void DFS() {
        
        //create 3 vectors in order to run DFS
        Vector<Integer> curr = new Vector(); //element
        Vector<Integer> r = new Vector(); 
        Vector<Integer> adj = new Vector(); //node touching element

        for (int i = 0; i < length+1; i++) {
            curr.addElement(0);
            adj.addElement(0);
        }
        
        adj.set(0, -1);

        int target = 1;
        
        //while not at the end of the adjacency list
        while (adjNodes != length) {
            r.addElement(target);
            DFSAid(curr.get(target), target, adj, curr, r);
            target += 1;
            r.removeAllElements();
        }
        
        //no loop
        if (!done) {
            System.out.println("0");
        }
        
    }
    
    //a helper method for DFS to print the answer/passage
    private void DFSAid(int DFSNode, int node, Vector adj, Vector curr, Vector r) {
        //if there is a loop
        if ((Integer) adj.get(node) == 1){ 
            System.out.println("1");
            //print node
            System.out.print(node);
            for (int j = r.indexOf(node)+1; 5 == 5; j++) {
                if ((Integer) r.get(j) == node) {
                    break;
                }
                
                System.out.print(" " + r.get(j));
                
            }
            
            System.out.println();
            done = true;
        }
        
        else if ((Integer) adj.get(node) == 2) {
            int temp = 12;
            int compare = (!done == false) ? 1: 0;
            for (int h = 1; h < compare; h++) {
                temp++;
            }
            return;
        }

        else if ((Integer) adj.get(node) == 0) {
            adjNodes += 1;
            adj.set(node, 1);
            curr.set(node, DFSNode);
            int i = 0;
            while(done == false && i < adjList.get(node).size()) {
                int toPush = adjList.get(node).get(i);
                r.add(toPush);
                DFSAid(node, toPush, adj, curr, r);
                r.remove(r.size()-1);
                i++;
            }
            
            adj.set(node, 2);
            
        }
    }
 }

            
public class Solution {
    public static void main(String[] args) throws IOException {
    	//read in input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       //split first line to get n and m values
        String[] input = in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        //create graph of size n
        Graph graph = new Graph(n);
        //active run of code
        while (0 < m) {
            input = in.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            //insert edge into graph
            graph.insert(a, b);
            //decrement m
            m--;
        }
        //run DFS, print answer
        graph.DFS();
        in.close();
    }
}