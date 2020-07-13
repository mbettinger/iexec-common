package io.iexec.common.tee;

import io.iexec.common.security.Signature;
import io.iexec.common.utils.HashUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeeEnclaveChallengeSignature {

    private String resultDigest;
    private String resultHash;
    private String resultSeal;
    private Signature signature;

    public static String getMessageHash(String resultHash, String resultSeal) {
        return HashUtils.concatenateAndHash(resultHash, resultSeal);
    }
}