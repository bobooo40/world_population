package com.napier.worldpopulation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void testcapitals()
    {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.name = "Tokyo";
        capital.country = "JPN";
        capital.population = 7980230;
        capitals.add(capital);
        app.printCapitals(capitals);
    }
}