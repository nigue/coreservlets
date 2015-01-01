package coreservlets.model;

/**
 * Bean to represent an account at the book site.
 */
public class Account {

    private final String id, firstName, lastName;
    private double balance;

    public Account(String id,
            String firstName,
            String lastName,
            double balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return (firstName);
    }

    public String getLastName() {
        return (lastName);
    }

    /**
     * Returns full name in American style: given name, space, family name.
     */
    public String getFullName() {
        return (firstName + " " + lastName);
    }

    public double getBalance() {
        return (balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns the balance as a String, formatted with two decimal places and
     * commas between thousands, millions, etc.
     */
    public String getBalanceDollars() {
        return (String.format("$%,.2f", balance));
    }
}
