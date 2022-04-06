import java.io.*;
import java.util.*;

public class UserInterface {
    public static void main(String[] args) throws Exception {

        Scanner input;
        File file = new File("stop_times.txt");
        ShortestPath dijkstra = new ShortestPath("stops.txt", "transfers.txt", "stop_times.txt");
        boolean quit = false;
        System.out.println("Hello, please input the number to choose which feature you would like to use:");
        while(!quit)
        {
            input = new Scanner(System.in);
            TST tst = new TST("stops.txt");
            System.out.println("Type in '1' for the shortest path between 2 bus stops,");
            System.out.println("Type in '2' to search a bus stop you're looking for,");
            System.out.println("Type in '3' for the arrival time of the bus,");
            System.out.print("Type in '0' to exit the program\n-> ");
            if(input.hasNextInt())
            {
                int val = input.nextInt();
                input.nextLine();
                if(val >= 0 && val <= 3)
                {
                    if(val == 0) { quit = true; }
                    if(val == 1)
                    {
                        String inputSource = "";
                        String inputDestination = "";
                        HashMap<String, Vertex> vertexes = dijkstra.vertexMap;
                        Double cost = dijkstra.cost;
                        boolean loop = true;
                        while(loop)
                        {
                            String regex = "[0-9]+";
                            System.out.print("\nPlease enter your starting bus stop ID: ");
                            inputSource = input.nextLine();
                            System.out.print("Please enter your destination bus stop ID: ");
                            inputDestination = input.nextLine();
                            if(!(inputSource.matches(regex)) || !(inputDestination.matches(regex)))
                            {
                                System.out.println("\nSorry, bus stop ID's can only contain number digits");
                            }
                            else
                            {
                                if(vertexes.get(inputSource) == null || vertexes.get(inputDestination) == null)
                                {
                                    System.out.println("\nSorry, one or both of the bus stops don't exist");
                                }
                                else { loop = false; }
                            }
                        }
                        Vertex source = vertexes.get(inputSource);
                        Vertex destination = vertexes.get(inputDestination);
                        dijkstra.computePath(source);
                        cost = dijkstra.cost;
                        System.out.println("The shortest path from " + source.name + " to " + destination.name +
                                           " is " + dijkstra.shortestPathList(destination) +
                                           "\nThe total cost of the path is: " + dijkstra.cost + "\n");
                    }
                    if(val == 2)
                    {
                        System.out.println("Please enter the name of the bus stop: ");
                        String name = input.nextLine();
                        tst.busStopInformation(name).forEach((info) -> {
                            System.out.println(info);
                        });
                    }
                    if(val == 3)
                    {
                        ArrivalTime.printArrival(file);
                    }
                }
            }
            else
            {
                System.out.println("Not a valid input, please try again.");
            }
        }
        System.out.println("Goodbye!");
    }
}
