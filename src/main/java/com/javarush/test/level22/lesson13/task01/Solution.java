package com.javarush.test.level22.lesson13.task01;

import java.util.ArrayList;
import java.util.StringTokenizer;

/* StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".") == {"level22", "lesson13", "task01"}
*/
public class Solution {
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer tokeniser = new StringTokenizer(query, delimiter);
        ArrayList<String> list = new ArrayList<>();
        while (tokeniser.hasMoreTokens())
            list.add(tokeniser.nextToken());
        String[] tokens = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
            tokens[i] = list.get(i);
        return tokens;
    }
}
