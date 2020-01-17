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

    public ArrayList<Country> getAllCountries() {
        try {
            Statement stmt = con.createStatement();
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
            System.out.println("Failed to get employee details");
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
        ArrayList<Country> countries = a.getAllCountries();

        System.out.println(countries.size());
        // Print countries
        for (int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);
            System.out.println(country.name + ", " + Integer.toString(country.population) + ", " + country.region);
        }

        // Disconnect from database
        a.disconnect();
    }

}