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
        Assert.assertNotNull(app.countries(4));
        ArrayList<Country> countries = app.countries(4);
        app.printCountries(countries);
    }

    // Testing get capital information by choice
    @Test
    void testGetCapital()
    {
        Assert.assertNotNull(app.capitals(4));
        ArrayList<City> capitals = app.capitals(4);
        app.printCapitals(capitals);
    }

    // Testing view Cities by user input
    @Test
    void getCityInfoUserInputWValidNoTest() {
        Assert.assertNotNull(app.getCitiesInWorldByPopulationUserInput(1, 10));
        app.viewCities(app.getCitiesInWorldByPopulationUserInput(1,10));
    }

    // Testing view Cities by valid user input
    @Test
    void viewCountriesInfoUserInputWValidNoTest() {
        Assert.assertNotNull(app.countries(1));
        ArrayList<Country> countries = app.countries(4);
        app.printCountries(countries);
    }

    // Testing view Cities by valid user input
    @Test
    void viewLanguageInfo() {
        Assert.assertNotNull(app.languge());
        ArrayList<CountryLanguage> langs = app.languge();
        app.viewLanguages(langs);
    }

    // Testing view population information by valid user input
    @Test
    void viewPopulationInfoUserInputWValidNoTest() {
        Assert.assertNotNull(app.populationLON(1));
        ArrayList<Dictionary> population = app.populationLON(1);
        app.viewPopulationLON(population);
    }
}
