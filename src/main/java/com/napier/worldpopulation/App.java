package com.napier.worldpopulation;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    /**
     * Prints a list of employees.
     * @param employees The list of employees to print.
     */

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        int i=0;
        for (i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // Extract all the countries from the database ordered by population
    public ArrayList<Country> getAllCountries() {
        try {
            Statement stmt = con.createStatement();
            // Region
            String query = "SELECT * FROM country ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.capital = rset.getString("Capital");
                country.region = rset.getString("Region");
                country.continent = rset.getString("Continent");
                country.population = rset.getInt("Population");
                countries.add(country);
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }

    // Extract all the countries from the database ordered by region and population
    public ArrayList<Country> getAllCountriesByRegions() {
        try {
            Statement stmt = con.createStatement();
            // Region
            String query = "SELECT * FROM country ORDER BY Region ASC, Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.capital = rset.getString("Capital");
                country.region = rset.getString("Region");
                country.continent = rset.getString("Continent");
                country.population = rset.getInt("Population");
                countries.add(country);
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }

    // Extract all the countries from the database ordered by continent and population
    public ArrayList<Country> getAllCountriesByContinents() {
        try {
            Statement stmt = con.createStatement();
            // Continent
            String query = "SELECT * FROM country ORDER BY Continent ASC, Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.capital = rset.getString("Capital");
                country.region = rset.getString("Region");
                country.continent = rset.getString("Continent");
                country.population = rset.getInt("Population");
                countries.add(country);
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }


    // Extract all the cities from the database ordered by population
    public ArrayList<City> getAllCities() {
        try {
            Statement stmt = con.createStatement();
            // Continent
            String query = "SELECT city.Name, city.District, city.Population, country.Name FROM city, country WHERE city.CountryCode=country.Code ORDER BY city.Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities details");
            return null;
        }
    }

    // Extract all the cities from database ordered by continent and population
    public ArrayList<City> getAllCitiesByContinent() {
        try {
            Statement stmt = con.createStatement();
            // Continent
            String query = "SELECT city.Name, city.District, city.Population, country.Name, country.Continent FROM city, country WHERE city.CountryCode=country.Code ORDER BY country.Continent ASC, city.Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.continent = rset.getString("country.Continent");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities details");
            return null;
        }
    }

    // Extract all the cities from database ordered by region and population
    public ArrayList<City> getAllCitiesByRegion() {
        try {
            Statement stmt = con.createStatement();
            // Region
            String query = "SELECT city.Name, city.District, city.Population, country.Name, country.Region FROM city, country WHERE city.CountryCode=country.Code ORDER BY country.Region ASC, city.Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.region = rset.getString("country.Region");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities details");
            return null;
        }
    }

    // Extract all the cities from database ordered by district and population
    public ArrayList<City> getAllCitiesByDistrict() {
        try {
            Statement stmt = con.createStatement();
            // District
            String query = "SELECT city.Name, city.District, city.Population, city.District, country.Name FROM city, country WHERE city.CountryCode=country.Code ORDER BY city.District ASC, city.Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities details");
            return null;
        }
    }

    // Extract all the countries from database ordered by countries
    public ArrayList<City> getAllCitiesByCountries() {
        try {
            Statement stmt = con.createStatement();
            // Continent
            String query = "SELECT city.Name, city.District, city.Population, city.District, country.Name FROM city, country WHERE city.CountryCode=country.Code ORDER BY country.Name ASC, city.Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities details");
            return null;
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Extract countries
        ArrayList<Country> countries = a.getAllCountriesByRegions();
        ArrayList<City> cities = a.getAllCitiesByContinent();

        System.out.println(countries.size());
        System.out.println(cities.size());
        // Print countries
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            System.out.println(city.name + ", " + Integer.toString(city.population) + ", " + city.continent);
        }

        // Disconnect from database
        a.disconnect();
    }

}