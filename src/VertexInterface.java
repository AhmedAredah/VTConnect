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

import java.util.Iterator;

/**
 * This class defines a vertex.
 * @param <T> is the type of the vertex label
 */
public interface VertexInterface<T> {
    /**
     * Gets this vertex’s label.
     * 
     * @return label.
     */
    public T getLabel();
    
    /**
     * get the number of neighbors of this vertex.
     * 
     * @return the number of neighbors of this vertex.
     */
    public int getNumberOfNeighbors();
    
    /**
     * Marks this vertex as visited.
     */
    public void visit();
    
    /**
     * Removes this vertex’s visited mark.
     */
    public void unvisit();
    
    /**
     * check if the vertex is visited or not.
     * @return true if the vertex is visited, false otherwise.
     */
    public boolean isVisited();
    
    /**
     * Connects this vertex and endVertex with a weighted edge. 
     * The two vertices cannot be the same, and must not already have this 
     * edge between them. Two vertices are equal (same)if their labels are 
     * equal (same).
     * 
     * @param endVertex is the end vertex of the edge.
     * @param edgeWeight is the cost associated with this edge.
     * @return true if the connection is successful, false otherwise.
     */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight);
    
    /**
     * Connects this vertex and endVertex with a weighted edge. 
     * The two vertices cannot be the same, and must not already have this 
     * edge between them. Two vertices are equal (same)if their labels are 
     * equal (same).
     * 
     * @param endVertex is the end vertex of the edge.
     * @return true if the connection is successful, false otherwise.
     */
    public boolean connect(VertexInterface<T> endVertex);
    
    /**
     * Disconnects this vertex from a given vertex with a weighted edge,
     * i.e., removes the edge. 
     * The Edge should exist in order to be disconnected.
     * 
     * @param endVertex is the end vertex of the edge.
     * @param edgeWeight is the weight of the edge between this vertex and
     * the end vertex.
     * @return true if the disconnection is successful, false otherwise.
     */
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight);
    
    /**
     * Disconnects this vertex from a given vertex with a weighted edge,
     * i.e., removes the edge. 
     * The Edge should exist in order to be disconnected.
     * 
     * @param endVertex is the end vertex of the edge.
     * @return true if the disconnection is successful, false otherwise.
     */
    public boolean disconnect(VertexInterface<T> endVertex);

    /**
     * Sees whether this vertex has at least one neighbor.
     * @return true if the vertex has at least one vertex as a neighbor.
     */
    public boolean hasNeighbor();
    /**
     * Gets an unvisited neighbor, if any, of this vertex.
     * @return an unvisited neighbor vertex.
     */
    public VertexInterface<T> getUnvisitedNeighbor();
    
    /**
     * Records the previous vertex on a path to this vertex.
     * @param predecessor is the predecessor of this vertex.
     */
    public void setPredecessor(VertexInterface<T> predecessor);
    /**
     * Gets the recorded predecessor of this vertex.
     * @return the predecessor of this vertex.
     */
    public VertexInterface<T> getPredecessor();
    
    /**
     * Sees whether a predecessor was recorded for this vertex.
     * @return true if the vertex has a predecessor, false otherwise.
     */
    public boolean hasPredecessor();
    
    /**
     * Records the cost of a path to this vertex.
     * @param newCost is the overwrite value cost.
     */
    public void setCost(double newCost);
    
    /**
     * get the cost of a path to this vertex.
     * @return saved cost value.
     */
    public double getCost();

    /**
     * generate an iterator class for the vertex neighbors
     * @return an iterator for the vertex neighbors
     */
    public Iterator<VertexInterface<T>> getNeighborIterator();
}
