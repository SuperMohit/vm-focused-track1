package strategy;

public class BankSimulation {
    public static void main(String[] args) {
        Account ac = new Account(1000, new CheckingWithdrawal(0));

        ac.withdraw(500);
        System.out.println("Succeeded");
        try {
            ac.withdraw(600);
            System.out.println("Succeeded");
        } catch (RuntimeException re) {
            System.out.println("Failed: " + re.getMessage());
        }
        // ask for overdraft...
        ac.grantOverdraft(new CheckingWithdrawal(-1000));
        ac.withdraw(600);
        System.out.println("Succeeded");

    }
}
