package com.napier.worldpopulation;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Dictionary;

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
        ArrayList<Country> countries = app.countries(2,"Asia", 10);
        Assert.assertNotNull(countries);
        app.printCountries(countries);
    }

    // Testing view country by valid user input
    @Test
    void viewCountriesInfoUserInputWValidNoTest() {
        ArrayList<Country> countries = app.countries(2,"Asia", 10);
        Assert.assertNotNull(countries);
        app.printCountries(countries);
    }

    // Testing get capital information by choice
    @Test
    void testGetCapital()
    {
        ArrayList<City> capitals = app.capitals(2,"Asia", 10);
        Assert.assertNotNull(capitals);
        app.printCapitals(capitals);
    }

    // Testing get capital information by choice
    @Test
    void testGetCapitalWValidNoTest()
    {
        ArrayList<City> capitals = app.capitals(4,"Asia", 10);
        Assert.assertNotNull(capitals);
        app.printCapitals(capitals);
    }

    // Testing view Cities by user input
    @Test
    void getCityInfoUserInputWValidNoTest() {
        ArrayList<City> cities = app.getCitiesInWorldByPopulation(6, "Asia",10);
        Assert.assertNotNull(cities);
        app.viewCities(cities);
    }

    // Testing view language information
    @Test
    void viewLanguageInfo() {
        ArrayList<CountryLanguage> langs = app.language();
        Assert.assertNotNull(langs);
        app.viewLanguages(langs);
    }

    // Testing view language information
    @Test
    void viewPopulationInfo() {
        ArrayList<Dictionary> populationLON = app.populationLON(1);
        Assert.assertNotNull(populationLON);
        app.viewPopulationLON(populationLON);
    }
}
