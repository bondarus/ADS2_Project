import java.io.*;
import java.util.*;

public class ShortestPath {

    public ShortestPath(String busStopsFile, String busTransfersFile, String busStopTimesFile) throws Exception {

        File busStops = new File(busStopsFile);
        Scanner sc1 = new Scanner(busStops);
        File busTransfers = new File(busTransfersFile);
        Scanner sc2 = new Scanner(busTransfers);
        File busStopTimes = new File(busStopTimesFile);
        Scanner sc3 = new Scanner(busStopTimes);

        while(sc1.hasNextLine())
        {
            String stop = sc1.nextLine();
            String array[] = stop.split(",");

        }
        while(sc2.hasNextLine())
        {
            String edge = sc2.nextLine();
            String array[] = edge.split(",");

        }
        while(sc3.hasNextLine())
        {
            String stop_time = sc3.nextLine();
            String array[] = stop_time.split(",");

        }

    }

}
