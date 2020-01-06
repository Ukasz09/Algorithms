package com.github.Ukasz09.dijkstra;

public class Edge {
    private int from;
    private int to;
    private long weight;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public long getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%d->%d (%d) ", from, to, weight);
    }
}