package com.napier.worldpopulation;
//import required packages
import java.sql.*;
import java.util.ArrayList;

public class App
{
//    Connection to MySQL database
    private Connection con = null;
    //    Connect to the MySQL database.
    public void connect(String location)
    {
        try
        {

            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
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
            // Message for database connection
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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
                        // Get country information by population
                        query = "SELECT * FROM country ORDER BY Population DESC";
                        break;
                    case 1:
                        // Get country information with specific continent by population
                        query = "SELECT * FROM country  WHERE Continent='Asia' ORDER BY Population DESC" ;
                        break;
                    case 2:
                        // Get country information with specific region by population
                        query = "SELECT * FROM country WHERE Region='Eastern Africa' ORDER BY Population DESC";
                        break;
                    case 3:
                        // Get country information by population with defined numbers
                        query = "SELECT * FROM country ORDER BY Population DESC LIMIT 10";
                        break;
                    case 4:
                        // Get country information with specific continent by population with defined number
                        query = "SELECT * FROM country  WHERE Continent='Asia' ORDER BY Population DESC LIMIT 10" ;
                        break;
                    case 5:
                        // Get country information with specific region by population with defined number
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
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }
        else
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
    }

    /// The following function produces cities information report filtered by different criteria

    public ArrayList<City> getCitiesInWorldByPopulation(int choice)  {
        try {
            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query = null;
            try {
                switch (choice) {
                    case 1:
                        // Get city information by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`Population` DESC";
                        break;
                    case 2:
                        // Get city information in the district by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`District` ASC, `city`.`Population` DESC ";
                        break;
                    case 3:
                        // Get city information in the continent by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `country`.`Continent` ASC, `city`.`Population` DESC";
                        break;
                    case 4:
                        // Get city information in the region by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `country`.`Region` ASC, `city`.`Population` DESC ";
                        break;
                    case 5:
                        // Get city information in the country by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `country`.`Name` ASC, `city`.`Population` DESC ";
                        break;
                    default:
                        System.out.println("An unknown error has occurred");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please, try again.");
            }
            return getCities(statement, query);
        }
        catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }
    /**
     * Prints a list of cities.
     * @param choice,number The list of countries to print
     */
    public ArrayList<City> getCitiesInWorldByPopulationUserInput(int choice,int number)  {
        try {
            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query = null;
            try {
                switch (choice) {
                    case 1:
                        // Get top N number city information by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`Population` LIMIT "+number;
                        break;
                    case 2:
                        // Get top N number city information in the district by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`District` ASC, `city`.`Population` DESC LIMIT"+number;
                        break;
                    case 3:
                        // Get top N number city information in the continent by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `country`.`Continent` ASC, `city`.`Population` DESC LIMIT "+number;
                        break;
                    case 4:
                        // Get top N number city information in the region by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `country`.`Region` ASC, `city`.`Population` DESC LIMIT "+number;
                        break;
                    case 5:
                        // Get top N number city information in the country by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `country`.`Name` ASC, `city`.`Population` DESC LIMIT "+number;
                        break;

                    default:
                        System.out.println("An unknown error has occurred");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please, try again.");
            }
            return getCities(statement, query);
        }
        catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }

    // Generate the array list from the chosen query
    private ArrayList<City> getCities(Statement statement, String query) throws SQLException {
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

    /**
     * Prints a list of cities.
     * @param Cities The list of capitals to print
     */

    public void viewCities (ArrayList<City> Cities) {
        if (Cities == null)
        {
            System.out.println("No cities");
            return;
        }
        else {
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
    }

    /// The following function produces capitals information report filtered by different criteria

    public ArrayList<City> capitals(int choice) {
        try {

            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query = null;
            try {
                switch (choice) {
                    case 0:
                        // Get capital information in the world by population
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 1:
                        // Get capital information in the continent by population
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE country.Continent='Asia' AND city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 2:
                        // Get capital information in the region by population
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE country.Region='Eastern Africa' AND city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 3:
                        // Get the top N numbers capital information in the world by population
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE city.ID=country.Capital ORDER BY Population DESC LIMIT 10";
                        break;
                    case 4:
                        // Get the top N numbers capital information in the continent by population
                        query = "SELECT city.Name, country.Name, city.Population FROM city, country WHERE country.Continent='Asia' AND city.ID=country.Capital ORDER BY Population DESC LIMIT 10";
                        break;
                    case 5:
                        // Get the top N numbers capital information in the region by population
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
                capital.Country = results.getString("country.Name");
                capital.Population = results.getInt("city.Population");
                capitals.add(capital);
            }
            return capitals;
        } catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get capitals details");
            return null;
        }
    }

    /**
     * Prints a list of capitals.
     * @param capitals The list of capitals to print
     */
    public void printCapitals(ArrayList<City> capitals)
    {
        if (capitals == null)
        {
            System.out.println("No capital information");
            return;
        }
        else
        {
            // Print header
            System.out.println(String.format("%-20s %-20s %-20s", "Name", "Country", "Population"));
            // Loop over all countries in the list
            for (City capital : capitals)
            {
                String emp_string =
                        String.format("%-20s %-20s %-20s",
                                capital.Name, capital.Country, capital.Population);
                System.out.println(emp_string);
            }
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:3306");
        }
        else
        {
            a.connect(args[0]);
        }
        // Countries Report Generation
        ArrayList<Country> countries = a.countries(4);
        a.printCountries(countries);

        // Countries Report Generation
        ArrayList<City> capitals = a.capitals(4);
        a.printCapitals(capitals);
        System.out.println(capitals.size());

        // All the cities information by population
        a.viewCities(a.getCitiesInWorldByPopulation(1));
        a.viewCities(a.getCitiesInWorldByPopulation(2));
        a.viewCities(a.getCitiesInWorldByPopulation(3));
        a.viewCities(a.getCitiesInWorldByPopulation(4));

        // All the top cities information by population
        a.viewCities(a.getCitiesInWorldByPopulationUserInput(1,10));
        a.viewCities(a.getCitiesInWorldByPopulationUserInput(2,10));
        a.viewCities(a.getCitiesInWorldByPopulationUserInput(3,10));
        a.viewCities(a.getCitiesInWorldByPopulationUserInput(4,10));



        // Disconnect from database
        a.disconnect();
    }

}