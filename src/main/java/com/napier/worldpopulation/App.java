package com.napier.worldpopulation;
//import required packages
import com.mysql.jdbc.SQLError;

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
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 1:
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE country.Continent='Asia' AND city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 2:
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE country.Region='Eastern Africa' AND city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 3:
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE city.ID=country.Capital ORDER BY Population DESC LIMIT 10";
                        break;
                    case 4:
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE country.Continent='Asia' AND city.ID=country.Capital ORDER BY Population DESC LIMIT 10";
                        break;
                    case 5:
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE country.Region='Eastern Africa' AND city.ID=country.Capital ORDER BY Population DESC LIMIT 10";
                    default:
                        System.out.println("An unknown error has occurred");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please, try again.");
            }

            ResultSet results = statement.executeQuery(query);
            ArrayList<City> capitals = new ArrayList<City>();
            // The following block pushes the retrieved data into the array list
            while(results.next()) {
                City capital = new City();
                capital.Name = results.getString("city.Name");
                capital.CountryName = results.getString("country.Name");
                capital.Population = results.getInt("city.Population");
                capitals.add(capital);
            }
            return capitals;
        } catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }

    public ArrayList<City> getCitiesInWorldByPopulation()  {
        try {
            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`Population` DESC";
//            String query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`District` ASC, `city`.`Population` DESC ";
//            String query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `country`.`Continent` ASC, `city`.`Population` DESC";
//            String query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `country`.`Name` ASC, `city`.`Population` DESC ";
            ResultSet results = statement.executeQuery(query);
            ArrayList<City> arr_c_world = new ArrayList<City>();
            System.out.println(results);
            while(results.next()) {
                City cities = new City();
                cities.CountryCode = results.getString("city.CountryCode");
                cities.District = results.getString("city.District");
                cities.CountryName = results.getString("country.Name");
                cities.CountryContinent = results.getString("country.Continent");
                cities.CountryRegion = results.getString("country.Region");
                cities.Population = results.getInt("city.Population");
                arr_c_world.add(cities);
            }
            return arr_c_world;
        }
        catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }

    public void viewCities (ArrayList<City> Cities) {
        System.out.println(String.format("%-35s %-5s %-20s %-11s %-50s %-20s %-26s", "City Name", "Country Code", "District", "Population", "Country Name", "Country Continent", "Country Region"));
        // Loop over all countries in the list
        for (City city : Cities)
        {
            String emp_string =
                    String.format("%-35s %-5s %-20s %-11s %-50s %-20s %-26s",
                            city.Name, city.CountryCode, city.District,city.Population,city.CountryName,city.CountryContinent,city.CountryRegion);
            System.out.println(emp_string);
        }
    }

    /**
     * Prints a list of capitals.
     * @param capitals The list of capitals to print
     */
    public void printCapitals(ArrayList<City> capitals)
    {
        // Print header
        System.out.println(String.format("%-20s %-20s %-20s", "Name", "Country", "Population"));
        // Loop over all countries in the list
        for (City capital : capitals)
        {
            String emp_string =
                    String.format("%-20s %-20s %-20s",
                            capital.Name, capital.CountryName, capital.Population);
            System.out.println(emp_string);
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        ArrayList<City> getCities = a.getCitiesInWorldByPopulation();
        a.viewCities(getCities);
        // Countries Report Generation
//        ArrayList<City> capitals = a.capitals(4);
//        a.printCapitals(capitals);
//        System.out.println(capitals.size());



        // Disconnect from database
        a.disconnect();
    }

}