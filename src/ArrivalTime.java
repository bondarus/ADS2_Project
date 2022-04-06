import java.io.*;
import java.util.*;
import java.nio.file.*;

public class ArrivalTime {

    public static int parseStringToInt(String s)
    {
        return Integer.parseInt(s.split(",")[0]);
    }

    public static void printArrivalTimes(ArrayList<String> list) throws Exception
    {
        Collections.sort(list, (a, b) -> parseStringToInt(a) - parseStringToInt(b));
        System.out.println("Trip_ID | Arrival_Time | Departure_Time | Stop_ID | Stop_Sequence | Stop_Headsign " +
                         "| Pickup_Type | Drop_Off_Type | Shape | Distance_Traveled");
        for(var i : list)
        {
            System.out.println(i);
        }
    }

    public static ArrayList<String> readTextFile(File file) throws Exception
    {
        ArrayList<String> lines = (ArrayList<String>)Files.readAllLines(Paths.get("stop_times.txt"));
        Files.lines(file.toPath()).map(s->s.trim()).filter(s->!s.matches("(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]"));
        return lines;
    }

    public static void printArrival(File file) throws Exception
    {
        Scanner input = new Scanner(System.in);
        boolean isTimeValid = false;
        ArrayList<String> stopTimes = readTextFile(file);
        ArrayList<String> result = new ArrayList();
        System.out.print("Please enter the arrival time in the format of HH:MM:SS : ");

        String timeInput = input.next();
        if(timeInput.matches("(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]"))
        {
            if(timeInput.length() == 7)
            {
                timeInput += " ";
            }
            for(int i = 0; i < stopTimes.size(); i++)
            {
                if(stopTimes.get(i).contains(timeInput))
                {
                    result.add(stopTimes.get(i));
                }
                isTimeValid = true;
            }
        }
        else if((timeInput.charAt(2)==':') && (timeInput.charAt(5)==':') && (timeInput.charAt(1)>=4))
        {
            System.out.println("Your time input exceeds 23:59:59");
        }
        else
        {
            System.out.println("Not a valid input.");
        }
        if(result.size() > 0) { printArrivalTimes(result); }
        //printArrivalTimes(result);
        if(!isTimeValid) { System.out.println("The arrival time you entered could not be found"); }
    }

}
