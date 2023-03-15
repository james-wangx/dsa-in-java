package com.codicefun.prim;

public class MGraph {
    int num; // number of nodes
    char[] nodes; // node data
    int[][] weight; // adjacency matrix

    public MGraph(int num) {
        this.num = num;
        this.nodes = new char[num];
        this.weight = new int[num][num];
    }
}
