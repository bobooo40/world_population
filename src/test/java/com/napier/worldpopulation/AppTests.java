package com.napier.worldpopulation;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class AppTests {
    static App app;
    @Rule
    private final ExpectedException exception = ExpectedException.none();

    @BeforeAll
    static void init() {
        app = new App();
//        app.connect("localhost:3306");

    }

    // Testing countries information
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
    void viewCapitalsNullInfoTest() {
        ArrayList<City> capitals = new ArrayList<City>();
        City capital = new City();
        capital.setName(null);
        capital.setCountryCode(null);
        capital.setPopulation(0L);
        capitals.add(capital);
        app.printCapitals(capitals);
    }

    // Testing Population information
    // Testing population information with invalid choice
    @Test
    void viewPopulationLONNullInfoTest() {
        Assert.assertNull(app.populationLON(10));
        app.viewPopulationLON(app.populationLON(10));
    }

    // Testing view population information
    @Test
    void viewPopulationInfoTest() {
        ArrayList<Dictionary> arr_population = new ArrayList<>();
        Dictionary temp_ppl = new Hashtable();
        temp_ppl.put("Criteria","Test");
        temp_ppl.put("TtlExistingPopulation",100000L);
        temp_ppl.put("TtlCityPopulation",70000L);
        temp_ppl.put("TtlNotLivingPopulation",30000L);
        temp_ppl.put("TtlCityPopulation (%)","70.00%");
        temp_ppl.put("TtlNotLivingPopulation (%)","30.00%");
        arr_population.add(temp_ppl);
        app.viewPopulationLON(arr_population);
    }

    // Testing view population information
    @Test
    void viewPopulationNullInfoTest() {
        app.viewPopulationLON(null);
    }

    // Testing exceptions
    // Testing exception for get countries information
    @Test
    void getCountriesExceptionTest() {
        exception.expect(Exception.class);
        exception.expectMessage("Failed to get countries details");
        app.countries(1);
    }

    // Testing exception for get cities information
    @Test
    void getCitiesExceptionTest() {
        exception.expect(Exception.class);
        exception.expectMessage("Failed to get cities details");
        app.getCitiesInWorldByPopulation(1);
    }

    // Testing exception for get cities information with user input
    @Test
    void getCitiesInWorldByPopulationUserInputExceptionTest() {
        exception.expect(Exception.class);
        exception.expectMessage("Failed to get cities details");
        app.getCitiesInWorldByPopulationUserInput(1,10);
    }

    // Testing exception for get capitals information
    @Test
    void getCapitalsExceptionTest() {
        exception.expect(Exception.class);
        exception.expectMessage("Failed to get capitals details");
        app.capitals(1);
    }

    // Testing exception for get population information
    @Test
    void getPopulationLONExceptionTest() {
        exception.expect(Exception.class);
        exception.expectMessage("Failed to get population details");
        app.populationLON(1);
    }

    // Testing exception for get language information
    @Test
    void getLanguageExceptionTest() {
        exception.expect(Exception.class);
        exception.expectMessage("Failed to get language details");
        app.populationLON(1);
    }

}