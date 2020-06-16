package com.duyj.work.guava;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class CollectionTest {
    public static void main(String[] args) {
        Map<String, Map<Long, List<String>>> map = Maps.newHashMap();

        ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d");

//        List<Integer> list2 = ImmutableList.listOf(1, 2, 3, 4);
//        int[] array2 = Ints.toArray(list2);


        ImmutableMap<String, String> map2 = ImmutableMap.of("key1", "value1", "key2", "value2");


        File file = new File(CollectionTest.class.getResource("/test.txt").getFile());
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String a = "";
        String title = checkNotNull(a);

    }
}
