package com.javarush.test.level20.lesson10.bonus02;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        int tmp = -1;
        boolean flag = false;
        int currentColumn = a[0].length;
        while (count != tmp)
        {
            tmp = count;
            one:
            for (int i = 0; i < a.length; i++)
            {
                for (int j = 0; j < a[0].length; j++)
                {
                    if (a[i][j] == 1 && !flag)
                    {
                        count++;
                        flag = true;
                        currentColumn = j;
                        a[i][j] = 0;
                    } else if (a[i][j] == 1 && flag && j >= currentColumn)
                        a[i][j] = 0;
                    else if (a[i][j] == 0 && flag && currentColumn < j)
                        break;
                    else if (a[i][j] == 0 && flag && currentColumn == j)
                        break one;
                }
            }
            flag = false;
        }
        return count;
    }
}
