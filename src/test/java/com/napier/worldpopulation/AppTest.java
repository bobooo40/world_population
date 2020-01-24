package com.napier.worldpopulation;

import com.napier.worldpopulation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void viewCitiesInfoTestNull()
    {
        app.viewCities(null);
    }

    @Test
    void viewCitiesInfoUserInputTest()
    {
        app.getCitiesInWorldByPopulationUserInput(1 ,0);
    }

    @Test
    void viewCitiesInfoUserInputWValidNoTest()
    {
        app.getCitiesInWorldByPopulationUserInput(1 ,10);
    }

    @Test
    void viewCitiesInfoTest()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.Name = "Tokyo";
        city.CountryCode = "JPN";
        city.District = "Tokyo-to";
        city.Population = 7980230;
        city.CountryName = "Japan";
        city.CountryContinent = "Asia";
        city.CountryRegion = "Eastern Asia";
        cities.add(city);
        app.viewCities(cities);
    }

    @Test
    void viewCitiesNullInfoTest()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.Name = "Tokyo";
        city.CountryCode = "JPN";
        city.District = "Tokyo-to";
        city.Population = 0;
        city.CountryName = "Japan";
        city.CountryContinent = "Asia";
        city.CountryRegion = "Eastern Asia";
        cities.add(city);
        app.viewCities(cities);
    }

}