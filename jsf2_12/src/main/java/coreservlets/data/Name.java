package coreservlets.data;

public class Name {

    private String firstName, lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return (firstName);
    }

    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    public String getLastName() {
        return (lastName);
    }

    public void setLastName(String newLastName) {
        lastName = newLastName;
    }
}
