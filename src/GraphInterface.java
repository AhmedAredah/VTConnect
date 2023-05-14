//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * @author Ahmed (AhmedAredah)
 * @version Nov 06, 2022
 * @param <T> is the type of the graph
 */
public interface GraphInterface <T>{
    /**
     * Adds a given vertex to this graph. If vertexLabel is null, 
     * it returns false.
     * @param vertexLabel is the name of the vertex.
     * @return true if successfully added, false otherwise.
     */
    public boolean addVertex(T vertexLabel);
    
    /**
     * Removes a vertex with the given vertexLabel from this graph and 
     * returns the removed vertex. 
     * If vertex does not exist, it will return null.
     * @param vertexLabel is the lookup value
     * @return the remove vertex
     */
    public VertexInterface<T> removeVertex(T vertexLabel);
    
    /**
     * Adds a weighted edge between two given distinct vertices that are 
     * currently in this graph. The desired edge must not already be in the 
     * graph. Note that the graph is undirected graph.
     * @param begin is the start vertex of the edge.
     * @param end is the end vertex of the edge.
     * @param edgeWeight is the weight assigned to this edge.
     * @return true if the edge is added to the graph, false otherwise.
     */
    public boolean addEdge(T begin, T end, double edgeWeight);   
    
    /**
     * Adds an un-weighted edge between two given distinct vertices that 
     * are currently in this graph. The desired edge must not already be 
     * in the graph.
     * @param begin is the start vertex of the edge.
     * @param end is the end vertex of the edge.
     * @return true if the edge is added to the graph, false otherwise.
     */
    public boolean addEdge(T begin, T end);
    
    /**
     * Removes a weighted edge between two given distinct vertices that are 
     * currently in this graph. The desired edge must already be in the graph.
     * It returns true if the removal is successful, false otherwise.
     * @param begin is the start vertex of the edge.
     * @param end is the end vertex of the edge.
     * @param edgeWeight is the weight assigned to this edge.
     * @return true if the edge is removed from the graph, false otherwise.
     */
    public boolean removeEdge(T begin, T end, double edgeWeight);
    
    /**
     * Removes an un-weighted edge between two given distinct vertices that are
     *  currently in this graph. The desired edge must already be in the graph.
     * It returns true if the removal is successful, false otherwise
     * @param begin is the start vertex of the edge.
     * @param end is the end vertex of the edge.
     * @return true if the edge is removed from the graph, false otherwise.
     */
    public boolean removeEdge(T begin, T end);
    
    /**
     * Sees whether an undirected edge exists between two given vertices.
     * @param begin is the start vertex of the edge.
     * @param end is the end vertex of the edge.
     * @return true if there is an edge between the two vertices, 
     * false otherwise.
     */
    public boolean hasEdge(T begin, T end);
    
    /**
     * gets the number of Vertices in this graph.
     * @return number of Vertices in this graph.
     */
    public int getNumberOfVertices();
    
    /**
     * gets the number of undirected Edges in this graph.
     * @return number of undirected Edges in this graph.
     */
    public int getNumberOfEdges();
    
    /**
     * check if this graph is empty.
     * @return true if the graph is empty, false otherwise.
     */
    public boolean isEmpty();
    
    /**
     * the list of all vertices in the graph. If the graph is empty, 
     * it returns null.
     * @return list of all vertices in the graph.
     */
    public LinkedList<VertexInterface<T>> getVertices();
    
    /**
     * clears the graph.
     */
    public void clear();
    
    /**
     * Performs a breadth-first traversal of a graph and returns the queue 
     * that contains the result. Empty queue can be returned.
     * @param origin the starting point of the traverse.
     * @return a list of the traversed vertices.
     */
    public Queue<T> getBreadthFirstTraversal(T origin);
    
    /**
     * gets the shortest distance between the origin and destination. 
     * If a path does not exist, it returns the maximum integer 
     * (to simulate infinity).
     * @param origin is the start vertex of the search.
     * @param destination is the end vertex of the search.
     * @param path is the path
     * @return cost of shortest path.
     */
    public int getShortestPath(T origin, T destination, Stack<T> path);
    
    
}
