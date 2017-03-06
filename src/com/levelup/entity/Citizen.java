package com.levelup.entity;

/**
 * Created by java on 10.01.2017.
 */
public class Citizen extends Entity {

    private String fistName;
    private String lastName;
    private int age;
    private Long streetId;

    public Citizen(String fistName, String lastName, int age, Long streetId) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.age = age;
        this.streetId = streetId;
    }

    public Citizen(Long id, String fistName, String lastName, int age, Long streetId) {
        super(id);
        this.fistName = fistName;
        this.lastName = lastName;
        this.age = age;
        this.streetId = streetId;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + getId() +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", streetId=" + streetId +
                '}';
    }
}
