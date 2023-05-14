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
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author Ahmed (AhmedAredah)
 * @version Nov 02, 2022
 * @param <T> is the vertex label type
 */
public class Vertex<T> implements VertexInterface<T>{
    
    /**
     * @author Ahmed (AhmedAredah)
     * @version Nov 02, 2022
     */
    private class Edge {
        // holds a vertex at the end of a node
        private VertexInterface<T> theEnd;
        // weight of the edge
        private double theWeight = 0.0;
        
        /**
         * instantiate an edge with weight
         * @param end is the end vertex
         * @param weight is the edge weight
         */
        protected Edge(VertexInterface<T> end, double weight) {
            this.theEnd = end;
            this.theWeight = weight;
        }
        
        /**
         * get the vertex at the end of the edge
         * @return end vertex of the current edge
         */
        protected VertexInterface<T> getTheEndVertex() {
            return theEnd;
        }
        
        /**
         * get the weight of the current edge
         * @return weight of the edge
         */
        protected double getWeight() {
            return this.theWeight;
        }
        
    }

    // Declarations
    //-------------
    private T label;  // holds the label of the node
    private boolean visited; // holds if the vertex is visited or not
    private VertexInterface<T> previousVertex; // holds a list of pre vertices
    private double cost;  // holds the total cost of finding the shortest path
    private LinkedList<Edge> edgeList; // holds a list of edges
    
    // Methods
    //--------
    
    /**
     * create a new vertex.
     * 
     * @param newLabel is the label of the new vertex.
     */
    public Vertex(T newLabel) {
        this.label = newLabel;
        this.edgeList = new LinkedList<>();
        this.visited = false;
        this.previousVertex = null;
        this.cost = 0.0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public T getLabel() {
        return this.label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfNeighbors() {
        return this.edgeList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit() {
        this.visited = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unvisit() {
        this.visited = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;
        // if null value was added, return false
        if (endVertex == null) {
            return false;
        }
        if (!this.equals(endVertex)) {
            Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;
            while(!duplicateEdge && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    duplicateEdge = true;
                }
            }
            if (!duplicateEdge) {
                this.edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0.0);
    }
    
    /**
     * generate an iterator class for the vertex neighbors
     * @return an iterator for the vertex neighbors
     */
    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight) {
        // if null value was added, return false
        if (endVertex == null) {
            return false;
        }
        // loop through the list to delete the element
        for (Edge e : this.edgeList) {
            if (e.getTheEndVertex().equals(endVertex) && 
                    e.getWeight() == edgeWeight) {
                return this.edgeList.remove(e);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean disconnect(VertexInterface<T> endVertex) {
        return disconnect(endVertex, 0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNeighbor() {
        return !this.edgeList.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> result = null;
        // get iterator of neighbors 
        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        // loop through all neighbors
        while( (neighbors.hasNext()) && (result == null)) {
            VertexInterface<T> nextNeighbor = neighbors.next();
            // if was not visited, return it
            if (!nextNeighbor.isVisited()) {
                result = nextNeighbor;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPredecessor() {
        return this.previousVertex != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCost() {
        return this.cost;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.label.toString();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        boolean result;
        if ((other == null) || (getClass() != other.getClass())) {
            result = false;
        }
        else {
            @SuppressWarnings("unchecked")
            VertexInterface<T> otherV = (VertexInterface<T>)other;
            result = label.equals(otherV.getLabel());
        }
        return result;
    }
    
    /**
     * iterate through all the neighbors of this vertex.
     * 
     * @author Ahmed
     *
     */
    private class NeighborIterator implements Iterator<VertexInterface<T>> {
        
        private Iterator<Edge> edges;
        
        private NeighborIterator() {
            edges = edgeList.iterator();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return edges.hasNext();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext()) {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getTheEndVertex();
            }
            else {
                throw new NoSuchElementException();
            }
            return nextNeighbor;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
}
