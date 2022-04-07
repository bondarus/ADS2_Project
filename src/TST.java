import java.io.*;
import java.util.*;

//Credit to Princeton
public class TST {

    private int n; //size
    private Node<String> root; //root of TST
    private HashMap<String, String> mapTable;

    private static class Node<String> {
        private char c;                         // character
        private Node<String> left, mid, right;  // left, middle, and right subtries
        private String val;                     // value associated with string
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null argument");
        }
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node<String> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    private Node<String> get(Node<String> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        char c = key.charAt(d);
        if      (c < x.c)              return get(x.left,  key, d);
        else if (c > x.c)              return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid,   key, d+1);
        else                           return x;
    }

    public void put(String key, String val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        if (!contains(key)) n++;
        else if(val == null) n--;       // delete existing key
        root = put(root, key, val, 0);
    }

    private Node<String> put(Node<String> x, String key, String val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<String>();
            x.c = c;
        }
        if      (c < x.c)               x.left  = put(x.left,  key, val, d);
        else if (c > x.c)               x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)  x.mid   = put(x.mid,   key, val, d+1);
        else                            x.val   = val;
        return x;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        Queue<String> queue = new LinkedList<String>();
        Node<String> x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.val != null) queue.add(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    private void collect(Node<String> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.val != null) queue.add(prefix.toString() + x.c);
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    public List<String> busStopInformation(String in)
    {
        List<String> busStopList = new LinkedList<>();
        this.keysWithPrefix(in).forEach((info) -> busStopList.add(mapTable.get(this.get(info))));
        if(busStopList.isEmpty()) busStopList.add("Bus stop not found");
        return busStopList;
    }

    public TST(String fileInput)
    {
        mapTable = new HashMap<String, String>();
        Scanner sc = null;
        File file = new File(fileInput);
        try {
            sc = new Scanner(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
        sc.nextLine();
        while(sc.hasNextLine())
        {
            String stopID, st;
            String line = sc.nextLine();
            String[] lineArray = line.split(",");
            stopID = lineArray[0];
            StringBuilder string = new StringBuilder();
            string.append(lineArray[2]);
            if(   string.substring(0,2).equals("SB") || string.substring(0,2).equals("NB")
               || string.substring(0,2).equals("WB") || string.substring(0,2).equals("EB"))
            {
                st = string.substring(0,2);
                string.delete(0,3);
                string.append(" "+st);
            }
            else if(string.substring(0,8).equals("FLAGSTOP"))
            {
                st = string.substring(0,11);
                string.delete(0,12);
                string.append(" "+st);
            }
            String stopName = string.toString();
            this.put(stopName, stopID);
            StringBuilder stringInfo = new StringBuilder();
            stringInfo.append("stop_id: "+stopID+"\n");
            stringInfo.append("stop_code: "+lineArray[1]+"\n");
            stringInfo.append("stop_name: "+stopName+"\n");
            stringInfo.append("stop_description: "+lineArray[3]+"\n");
            stringInfo.append("stop_lat: "+lineArray[4]+"\n");
            stringInfo.append("stop_lon: "+lineArray[5]+"\n");
            stringInfo.append("zone: "+lineArray[6]+"\n");
            stringInfo.append("location: "+lineArray[8]+"\n");
            String stopInfo = stringInfo.toString();
            mapTable.put(stopID, stopInfo);
        }

    }

}
