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



}