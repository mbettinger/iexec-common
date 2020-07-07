package com.iexec.common.chain;

import com.iexec.common.contract.generated.IexecHubContract;
import com.iexec.common.utils.BytesUtils;
import lombok.*;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ChainContribution {

    private ChainContributionStatus status;
    private String resultHash;
    private String resultSeal;
    private String enclaveChallenge;

    public ChainContribution(BigInteger status, byte[] resultHash, byte[] resultSeal, String enclaveChallenge) {
        this.setStatus(status);
        this.setResultHash(resultHash);
        this.setResultSeal(resultSeal);
        this.setEnclaveChallenge(enclaveChallenge);
    }

    public static ChainContribution toContribution(IexecHubContract.Contribution contribution) {
        if (contribution != null) {
            return new ChainContribution(contribution.status,
                    contribution.resultHash,
                    contribution.resultSeal,
                    contribution.enclaveChallenge);
        }
        return null;
    }

    public void setStatus(BigInteger status) {
        this.status = ChainContributionStatus.getValue(status);
    }

    public void setResultHash(byte[] resultHash) {
        this.resultHash = BytesUtils.bytesToString(resultHash);
    }

    public void setResultSeal(byte[] resultSeal) {
        this.resultSeal = BytesUtils.bytesToString(resultSeal);
    }

    public void setStatus(ChainContributionStatus status) {
        this.status = status;
    }

    public void setResultHash(String resultHash) {
        this.resultHash = resultHash;
    }

    public void setResultSeal(String resultSeal) {
        this.resultSeal = resultSeal;
    }

    public void setEnclaveChallenge(String enclaveChallenge) {
        this.enclaveChallenge = enclaveChallenge;
    }

}
