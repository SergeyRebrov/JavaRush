package com.javarush.test.level34.lesson02.home01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin(2*(-5+1.5*4)+28)
Результат:
0.5 6
*/
public class Solution
{
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        String s;

        solution.recursion("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6


        s = "(-2)^(-2)";
        System.out.print(s + " expected output 0.25 3 actually ");
        solution.recursion(s, 0);

        s = "89-cos(180)^2";
        System.out.print(s + " expected output 88 3 actually ");
        solution.recursion(s, 0);

        s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);

        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);

        s = "tan(-45)";
        System.out.print(s + " expected output -1 2 actually ");
        solution.recursion(s, 0);

        s = "0.305";
        System.out.print(s + " expected output 0.3 0 actually ");
        solution.recursion(s, 0);

        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);

        s = "(0.3051)";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);

        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);

        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);

        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);

        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);

        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);

//        s = "2^10+2^(5+5)";
//        System.out.print(s + " expected output 2048 4 actually ");
//        solution.recursion(s, 0);
//
//        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
//        System.out.print(s + " expected output 72.96 8 actually ");
//        solution.recursion(s, 0);
//
//        s = "0.000025+0.000012";
//        System.out.print(s + " expected output 0 1 actually ");
//        solution.recursion(s, 0);
//
//        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
//        System.out.print(s + " expected output -3 16 actually ");
//        solution.recursion(s, 0);
//
//        s = "cos(3 + 19*3)";
//        System.out.print(s + " expected output 0.5 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
//        System.out.print(s + " expected output 8302231.36 14 actually ");
//        solution.recursion(s, 0);
//
//        s = "(-1 + (-2))";
//        System.out.print(s + " expected output -3 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "-sin(2*(-5+1.5*4)+28)";
//        System.out.print(s + " expected output -0.5 7 actually ");
//        solution.recursion(s, 0);
//
//
//        s = "sin(100)-sin(100)";
//        System.out.print(s + " expected output 0 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "-(-22+22*2)";
//        System.out.print(s + " expected output -22 4 actually ");
//        solution.recursion(s, 0);
//
//        s = "-2^(-2)";
//        System.out.print(s + " expected output -0.25 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "-(-2^(-2))+2+(-(-2^(-2)))";
//        System.out.print(s + " expected output 2.5 10 actually ");
//        solution.recursion(s, 0);
//
//        s = "(-2)*(-2)";
//        System.out.print(s + " expected output 4 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "(-2)/(-2)";
//        System.out.print(s + " expected output 1 3 actually ");
//        solution.recursion(s, 0);
//
//        s = "sin(-30)";
//        System.out.print(s + " expected output -0.5 2 actually ");
//        solution.recursion(s, 0);
//
//        s = "cos(-30)";
//        System.out.print(s + " expected output 0.87 2 actually ");
//        solution.recursion(s, 0);
//
//        s = "tan(-30)";
//        System.out.print(s + " expected output -0.58 2 actually ");
//        solution.recursion(s, 0);
//
//        s = "2+8*(9/4-1.5)^(1+1)";
//        System.out.print(s + " expected output 6.5 6 actually ");
//        solution.recursion(s, 0);
//
//        s = "0.005 ";
//        System.out.print(s + " expected output 0.01 0 actually ");
//        solution.recursion(s, 0);
//
//        s = "0.0049 ";
//        System.out.print(s + " expected output 0 0 actually ");
//        solution.recursion(s, 0);
//
//        s = "0+0.304";
//        System.out.print(s + " expected output 0.3 1 actually ");
//        solution.recursion(s, 0);
    }

    private int countOperation(String operationString)
    {
        int countOperation = 0;
        operationString = String.valueOf(operationString);
        while (true)
        {
            if (operationString.contains("*"))
            {
                operationString = operationString.replaceFirst("\\*", "");
                countOperation++;
            } else if (operationString.contains("/"))
            {
                operationString = operationString.replaceFirst("/", "");
                countOperation++;
            } else if (operationString.contains("^"))
            {
                operationString = operationString.replaceFirst("\\^", "");
                countOperation++;
            } else if (operationString.contains("+"))
            {
                operationString = operationString.replaceFirst("\\+", "");
                countOperation++;
            } else if (operationString.contains("-"))
            {
                operationString = operationString.replaceFirst("-", "");
                countOperation++;
            } else if (operationString.contains("sin"))
            {
                operationString = operationString.replaceFirst("sin", "");
                countOperation++;
            } else if (operationString.contains("cos"))
            {
                operationString = operationString.replaceFirst("cos", "");
                countOperation++;
            } else if (operationString.contains("tan"))
            {
                operationString = operationString.replaceFirst("tan", "");
                countOperation++;
            } else
                break;
        }

        return countOperation;
    }

    public void recursion(final String expression, int countOperation)
    {
        //implement
        if (countOperation == 0)
        {
            countOperation = countOperation(expression);
        }

        String exp = String.valueOf(expression);
        if (exp.contains(" "))
            exp = exp.replaceAll(" ", "");

        if (exp.contains("(") ||
                exp.contains(")") ||
                exp.contains("+") ||
                exp.matches(".+-.+") ||
                exp.contains("*") ||
                exp.contains("/") ||
                exp.contains("^") ||
                exp.contains("sin") ||
                exp.contains("cos") ||
                exp.contains("tan"))
        {

            if (!exp.matches(".*[a-z]+\\(-?\\d+\\.?\\d*\\).*"))
                if (exp.matches("[a-z]*\\(.*\\(-?\\d+\\.?\\d*\\).*") || exp.matches("[^a-z]*\\(-?\\d+\\.?\\d*\\).*"))
                {
                    Pattern p = Pattern.compile("\\(-?\\d+\\.?\\d*\\)");
                    Matcher m = p.matcher(exp);
                    while (m.find())
                    {
                        String tmp = m.group().substring(1, m.group().length() - 1);
                        exp = exp.replaceAll("\\(-?\\d+\\.?\\d*\\)", tmp);
                    }
                }

            if (exp.contains("+-"))
                exp = exp.replace("+-", "-");

            String string = "";
            if (exp.contains("("))
            {
                if (!exp.matches(".*[a-z]+\\(-?\\d+\\.?\\d*\\).*"))
                {
                    string = exp.substring(exp.lastIndexOf("(") + 1);
                    string = string.substring(0, string.indexOf(")"));
                }
            } else
                string = String.valueOf(exp);

            if (string.contains("^"))
            {
                Pattern p = Pattern.compile("-?\\d+\\.?\\d*\\^-?\\d+\\.?\\d*");
                Matcher m = p.matcher(string);
                while (m.find())
                {
                    String[] buffer = m.group().split("\\^");
                    String sign = "";
                    if (buffer[0].contains("-"))
                        sign = "-";
                    double x = Math.pow(Double.parseDouble(buffer[0]), Double.parseDouble(buffer[1]));
                    x = (double) Math.round(x * 100) / 100;
                    exp = exp.replaceFirst(m.group().replaceFirst("\\^", "\\\\^"), sign + String.valueOf(x));
                }
            } else if (string.contains("*"))
            {
                Pattern p = Pattern.compile("\\d+\\.?\\d*\\*-?\\d+\\.?\\d*");
                Matcher m = p.matcher(string);
                while (m.find())
                {
                    String[] buffer = m.group().split("\\*");
                    double x = Double.parseDouble(buffer[0]) * Double.parseDouble(buffer[1]);
                    x = (double) Math.round(x * 100) / 100;
                    exp = exp.replaceFirst(m.group().replaceFirst("\\*", "\\\\*"), String.valueOf(x));
                }
            } else if (string.contains("/"))
            {
                Pattern p = Pattern.compile("\\d+\\.?\\d*/-?\\d+\\.?\\d*");
                Matcher m = p.matcher(string);
                while (m.find())
                {
                    String[] buffer = m.group().split("/");
                    double x = Double.parseDouble(buffer[0]) / Double.parseDouble(buffer[1]);
                    x = (double) Math.round(x * 100) / 100;
                    exp = exp.replaceFirst(m.group(), String.valueOf(x));
                }
            } else if (string.contains("+"))
            {
                Pattern p = Pattern.compile("-?\\d+\\.?\\d*\\+\\d+\\.?\\d*");
                Matcher m = p.matcher(string);
                while (m.find())
                {
                    String[] buffer = m.group().split("\\+");
                    String sign = "";
                    if (buffer[0].contains("-"))
                        sign = "+";
                    double x = Double.parseDouble(buffer[0]) + Double.parseDouble(buffer[1]);
                    x = (double) Math.round(x * 100) / 100;
                    exp = exp.replaceFirst(m.group().replaceFirst("\\+", "\\\\+"), sign + String.valueOf(x));
                }
            } else if (string.matches("-?.+-.+"))
            {
                String sign = "";
                if (string.matches("-.*"))
                {
                    sign = "-";
                }
                Pattern p = Pattern.compile("\\d+\\.?\\d*-\\d+\\.?\\d*");
                Matcher m = p.matcher(string);
                while (m.find())
                {
                    String[] buffer = m.group().split("-");
                    double x = Double.parseDouble(sign + buffer[0]) - Double.parseDouble(buffer[1]);
                    x = (double) Math.round(x * 100) / 100;
                    exp = exp.replaceFirst(sign + m.group(), String.valueOf(x));
                }
            } else if (exp.matches(".*sin\\(-?\\d+\\.?\\d*\\).*"))
            {
                Pattern p = Pattern.compile("sin\\(-?\\d+\\.?\\d*\\)");
                Matcher m = p.matcher(exp);
                while (m.find())
                {
                    string = m.group().substring(m.group().indexOf("(") + 1, m.group().indexOf(")"));
                    double x = Math.sin(Math.toRadians(Double.parseDouble(string)));
                    x = (double) Math.round(x * 100) / 100;
                    exp = exp.replace(m.group().replace("\\(", "\\\\(").replace("\\)", "\\\\)"), String.valueOf(x));
                }
            } else if (exp.matches(".*cos\\(-?\\d+\\.?\\d*\\).*"))
            {
                Pattern p = Pattern.compile("cos\\(-?\\d+\\.?\\d*\\)");
                Matcher m = p.matcher(exp);
                while (m.find())
                {
                    string = m.group().substring(m.group().indexOf("(") + 1, m.group().indexOf(")"));
                    double x = Math.cos(Math.toRadians(Double.parseDouble(string)));
                    x = (double) Math.round(x * 100) / 100;
                    exp = exp.replace(m.group().replace("\\(", "\\\\(").replace("\\)", "\\\\)"), String.valueOf(x));
                }
            } else if (exp.matches(".*tan\\(-?\\d+\\.?\\d*\\).*"))
            {
                Pattern p = Pattern.compile("tan\\(-?\\d+\\.?\\d*\\)");
                Matcher m = p.matcher(exp);
                while (m.find())
                {
                    string = m.group().substring(m.group().indexOf("(") + 1, m.group().indexOf(")"));
                    double x = Math.tan(Math.toRadians(Double.parseDouble(string)));
                    x = (double) Math.round(x * 100) / 100;
                    exp = exp.replace(m.group().replace("\\(", "\\\\(").replace("\\)", "\\\\)"), String.valueOf(x));
                }
            }

            recursion(exp, countOperation);
        } else
        {
            System.out.println((double) Math.round(Double.parseDouble(expression) * 100) / 100 + " " + countOperation);
        }
    }

    public Solution()
    {
        //don't delete
    }
}
