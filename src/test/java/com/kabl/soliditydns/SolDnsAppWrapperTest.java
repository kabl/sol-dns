/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kabl.soliditydns;

import com.kabl.soliditydns.generated.Cmc;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import static org.hamcrest.core.Is.is;

import com.kabl.soliditydns.generated.SolDnsApp;
import static org.hamcrest.CoreMatchers.not;
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
public class SolDnsAppWrapperTest {

    private static final String CMC_ADDR = "0x7f1153f671cb6ce0a5f88e6791f4f24145b4ac5a";
    private static final String KEY_FILE1 = "src/test/resources/keystore/UTC--2016-08-03T10-43-33.966873174Z--de1e758511a7c67e7db93d1c23c1060a21db4615";

    private static final String APP_NAME = "soldnsapp";

    private static final BigInteger GAS_PRICE = BigInteger.valueOf(50_000_000_000L);
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(2_000_000);

    private static Credentials credentials;
    private static Parity parity;
    private static SolDnsAppWrapper solDnsAppWrapper;
    private static CmcWrapper cmcWrapper;
    private static TestData testData1;

    @BeforeClass
    public static void setUpClass() throws IOException, CipherException {

        credentials = WalletUtils.loadCredentials("password", KEY_FILE1);
        testData1 = TestData.staticGenerate(credentials.getAddress());

        parity = Parity.build(new HttpService("http://localhost:8545"));
//        parity.personalUnlockAccount(credentials.getAddress(), "password", new BigInteger("100000"));

        Cmc cmc = Cmc.load(CMC_ADDR, parity, credentials, GAS_PRICE, GAS_LIMIT);
        cmcWrapper = new CmcWrapper(cmc);
        // cmcWrapper.bootstrap();
        String dnsManagerAddress = cmcWrapper.getAddress(APP_NAME);

        SolDnsApp solDnsApp = SolDnsApp.load(dnsManagerAddress, parity, credentials, GAS_PRICE, GAS_LIMIT);
        solDnsAppWrapper = new SolDnsAppWrapper(solDnsApp);
    }

    @Test
    public void test_00_getAddress() {
        String solDnsAppAddress = cmcWrapper.getAddress(APP_NAME);
        String dnsDbAddress = cmcWrapper.getAddress("dnsdb");
        String notExist = cmcWrapper.getAddress("not-exist");

        assertNotNull(solDnsAppAddress, is(not("0x0")));
        assertNotNull(dnsDbAddress, is(not("0x0")));
        assertNotNull(notExist, is("0x0"));
    }

    @Test
    public void test_01_register_and_getEntryByName() {
        solDnsAppWrapper.register(testData1.getDnsName(), testData1.getDnsEntry());

        String actualValue = solDnsAppWrapper.getEntryByName(testData1.getDnsName());
        assertThat(actualValue, is(testData1.getDnsEntry()));
    }

    @Test
    public void test_02_override_and_getEntryByName() {
        String newEntry = "9.9.9.9";

        solDnsAppWrapper.register(testData1.getDnsName(), newEntry);
        String actualValue = solDnsAppWrapper.getEntryByName(testData1.getDnsName());

        assertThat(actualValue, is(newEntry));
    }

    @Test
    public void test_03_getOwnerByName() {
        String actualOwner = solDnsAppWrapper.getOwnerByName(testData1.getDnsName());
        assertThat(actualOwner, is(testData1.getOwner()));
    }

    @Test
    public void test_04_deleteEntryByName() {
        solDnsAppWrapper.deleteEntryByName(testData1.getDnsName());

        String actualValue = solDnsAppWrapper.getEntryByName(testData1.getDnsName());
        assertThat(actualValue, is("404"));
    }
}
