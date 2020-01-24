package com.napier.worldpopulation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testCapitals()
    {
        ArrayList<City> capitals = app.capitals(1);

        for (City capital : capitals)
        {
            assertEquals(capital.Name, "Seoul");
            assertEquals(capital.Country, "Afghanistan");
        }

    }

    @Test
    void testGetCountries()
    {
        ArrayList<Country> countries = app.countries(4);
        for (Country country : countries)
        {
            assertEquals(country.continent, "Asia");
        }
    }

    @Test
    void testGetCities()
    {
        ArrayList<City> arr_c_world = app.getCitiesInWorldByPopulation(4);
        for (City city: arr_c_world)
        {
            assertEquals(city.CountryName,"JPN");
        }
    }

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