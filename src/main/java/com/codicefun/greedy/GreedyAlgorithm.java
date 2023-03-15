package com.codicefun.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GreedyAlgorithm {
    public static List<String> findLeastBroadcast(HashMap<String, HashSet<String>> broadcasts) {
        HashSet<String> allAreas = new HashSet<>();
        broadcasts.values().forEach(allAreas::addAll);
        ArrayList<String> selects = new ArrayList<>(); // save selected broadcast
        HashSet<String> tempSet = new HashSet<>(); // save temp areas
        String maxKey; // largest area covered

        while (allAreas.size() != 0) {
            maxKey = null; // reset
            for (String key : broadcasts.keySet()) {
                tempSet.clear(); // reset
                tempSet.addAll(broadcasts.get(key));
                tempSet.retainAll(allAreas); // return intersection
                int size = tempSet.size();
                if (size > 0 && (maxKey == null || size > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        return selects;
    }
}
