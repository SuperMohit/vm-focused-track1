package strategy;

import java.util.List;

public class CheckingWithdrawal implements WithdrawPermission {
    private long minBalance;
    public CheckingWithdrawal(long initialOverdraft) {
        minBalance = initialOverdraft;
    }
    @Override
    public boolean permit(long amount, long balance, List<Integer> txnHist) {
        return balance - amount >= minBalance;
    }
}
