package com.javarush.test.level33.lesson05.home05;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
@JsonSubTypes(@JsonSubTypes.Type(value = Parking.class, name = ".Parking"))
public class Parking
{
    public String name;
    public String city;

    @JsonDeserialize(as = ArrayList.class)
    public List<Auto> autos;

    public Parking(String name, String city)
    {
        this.name = name;
        this.city = city;
    }

    public void setAutos(List<Auto> autos)
    {
        this.autos = autos;
    }

    @Override
    public String toString()
    {
        return "Parking{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", autos=" + autos +
                '}';
    }
}