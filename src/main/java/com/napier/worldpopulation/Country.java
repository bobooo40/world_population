package com.napier.worldpopulation;
// Declare public function for Country information
public class Country {

    /**
     * Declare Country's Code
     */
    private String code;

    /**
     * Declare Country's name
     */
    private String name;

    /**
     * Declare Country's Continent
     */
    private String continent;

    /**
     * Declare Country's region
     */
    private String region;

    /**
     * Declare Country's Capital
     */
    private String Capital;

    /**
     * Declare Country's population
     */
    private Long population;

    /**
     * Declare Country's Surface Area
     */
    private String SurfaceArea;

    /**
     * Declare Country's Independent Year
     */
    private int IndepYear;

    /**
     * Declare Country's LifeExpectancy
     */
    private float LifeExpectancy;

    /**
     * Declare Country's Gross National Product
     */
    private float GNP;

    /**
     * Declare Country's GNPOld
     */
    private float GNPOld;

    /**
     * Declare Country's Local Name
     */
    private String LocalName;

    /**
     * Declare Country's Government Form
     */
    private String GovernmentForm;

    /**
     * Declare Country's Head of State
     */
    private String HeadOfState;

    /**
     * Declare Country's Code2
     */

    private String Code2;

    /**
     * Declare getter and setter for all relevant attributes
     */

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        this.Capital = capital;
    }

    public String getCode2() {
        return Code2;
    }

    public void setCode2(String code2) {
        Code2 = code2;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getSurfaceArea() {
        return SurfaceArea;
    }

    public void setSurfaceArea(String surfaceArea) {
        SurfaceArea = surfaceArea;
    }

    public int getIndepYear() {
        return IndepYear;
    }

    public void setIndepYear(int indepYear) {
        IndepYear = indepYear;
    }

    public float getLifeExpectancy() {
        return LifeExpectancy;
    }

    public void setLifeExpectancy(float lifeExpectancy) {
        LifeExpectancy = lifeExpectancy;
    }

    public float getGNP() {
        return GNP;
    }

    public void setGNP(float GNP) {
        this.GNP = GNP;
    }

    public float getGNPOld() {
        return GNPOld;
    }

    public void setGNPOld(float GNPOld) {
        this.GNPOld = GNPOld;
    }

    public String getLocalName() {
        return LocalName;
    }

    public void setLocalName(String localName) {
        LocalName = localName;
    }

    public String getGovernmentForm() {
        return GovernmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        GovernmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return HeadOfState;
    }

    public void setHeadOfState(String headOfState) {
        HeadOfState = headOfState;
    }
}