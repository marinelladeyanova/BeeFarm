package com.storage.model.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Event {
    private int id;
    private String name;
    private LocalDate date;
    private String status;
    private String details;
    private String locationName;
    private ContactInfo contactInfo;
    private ArrayList<String> tags;


    public Event() {
        this.tags = new ArrayList<>();
    }

    public Event(int id) {
        this.id = id;
        this.tags = new ArrayList<>();
    }

    public Event(int id, String name, LocalDate date, String status, String details, String locationName) {
        this(id);
        this.name = name;
        this.date = date;
        this.status = status;
        this.details = details;
        this.locationName = locationName;

    }

    public Event(int id, String name, LocalDate date, String status, String details, String locationName, ContactInfo contactInfo, String tag) {
        this(id, name, date, status, details, locationName);
        this.contactInfo = contactInfo;
        this.tags.add(tag);
    }


    /*
     *
     * getters and setters
     *
     * */

    public void addTag(String tag) {
        tags.add(tag);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        if (details != null)
            return details;
        return "";
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocationName() {
        if (locationName != null)
            return locationName;
        return "";
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
