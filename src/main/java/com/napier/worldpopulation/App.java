package com.napier.worldpopulation;
//import required packages

import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
//    Connection to MySQL database
    private Connection con = null;
    static Logger log = Logger.getLogger(App.class.getName());

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
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                }
            } catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }

            ResultSet results = statement.executeQuery(query);
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
            return countries;
        } catch (Exception e) {
            // Error message
            log.log(Level.WARNING,"Failed to get countries details",e.getMessage());
            return null;
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
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                }
            } catch (NumberFormatException e) {

                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }
            return getCities(statement, query);
        }
        catch (Exception e) {
            // Error message
            log.log(Level.WARNING,"Failed to get cities details",e.getMessage());
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
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                }
            } catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }
            return getCities(statement, query);
        }
        catch (Exception e) {
            // Error message
            log.log(Level.WARNING,"Failed to get cities details",e.getMessage());
            return null;
        }
    }

    // Generate the array list from the chosen query
    public ArrayList<City> getCities(Statement statement, String query) throws SQLException {
        ResultSet results = statement.executeQuery(query);
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
        return arr_c_world;
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
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                }
            } catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
            }

            ResultSet results = statement.executeQuery(query);
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
            return capitals;
        } catch (Exception e) {

            // Error message
            log.log(Level.SEVERE,"Failed to get capitals details",e.getMessage());
            return null;
        }
    }

    /// The following function produces population who live in cities or not information report filtered by different criteria

    public ArrayList<Dictionary> populationLON(int choice) {
        try {

            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query_city_ppl = null;
            String query_existiong_ppl = null;
            String criteria = null;
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
                        // Error message for invalid number selection
                        log.warning("Invalid selection. Please, try again.");
                }
            } catch (NumberFormatException e) {
                // Error message for unknown error
                log.log(Level.SEVERE,"An unknown error has occurred",e);
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
            return arr_population;
        } catch (Exception e) {
            // Error message
            log.log(Level.WARNING,"Failed to get population details",e.getMessage());
            return null;
        }
    }

    /// The following function produces population who speak different langauge report filtered by different criteria

    public ArrayList<CountryLanguage> language()
    {
        try {

            Statement statement = con.createStatement();
            // The following is a query to retrieve all the countries in the world
            String query="SELECT `countrylanguage`.`Language`, SUM(`country`.`Population`*(`countrylanguage`.`Percentage`/100)) AS `NoOfSpeakers` FROM `countrylanguage` LEFT JOIN `country` ON `countrylanguage`.`CountryCode` = `country`.`Code` WHERE `countrylanguage`.`Language`= 'Arabic' OR `countrylanguage`.`Language`='Chinese' OR `countrylanguage`.`Language`= 'English' OR `countrylanguage`.`Language`= 'Hindi' OR `countrylanguage`.`Language`= 'Spanish' GROUP BY `countrylanguage`.`Language` ORDER BY `NoOfSpeakers` DESC ";
            ArrayList<CountryLanguage> arr_lang = new ArrayList<CountryLanguage>();
            ResultSet results_lang = statement.executeQuery(query);

            // The following block pushes the retrieved data into the array list
            while(results_lang.next()) {
                CountryLanguage lang = new CountryLanguage();
                lang.setLanguage(results_lang.getString("countrylanguage.Language"));
                lang.setNoOfSpeakers(results_lang.getLong("NoOfSpeakers"));
                arr_lang.add(lang);
            }
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
            System.out.println(String.format("%5s %-35s %-5s %-20s %-11s %-50s %-20s %-26s","###", "City Name", "Country Code", "District", "Population", "Country Name", "Country Continent", "Country Region"));
            // Loop over all countries in the list
            int index = 1;
            for (City city : Cities)
            {
                String data_string =
                        String.format("%-5d %-35s %-5s %-20s %-11s %-50s %-20s %-26s", index,
                                city.getName(), city.getCountryCode(), city.getDistrict(),city.getPopulation(),city.getCountryName(),city.getCountryContinent(),city.getCountryRegion());
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
//            System.out.println("No capital information");
        }
        else
        {
            // Print header
            System.out.println(String.format("%-5s %-20s %-20s %-20s", "###", "Name", "Country", "Population"));
            // Loop over all countries in the list

            int index = 0;
            for (City capital : capitals)
            {
                String data_string =
                        String.format("%5d %-20s %-20s %-20s", index,
                                capital.getName(), capital.getCountryName(), capital.getPopulation());

                System.out.println(data_string);
                index++;
            }
        }
    }

    /**
     * Prints a list of poeple who speak different languages.
     * @param langs The list of population to print
     */
    public void viewLanguages(ArrayList<CountryLanguage> langs) {
        if (langs == null) {
            log.info("No language information");
//            System.out.println("No language information");
        } else {
            // Print header
            System.out.println(String.format("%5s %-20s %-30s","###", "Language", "Number of Speakers"));
            // Loop over all languages in the
            int index = 1;
            for (CountryLanguage lang : langs) {
                String data_string =
                        String.format("%5d %-20s %30s", index,
                                lang.getLanguage(), lang.getNoOfSpeakers());
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
//            System.out.println("No population information");
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

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:33060");
        }
        else
        {
            a.connect(args[0]);
        }
        // Countries Report Generation
//        ArrayList<Country> countries = a.countries(4);
//        a.printCountries(countries);
//
//        // Capitals Report Generation
//        ArrayList<City> capitals = a.capitals(4);
//        a.printCapitals(capitals);
//
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

        a.viewLanguages(a.language());

        // Disconnect from database
        a.disconnect();
    }

}