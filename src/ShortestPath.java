import java.io.*;
import java.util.*;

public class ShortestPath {

    public ArrayList<String> startStops = new ArrayList<String>();
    public ArrayList<String> endStops = new ArrayList<String>();
    public ArrayList<Double> weights = new ArrayList<Double>();

    public List<Vertex> shortestPathList(Vertex destVertex)
    {
        List<Vertex> path = new ArrayList<>();
        for(Vertex vertex = destVertex; vertex != null; vertex = vertex.getPreviousVertex())
        {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    public void computePath(Vertex sourceVertex)
    {
        sourceVertex.setDistance(0);
        PriorityQueue<Vertex> q = new PriorityQueue<>();
        q.add(sourceVertex);
        while(!q.isEmpty())
        {
            Vertex vertex = q.poll();
            for(Edge edge : vertex.getEdges())
            {
                Vertex vertex2 = edge.getEndVertex();
                double weight = edge.getWeight();
                double distance = vertex.getDistance();
                distance = distance + weight;
                if(distance < vertex2.getDistance())
                {
                    q.remove(vertex);
                    vertex2.setPreviousVertex(vertex);
                    vertex2.setDistance(distance);
                    q.add(vertex2);
                }
            }
        }
    }

    public ShortestPath(String busStopsFile, String busTransfersFile, String busStopTimesFile) throws Exception {

        File busStops = new File(busStopsFile);
        Scanner sc1 = new Scanner(busStops);
        File busTransfers = new File(busTransfersFile);
        Scanner sc2 = new Scanner(busTransfers);
        File busStopTimes = new File(busStopTimesFile);
        Scanner sc3 = new Scanner(busStopTimes);

        HashMap<String, Vertex> vertexMap = new HashMap<String, Vertex>();
        ArrayList<String[]> busTripStopID = new ArrayList<String[]>();
        while(sc1.hasNextLine())
        {
            String stop = sc1.nextLine();
            String array[] = stop.split(",");
            Vertex vertex = new Vertex(array[0]);
            vertexMap.put(array[0], vertex);
        }

        while(sc2.hasNextLine())
        {
            String edge = sc2.nextLine();
            String array[] = edge.split(",");
            startStops.add(array[0]);
            endStops.add(array[1]);
            if(array[2]=="2") weights.add(Double.parseDouble(array[3])/100);
            else if(array[2]=="0") weights.add(2.0);
            else weights.add(1.0);
        }

        for(int i = 1; i < startStops.size(); i++)
        {
            Vertex vertex1 = vertexMap.get(startStops.get(i));
            Vertex vertex2 = vertexMap.get(endStops.get(i));
            Double weight = weights.get(i);
            vertex1.addEdgeNeighbour(new Edge(weight, vertex1, vertex2));
            vertexMap.put(startStops.get(i), vertex1);
        }

        while(sc3.hasNextLine())
        {
            String stop_time = sc3.nextLine();
            String array[] = stop_time.split(",");
            String busTripID = array[0];
            String busStopID = array[3];
            String array2[] = { busTripID, busStopID };
            busTripStopID.add(array2);
        }

        String tripID1, stopID1;
        tripID1 = busTripStopID.get(1)[0];
        stopID1 = busTripStopID.get(1)[1];
        Vertex vertex1 = vertexMap.get(stopID1);
        for(int i = 2; i < busTripStopID.size(); i++)
        {
            String tripID2 = busTripStopID.get(i)[0];
            String stopID2 = busTripStopID.get(i)[1];
            if(tripID1.equals(tripID2))
            {
                Vertex vertex2 = vertexMap.get(stopID2);
                vertex1.addEdgeNeighbour(new Edge(1.0, vertex1, vertex2));
                vertexMap.put(vertex1.name, vertex1);
                vertex1 = vertex2;
            }
            else
            {
                tripID1 = tripID2;
                Vertex vertex2 = vertexMap.get(stopID2);
                vertex1 = vertex2;
            }
        }
    }

}
