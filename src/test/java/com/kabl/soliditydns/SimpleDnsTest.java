package com.kabl.soliditydns;

import com.kabl.soliditydns.generated.SimpleDnsABI;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.Future;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

public class SimpleDnsTest extends Scenario {

    private final static String CONTRACT_ADDR = "0x53498400e7917ae6cb2b2ae5167ae8963156fc6a";
    private SimpleDnsABI simpleDnsABI;

    //https://github.com/web3j/web3j/blob/master/src/integration-test/java/org/web3j/protocol/scenarios/Scenario.java
    public SimpleDnsTest() {
        super(Parity.build(new HttpService("http://localhost:8545")));

    }

    @Before
    public void init() throws IOException, CipherException {
        String path = "src/test/resources/keystore/UTC--2016-08-03T10-43-33.966873174Z--de1e758511a7c67e7db93d1c23c1060a21db4615";
        File f = new File(path);

        System.out.println("path: " + f.getAbsolutePath());
        Credentials credentials = WalletUtils.loadCredentials("password", path);

        simpleDnsABI = SimpleDnsABI.load(CONTRACT_ADDR, super.parity, credentials, GAS_PRICE, GAS_LIMIT);
    }

    @Test
    public void testSetAndGetValue() throws Exception {
        String expected = "hi from web3j";

        Future<TransactionReceipt> future = simpleDnsABI.setValue(new Utf8String(expected));
        TransactionReceipt txr = future.get();
        BigInteger blockNumber = txr.getBlockNumber();
        System.out.println("blockNumber: " + blockNumber);

        Future<Utf8String> utf8Resp = simpleDnsABI.getValue();
        Assert.assertThat(utf8Resp.get().getValue(), is(expected));
    }

    @Test
    public void testRegisterNameAndGetByIp() throws Exception {
        String expectedName = "google.com";
        String expectedIp = "8.8.8.8";

        //simpledns.registerName("demo.ch", "1.2.3.4", {from: eth.accounts[0]});
        Future<TransactionReceipt> future = simpleDnsABI.registerName(new Utf8String(expectedName), new Utf8String((expectedIp)));
        TransactionReceipt txr = future.get();
        BigInteger blockNumber = txr.getBlockNumber();
        System.out.println("blockNumber: " + blockNumber);

        Future<Utf8String> utf8Resp = simpleDnsABI.getIpByName(new Utf8String(expectedName));
        Assert.assertThat(utf8Resp.get().getValue(), is(expectedIp));

    }
}
