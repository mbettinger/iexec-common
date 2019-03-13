package com.iexec.common.security;

import com.iexec.common.utils.BytesUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.util.Arrays;
import org.web3j.crypto.Sign;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Signature {

    // this value is the hexadecimal string of the signature
    private String value;

    public Signature(byte[] sign) {
        this.value = BytesUtils.bytesToString(sign);
    }

    public Signature(byte[] r, byte[] s, byte v) {
        this(Arrays.concatenate(r, s, new byte[]{v}));
    }

    public Signature(Sign.SignatureData sign) {
        this(sign.getR(), sign.getS(), sign.getV());
    }

    public byte[] getR(){
        return Arrays.copyOfRange(BytesUtils.stringToBytes(value), 0, 32);
    }

    public byte[] getS(){
        return Arrays.copyOfRange(BytesUtils.stringToBytes(value), 32, 64);
    }

    public byte getV(){
        return BytesUtils.stringToBytes(value)[64];
    }
}
