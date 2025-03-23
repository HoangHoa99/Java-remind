package oop;

public class Author {
    private String name;
    private int yearOfBirth;
    private String gender;
    private String nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String displayInfo() {
        return "Author name: " + getName() + "\n"
                + " year of birth: " + getYearOfBirth() + "\n"
                + " gender: " + getGender() + "\n"
                + " nationality: " + getNationality();
    }
}
