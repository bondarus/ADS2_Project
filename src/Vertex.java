import java.util.*;

public class Vertex  implements Comparable<Vertex>{
    public String name;
    private List<Edge> edges;
    private double distance = Double.MAX_VALUE;
    private Vertex previousVertex;
    private boolean visited;

    public Vertex(String name)
    {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public List<Edge> getEdges() { return edges; }

    public void setEdges(List<Edge> edges) { this.edges = edges; }

    public void addEdgeNeighbour(Edge edge) { this.edges.add(edge); }

    public double getDistance() { return distance; }

    public void setDistance(double distance) { this.distance = distance; }

    public Vertex getPreviousVertex() { return previousVertex; }

    public void setPreviousVertex(Vertex previousVertex) { this.previousVertex = previousVertex; }

    public boolean isVisited() { return visited; }

    public void setVisited(boolean visited) { this.visited = visited; }

    public String toString() { return name; }

    public int compareTo(Vertex vertexCompare)
    {
        return Double.compare(this.distance, vertexCompare.distance);
    }
}
