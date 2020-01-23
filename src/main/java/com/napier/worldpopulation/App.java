package com.napier.worldpopulation;
//import required packages
import java.sql.*;
import java.util.ArrayList;

public class App
{
//    Connection to MySQL database
    private Connection con = null;

//    Connect to the MySQL database.
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
//            Error message for sql driver connection
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        int i;
        for (i = 0; i < retries; ++i)
        {
            // Message for database connenction
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
                // Error message for database connection
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                // Error message for thread interruption
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    // Disconnect from the MySQL database
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
                // Error message for closing database connection
                System.out.println("Error closing connection to database");
            }
        }
    }

    // The following function produces countries information report filtered by different criteria
    public ArrayList<Country> countries(int choice) {
        try {

            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query = null;
            try {
                switch (choice) {
                    case 0:
                        query = "SELECT * FROM country ORDER BY Population DESC";
                        break;
                    case 1:
                        query = "SELECT * FROM country  WHERE Continent='Asia' ORDER BY Population DESC" ;
                        break;
                    case 2:
                        query = "SELECT * FROM country WHERE Region='Eastern Africa' ORDER BY Population DESC";
                        break;
                    case 3:
                        query = "SELECT * FROM country ORDER BY Population DESC LIMIT 10";
                        break;
                    case 4:
                        query = "SELECT * FROM country  WHERE Continent='Asia' ORDER BY Population DESC LIMIT 10" ;
                        break;
                    case 5:
                        query = "SELECT * FROM country WHERE Region='Eastern Africa' ORDER BY Population DESC LIMIT 10";
                        break;
                    default:
                        System.out.println("An unknown error has occurred");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please, try again.");
            }

            ResultSet results = statement.executeQuery(query);
            ArrayList<Country> countries = new ArrayList<Country>();
            // The following block pushes the retrieved data into the array list
            while(results.next()) {
                Country country = new Country();
                country.code = results.getString("Code");
                country.name = results.getString("Name");
                country.continent = results.getString("Continent");
                country.region = results.getString("Region");
                country.population = results.getInt("Population");
                country.capital = results.getString("Capital");
                countries.add(country);
            }
            return countries;
        } catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }

    /**
     * Prints a list of countries.
     * @param countries The list of countries to print
     */
    public void printCountries(ArrayList<Country> countries)
    {
        // Print header
        System.out.println(String.format("%-10s %-20s %-20s %-35s %-20s %-20s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (Country country : countries)
        {
            String emp_string =
                    String.format("%-10s %-20s %-20s %-35s %-20s %-20s",
                            country.code, country.name, country.continent, country.region, country.population, country.capital);
            System.out.println(emp_string);
        }
    }




    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Countries Report Generation
        ArrayList<Country> countries = a.countries(4);
        a.printCountries(countries);



        // Disconnect from database
        a.disconnect();
    }

}