package com.napier.worldpopulation;
//import required packages

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

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
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "example");

//                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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
                country.population = results.getLong("Population");
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
                String data_string =
                        String.format("%-10s %-20s %-20s %-35s %-20s %-20s",
                                country.code, country.name, country.continent, country.region, country.population, country.capital);
                System.out.println(data_string);
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
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`District` ASC, `city`.`Population` DESC LIMIT "+number;
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
        while(results.next()) {
            City cities = new City();

            cities.Name = results.getString("city.Name");
            cities.CountryCode = results.getString("city.CountryCode");
            cities.District = results.getString("city.District");
            cities.CountryName = results.getString("country.Name");
            cities.CountryContinent = results.getString("country.Continent");
            cities.CountryRegion = results.getString("country.Region");
            cities.Population = results.getLong("city.Population");
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

                String data_string =
                        String.format("%-35s %-5s %-20s %-11s %-50s %-20s %-26s",
                                city.Name, city.CountryCode, city.District,city.Population,city.CountryName,city.CountryContinent,city.CountryRegion);
                System.out.println(data_string);
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
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 1:
                        // Get capital information in the continent by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE country.Continent='Asia' AND city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 2:
                        // Get capital information in the region by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE country.Region='Eastern Africa' AND city.ID=country.Capital ORDER BY Population DESC";
                        break;
                    case 3:
                        // Get the top N numbers capital information in the world by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE city.ID=country.Capital ORDER BY Population DESC LIMIT 10";
                        break;
                    case 4:
                        // Get the top N numbers capital information in the continent by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region FROM city, country WHERE country.Continent='Asia' AND city.ID=country.Capital ORDER BY Population DESC LIMIT 10";
                        break;
                    case 5:
                        // Get the top N numbers capital information in the region by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE country.Region='Eastern Africa' AND city.ID=country.Capital ORDER BY Population DESC LIMIT 10";
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
                capital.Population = results.getLong("city.Population");
                capital.Continent = results.getString("country.Continent");
                capital.Region = results.getString("country.Region");

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
                String data_string =
                        String.format("%-20s %-20s %-20s",
                                capital.Name, capital.Country, capital.Population);
                System.out.println(data_string);
            }
        }
    }

    /// The following function produces population who live in cities or not information report filtered by different criteria

    public ArrayList<Dictionary> populationLON(int choice) {
        try {

            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query_city_ppl = null, query_existiong_ppl = null, criteria = null;
            try {
                switch (choice) {
                    case 1:
                        // Get city population information in the each continent
                        criteria = "country.Continent";
                        query_city_ppl = "SELECT `country`.`Continent`, SUM(`city`.`Population`) AS `TtlCityPopulation` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` GROUP BY `country`.`Continent`  ORDER BY `country`.`Continent` ASC ";
                        query_existiong_ppl = "SELECT `country`.`Continent`, SUM(`country`.`Population`) AS `TtlExistingPopulation` FROM `country` GROUP BY `country`.`Continent`  ORDER BY `country`.`Continent` ASC ";
                        break;
                    case 2:
                        // Get city population information in the each region
                        criteria = "country.Region";
                        query_city_ppl = "SELECT `country`.`Region`, SUM(`city`.`Population`) AS `TtlCityPopulation` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` GROUP BY `country`.`Region` ORDER BY `country`.`Region` ASC ";
                        query_existiong_ppl = "SELECT `country`.`Region`, SUM(`country`.`Population`) AS `TtlExistingPopulation` FROM `country` GROUP BY `country`.`Region` ORDER BY `country`.`Region` ASC";
                        break;
                    case 3:
                        // Get city population information in the each country
                        criteria = "country.Name";
                        query_city_ppl = "SELECT `country`.`Name`, SUM(`city`.`Population`) AS `TtlCityPopulation` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` GROUP BY `country`.`Name` ORDER BY `country`.`Name` ASC ";
                        query_existiong_ppl = "SELECT `country`.`Name`, SUM(`country`.`Population`) AS `TtlExistingPopulation` FROM `country` GROUP BY `country`.`Name` ORDER BY `country`.`Name` ASC";
                        break;
                    default:
                        System.out.println("An unknown error has occurred");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please, try again.");
            }
            ArrayList<Dictionary> arr_population = new ArrayList<>();
            ResultSet results_existing_ppl = statement.executeQuery(query_existiong_ppl);

            // The following block pushes the retrieved data into the array list
            while(results_existing_ppl.next()) {
                Dictionary temp = new Hashtable();
                temp.put("TtlExistingPopulation",results_existing_ppl.getLong("TtlExistingPopulation"));
                temp.put("Criteria",results_existing_ppl.getString(criteria));
                temp.put("TtlCityPopulation",0L);
                arr_population.add(temp);
            }

            ResultSet results_city_ppl = statement.executeQuery(query_city_ppl);
            // The following block pushes the retrieved data into the array list
            while(results_city_ppl.next()) {
                addInfo: {
                    for (int i = 0; i < arr_population.size(); i++) {
                        if (arr_population.get(i).get("Criteria").toString().equals(results_city_ppl.getString(criteria))) {

                            arr_population.get(i).put("TtlCityPopulation",results_city_ppl.getLong("TtlCityPopulation"));
                            Long temp_ttl_city = results_city_ppl.getLong("TtlCityPopulation");
                            Long temp_ttl_ext = (Long) arr_population.get(i).get("TtlExistingPopulation");
                            // Convert double to show two decimal place
                            double temp = temp_ttl_city*100;
                            String temp_per = String.format("%.2f", temp/temp_ttl_ext) + " %";
                            arr_population.get(i).put("TtlCityPopulation (%)",temp_per);
                            Long temp_ttl_not = temp_ttl_ext - temp_ttl_city;
                            temp = temp_ttl_not*100;
                            temp_per = String.format("%.2f", temp/temp_ttl_ext) + " %";
                            arr_population.get(i).put("TtlNotLivingPopulation",temp_ttl_not);
                            arr_population.get(i).put("TtlNotLivingPopulation (%)",temp_per);
                            break addInfo;
                        }
                    }
                }
            }
            return arr_population;
        } catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /// The following function produces population who speak different langauge report filtered by different criteria

    public ArrayList<CountryLanguage> languge() {
        try {

            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query="SELECT `countrylanguage`.`Language`, SUM(`country`.`Population`*(`countrylanguage`.`Percentage`/100)) AS `NoOfSpeakers` FROM `countrylanguage` LEFT JOIN `country` ON `countrylanguage`.`CountryCode` = `country`.`Code` WHERE `countrylanguage`.`Language`= 'Arabic' OR `countrylanguage`.`Language`='Chinese' OR `countrylanguage`.`Language`= 'English' OR `countrylanguage`.`Language`= 'Hindi' OR `countrylanguage`.`Language`= 'Spanish' GROUP BY `countrylanguage`.`Language` ORDER BY `NoOfSpeakers` DESC ";
            ArrayList<CountryLanguage> arr_lang = new ArrayList<CountryLanguage>();
            ResultSet results_lang = statement.executeQuery(query);

            // The following block pushes the retrieved data into the array list
            while(results_lang.next()) {
                CountryLanguage lang = new CountryLanguage();
                lang.Language = results_lang.getString("countrylanguage.Language");
                lang.NoOfSpeakers = results_lang.getLong("NoOfSpeakers");
                arr_lang.add(lang);
            }
            return arr_lang;
        } catch (Exception e) {
            // Error message
            System.out.println(e.getMessage());
            System.out.println("Failed to get country language details");
            return null;
        }
    }

    /**
     * Prints a list of poeple who speak different languages.
     * @param langs The list of population to print
     */
    public void viewLanguages(ArrayList<CountryLanguage> langs) {
        if (langs == null) {
            System.out.println("No language information");
            return;
        } else {
            // Print header
            System.out.println(String.format("%-20s %-20s", "Language", "Number of Speakers"));
            // Loop over all languages in the list
            for (CountryLanguage lang : langs) {
                String data_string =
                        String.format("%-20s %-20s",
                                lang.Language, lang.NoOfSpeakers);
                System.out.println(data_string);
            }
        }
    }

    /**
     * Prints a list of population living in the cities or not
     * @param population The list of population to print
     */
    public void viewPopulationLON(ArrayList<Dictionary> population)
    {
        if (population == null)
        {
            System.out.println("No population information");
            return;
        }
        else
        {
            // Print header
            System.out.println(String.format("%-35s %-35s %-35s %-35s %-35s %-35s", "Name", "Total Existing Population",
                    "Total Living Population In Cities", "Total Not Living Population",
                    "Total Living Population In Cities (%)", "Total Not Living Population (%)"));
            // Loop over all population in the list
            for (int i = 0; i < population.size(); i++)
            {
                String data_string =
                        String.format("%-35s %-35s %-35s %-35s %-35s %-35s",population.get(i).get("Criteria"),
                                population.get(i).get("TtlExistingPopulation"), population.get(i).get("TtlCityPopulation"),
                                population.get(i).get("TtlNotLivingPopulation"), population.get(i).get("TtlCityPopulation (%)"),population.get(i).get("TtlNotLivingPopulation (%)"));
                System.out.println(data_string);
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
//        ArrayList<Country> countries = a.countries(4);
//        a.printCountries(countries);
//
//        // Countries Report Generation
//        ArrayList<City> capitals = a.capitals(4);
//        a.printCapitals(capitals);

//        // All the cities information by population
//        a.viewCities(a.getCitiesInWorldByPopulation(1));
//        a.viewCities(a.getCitiesInWorldByPopulation(2));
//        a.viewCities(a.getCitiesInWorldByPopulation(3));
//        a.viewCities(a.getCitiesInWorldByPopulation(4));
//
//        // All the top cities information by population
//        a.viewCities(a.getCitiesInWorldByPopulationUserInput(1,10));
//        a.viewCities(a.getCitiesInWorldByPopulationUserInput(2,10));
//        a.viewCities(a.getCitiesInWorldByPopulationUserInput(3,10));
//        a.viewCities(a.getCitiesInWorldByPopulationUserInput(4,10));

        // All the population information who live in the cities nor not
        a.viewPopulationLON(a.populationLON(1));
        a.viewPopulationLON(a.populationLON(2));
        a.viewPopulationLON(a.populationLON(3));

        a.viewLanguages(a.languge());

        // Disconnect from database
        a.disconnect();
    }

}