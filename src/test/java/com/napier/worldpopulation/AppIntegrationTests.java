package com.napier.worldpopulation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AppIntegrationTests
{
    static App app;

    // Testing database connection
    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    // Testing get country information by choice
    @Test
    void testGetCountry()
    {

        ArrayList<Country> countries = app.countries(4);
        app.printCountries(countries);
    }


//    @Test
//    void testGetCities()
//    {
//        ArrayList<City> capitals = app.capitals(2);
//
//        for (City capital : capitals)
//        {
//            assertEquals(capital.CountryRegion, "Eastern Africa");
//        }
//
//    }

    // Testing get capital information by choice
    @Test
    void testGetCaptial()
    {
        ArrayList<City> capitals = app.capitals(4);
        app.printCapitals(capitals);
//        ArrayList<City> capitals = app.capitals(5);
//        for (City capital : capitals)
//        {
//            System.out.println(capital.Region);
//            assertEquals(capital.Region,"Eastern Africa");
//        }
    }

//    @Test
//    void testGetCitiesByUserDefine()
//    {
//        ArrayList<City> arr_c_world = app.getCitiesInWorldByPopulationUserInput(2,10);
//        assertEquals(arr_c_world.size(), 10);
//    }

    // Testing countries information
    @Test
    void printCountriesInfoTest()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.code="AGO";
        country.name="Angola";
        country.continent="Africa";
        country.region="Central Africa";
        country.population= 12878000L;
        country.capital="Angola";
        countries.add(country);
        app.printCountries(countries);
    }

    // Testing null countries information
    @Test
    void printCountriesNullInfoTest()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.code=null;
        country.name=null;
        country.continent=null;
        country.region=null;
        country.population=0L;
        country.capital=null;
        countries.add(country);
        app.printCountries(countries);
    }

    // Testing capitals information
    @Test
    void viewCapitalsInfoTest()
    {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.Name = "Tokyo";
        capital.Country = "JPN";
        capital.Population = 7980230L;
        capitals.add(capital);
        app.printCapitals(capitals);
    }

    // Testing null capital information
    @Test
    void viewCaptitalNullInfoTest()
    {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.Name = null;
        capital.Country = null;
        capital.Population = 0L;
        capitals.add(capital);
        app.printCapitals(capitals);
    }


}