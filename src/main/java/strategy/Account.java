package strategy;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private long balance;
    private List<Integer> history = new ArrayList<>();
    private WithdrawPermission withdrawPermission; // Strategy object 1
    private FeeCalculator feeCalc; // Strategy object 2...

    public Account(long initialBal, WithdrawPermission withdrawPermission) {
        balance = initialBal;
        this.withdrawPermission = withdrawPermission;
    }

    public void deposit(long amount) {
        balance += amount;
        history.add((int)amount); // YIKES!!! FIX THIS
    }

    public void withdraw(long amount) {
        if (withdrawPermission.permit(amount, balance, history)) {
            balance -= amount;
        } else {
            throw new RuntimeException("Not enough money!"); // BAD use of exceptions!
        }
    }

    public void grantOverdraft(WithdrawPermission wdp) {
        this.withdrawPermission = wdp;
    }
}
