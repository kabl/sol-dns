/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kabl.soliditydns;

import com.kabl.soliditydns.generated.SolDnsApp;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.web3j.abi.datatypes.Address;

public class SolDnsAppWrapper {

    private static final int TX_WAIT_MAX_SEC = 120;

    private final SolDnsApp solDnsApp;

    public SolDnsAppWrapper(SolDnsApp solDnsApp) {
        this.solDnsApp = solDnsApp;
    }

    public void register(String dnsName, String value) {
        try {
            Future<TransactionReceipt> txRec = solDnsApp.register(ContractUtil.toBytes32(dnsName), ContractUtil.toBytes32(value));
            BigInteger blockNumber = txRec.get(TX_WAIT_MAX_SEC, TimeUnit.SECONDS).getBlockNumber();

            if (!value.equals(getEntryByName(dnsName))) {
                throw new RuntimeException("Data couldn't be written to the Blockchain");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteEntryByName(String dnsName) {
        try {
            Future<TransactionReceipt> txRec = solDnsApp.deleteEntryByName(ContractUtil.toBytes32(dnsName));
            BigInteger blockNumber = txRec.get(TX_WAIT_MAX_SEC, TimeUnit.SECONDS).getBlockNumber();

            if (!"404".equals(getEntryByName(dnsName))) {
                throw new RuntimeException("Data couldn't be deleted from the Blockchain");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getEntryByName(String dnsName) {
        try {
            Future<Bytes32> future = solDnsApp.getEntryByName(ContractUtil.toBytes32(dnsName));
            byte[] value = future.get(10, TimeUnit.SECONDS).getValue();
            return ContractUtil.toString(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getOwnerByName(String dnsName) {
        try {
            Future<Address> future = solDnsApp.getOwnerByName(ContractUtil.toBytes32(dnsName));
            BigInteger address = future.get(10, TimeUnit.SECONDS).getValue();
            return "0x" + address.toString(16);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCmcAddress() {
        try {
            Future<Address> future = solDnsApp.getCmcAddress();
            BigInteger address = future.get(10, TimeUnit.SECONDS).getValue();
            return "0x" + address.toString(16);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getContract(String name) {
        try {
            Future<Address> future = solDnsApp.getContract(ContractUtil.toBytes32(name));
            BigInteger address = future.get(10, TimeUnit.SECONDS).getValue();
            return "0x" + address.toString(16);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
