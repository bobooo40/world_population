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
    void testGetCountry()
    {
        ArrayList<Country> countries = app.countries(4);
        for (Country country : countries)
        {
            assertEquals(country.continent, "Asia");
        }
    }
    @Test
    void printCountriesInfoTestNull()
    {
        app.printCountries(null);
    }

    //    @Test
//    void viewCitiesInfoUserInputTest()
//    {
//        app.getCitiesInWorldByPopulationUserInput(1 ,0);
//    }
//
//    @Test
//    void viewCitiesInfoUserInputWValidNoTest()
//    {
//        app.getCitiesInWorldByPopulationUserInput(1 ,10);
//    }
//
    @Test
    void printCountriesInfoTest()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.code="AGO";
        country.name="Angola";
        country.continent="Africa";
        country.region="Central Africa";
        country.population=12878000;
        country.capital="Angola";
        countries.add(country);
        app.printCountries(countries);
    }

    @Test
    void printCountriesNullInfoTest()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.code=null;
        country.name=null;
        country.continent=null;
        country.region=null;
        country.population=0;
        country.capital=null;
        countries.add(country);
        app.printCountries(countries);
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
        capital.Name = "Tokyo";
        capital.Country = "JPN";
        capital.Population = 7980230;
        capitals.add(capital);
        app.printCapitals(capitals);
    }

    @Test
    void viewCaptitalNullInfoTest()
    {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.Name = null;
        capital.Country = null;
        capital.Population = 0;
        capitals.add(capital);
        app.printCapitals(capitals);
    }
}