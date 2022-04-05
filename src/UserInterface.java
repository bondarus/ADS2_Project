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

                    }
                    if(val == 2)
                    {

                    }
                    if(val == 3)
                    {

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
