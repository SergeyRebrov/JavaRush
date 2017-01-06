package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution
{

    public static StringBuilder getCondition(Map<String, String> params)
    {
        StringBuilder result = null;
        if (params != null)
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value == null || key == null)
                    continue;
                if (result == null)
                {
                    result = new StringBuilder();
                    result.append(key + " = '" + value + "'");
                } else
                    result.append(" and " + key + " = '" + value + "'");
            }
        }
        return result == null ? new StringBuilder() : result;
    }
}
