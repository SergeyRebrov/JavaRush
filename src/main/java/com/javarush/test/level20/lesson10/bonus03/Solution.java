package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution
{
    public static void main(String[] args)
    {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> words = detectAllWords(crossword, "home", "same", "emoh", "emas", "fderlk", "klredf", "fulmp", "poeejj", "jjeeop",
                "pmluf", "kovhj", "jhvok", "lprr", "rrpl", "lprr", "voel", "lock", "r", "re", "eo", "oe", null, "", " ");

        for (Word word : words)
            System.out.println(word);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words)
    {
        List<Word> wordList = new ArrayList<>();

        for (String word : words)
        {

            if (word == null)
                continue;

            word = word.trim();

            if (word.equals(""))
                continue;

            char[] wordSymbols = word.toCharArray();
            Word w = new Word(word);
            w.setStartPoint(-1, -1);

            one:
            for (int y = 0; y < crossword.length; y++)
            {
                two:
                for (int x = 0; x < crossword[0].length; x++)
                {
                    Direction direction = Direction.NORTH;
                    if (crossword[y][x] == wordSymbols[0])
                    {

                        for (int i = 0; i < wordSymbols.length; i++)
                        {
                            if (direction == Direction.SOUTH &&
                                    y + i < crossword.length &&
                                    crossword[y + i][x] == wordSymbols[i])
                            {
                                w.setEndPoint(x, y + i);
                            } else if (direction == Direction.SOUTH_EAST &&
                                    y + i < crossword.length &&
                                    x + i < crossword[0].length &&
                                    crossword[y + i][x + i] == wordSymbols[i])
                            {
                                w.setEndPoint(x + i, y + i);
                            } else if (direction == Direction.EAST &&
                                    x + i < crossword[0].length &&
                                    crossword[y][x + i] == wordSymbols[i])
                            {
                                w.setEndPoint(x + i, y);
                            } else if (direction == Direction.NORTH_EAST &&
                                    y - i >= 0 &&
                                    x + i < crossword[0].length &&
                                    crossword[y - i][x + i] == wordSymbols[i])
                            {
                                w.setEndPoint(x + i, y - i);
                            } else if (direction == Direction.NORTH &&
                                    y - i >= 0 &&
                                    crossword[y - i][x] == wordSymbols[i])
                            {
                                w.setEndPoint(x, y - i);
                            } else if (direction == Direction.NORTH_WEST &&
                                    y - i >= 0 &&
                                    x - i >= 0 &&
                                    crossword[y - i][x - i] == wordSymbols[i])
                            {
                                w.setEndPoint(x - i, y - i);
                            } else if (direction == Direction.WEST &&
                                    x - i >= 0 &&
                                    crossword[y][x - i] == wordSymbols[i])
                            {
                                w.setEndPoint(x - i, y);
                            } else if (direction == Direction.SOUTH_WEST &&
                                    y + i < crossword.length &&
                                    x - i >= 0 &&
                                    crossword[y + i][x - i] == wordSymbols[i])
                            {
                                w.setEndPoint(x - i, y + i);
                            } else
                            {
                                i = 0;

                                if (direction == Direction.NORTH)
                                    direction = Direction.NORTH_EAST;
                                else if (direction == Direction.NORTH_EAST)
                                    direction = Direction.EAST;
                                else if (direction == Direction.EAST)
                                    direction = Direction.SOUTH_EAST;
                                else if (direction == Direction.SOUTH_EAST)
                                    direction = Direction.SOUTH;
                                else if (direction == Direction.SOUTH)
                                    direction = Direction.SOUTH_WEST;
                                else if (direction == Direction.SOUTH_WEST)
                                    direction = Direction.WEST;
                                else if (direction == Direction.WEST)
                                    direction = Direction.NORTH_WEST;
                                else
                                    continue two;
                            }
                        }
                        w.setStartPoint(x, y);
                        break one;
                    }
                }
            }
            if (w.startX != -1 && w.startY != -1)
                wordList.add(w);
        }


        return wordList;
    }

    public enum Direction
    {
        NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST;
    }

    public static class Word
    {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text)
        {
            this.text = text;
        }

        public void setStartPoint(int i, int j)
        {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j)
        {
            endX = i;
            endY = j;
        }

        @Override
        public String toString()
        {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
