package ClientPackage;

public class UserAccount {

    private Person person;
    private Account account;

    public UserAccount() {
    }

    public UserAccount(Person person, Account account) {
        setPerson(person);
        setAccount(account);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return person + "#" + account;
    }
}
