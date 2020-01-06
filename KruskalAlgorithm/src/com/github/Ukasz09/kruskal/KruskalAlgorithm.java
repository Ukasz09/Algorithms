package com.github.Ukasz09.kruskal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KruskalAlgorithm {
    private int amountOfVertices;
    private ArrayList<Edge> allEdges = new ArrayList<>();
    private ArrayList<Edge> mst = new ArrayList<>();
    private long result;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public KruskalAlgorithm(int amountOfVertices) {
        this.amountOfVertices = amountOfVertices;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        allEdges.add(edge);
    }

    public void findMST() {
        long start = System.nanoTime();
        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));
        pq.addAll(allEdges);
        int[] parent = new int[amountOfVertices];
        makeSet(parent);
        mst = new ArrayList<>();

        int index = 0;
        while (index < amountOfVertices - 1) {
            Edge edge = pq.remove();
            int x_set = find(parent, edge.source);
            int y_set = find(parent, edge.destination);
            if (x_set != y_set) {
                mst.add(edge);
                index++;
                union(parent, x_set, y_set);
            }
        }

        long end = System.nanoTime();
        result = end - start;
    }

    public void printMST() {
        if (mst != null) {
            if (mst.size() == 0) {
                System.out.println("No elements in graph");
                return;
            }
            printGraph(mst);
        } else System.out.println("No elements in graph");
    }

    private void makeSet(int[] parent) {
        for (int i = 0; i < amountOfVertices; i++)
            parent[i] = i;
    }

    private int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex)
            return find(parent, parent[vertex]);
        return vertex;
    }

    private void union(int[] parent, int x, int y) {
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        parent[y_set_parent] = x_set_parent;
    }

    private void printGraph(ArrayList<Edge> edgeList) {
        for (Edge edge : edgeList)
            System.out.println("[" + edge.source + ", " + edge.destination + "], weight: " + edge.weight);
    }

    public long getResult() {
        return result;
    }

    public ArrayList<Edge> getMst() {
        return mst;
    }
}




