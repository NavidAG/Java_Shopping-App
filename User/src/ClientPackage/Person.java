package ClientPackage;

public class Person {

    private String firstName;
    private String lastName;
    private int nationalNumber;

    public Person() {
    }

    public Person(String firstName, String lastName, int nationalNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setNationalNumber(nationalNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(int nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    @Override
    public String toString() {
        return  firstName
                + "#"
                + lastName
                +"#"
                + nationalNumber;
    }
}
