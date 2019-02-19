package com.iexec.common.utils;

import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

import com.iexec.common.security.Signature;

public class SignatureUtils {

    private SignatureUtils() {
        throw new UnsupportedOperationException();
    }

    //Note to dev: The following block is copied/pasted from web3j 4.0-beta at this commit:
    // https://github.com/web3j/web3j/commit/4997746e566faaf9c88defad78af54ede24db65b
    // Once we update to web3j 4.0, the signPrefixedMessage method should be directly available and this block should
    // be deleted

    //!!!!!!!!!!! Beginning block web3j 4.0-beta !!!!!!!!!!!
    public static final String MESSAGE_PREFIX = "\u0019Ethereum Signed Message:\n";

    public static byte[] getEthereumMessagePrefix(int messageLength) {
        return MESSAGE_PREFIX.concat(String.valueOf(messageLength)).getBytes();
    }

    public static byte[] getEthereumMessageHash(byte[] message) {
        byte[] prefix = getEthereumMessagePrefix(message.length);
        byte[] result = new byte[prefix.length + message.length];
        System.arraycopy(prefix, 0, result, 0, prefix.length);
        System.arraycopy(message, 0, result, prefix.length, message.length);
        return Hash.sha3(result);
    }

    public static Sign.SignatureData signPrefixedMessage(byte[] message, ECKeyPair ecKeyPair) {
        return Sign.signMessage(getEthereumMessageHash(message), ecKeyPair, false);
    }
    // !!!!!!!!!!! End block web3j 4.0-beta !!!!!!!!!!!

    public static boolean doesSignatureMatchesAddress(byte[] signatureR,
                                                      byte[] signatureS,
                                                      String hashToCheck,
                                                      String signerAddress){
        // check that the public address of the signer can be found
        for (int i = 0; i < 4; i++) {
            BigInteger publicKey = Sign.recoverFromSignature((byte) i,
                    new ECDSASignature(
                            new BigInteger(1, signatureR),
                            new BigInteger(1, signatureS)),
                    BytesUtils.stringToBytes(hashToCheck));

            if (publicKey != null) {
                String addressRecovered = "0x" + Keys.getAddress(publicKey);

                if (addressRecovered.equals(signerAddress)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Signature hashAndSign(String stringToSign, String walletAddress, ECKeyPair ecKeyPair) {
        byte[] message = Hash.sha3(BytesUtils.stringToBytes(stringToSign));
        Sign.SignatureData sign = Sign.signMessage(message, ecKeyPair, false);

        return Signature.builder()
                .walletAddress(walletAddress)
                .signR(sign.getR())
                .signS(sign.getS())
                .signV(sign.getV())
                .build();
    }

    public static String hashAndSignAsString(String stringToSign, ECKeyPair ecKeyPair) {
        byte[] message = Hash.sha3(BytesUtils.stringToBytes(stringToSign));
        Sign.SignatureData sign = Sign.signMessage(message, ecKeyPair, false);
        return createStringFromSignature(sign);
    }

    public static String signAsString(String stringToSign, ECKeyPair ecKeyPair) {
        byte[] message = Numeric.hexStringToByteArray(stringToSign);
        Sign.SignatureData sign = Sign.signMessage(message, ecKeyPair, false);
        return createStringFromSignature(sign);
    }

    private static String createStringFromSignature(Sign.SignatureData sign) {
        String r = Numeric.toHexString(sign.getR());
        String s = Numeric.toHexString(sign.getS());
        String v = Integer.toHexString(sign.getV());
        return String.join("", r, Numeric.cleanHexPrefix(s), v);
    }

}
