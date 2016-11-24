package com.kabl.soliditydns;

import org.web3j.abi.datatypes.generated.Bytes32;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public final class ContractUtil {

    public static Bytes32 toBytes32(String value) {
        byte[] rawValue = value.getBytes(StandardCharsets.UTF_8);
        byte[] finalRawValue = Arrays.copyOf(rawValue, Bytes32.MAX_BYTE_LENGTH);
        return new Bytes32(finalRawValue);
    }

    public static String toString(byte[] arr) {
        int i;
        for (i = 0; i < arr.length && arr[i] != 0; i++) {
        }
        return new String(arr, 0, i, StandardCharsets.UTF_8);
    }
}
