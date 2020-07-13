package io.iexec.common.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BytesUtilsTests {

    private String hexaString = "0x9e916a7d68e0ed8714fde137ed60de0e586e75467ae6ca0b090950f772ca9ac8";
    private byte[] bytes = new byte[]{-98, -111, 106, 125, 104, -32, -19, -121, 20, -3, -31, 55, -19, 96, -34, 14, 88, 110,
            117, 70, 122, -26, -54, 11, 9, 9, 80, -9, 114, -54, -102, -56};

    @Test
    public void shouldBeValid() {
        Assert.assertEquals(hexaString, BytesUtils.bytesToString(bytes));
        Assert.assertArrayEquals(BytesUtils.stringToBytes(hexaString), bytes);

        Assert.assertEquals(BytesUtils.bytesToString(BytesUtils.stringToBytes(hexaString)), hexaString);
        Assert.assertArrayEquals(BytesUtils.stringToBytes(BytesUtils.bytesToString(bytes)), bytes);
    }

    @Test
    public void shoudBeHexaStringWithPrefix() {
        assertTrue(BytesUtils.isHexaString("0xabc123"));
    }

    @Test
    public void shoudBeHexaStringWithoutPrefix() {
        assertTrue(BytesUtils.isHexaString("abc123"));
    }

    @Test
    public void shoudNotBeHexaStringSinceNotHexa() {
        assertFalse(BytesUtils.isHexaString("0xabc123defg"));
    }

    @Test
    public void shoudNotBeHexaStringSinceEmptyWithPrefix() {
        assertFalse(BytesUtils.isHexaString("0x"));
    }

    @Test
    public void shoudNotBeHexaStringSinceEmpty() {
        assertFalse(BytesUtils.isHexaString(""));
    }

    @Test
    public void shouldBeABytes32() {
        assertTrue(BytesUtils.isBytes32(bytes));
        assertTrue(BytesUtils.isBytes32(BytesUtils.stringToBytes(hexaString)));
    }

    @Test
    public void shouldNotBeAByte32() {
        assertFalse(BytesUtils.isBytes32(BytesUtils.stringToBytes("0xabc123defg")));
    }

    @Test
    public void shouldNotBeAByte32Null() {
        assertFalse(BytesUtils.isBytes32(new byte[0]));
        assertFalse(BytesUtils.isBytes32(new byte[128]));
    }

    @Test
    public void shouldReturnSameStringSinceAlreadyBytes32() {
        byte[] bytes32 = BytesUtils.stringToBytes32(hexaString);
        assertEquals(bytes32.length, 32);
        assertArrayEquals(bytes32, bytes);
        Assert.assertEquals(BytesUtils.bytesToString(bytes32), hexaString);
    }

    @Test
    public void shouldPadStringToBeBytes32() {
        String notBytes32String = "0xabc";
        String bytes32String = "0xabc0000000000000000000000000000000000000000000000000000000000000";

        byte[] returnedBytes = BytesUtils.stringToBytes32(notBytes32String);

        assertEquals(returnedBytes.length, 32);
        Assert.assertEquals(BytesUtils.bytesToString(returnedBytes), bytes32String);
    }
}
