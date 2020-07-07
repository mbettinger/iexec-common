package com.iexec.common.chain;

import com.iexec.common.contract.generated.IexecHubContract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChainAccount {

    private long deposit;//TODO Rename deposit to stake
    private long locked;

    public ChainAccount(BigInteger deposit, BigInteger locked) {
        this.setDeposit(deposit.longValue());
        this.setLocked(locked.longValue());
    }

    public static ChainAccount toAccount(IexecHubContract.Account account) {
        if (account != null) {
            return new ChainAccount(account.stake, account.locked);
        }
        return null;
    }

}
