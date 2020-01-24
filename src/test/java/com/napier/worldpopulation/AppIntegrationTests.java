package com.napier.worldpopulation;

import com.napier.worldpopulation.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTests
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void testGetCities()
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
}