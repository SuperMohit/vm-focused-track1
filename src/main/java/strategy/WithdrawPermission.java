package strategy;

import java.util.List;

public interface WithdrawPermission {
    boolean permit(long amount, long balance, List<Integer> history);
}
