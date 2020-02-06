package com.napier.worldpopulation;
//import required packages

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
//    Connection to MySQL database
    private Connection con = null;
    static final Logger log = Logger.getLogger(App.class.getName());
    static final Scanner input = new Scanner(System.in);

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
            // Error message for sql driver connection
            log.log(Level.SEVERE,"Could not load SQL driver", e);
            System.exit(-1);
        }

        int retries = 10;
        int i;
        for (i = 0; i < retries; ++i)
        {
            // Message for database connection
            log.info("Connecting to World database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "example");
                log.info("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                // Error message for database connection
                log.log(Level.WARNING, String.format("%s %d","Failed to connect to database attempt",i),sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                // Error message for thread interruption
                log.log(Level.WARNING,"Thread interrupted? Should not happen.",ie);
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
                log.log(Level.SEVERE,"Error closing connection to database",e);
            }
        }
    }

    /**
     * The following function produces countries information report filtered by different criteria including top number.
     * @param choice,name,limit The list of countries to print
     */
    public ArrayList<Country> countries(int choice, String name, int limit) {
        try {

            PreparedStatement pre_stmt = null;
            ResultSet results = null;
            // The following is a query to retrieve all the countries in the world
            String query = null;
            try {
                switch (choice) {
                    case 1:
                        // Get country information by population
                        query = "SELECT * FROM country ORDER BY Population DESC ";
                        pre_stmt=con.prepareStatement(query);
                        results = pre_stmt.executeQuery();
                        break;
                    case 2:
                        // Get country information with specific continent by population
                        query = "SELECT * FROM country WHERE Continent = (?) ORDER BY Population DESC ";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        results = pre_stmt.executeQuery();
                        break;
                    case 3:
                        // Get country information with specific region by population
                        query = "SELECT * FROM country WHERE Region=(?) ORDER BY Population DESC";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        results = pre_stmt.executeQuery();;
                        break;
                    case 4:
                        // Get country information by population
                        query = "SELECT * FROM country ORDER BY Population DESC LIMIT ? ";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setInt(1,limit);
                        results = pre_stmt.executeQuery();
                        break;
                    case 5:
                        // Get country information with specific continent by population
                        query = "SELECT * FROM country WHERE Continent = (?) ORDER BY Population DESC LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        pre_stmt.setInt(2,limit);
                        results = pre_stmt.executeQuery();;
                        break;
                    case 6:
                        // Get country information with specific region by population
                        query = "SELECT * FROM country WHERE Region = (?) ORDER BY Population DESC LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        pre_stmt.setInt(2,limit);
                        results = pre_stmt.executeQuery();;
                        break;
                    default:
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }
            ArrayList<Country> countries = new ArrayList<Country>();
            // The following block pushes the retrieved data into the array list
            while(results.next()) {
                Country country = new Country();
                country.setCode(results.getString("Code"));
                country.setName(results.getString("Name"));
                country.setContinent(results.getString("Continent"));
                country.setRegion(results.getString("Region"));
                country.setPopulation(results.getLong("Population"));
                country.setCapital(results.getString("Capital")) ;
                countries.add(country);
            }
            pre_stmt.close();
            results.close();
            return countries;
        } catch (Exception e) {
            // Error message
            log.log(Level.WARNING,"Failed to get countries details",e.getMessage());
            return null;
        }
    }

    /**
     * The following function produces cities information report filtered by different criteria including top number.
     * @param choice,name,number The list of cities to print
     */
    public ArrayList<City> getCitiesInWorldByPopulation(int choice, String name, int number)  {
        try {

            // The following is a query to retrieve all the countries in the world
            String query = null;
            PreparedStatement pre_stmt = null;
            ResultSet results = null;
            try {
                switch (choice) {
                    case 1:
                        // Get city information by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`Population` DESC ";
                        pre_stmt=con.prepareStatement(query);
                        results = pre_stmt.executeQuery();
                        ;
                        break;
                    case 2:
                        // Get city information in the district by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Continent` = (?) ORDER BY `city`.`Population` DESC ";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        results = pre_stmt.executeQuery();
                        break;
                    case 3:
                        // Get city information in the continent by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Region` = (?) ORDER BY  `city`.`Population` DESC ";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        results = pre_stmt.executeQuery();
                        break;
                    case 4:
                        // Get city information in the region by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Name` = (?) ORDER BY `city`.`Population` DESC ";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        results = pre_stmt.executeQuery();
                        break;
                    case 5:
                        // Get city information in the country by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` WHERE `city`.`District` = (?) ORDER BY `city`.`Population` DESC ";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        results = pre_stmt.executeQuery();
                        break;
                    case 6:
                        // Get top N number city information by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` ORDER BY `city`.`Population` DESC LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setInt(1,number);
                        results = pre_stmt.executeQuery();
                        ;
                        break;
                    case 7:
                        // Get top N number city information in the continent by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Continent` = (?) ORDER BY  `city`.`Population` DESC  LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        pre_stmt.setInt(2,number);
                        results = pre_stmt.executeQuery();
                        break;
                    case 8:
                        // Get top N number city information in the region by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Region` = (?) ORDER BY `city`.`Population` DESC  LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        pre_stmt.setInt(2,number);
                        results = pre_stmt.executeQuery();
                        break;
                    case 9:
                        // Get top N number city information in the country by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Name` = (?) ORDER BY `city`.`Population` DESC  LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        pre_stmt.setInt(2,number);
                        results = pre_stmt.executeQuery();
                        break;
                    case 10:
                        // Get top N number city information in the district by population
                        query = "SELECT `city`.*, `country`.`Name`, `country`.`Continent`, `country`.`Region` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` WHERE `city`.`District` = (?) ORDER BY `city`.`Population` DESC  LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        pre_stmt.setInt(2,number);
                        results = pre_stmt.executeQuery();
                        break;
                    default:
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                        break;
                }
            }
            catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }
            ArrayList<City> arr_c_world = new ArrayList<City>();
            while(results.next()) {
                City cities = new City();
                cities.setName(results.getString("city.Name"));
                cities.setCountryCode(results.getString("city.CountryCode"));
                cities.setDistrict(results.getString("city.District"));
                cities.setCountryName(results.getString("country.Name"));
                cities.setCountryContinent(results.getString("country.Continent"));
                cities.setRegion(results.getString("country.Region"));
                cities.setPopulation(results.getLong("city.Population"));
                arr_c_world.add(cities);
            }
            pre_stmt.close();
            results.close();
            return arr_c_world;
        }
        catch (Exception e) {
            // Error message
            log.log(Level.WARNING,"Failed to get cities details",e.getMessage());
            return null;
        }

    }

    /**
     * The following function produces capitals information report filtered by different criteria
     * @param choice,name,limit The list of capitals to print
     */
    public ArrayList<City> capitals(int choice, String name, int limit) {
        try {

            // The following is a query to retrieve all the countries in the
            String query = null;
            PreparedStatement pre_stmt = null;
            ResultSet results = null;
            try {
                switch (choice) {
                    case 1:
                        // Get capital information in the world by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE city.ID = country.Capital ORDER BY city.Population DESC";
                        pre_stmt=con.prepareStatement(query);
                        results = pre_stmt.executeQuery();
                        break;
                    case 2:
                        // Get capital information in the continent by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE country.Continent= (?) AND city.ID=country.Capital ORDER BY city.Population DESC";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        results = pre_stmt.executeQuery();
                        break;
                    case 3:
                        // Get capital information in the region by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE country.Region = (?) AND city.ID=country.Capital ORDER BY city.Population DESC";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        results = pre_stmt.executeQuery();
                        break;
                    case 4:
                        // Get the top N numbers capital information in the world by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE city.ID=country.Capital ORDER BY city.Population DESC LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setInt(1,limit);
                        results = pre_stmt.executeQuery();
                        break;
                    case 5:
                        // Get the top N numbers capital information in the continent by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region FROM city, country WHERE country.Continent= (?) AND city.ID=country.Capital ORDER BY city.Population DESC LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        pre_stmt.setInt(2,limit);
                        results = pre_stmt.executeQuery();
                        break;
                    case 6:
                        // Get the top N numbers capital information in the region by population
                        query = "SELECT city.Name, country.Name, city.Population, country.Continent, country.Region  FROM city, country WHERE country.Region= (?) AND city.ID=country.Capital ORDER BY city.Population DESC LIMIT ?";
                        pre_stmt=con.prepareStatement(query);
                        pre_stmt.setString(1,name);
                        pre_stmt.setInt(2,limit);
                        results = pre_stmt.executeQuery();
                        break;
                    default:
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }
            ArrayList<City> capitals = new ArrayList<City>();
            // The following block pushes the retrieved data into the array list
            while(results.next()) {
                City capital = new City();
                capital.setName(results.getString("city.Name"));
                capital.setCountryName(results.getString("country.Name"));
                capital.setPopulation(results.getLong("city.Population"));
                capital.setContinent(results.getString("country.Continent"));
                capital.setRegion(results.getString("country.Region"));
                capitals.add(capital);
            }
            pre_stmt.close();
            results.close();
            return capitals;
        } catch (Exception e) {

            // Error message
            log.log(Level.SEVERE,"Failed to get capitals details",e.getMessage());
            return null;
        }
    }

    /**
     * The following function produces population who live in cities or not information report filtered by different criteria
     * @param choice The list of population to print
     */
    public ArrayList<Dictionary> populationLON(int choice) {
        try {

            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query_city_ppl = null;
            String query_ext_ppl = null;
            String criteria = null;
            try {
                switch (choice) {
                    case 1:
                        // Get city population information in the each continent
                        criteria = "country.Continent";
                        query_city_ppl = "SELECT `country`.`Continent`, SUM(`city`.`Population`) AS `TtlCityPopulation` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` GROUP BY `country`.`Continent`  ORDER BY `country`.`Continent` ASC ";
                        query_ext_ppl = "SELECT `country`.`Continent`, SUM(`country`.`Population`) AS `TtlExistingPopulation` FROM `country` GROUP BY `country`.`Continent`  ORDER BY `country`.`Continent` ASC ";
                        break;
                    case 2:
                        // Get city population information in the each region
                        criteria = "country.Region";
                        query_city_ppl = "SELECT `country`.`Region`, SUM(`city`.`Population`) AS `TtlCityPopulation` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` GROUP BY `country`.`Region` ORDER BY `country`.`Region` ASC ";
                        query_ext_ppl = "SELECT `country`.`Region`, SUM(`country`.`Population`) AS `TtlExistingPopulation` FROM `country` GROUP BY `country`.`Region` ORDER BY `country`.`Region` ASC";
                        break;
                    case 3:
                        // Get city population information in the each country
                        criteria = "country.Name";
                        query_city_ppl = "SELECT `country`.`Name`, SUM(`city`.`Population`) AS `TtlCityPopulation` FROM `city` LEFT JOIN `country` ON `city`.`CountryCode` = `country`.`Code` GROUP BY `country`.`Name` ORDER BY `country`.`Name` ASC ";
                        query_ext_ppl = "SELECT `country`.`Name`, SUM(`country`.`Population`) AS `TtlExistingPopulation` FROM `country` GROUP BY `country`.`Name` ORDER BY `country`.`Name` ASC";
                        break;
                    default:
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }
            ArrayList<Dictionary> arr_population = new ArrayList<>();
            ResultSet rst_ext_ppl = statement.executeQuery(query_ext_ppl);

            // The following block pushes the retrieved data into the array list
            while(rst_ext_ppl.next()) {
                Dictionary temp = new Hashtable();
                temp.put("TtlExistingPopulation",rst_ext_ppl.getLong("TtlExistingPopulation"));
                temp.put("Criteria",rst_ext_ppl.getString(criteria));
                temp.put("TtlCityPopulation",0L);
                arr_population.add(temp);
            }

            ResultSet results_city_ppl = statement.executeQuery(query_city_ppl);
            // The following block pushes the retrieved data into the array list
            while(results_city_ppl.next()) {
                addInfo: {
                    for (Dictionary arr_temp:arr_population) {
                        if (arr_temp.get("Criteria").toString().equals(results_city_ppl.getString(criteria))) {

                            arr_temp.put("TtlCityPopulation",results_city_ppl.getLong("TtlCityPopulation"));
                            Long temp_ttl_city = results_city_ppl.getLong("TtlCityPopulation");
                            Long temp_ttl_ext = (Long) arr_temp.get("TtlExistingPopulation");
                            // Convert double to show two decimal place
                            double temp = temp_ttl_city*100;
                            String temp_per = String.format("%.2f", temp/temp_ttl_ext) + " %";
                            arr_temp.put("TtlCityPopulation (%)",temp_per);
                            Long temp_ttl_not = temp_ttl_ext - temp_ttl_city;
                            temp = temp_ttl_not*100;
                            temp_per = String.format("%.2f", temp/temp_ttl_ext) + " %";
                            arr_temp.put("TtlNotLivingPopulation",temp_ttl_not);
                            arr_temp.put("TtlNotLivingPopulation (%)",temp_per);
                            break addInfo;
                        }
                    }
                }
            }
            statement.close();
            rst_ext_ppl.close();
            results_city_ppl.close();
            return arr_population;
        } catch (Exception e) {
            // Error message
            log.log(Level.WARNING,"Failed to get population details",e.getMessage());
            return null;
        }
    }

    /**
     * The following function produces population who live in cities or not information report filtered by different criteria
     * @param choice The list of population to print
     */
    public ArrayList<Dictionary> population(int choice) {
        try {

            // The following is a query to retrieve all the countries in the
            String query = null;
            PreparedStatement pre_stmt;
            ResultSet results;
            String criteria = "";
            try {
                switch (choice) {
                    case 1:
                        // Get population information in the world
                        query = "SELECT SUM(`country`.`Population`) AS `Ttl_Population` FROM `country` ";
                        break;
                    case 2:
                        // Get population information in the world by continent
                        criteria = "country.Continent";
                        query = "SELECT `country`.`Continent`, SUM(`country`.`Population`) AS `Ttl_Population` FROM `country` GROUP BY `country`.`Continent` ";
                        break;
                    case 3:
                        // Get population information in the world by region
                        criteria = "country.Region";
                        query = "SELECT `country`.`Region`, SUM(`country`.`Population`) AS `Ttl_Population` FROM `country` GROUP BY `country`.`Region` ";
                        break;
                    case 4:
                        // Get population information in the world by country
                        criteria = "country.Name";
                        query = "SELECT `country`.`Name`, `country`.`Population` AS `Ttl_Population` FROM `country` ";
                        break;
                    case 5:
                        // Get the top N numbers capital information in the world by population
                        criteria = "city.District";
                        query = "SELECT `city`.`District`, SUM(`city`.`Population`) AS `Ttl_Population` FROM `city` GROUP BY `city`.`District` ";
                        break;
                    case 6:
                        // Get the top N numbers capital information in the continent by population
                        criteria = "city.Name";
                        query = "SELECT `city`.`Name`, `city`.`Population` AS `Ttl_Population` FROM `city` ";
                        break;
                    default:
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }
            pre_stmt=con.prepareStatement(query);
            results = pre_stmt.executeQuery();
            ArrayList<Dictionary> arr_population = new ArrayList<>();
            // The following block pushes the retrieved data into the array list
            while(results.next()) {
                Dictionary temp = new Hashtable();
                temp.put("TtlPopulation",results.getLong("Ttl_Population"));
                if (criteria.equals(""))
                {
                    temp.put("Name","World");
                }
                else
                {
                    temp.put("Name",results.getString(criteria));
                }
                arr_population.add(temp);
            }
            pre_stmt.close();
            results.close();
            return arr_population;
        } catch (Exception e) {

            // Error message
            log.log(Level.SEVERE,e.getMessage());
            return null;
        }
    }

    /**
     * The following function produces population who speak different language report filtered by different criteria
     */
    public ArrayList<CountryLanguage> language()
    {
        try {
            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query="SELECT `countrylanguage`.`Language`, SUM( `country`.`Population` *(`countrylanguage`.`Percentage` / 100) ) AS `NoOfSpeakers`, ( CONCAT(ROUND (( SUM( `country`.`Population` *(`countrylanguage`.`Percentage` / 100) ) / SUM(`country`.`Population`) ) * 100,2),' %') ) AS `Percentage` FROM `countrylanguage` LEFT JOIN `country` ON `countrylanguage`.`CountryCode` = `country`.`Code` WHERE `countrylanguage`.`Language` = 'Arabic' OR `countrylanguage`.`Language` = 'Chinese' OR `countrylanguage`.`Language` = 'English' OR `countrylanguage`.`Language` = 'Hindi' OR `countrylanguage`.`Language` = 'Spanish' GROUP BY `countrylanguage`.`Language` ORDER BY `NoOfSpeakers` DESC ";
            ArrayList<CountryLanguage> arr_lang = new ArrayList<CountryLanguage>();
            ResultSet results_lang = statement.executeQuery(query);

            // The following block pushes the retrieved data into the array list
            while(results_lang.next()) {
                CountryLanguage lang = new CountryLanguage();
                lang.setLanguage(results_lang.getString("countrylanguage.Language"));
                lang.setNoOfSpeakers(results_lang.getLong("NoOfSpeakers"));
                lang.setPercentage(results_lang.getString("Percentage"));
                arr_lang.add(lang);
            }
            statement.close();
            return arr_lang;
        } catch (Exception e) {
            // Error message
            log.log(Level.WARNING,"Failed to get language details",e.getMessage());
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
            log.info("No countries");
        }
        else
        {
            // Print header
            System.out.println(String.format("%5s %-10s %-20s %-20s %-35s %-20s %-20s","###", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            // Loop over all countries in the list
            int index =1;
            for (Country country : countries)
            {
                String data_string =
                        String.format("%5d %-10s %-20s %-20s %-35s %-20s %-20s",index,
                                country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());
                System.out.println(data_string);
                index++;
            }
        }
    }

    /**
     * Prints a list of cities.
     * @param Cities The list of capitals to print
     */
    public void viewCities (ArrayList<City> Cities) {
        if (Cities == null)
        {
            log.info("No cities");
        }
        else {
            System.out.println(String.format("%5s %-35s %-10s %-20s %-11s %-50s %-20s %-26s","###", "City Name", "Country Code", "District", "Population", "Country Name", "Country Continent", "Country Region"));
            // Loop over all countries in the list
            int index = 1;
            for (City city : Cities)
            {
                String data_string =
                        String.format("%5d %-35s %-10s %-20s %-11s %-50s %-20s %-26s", index,
                                city.getName(), city.getCountryCode(), city.getDistrict(),city.getPopulation(),city.getCountryName(),city.getCountryContinent(),city.getRegion());
                System.out.println(data_string);
                index++;
            }
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
            log.info("No capital information");
        }
        else
        {
            // Print header
            System.out.println(String.format("%-5s %-20s %-20s %-20s %-20s %-20s", "###", "Name", "Country", "Region", "Continent", "Population"));
            // Loop over all countries in the list
            int index = 1;
            for (City capital : capitals)
            {
                String data_string =
                        String.format("%5d %-20s %-20s %-20s %-20s %-20s", index,
                                capital.getName(), capital.getCountryName(), capital.getRegion(),capital.getContinent(),capital.getPopulation());

                System.out.println(data_string);
                index++;
            }
        }
    }

    /**
     * Prints a list of people who speak different languages.
     * @param langs The list of population to print
     */
    public void viewLanguages(ArrayList<CountryLanguage> langs) {
        if (langs == null) {
            log.info("No language information");
        } else {
            // Print header
            System.out.println(String.format("%5s %-20s %-30s %-20s","###", "Language", "Number of Speakers", "Number of Speakers (%)"));
            // Loop over all languages in the
            int index = 1;
            for (CountryLanguage lang : langs) {
                String data_string =
                        String.format("%5d %-20s %30s %20s", index,
                                lang.getLanguage(), lang.getNoOfSpeakers(),lang.getPercentage());
                System.out.println(data_string);
                index++;
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
            log.info("No population information");
        }
        else
        {
            // Print header
            System.out.println(String.format("%5s %-35s %-35s %-35s %-35s %-40s %-40s", "###","Name", "Total Existing Population",
                    "Total Living Population In Cities", "Total Not Living Population",
                    "Total Living Population In Cities (%)", "Total Not Living Population (%)"));
            // Loop over all population in the list
            int index = 1;
            for (Dictionary temp_ppl: population)
            {
                String data_string =
                        String.format("%5d %-35s %35s %35s %35s %40s %40s",index,temp_ppl.get("Criteria"),
                                temp_ppl.get("TtlExistingPopulation"), temp_ppl.get("TtlCityPopulation"),
                                temp_ppl.get("TtlNotLivingPopulation"), temp_ppl.get("TtlCityPopulation (%)"),temp_ppl.get("TtlNotLivingPopulation (%)"));
                index++;
                System.out.println(data_string);
            }
        }
    }

    /**
     * Prints a list of population
     * @param population The list of population to print
     */
    public void viewPopulation(ArrayList<Dictionary> population)
    {
        if (population == null)
        {
            log.info("No population information");
        }
        else
        {
            // Print header
            System.out.println(String.format("%5s %-35s %-35s ", "###","Name", "Total Population"));
            // Loop over all population in the list
            int index = 1;
            for (Dictionary temp_ppl: population)
            {
                String data_string =
                        String.format("%5d %-35s %35s ",index,temp_ppl.get("Name"),
                                temp_ppl.get("TtlPopulation"));
                index++;
                System.out.println(data_string);
            }
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App app = new App();

        // Connect to database
        if (args.length < 1)
        {
            app.connect("localhost:33060");
        }
        else
        {
            app.connect(args[0]);
        }
        // Countries Report Generation
//        app.printCountries(app.countries(1,null,0));
//        app.printCountries(app.countries(2,"Asia",0));
//        app.printCountries(app.countries(3,"Antarctica", 0));
//        app.printCountries(app.countries(4,null, 10));
//        app.printCountries(app.countries(5,"Asia", 10));
//        app.printCountries(app.countries(6,"Antarctica", 10));


        // Capitals Report Generation including top number
//        app.printCapitals(app.capitals(1,null,0));
//        app.printCapitals(app.capitals(2,"Asia",0));
//        app.printCapitals(app.capitals(3,"Caribbean",0));
//        app.printCapitals(app.capitals(4,null,10));
//        app.printCapitals(app.capitals(5,"Asia",10));
//        app.printCapitals(app.capitals(6,"Caribbean",10));

        // All the cities information by population including top number
    //    app.viewCities(app.getCitiesInWorldByPopulation(1, null, 0));
        //app.viewCities(app.getCitiesInWorldByPopulation(2,"Asia",0));
        //app.viewCities(app.getCitiesInWorldByPopulation(3,"Caribbean",0));
        //app.viewCities(app.getCitiesInWorldByPopulation(4,"North Korea",0));
        //app.viewCities(app.getCitiesInWorldByPopulation(5,"kabol",0));
        //app.viewCities(app.getCitiesInWorldByPopulation(6, null, 10));
        //app.viewCities(app.getCitiesInWorldByPopulation(7,"Asia",10));
        //app.viewCities(app.getCitiesInWorldByPopulation(8,"Caribbean",10));
        //app.viewCities(app.getCitiesInWorldByPopulation(9,"North Korea",10));
        //app.viewCities(app.getCitiesInWorldByPopulation(10,"kabol",10));


//        // All the population information who live in the cities nor not
//        app.viewPopulationLON(app.populationLON(1));
//        app.viewPopulationLON(app.populationLON(2));
//        app.viewPopulationLON(app.populationLON(3));
//
//        // All the population information of desired language
//        app.viewLanguages(app.language());


//        app.viewPopulation(app.population(1));
//        app.viewPopulation(app.population(2));
//        app.viewPopulation(app.population(3));
//        app.viewPopulation(app.population(4));
//        app.viewPopulation(app.population(5));
//        app.viewPopulation(app.population(6));

        String retry = "y";
        while (retry.endsWith("y"))
        {
            app.menu();
            int choice = app.getIntInput(1,6);
            app.perform(choice, app);
            System.out.print("Retry again (y/n): ");
            retry = input.nextLine();
        }

        // Disconnect from database
        app.disconnect();
    }

    // The function prompts for an integer input
    private int getIntInput(int a, int b) {
        int choice = -1;

        // variables a & b serve as limiters
        while (choice < a || choice > b) {
            try {
                System.out.print("Enter your choice of action: ");
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please try again.");
            }
        }
        return choice;
    }

    private int getLimitInput() {
        int lmt = -1;

        // variables a & b serve as limiters
        while (lmt <= 0) {
            try {
                System.out.print("Enter your limit numbers: ");
                lmt = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid limit number. Please try again.");
            }
        }
        return lmt;
    }
    // The function prompts for a string input
    private String getStringInput() {
        String criteria = null;

        while (criteria == null) {
            try {
                System.out.print("Enter input: ");
                criteria = input.nextLine();
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Invalid data input. Please try again.");
            }
        }
        return criteria;
    }


    // The function displays the main menu to the users
    private void menu() {
        System.out.println("Main Menu\n");
        System.out.println("Choose sub-menu to display\n");
        System.out.println("1) Countries report:\n");
        System.out.println("2) Cities report\n");
        System.out.println("3) Capital cities report\n");
        System.out.println("4) Living or not population report\n");
        System.out.println("5) Population report\n");
        System.out.println("6) Language report\n");
        System.out.println("7) Exit\n");
    }

    // The function displays countries sub-menu to the users
    private void countriesSubMenu() {
        System.out.println("Countries Sub-Menu\n");
        System.out.println("Choose report to generate:\n");
        System.out.println("1) All the countries in the world organised by largest population to smallest\n");
        System.out.println("2) All the countries in a continent organised by largest population to smallest\n");
        System.out.println("3) All the countries in a region organised by largest population to smallest\n");
        System.out.println("4) The top N populated countries in the world\n");
        System.out.println("5) The top N populated countries in a continent\n");
        System.out.println("6) The top N populated countries in a region\n");
    }

    // The function displays cities sub-menu to the users
    private void citiesSubMenu() {
        System.out.println("Cities Sub-Menu\n");
        System.out.println("Choose report to generate:\n");
        System.out.println("1) All the cities in the world organised by largest population to smallest\n");
        System.out.println("2) All the cities in a continent organised by largest population to smallest\n");
        System.out.println("3) All the cities in a region organised by largest population to smallest\n");
        System.out.println("4) All the cities in a country organised by largest population to smallest\n");
        System.out.println("5) All the cities in a district organised by largest population to smallest\n");
        System.out.println("6) The top N populated cities in the world\n");
        System.out.println("7) The top N populated cities in a continent\n");
        System.out.println("8) The top N populated cities in a region\n");
        System.out.println("9) The top N populated cities in a country\n");
        System.out.println("10) The top N populated cities in a district\n");
    }

    // The function displays capital cities sub-menu to the users
    private void capitalCitiesSubMenu() {
        System.out.println("Capital Cities Sub-Menu\n");
        System.out.println("Choose report to generate. *N* must be provided by the user.\n");
        System.out.println("1) All the capital cities in the world organised by largest population to smallest\n");
        System.out.println("2) All the capital cities in a continent organised by largest population to smallest\n");
        System.out.println("3) All the capital cities in a region organised by largest population to smallest\n");
        System.out.println("4) The top N populated capital cities in the world\n");
        System.out.println("5) The top N populated capital cities in a continent\n");
        System.out.println("6) The top N populated capital cities in a region\n");
    }

    // The function displays population sub-menu to the users
    private void populationLONSubMenu() {
        System.out.println("Population living or not Sub-Menu\n");
        System.out.println("1) The population of people, people living in cities, and people not living in cities in each continent\n");
        System.out.println("2) The population of people, people living in cities, and people not living in cities in each region\n");
        System.out.println("3) The population of people, people living in cities, and people not living in cities in each country\n");
    }

    // The function displays population sub-menu to the users
    private void populationSubMenu() {
        System.out.println("Population Sub-Menu\n");
        System.out.println("1) The population of the world\n");
        System.out.println("2) The population of a continent\n");
        System.out.println("3) The population of a region\n");
        System.out.println("4) The population of a country\n");
        System.out.println("5) The population of a district\n");
        System.out.println("6) The population of a city\n");
    }

    // function to execute functions and generate report
    private void perform(int coa, App a) {
        int lmt = 0;
        int choice = 0;
        String name = null;
        switch(coa) {
            case 1:
                a.countriesSubMenu();
                choice = a.getIntInput(1, 6);
                if (choice > 1)
                {
                    name = a.getStringInput();
                }
                if (choice > 3)
                {
                    lmt = a.getLimitInput();
                }
                a.printCountries(a.countries(choice, name, lmt));
                break;
            case 2:
                a.citiesSubMenu();
                choice = a.getIntInput(1, 10);
                if ((2 >= choice && choice <= 5) || (7 >= choice && choice <= 10))
                {
                    name = a.getStringInput();
                }
                if (choice > 5)
                {
                    lmt = a.getLimitInput();
                }
                a.viewCities(a.getCitiesInWorldByPopulation(choice, name, lmt));
                break;
            case 3:
                a.capitalCitiesSubMenu();
                choice = a.getIntInput(1, 6);
                if ((2 >= choice && choice <= 3) || (5 >= choice && choice <= 6))
                {
                    name = a.getStringInput();
                }
                if (choice > 3)
                {
                    lmt = a.getLimitInput();
                }
                a.printCapitals(a.capitals(choice, name, lmt));
                break;
            case 4:
                a.populationLONSubMenu();
                choice = a.getIntInput(1, 3);
                a.viewPopulationLON(a.populationLON(choice));
                break;
            case 5:
                a.populationSubMenu();
                choice = a.getIntInput(1, 6);
                a.viewPopulation(a.population(choice));
                break;
            case 6:
                a.viewLanguages(a.language());
                break;

            case 7:
                System.exit(-1);
            default:
                System.out.println("Your input is incorrect.");
                break;
        }
    }
}