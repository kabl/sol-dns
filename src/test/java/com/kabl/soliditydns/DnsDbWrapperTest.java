/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kabl.soliditydns;

import com.kabl.soliditydns.generated.DnsDB;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import static org.hamcrest.core.Is.is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DnsDbWrapperTest {

    private final static String CONTRACT_ADDR = "0xe073c28dd611812cf4b94ed369ecc6ed614978f1";
    private final static String KEY_FILE1 = "src/test/resources/keystore/UTC--2016-08-03T10-43-33.966873174Z--de1e758511a7c67e7db93d1c23c1060a21db4615";
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(50_000_000_000L);
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(2_000_000);
    private static final String RANDOM_SUFFIX = "" + new Random().nextInt(1_000_000);
    private static Credentials credentials;
    private static Parity parity;
    private static DnsDbWrapper dnsDbWrapper;

    @BeforeClass
    public static void setUpClass() throws IOException, CipherException {

        credentials = WalletUtils.loadCredentials("password", KEY_FILE1);

        parity = Parity.build(new HttpService("http://localhost:8545"));
//        parity.personalUnlockAccount(credentials.getAddress(), "password", new BigInteger("100000"));

        DnsDB dnsDB = DnsDB.load(CONTRACT_ADDR, parity, credentials, GAS_PRICE, GAS_LIMIT);
        dnsDbWrapper = new DnsDbWrapper(dnsDB);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void test_01_register() {
        String expectedName = "google.com" + RANDOM_SUFFIX;
        String expectedValue = "8.8.8.8" + RANDOM_SUFFIX;

        dnsDbWrapper.register(expectedName, expectedValue);
    }

    @Test
    public void test_02_getEntryByName() {
        String expectedName = "google.com" + RANDOM_SUFFIX;
        String expectedValue = "8.8.8.8" + RANDOM_SUFFIX;

        String actualValue = dnsDbWrapper.getEntryByName(expectedName);

        assertThat(actualValue, is(expectedValue));
    }

    @Test
    public void test_03_deleteEntryByName() {
        String expectedName = "google.com" + RANDOM_SUFFIX;
        dnsDbWrapper.deleteEntryByName(expectedName);
    }

}
