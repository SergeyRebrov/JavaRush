package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 26.01.2017.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String userAgent = "Chrome/55.0.2883.87";
    private static final String referrer = "http://hh.ru/";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        Document document = null;
        List<Vacancy> vacancies = new ArrayList<>();
        try
        {
            int page = 0;
            while ((document = getDocument(searchString, page)) != null)
            {
                List<Element> elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements != null && !elements.isEmpty())
                {
                    for (Element element : elements)
                    {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                        vacancy.setSalary(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                        vacancy.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                        vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        vacancy.setSiteName("hh.ru");
                        vacancy.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
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

    protected Document getDocument(String searchString, int page) throws IOException
    {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent(userAgent).referrer(referrer).get();
    }
}
