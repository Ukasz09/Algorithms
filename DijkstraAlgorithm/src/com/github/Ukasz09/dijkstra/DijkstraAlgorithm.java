package com.github.Ukasz09.dijkstra;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraAlgorithm {
    class DistanceToEdge implements Comparable<DistanceToEdge> {
        private int edge;
        private long distance;

        public DistanceToEdge(int edge, long distance) {
            this.edge = edge;
            this.distance = distance;
        }

        private DijkstraAlgorithm getOuterType() {
            return DijkstraAlgorithm.this;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + (int) (distance ^ (distance >>> 32));
            result = prime * result + edge;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DistanceToEdge other = (DistanceToEdge) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (distance != other.distance)
                return false;
            return edge == other.edge;
        }

        @Override
        public int compareTo(DistanceToEdge param) {
            int cmp = Long.compare(distance, param.getDistance());

            if (cmp == 0)
                return Integer.compare(edge, param.getEdge());
            return 0;
        }

        public long getDistance() {
            return distance;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }

        public int getEdge() {
            return edge;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Edge[] edgeTo;
    private Long[] distanceTo;
    private Queue<DistanceToEdge> priorityQueue;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DijkstraAlgorithm(Graph graph, int source) {
        edgeTo = new Edge[graph.getNumberOfVertices()];
        distanceTo = new Long[graph.getNumberOfVertices()];
        priorityQueue = new PriorityQueue<>(graph.getNumberOfVertices());

        for (int v = 0; v < graph.getNumberOfVertices(); v++)
            distanceTo[v] = Long.MAX_VALUE;
        distanceTo[source] = 0L;

        priorityQueue.offer(new DistanceToEdge(source, 0L));
        while (!priorityQueue.isEmpty())
            updateEdgeToVertex(graph, priorityQueue.poll().getEdge());
    }

    private void updateEdgeToVertex(Graph graph, int vertex) {
        for (Edge edge : graph.getNeighborhoodList(vertex)) {
            int w = edge.to();
            if (distanceTo[w] > distanceTo[vertex] + edge.getWeight()) {
                distanceTo[w] = distanceTo[vertex] + edge.getWeight();
                edgeTo[w] = edge;
                DistanceToEdge dte = new DistanceToEdge(w, distanceTo[w]);
                priorityQueue.remove(dte);
                priorityQueue.offer(dte);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return distanceTo[v] < Long.MAX_VALUE;
    }

    public Iterable<Edge> getPathTo(int v) {
        Deque<Edge> path = new ArrayDeque<>();
        if (!hasPathTo(v))
            return path;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public long getDistanceTo(int v) {
        return distanceTo[v];
    }
}