package com.codicefun.greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class GreedyAlgorithmTest {

    @Test
    public void shouldFindLeastBroadcast() {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashSet<String> hashSet4 = new HashSet<>();
        HashSet<String> hashSet5 = new HashSet<>();

        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        hashSet4.add("上海");
        hashSet4.add("天津");
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        List<String> list = new ArrayList<>();
        list.add("K1");
        list.add("K2");
        list.add("K3");
        list.add("K5");

        List<String> res = GreedyAlgorithm.findLeastBroadcast(broadcasts);
        assertEquals(res, list);
    }
}
