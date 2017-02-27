package com.javarush.test.level19.lesson03.task04;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution
{
    public static class PersonScannerAdapter implements PersonScanner
    {
        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner)
        {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            Person person = null;
            if (scanner.hasNextLine())
            {
                String s = scanner.nextLine();
                int a = s.indexOf(' ');
                String firstName = s.substring(0, a).trim();
                int b = s.indexOf(' ', a + 1);
                String middleName = s.substring(a, b).trim();
                int c = s.indexOf(' ', b + 1);
                String lastName = s.substring(b, c).trim();
                int d = s.indexOf(' ', c + 1);
                Date birthDate = null;


                SimpleDateFormat sdf = new SimpleDateFormat("dd mm yyyy");

                try
                {
                    birthDate = sdf.parse(s.substring(c, s.length()).trim());
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                person = new Person(lastName, firstName, middleName, birthDate);

            }
            return person;
             /**
            Person person=null;
            if(scanner.hasNext()){

                String last = scanner.next();
                String first = scanner.next();
                String middle = scanner.next();
                int day = scanner.nextInt();
                int month = scanner.nextInt()-1;
                int year = scanner.nextInt();
                Calendar date = new GregorianCalendar(year, month, day);
                person = new Person(first, middle, last,date.getTime());
            }

            return person;
             **/
        }

        @Override
        public void close() throws IOException
        {
            scanner.close();
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:/3.txt");
        Scanner scanner = new Scanner(file);
        PersonScannerAdapter adapter = new PersonScannerAdapter(scanner);

        System.out.println(adapter.read());
        adapter.close();
    }

}
