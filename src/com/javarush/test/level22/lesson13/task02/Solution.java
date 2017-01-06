package com.javarush.test.level22.lesson13.task02;


import java.io.*;
import java.nio.charset.Charset;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(args[0]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);

        Charset utf8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");

        while (fileInputStream.available() > 0)
        {
            byte[] buffer = new byte[1000];
            int count = fileInputStream.read(buffer);
            String s = new String(buffer, 0, count, utf8);
            buffer = s.getBytes(windows1251);
            fileOutputStream.write(buffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
