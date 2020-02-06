package com.napier.worldpopulation;
// Declare public function for Country Language information
public class CountryLanguage {

    /**
     * Declare Country's Code
     */
    private String CountryCode;

    /**
     * Declare Country's Language
     */
    private String Language;

    /**
     * Declare is official
     */
    private boolean IsOffical;

    /**
     * Declare usage percentage
     */
    private String Percentage;

    /**
     * Declare no of speakers
     */
    private Long NoOfSpeakers;

    /**
     * Declare getter and setter for all relevant attributes
     */
    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public boolean isOffical() {
        return IsOffical;
    }

    public void setOffical(boolean offical) {
        IsOffical = offical;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public Long getNoOfSpeakers() {
        return NoOfSpeakers;
    }

    public void setNoOfSpeakers(Long noOfSpeakers) {
        NoOfSpeakers = noOfSpeakers;
    }
}