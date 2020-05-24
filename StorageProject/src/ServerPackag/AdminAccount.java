package ServerPackag;

public class AdminAccount {

    private Person person;
    private Account account;

    public AdminAccount() {
    }

    public AdminAccount(Person person, Account account) {
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
}
