package ServerPackag;

public class Account {

    private String userName;
    private String password;
    private int accountNumber;
    private double Balance ;

    public Account() {
    }

    public Account(String userName, String password, int accountNumber, double balance) {
        setUserName(userName);
        setPassword(password);
        setAccountNumber(accountNumber);
        setBalance(balance);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public void withdraw (double price){
        setBalance(getBalance()-price);
    }
    public void charge(double amount){
        setBalance(getBalance()+ amount);
    }

    @Override
    public String toString() {
        return  userName
                + "#"
                + password
                + "#"
                + accountNumber
                + "#"
                + Balance
                + "#";
    }
}
