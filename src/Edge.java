import java.util.*;

public class Edge {
    private Vertex startVertex, endVertex;
    private double edgeWeight;

    public Edge(double edgeWeight, Vertex startVertex, Vertex endVertex)
    {
        this.edgeWeight = edgeWeight;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }

    public double getWeight() { return edgeWeight; }

    public void setWeight(double edgeWeight) { this.edgeWeight = edgeWeight; }

    public Vertex getStartVertex() { return startVertex; }

    public void setStartVertex(Vertex startVertex) { this.startVertex = startVertex; }

    public Vertex getEndVertex() { return endVertex; }

    public void setEndVertex(Vertex endVertex) { this.endVertex = endVertex; }
}
