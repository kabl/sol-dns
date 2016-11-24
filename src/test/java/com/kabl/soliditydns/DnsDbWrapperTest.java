/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kabl.soliditydns;

import com.kabl.soliditydns.generated.Cmc;
import com.kabl.soliditydns.generated.DnsDB;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import static org.hamcrest.core.Is.is;

import com.kabl.soliditydns.generated.DnsManager;
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

//    Contract mined! address: 0x0009536d4db8ac71997bdc11bd623da7bb9189b6 transactionHash: 0xed9aaa66a785c53d6aa493f15e3a7b848a6cf35eb6a2e2fb3acd552baf468887
//Contract mined! address: 0x560a6308ef487fcb9a20dbac2724c2e746e8e870 transactionHash: 0x7aa33a5a62dc888432f36ab33acfacc63f1a0bb2662eea3c2c129d8c61a4d9a6
    private final static String CMC_ADDR = "0x222579836b42d0e45a106ae716440e210bad1c1b"; //is wrong
    private final static String KEY_FILE1 = "src/test/resources/keystore/UTC--2016-08-03T10-43-33.966873174Z--de1e758511a7c67e7db93d1c23c1060a21db4615";
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(50_000_000_000L);
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(2_000_000);
    private static final String RANDOM_SUFFIX = "" + new Random().nextInt(1_000_000);
    private static Credentials credentials;
    private static Parity parity;
    private static DnsManagerWrapper dnsManagerWrapper;
    private static CmcWrapper cmcWrapper;

    @BeforeClass
    public static void setUpClass() throws IOException, CipherException {

        credentials = WalletUtils.loadCredentials("password", KEY_FILE1);

        parity = Parity.build(new HttpService("http://localhost:8545"));
//        parity.personalUnlockAccount(credentials.getAddress(), "password", new BigInteger("100000"));

        Cmc cmc = Cmc.load(CMC_ADDR, parity, credentials, GAS_PRICE, GAS_LIMIT);
        cmcWrapper = new CmcWrapper(cmc);
//        cmcWrapper.bootstrap();
        String dnsManagerAddress = cmcWrapper.getAddress("dnsmanager");

        DnsManager dnsManager = DnsManager.load(dnsManagerAddress, parity, credentials, GAS_PRICE, GAS_LIMIT);
        dnsManagerWrapper = new DnsManagerWrapper(dnsManager);
    }

    @Test
    public void test_00_getAddress() {
        String dnsManagerAddress = cmcWrapper.getAddress("dnsmanager");
        String dnsDbAddress = cmcWrapper.getAddress("dnsdb");
        String notExist = cmcWrapper.getAddress("not-exist");

        String dnsManagerAddress2 = dnsManagerWrapper.getContract("dnsmanager");
        String dnsDbAddress2 = dnsManagerWrapper.getContract("dnsdb");
        String notExist2 = dnsManagerWrapper.getContract("not-exist");
        
        String cmc = dnsManagerWrapper.getCmcAddress();

        System.out.println("dnsManagerAddress: " + dnsManagerAddress);
        System.out.println("dnsDbAddress: " + dnsDbAddress);
        System.out.println("notExist: " + notExist);
    }

    @Test
    public void test_01_register() {
        String expectedName = "google.com" + RANDOM_SUFFIX;
        String expectedValue = "8.8.8.8" + RANDOM_SUFFIX;

        dnsManagerWrapper.register(expectedName, expectedValue);
    }

    @Test
    public void test_02_getEntryByName() {
        String expectedName = "google.com" + RANDOM_SUFFIX;
        String expectedValue = "8.8.8.8" + RANDOM_SUFFIX;

        String actualValue = dnsManagerWrapper.getEntryByName(expectedName);

        assertThat(actualValue, is(expectedValue));
    }

    @Test
    public void test_03_deleteEntryByName() {
        String expectedName = "google.com" + RANDOM_SUFFIX;
        dnsManagerWrapper.deleteEntryByName(expectedName);
    }

}
