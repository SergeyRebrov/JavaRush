package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Sergey on 27.01.2017.
 */
public class HtmlView implements View
{
    private Controller controller;
    private final String filePath;

    public HtmlView()
    {
        filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";
    }

    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod()
    {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException
    {
        Document document = getDocument();
        Element template = document.getElementsByClass("template").first();
        Element newTemplate = template.clone();
        newTemplate.removeClass("template").removeAttr("style");
        document.getElementsByAttributeValue("class", "vacancy").remove();

        for (Vacancy vacancy : vacancies)
        {
            Element newTag = newTemplate.clone();
            newTag.getElementsByClass("city").first().appendText(vacancy.getCity());
            newTag.getElementsByClass("companyName").first().appendText(vacancy.getCompanyName());
            newTag.getElementsByClass("salary").first().appendText(vacancy.getSalary());
            Element title = newTag.getElementsByTag("a").first().appendText(vacancy.getTitle());
            title.attr("href", vacancy.getUrl());
            template.before(newTag.outerHtml());
        }

        return document.html();
    }

    private void updateFile(String file)
    {
        try (OutputStream fileOutputStream = new FileOutputStream(filePath))
        {
            fileOutputStream.write(file.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

}
