package com.codicefun.dsa.kruskal;

public class EData {
    public char start;
    public char end;
    public int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("<%c, %c>: %d", start, end, weight);
    }
}
