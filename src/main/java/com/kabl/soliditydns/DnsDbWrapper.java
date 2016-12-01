///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.kabl.soliditydns;
//
//import com.kabl.soliditydns.generated.DnsDB;
//import java.math.BigInteger;
//import org.web3j.abi.datatypes.generated.Bytes32;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//
//public class DnsDbWrapper {
//    
//    private static final int TX_WAIT_MAX_SEC = 120;
//
//    private final DnsDB dnsDB;
//
//    public DnsDbWrapper(DnsDB dnsDB) {
//        this.dnsDB = dnsDB;
//    }
//
//    public void register(String name, String value) {
//        try {
//            Future<TransactionReceipt> txRec = dnsDB.register(ContractUtil.toBytes32(name), ContractUtil.toBytes32(value));
//            BigInteger blockNumber = txRec.get(TX_WAIT_MAX_SEC, TimeUnit.SECONDS).getBlockNumber();
//
//            if (!value.equals(getEntryByName(name))) {
//                throw new RuntimeException("Data couldn't be written to the Blockchain");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public void deleteEntryByName(String name) {
//        try {
//            Future<TransactionReceipt> txRec = dnsDB.deleteEntryByName(ContractUtil.toBytes32(name));
//            BigInteger blockNumber = txRec.get(TX_WAIT_MAX_SEC, TimeUnit.SECONDS).getBlockNumber();
//
//            if (!"404".equals(getEntryByName(name))) {
//                throw new RuntimeException("Data couldn't be deleted from the Blockchain");
//
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String getEntryByName(String name) {
//        try {
//            Future<Bytes32> future = dnsDB.getEntryByName(ContractUtil.toBytes32(name));
//            byte[] value = future.get(10, TimeUnit.SECONDS).getValue();
//            return ContractUtil.toString(value);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
