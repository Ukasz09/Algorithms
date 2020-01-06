package com.github.Ukasz09.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int amountVertices;
    private int amountEdges;
    private List[] neighborhoodLists;

    public Graph(int vertices) {
        this.amountVertices = vertices;
        this.amountEdges = 0;
        this.neighborhoodLists = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++)
            neighborhoodLists[i] = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        neighborhoodLists[edge.from()].add(edge);
        amountEdges++;
    }

    public int getNumberOfEdges() {
        return amountEdges;
    }

    public int getNumberOfVertices() {
        return amountVertices;
    }

    public Iterable<Edge> getNeighborhoodList(int v) {
        return neighborhoodLists[v];
    }

}