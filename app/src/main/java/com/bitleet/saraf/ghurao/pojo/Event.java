package com.bitleet.saraf.ghurao.pojo;


import java.util.ArrayList;
import java.util.List;

public class Event {
    private String id;
    private String CreatedDate;
    private String Name;
    private String startingLocation;
    private String destinationLocation;
    private String departuerDate;
    private double budget;
    private List<Expense> expenseList = new ArrayList<>();
    private List<Moments> momentsList = new ArrayList<>();


    public Event(String id, String createdDate, String Name, String startingLocation, String destinationLocation, String departuerDate, double budget) {
        this.id = id;
        CreatedDate = createdDate;
        this.Name = Name;
        this.startingLocation = startingLocation;
        this.destinationLocation = destinationLocation;
        this.departuerDate = departuerDate;
        this.budget = budget;
    }

    public Event(String id, String createdDate, String Name, String startingLocation, String destinationLocation, String departuerDate, double budget, List<Expense> expenseList, List<Moments> momentsList) {
        this.id = id;
        CreatedDate = createdDate;
        this.Name = Name;
        this.startingLocation = startingLocation;
        this.destinationLocation = destinationLocation;
        this.departuerDate = departuerDate;
        this.budget = budget;
        this.expenseList = expenseList;
        this.momentsList = momentsList;
    }

    public Event(String s, String s1, String test, int i) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getDepartuerDate() {
        return departuerDate;
    }

    public void setDepartuerDate(String departuerDate) {
        this.departuerDate = departuerDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<Moments> getMomentsList() {
        return momentsList;
    }

    public void setMomentsList(List<Moments> momentsList) {
        this.momentsList = momentsList;
    }
}