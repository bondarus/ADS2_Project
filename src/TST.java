import java.io.*;
import java.util.*;

public class TST {

    private int n; //size
    private Node root; //root of TST

    private static class Node
    {
        private String val;
        private char c;
        private Node left, mid, right;
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public String get(String key)
    {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    private Node get(Node x, String key, int d)
    {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, String val)
    { root = put(root, key, val, 0); }

    private Node put(Node x, String key, String val, int d)
    {
        char c = key.charAt(d);
        if (x == null) { x = new Node(); x.c = c; }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d+1);
        else x.val = val;
        return x;
    }

    public Iterable<String> keysWithPrefix(String prefix)
    {
        Queue<String> queue = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, prefix, queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> q)
    {
        if (x == null) return;
        if (x.val != null) q.enqueue(prefix);
        for (char c = 0; c < R; c++)
            collect(x.next[c], prefix + c, q);
    }

    public TST(String fileInput)
    {

    }

}
