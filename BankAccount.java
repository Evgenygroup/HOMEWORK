
public class BankAccount {
    String IBANN;
    Person1 owner;

    public BankAccount(String IBANN, Person1 owner) {
        this.IBANN = IBANN;
        this.owner = owner;
    }

    public String getIBANN() {
        return IBANN;
    }

    public Person1 getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return IBANN
                ;
    }
}
