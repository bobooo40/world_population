package com.napier.worldpopulation;

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
    void testCapitals()
    {
        ArrayList<City> capitals = app.capitals(1);
        City city = new City();
        assertEquals(city.name, "Tokyo");
        assertEquals(city.country, "JPN");
        assertEquals(city.population,7980230);
    }
}