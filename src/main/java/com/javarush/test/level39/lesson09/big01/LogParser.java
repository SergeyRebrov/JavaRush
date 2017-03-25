package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.QLQuery;

import java.nio.file.Path;
import java.util.Set;

public class LogParser extends LogParserImplEventQuery implements QLQuery
{
    public LogParser(Path logDir)
    {
        super(logDir);
    }

    @Override
    public Set<Object> execute(String query)
    {
        return null;
    }
}
