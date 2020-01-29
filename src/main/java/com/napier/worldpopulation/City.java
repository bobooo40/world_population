package com.napier.worldpopulation;
// Declare public function for City information
public class City {

    /**
     * Declare City's name
     */
    private String Name;

    /**
     * Declare City's continent
     */
    private String Continent;

    /**
     * Declare City's Region
     */
    private String Region;

    /**
     * Declare City's dictrict
     */
    private String District;

    /**
     * Declare City's population
     */
    private Long Population;

    /**
     * Declare Country's Code
     */
    private String CountryCode;

    /**
     * Declare Country Name
     */
    private String CountryName;

    /**
     * Declare Country's Continent
     */
    private String CountryContinent;

    /**
     * Declare Country's Region
     */
    private String CountryRegion;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public Long getPopulation() {
        return Population;
    }

    public void setPopulation(Long population) {
        Population = population;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryContinent() {
        return CountryContinent;
    }

    public void setCountryContinent(String countryContinent) {
        CountryContinent = countryContinent;
    }

    public String getCountryRegion() {
        return CountryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        CountryRegion = countryRegion;
    }
}