package com.luv2code.springmvcdemo.student;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String favoriteLanguage;
    private ArrayList<String> operatingSystems = new ArrayList<>();

    private LinkedHashMap<String, String> countryOptions;

    public Student() {
        this.countryOptions = new LinkedHashMap<>();
        this.countryOptions.put("BR", "Brazil");
        this.countryOptions.put("FR", "France");
        this.countryOptions.put("DE", "Germany");
        this.countryOptions.put("IN", "India");
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LinkedHashMap<String, String> getCountryOptions() {
        return this.countryOptions;
    }

    public String getFavoriteLanguage() {
        return this.favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public void setCountryOptions(LinkedHashMap<String, String> countryOptions) {
        this.countryOptions = countryOptions;
    }

    public ArrayList<String> getOperatingSystems() {
        return this.operatingSystems;
    }

    public void setOperatingSystems(ArrayList<String> operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

}
