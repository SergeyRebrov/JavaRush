package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.*;
import java.util.*;

public class Solution
{

    private static String string;

    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(reader.readLine());
        String tag = args[0];
        FileReader fileReader = new FileReader(file);

        StringBuilder stringBuilder = new StringBuilder();
        while (fileReader.ready())
            stringBuilder.append((char) fileReader.read());

        string = stringBuilder.toString();

        String openingTag = "<" + tag;
        String closingTag = "/" + tag + ">";

        Map<Integer, String> tagListMap = new TreeMap<>();

        int indexOpenTag = 0;
        int indexCloseTag = 0;
        while (true)
        {
            indexOpenTag = string.indexOf(openingTag, indexOpenTag);
            indexCloseTag = string.indexOf(closingTag, indexCloseTag);
            if (indexCloseTag == -1)
                break;
            tagListMap.put(indexOpenTag++, "open");
            tagListMap.put(indexCloseTag++, "close");
        }

        int countTag = 0;
        Set<Integer> numberTag = new LinkedHashSet<>();
        for (Map.Entry<Integer, String> entryOne : tagListMap.entrySet())
        {
            for (Map.Entry<Integer, String> entryTwo : tagListMap.entrySet())
                if (entryOne.getKey() < entryTwo.getKey())
                    if (entryOne.getValue().equals(entryTwo.getValue()))
                        countTag++;
                    else
                        if (countTag == 0)
                        {
                            numberTag.add(entryOne.getKey());
                            numberTag.add(entryTwo.getKey());
                            break;
                        } else
                            countTag--;
            countTag = 0;
        }

        List<Integer> list = new ArrayList<>(numberTag);

        for (int i = 0; i < list.size() - 1; i += 2)
        {
            System.out.println(string.substring(list.get(i), list.get(i + 1) + closingTag.length()));
        }
    }
}
