package com.qa.project.ui.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class UserFormModel {

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    // это лучше было бы сделать через кэшированный API запрос при инициализации проекта,
    // но к сожалению доступа к API у меня нет
    public enum States {
        NCR(List.of(City.DELHI, City.GURGAON, City.NOIDA)),
        UTTAR_PRADESH(List.of(City.AGRA, City.LUCKNOW, City.MERRUT)),
        HARYANA(List.of(City.KARNAL, City.PANIPAT)),
        RAJASTHAN(List.of(City.JAIPUR, City.JAISELMER));

        private final List<City> cities;

        States(List<City> cities) {
            this.cities = cities;
        }

        public List<City> getCities() {
            return cities;
        }
    }

    public enum City {
        DELHI, GURGAON, NOIDA,
        AGRA, LUCKNOW, MERRUT,
        KARNAL, PANIPAT,
        JAIPUR, JAISELMER
    }

    public enum Hobby {
        SPORTS, READING, MUSIC
    }

    private String username;

    private String email;

    private Gender gender;

    private String mobileNumber;

    private LocalDate birthDate;

    private List<String> subjects;

    private byte[] photo;

    private String currentAddress;

    private States state;

    private City city;

    private Set<Hobby> hobbies;

    public void setCity(City city) {
        if (city != null && state != null && !state.getCities().contains(city)) {
            throw new IllegalArgumentException("City not in state");
        }
        this.city = city;
    }

    public void setMobileNumber(String mobileNumber) {
        if (mobileNumber != null && mobileNumber.length() != 10) {
            throw new IllegalArgumentException("Mobile number not in 10 digits");
        }
        this.mobileNumber = mobileNumber;
    }
    public void setEmail(String email) {
        if (email != null && email.matches("^(?i)[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }





}
