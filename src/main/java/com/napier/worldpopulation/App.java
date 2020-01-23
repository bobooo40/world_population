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
    public ArrayList<City> capitals(int choice) {
        try {

            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query = null;
            try {
                switch (choice) {
                    case 0:
                        query = "SELECT city.Name, city.Population, country.Name FROM city, country WHERE city.ID=country.Capital ORDER BY city.Population DESC";
                        break;
                    default:
                        System.out.println("An unknown error has occurred");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please, try again.");
            }

            ResultSet results = statement.executeQuery(query);
            ArrayList<City> cities = new ArrayList<City>();
            // The following block pushes the retrieved data into the array list
            while(results.next()) {
                City city = new City();
                city.name = results.getString("city.Name");
                city.country = results.getString("country.Name");
                city.population = results.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get capitals details");
            return null;
        }
    }

    /**
     * Prints a list of capital cities.
     * @param cities The list of countries to print
     */
    public void printCapitals(ArrayList<City> cities)
    {
        // Print header
        System.out.println(String.format("%-20s %-20s %-20s", "Name", "Country", "Population"));
        // Loop over all countries in the list
        for (City city : cities)
        {
            String emp_string =
                    String.format("%-20s %-20s %-20s",
                            city.name, city.country, city.population);
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
        ArrayList<City> cities = a.capitals(0);
        System.out.println(cities.size());



        // Disconnect from database
        a.disconnect();
    }

}