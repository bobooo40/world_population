package com.napier.worldpopulation;

import com.napier.worldpopulation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTests {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
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

    @Test
    void printCitiesInfoTestNull()
    {
        app.viewCities(null);
    }

//
//    @Test
//    void viewCityInfoUserInputWValidNoTest()
//    {
//        App.viewCities(App.getCitiesInWorldByPopulationUserInput(1,10));
//    }

    @Test
    void viewCitiesInfoTest()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.Name = "Tokyo";
        city.Country = "JPN";
        city.Population = 7980230;
        cities.add(city);
        app.viewCities(cities);
    }

    @Test
    void viewCitiesNullInfoTest()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.Name = null;
        city.Country = null;
        city.Population = 0;
        cities.add(city);
        app.printCapitals(cities);
    }

}