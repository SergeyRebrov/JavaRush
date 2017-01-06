package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();

            User user1 = new User();
            user1.setFirstName("Ivan");
            user1.setLastName("Ivanov");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
            Date birthDate = dateFormat.parse("29-09-1985");
            user1.setBirthDate(birthDate);
            user1.setCountry(User.Country.RUSSIA);
            user1.setMale(true);
            javaRush.users.add(user1);

            User user2 = new User();
            user2.setFirstName("Anna");
            user2.setLastName(null);
            birthDate = dateFormat.parse("12-11-1999");
            user2.setBirthDate(birthDate);
            user2.setCountry(User.Country.OTHER);
            user2.setMale(false);
            javaRush.users.add(user2);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);


            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            String isHasUsers = users != null ? "yes" : "no";
            printWriter.println(isHasUsers);
            if (isHasUsers.equals("yes"))
            {
                for (User user : users)
                {
                    printWriter.println(user.getFirstName());
                    printWriter.println(user.getLastName());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    printWriter.println(dateFormat.format(user.getBirthDate()));
                    printWriter.println(user.getCountry().getDisplayedName());
                    printWriter.println(user.isMale());
                    printWriter.flush();
                }
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String isHasUsers = reader.readLine();
            if (isHasUsers.equals("yes"))
            {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    User user = new User();

                    user.setFirstName(line);
                    user.setLastName(reader.readLine());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = dateFormat.parse(reader.readLine());
                    user.setBirthDate(date);
                    String country = reader.readLine();
                    if (country.equals("Ukraine"))
                        user.setCountry(User.Country.UKRAINE);
                    if (country.equals("Russia"))
                        user.setCountry(User.Country.RUSSIA);
                    if (country.equals("Other"))
                        user.setCountry(User.Country.OTHER);
                    user.setMale(Boolean.parseBoolean(reader.readLine()));

                    users.add(user);
                }
            }
            reader.close();
        }
    }
}
