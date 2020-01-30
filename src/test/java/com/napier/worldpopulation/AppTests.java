package com.napier.worldpopulation;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AppTests {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();

    }

    // Testing countries information
    // Testing null countries information
    @Test
    void printCountriesInfoTestNull() {
        app.printCountries(null);
    }
    // Testing view Countries information
    @Test
    void printCountriesInfoTest() {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setCode("AGO");
        country.setName("Angola");
        country.setContinent("Africa");
        country.setRegion("Central Africa");
        country.setPopulation(12878000L);
        country.setCapital("Angola");
        countries.add(country);
        app.printCountries(countries);
    }

    // Testing view null countries
    @Test
    void printCountriesNullInfoTest() {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setCode(null);
        country.setName(null);
        country.setContinent(null);
        country.setRegion(null);
        country.setPopulation(0L);
        country.setCapital(null);
        countries.add(country);
        app.printCountries(countries);
    }
    @Test
    void viewCountriesInfoUserInputWNValidNoTest() {
        Assert.assertNull(app.countries(10));
        ArrayList<Country> countries = app.countries(10);
        app.printCountries(countries);
    }

    // Testing Cities information
    // Testing view Cities information
    @Test
    void viewCitiesInfoTest() {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setName("Tokyo");
        city.setCountryCode("JPN");
        city.setPopulation(7980230L);
        cities.add(city);
        app.viewCities(cities);
    }
    @Test
    void viewCitiesNullInfoTest() {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setName(null);
        city.setCountryCode(null);
        city.setPopulation(0L);
        cities.add(city);
        app.printCapitals(cities);
    }


    @Test
    void getCityInfoUserInputWNValidNoTest() {
        Assert.assertNull(app.getCitiesInWorldByPopulationUserInput(10, 10));
        app.viewCities(app.getCitiesInWorldByPopulationUserInput(10,10));
    }


    // Testing view null capitals information
    @Test
    void printCapitalInfoTestNull() {
        app.printCapitals(null);
    }

//    @Test
//    void viewCapitalsInfoUserInputWValidNoTest()
//    {
//        app.getCitiesInWorldByPopulationUserInput(1 ,10);
//    }

    // Testing view capitals information
    @Test
    void viewCapitalsInfoTest() {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.setName("Tokyo");
        capital.setCountryCode("JPN");
        capital.setPopulation(7980230L);
        capitals.add(capital);
        app.printCapitals(capitals);
    }

    // Testing view null capitals information
    @Test
    void viewCapitalNullInfoTest() {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.setName(null);
        capital.setCountryCode(null);
        capital.setPopulation(0L);
        capitals.add(capital);
        app.printCapitals(capitals);
    }

    // Testing view null capitals information
    @Test
    void viewCapitalsInfoTestNull() {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setName(null);
        city.setCountryCode(null);
        city.setPopulation(0L);
        cities.add(city);
        app.printCapitals(cities);
    }

    //Testing view capital info
    @Test
    void viewCapitalsNullInfoTest() {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.setName(null);
        capital.setCountryName(null);
        capital.setPopulation(0L);
        capital.setContinent(null);
        capital.setRegion(null);
        capitals.add(capital);
        app.printCapitals(capitals);
    }

    //Testing view populationLON
//    @Test
//    void viewPopulationLON() {
//        ArrayList<Dictionary> population = null;
//    }
}