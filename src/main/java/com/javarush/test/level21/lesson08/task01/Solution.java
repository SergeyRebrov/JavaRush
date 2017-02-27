package com.javarush.test.level21.lesson08.task01;

import java.util.LinkedHashMap;
import java.util.Map;

/* Глубокое клонирование карты
Клонируйтие объект класса Solution используя глубокое клонирование.
Данные в карте users также должны клонироваться.
Метод main изменять нельзя.
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }

        clone.users.put("Ivan", new User(12, "Ivan"));
        solution.users.put("Petr", new User(12, "Petr"));
        System.out.println(solution.users);
        System.out.println(clone.users);
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public Object clone() throws CloneNotSupportedException
        {
            return super.clone();
        }
    }

    public Solution clone() throws CloneNotSupportedException
    {
        Solution cloneSolution = (Solution) super.clone();
        cloneSolution.users = new LinkedHashMap<>();
        for (Map.Entry<String, User> entry : this.users.entrySet())
        {
            cloneSolution.users.put(entry.getKey(), (User)entry.getValue().clone());
        }
        return cloneSolution;
    }
}
