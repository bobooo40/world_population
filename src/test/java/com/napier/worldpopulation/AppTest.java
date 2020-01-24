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
    void printCapitalInfoTestNull()
    {
        app.printCapitals(null);
    }

//    @Test
//    void viewCapitalsInfoUserInputWValidNoTest()
//    {
//        App.getCapitalsInWorldByPopulationUserInput(1 ,10);
//    }

    @Test
    void viewCapitalsInfoTest()
    {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.name = "Tokyo";
        capital.country = "JPN";
        capital.population = 7980230;
        capitals.add(capital);
        app.printCapitals(capitals);
    }

    @Test
    void viewCitiesNullInfoTest()
    {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.name = null;
        capital.country = null;
        capital.population = 0;
        capitals.add(capital);
        app.printCapitals(capitals);
    }

}