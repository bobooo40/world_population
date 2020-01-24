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
        ArrayList<City> capitals = app.capitals(1);
        City capital = new City();
        assertEquals(capital.name, "Tokyo");
        assertEquals(capital.country, "JPN");
        assertEquals(capital.population,7980230);
    }
}