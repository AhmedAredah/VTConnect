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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This generic class implements the GraphInterface. The implementation 
 * will provide an undirected graph. The class has attribute, which contains 
 * the collection of vertices the graph has. This attribute is a collection 
 * of key value pair, where key is the label of the vertex and the value is 
 * the vertex. Note that this attribute can be any data structure that 
 * implements the Map interface.
 * 
 * @author Ahmed (AhmedAredah)
 * @version Nov 02, 2022
 * @param <T> is the Graph label type
 */
public class Graph<T> implements GraphInterface<T>{
    
    private HashMap<T, VertexInterface<T>> vertices;
    private int edgeCount;
    
    /**
     * 
     */
    public Graph() {
        vertices = new HashMap<T, VertexInterface<T>>();
        edgeCount = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addVertex(T vertexLabel) {
        VertexInterface<T> addOutcome = this.vertices.put(vertexLabel, 
                                                 new Vertex<T>(vertexLabel));
        return addOutcome == null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public VertexInterface<T> removeVertex(T vertexLabel) {
        // disconnect all nodes first that are attached to this vertexLabel
        VertexInterface<T> toDelete = this.vertices.get(vertexLabel);
        for (HashMap.Entry<T,VertexInterface<T>> v : this.vertices.entrySet()) {
            if (! v.getValue().equals(toDelete)) {
                v.getValue().disconnect(toDelete);
            }
        }
        // then delete the vertex itself
        return this.vertices.remove(vertexLabel);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        
        VertexInterface<T> beginV = this.vertices.get(begin);
        VertexInterface<T> endV = this.vertices.get(end);
        
        if ((beginV != null) && (endV != null)) {
            result = beginV.connect(endV, edgeWeight);
            result = endV.connect(beginV, edgeWeight);
        }
        if (result) {
            edgeCount ++;
        }
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0.0);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        
        VertexInterface<T> beginV = this.vertices.get(begin);
        VertexInterface<T> endV = this.vertices.get(end);
        
        if ((beginV != null) && (endV != null)) {
            result = beginV.disconnect(endV, edgeWeight);
            result = endV.disconnect(beginV, edgeWeight);
        }
        if (result) {
            edgeCount --;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(T begin, T end) {
        return removeEdge(begin, end, 0.0);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        VertexInterface<T> beginVertex = this.vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        if ( (beginVertex != null) && (endVertex != null) ) {
            Iterator<VertexInterface<T>> neighbors = 
                                            beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    found = true;
                }
            } // end while
        } // end if
        return found;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.vertices.isEmpty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<VertexInterface<T>> getVertices() {
        LinkedList<VertexInterface<T>> lst = 
                                        new LinkedList<VertexInterface<T>>();
        this.vertices.forEach((k,v) -> lst.add(v));
        return lst;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.vertices.clear();
        edgeCount = 0;
    }
    
    /**
     * reset the vertices visit for finding the shortest path.
     */
    private void resetVertices() {
        for (HashMap.Entry<T, VertexInterface<T>> set : 
                                                    this.vertices.entrySet()) {
            VertexInterface<T> t = set.getValue();
            t.unvisit();
            t.setCost(0);
            t.setPredecessor(null);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Queue<T> getBreadthFirstTraversal(T origin) {
        // reset everything before traversing 
        this.resetVertices();
        Queue<T> traversalOrder = new LinkedList<>();
        Queue<VertexInterface<T>> vertexQueue = new LinkedList<>();
        VertexInterface<T> originVertex = vertices.get(origin);
        if (originVertex == null) {
            return traversalOrder;
        }
        originVertex.visit();
        traversalOrder.add(origin);
        vertexQueue.add(originVertex);
        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.remove();
            Iterator<VertexInterface<T>> neighbors = 
                                            frontVertex.getNeighborIterator();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    traversalOrder.add(nextNeighbor.getLabel());
                    vertexQueue.add(nextNeighbor);
                } // end if
            } // end while
        } // end while
        return traversalOrder;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getShortestPath(T origin, T destination, Stack<T> path) {
        resetVertices();
        boolean done = false;
        Queue<VertexInterface<T>> vertexQueue = new LinkedList<>();
        VertexInterface<T> originVertex = this.vertices.get(origin);
        VertexInterface<T> endVertex = this.vertices.get(destination);
        // if the 2 vertices do not exist, return infinity
        if (endVertex == null || originVertex == null) {
            return (int) Integer.MAX_VALUE;
        }
        originVertex.visit();
        
        vertexQueue.add(originVertex);
        while (!done && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.remove();
            Iterator<VertexInterface<T>> neighbors = 
                                            frontVertex.getNeighborIterator();
            while (!done && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.add(nextNeighbor);
                } // end if
                if (nextNeighbor.equals(endVertex)) {
                    done = true;
                }
            } // end while
        } // end while
        // Traversal ends; construct shortest path
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while
        return (pathLength == 0) ? (int) Integer.MAX_VALUE : pathLength;
    }
    

}
