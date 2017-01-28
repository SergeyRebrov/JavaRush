package com.javarush.test.level28.lesson15.big01.model;


import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 28.01.2017.
 */
public class MoikrugStrategy extends HHStrategy
{

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private static final String referrer = "https://moikrug.ru";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        Document document = null;
        List<Vacancy> vacancies = new ArrayList<>();
        try
        {
            int page = 1;
            while ((document = getDocument(searchString, page)) != null)
            {
                List<Element> elements = document.getElementsByClass("job");
                if (elements != null && !elements.isEmpty())
                {
                    for (Element element : elements)
                    {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.getElementsByClass("title").text());
                        vacancy.setSalary(element.getElementsByClass("salary").text());
                        vacancy.setCity(element.getElementsByClass("location").text());
                        vacancy.setCompanyName(element.getElementsByClass("company_name").text());
                        vacancy.setSiteName("moikrug.ru");
                        vacancy.setUrl(element.getElementsByClass("title").select("a").attr("abs:href"));
                        vacancies.add(vacancy);
                    }
                }
                else
                    break;
                page++;
            }
        }
        catch (IOException ignore)
        {
        }

        return vacancies;
    }

    @Override
    protected Document getDocument(String searchString, int page) throws IOException
    {
        return Jsoup.connect(String.format(URL_FORMAT, page, searchString)).userAgent(userAgent).referrer(referrer).get();
    }
}
