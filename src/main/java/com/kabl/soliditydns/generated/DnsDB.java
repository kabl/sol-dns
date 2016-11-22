package com.kabl.soliditydns.generated;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.Contract;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.</p>
 */
public final class DnsDB extends Contract {
    private static final String BINARY = "pragma solidity ^0.4.2;\n"
            + "\n"
            + "contract DnsDB {\n"
            + "\n"
            + "     struct DnsEntry\n"
            + "     {\n"
            + "         address owner;\n"
            + "         bytes32 entry;\n"
            + "     }\n"
            + "\n"
            + "     mapping (bytes32 => DnsEntry) dnsEntriesByName;\n"
            + "     event eventDnsDB_newEntry(bytes32 dnsName, bytes32 entry);\n"
            + "\n"
            + "\n"
            + "     function register(bytes32 dnsName, bytes32 entry) returns (bool success) {\n"
            + "\n"
            + "        dnsEntriesByName[dnsName] = DnsEntry(msg.sender, entry);\n"
            + "        eventDnsDB_newEntry(dnsName, entry);\n"
            + "\n"
            + "        success = true;\n"
            + "     }\n"
            + "\n"
            + "     function deleteEntryByName(bytes32 name) returns (bool success){\n"
            + "        if(dnsEntriesByName[name].owner != address(0x0)){\n"
            + "            delete dnsEntriesByName[name].owner;\n"
            + "            delete dnsEntriesByName[name];\n"
            + "            success = true;\n"
            + "        }else{\n"
            + "            success = false;\n"
            + "        }\n"
            + "     }\n"
            + "\n"
            + "     function getEntryByName(bytes32 name) constant returns (bytes32 entry){\n"
            + "         if(dnsEntriesByName[name].owner != address(0x0))\n"
            + "             return dnsEntriesByName[name].entry;\n"
            + "         else\n"
            + "             return \"404\";\n"
            + "     }\n"
            + "}\n";

    private DnsDB(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> register(Bytes32 dnsName, Bytes32 entry) {
        Function function = new Function("register", Arrays.<Type>asList(dnsName, entry), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bytes32> getEntryByName(Bytes32 name) {
        Function function = new Function("getEntryByName", 
                Arrays.<Type>asList(name), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> deleteEntryByName(Bytes32 name) {
        Function function = new Function("deleteEntryByName", Arrays.<Type>asList(name), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public EventValues processEventDnsDB_newEntryEvent(TransactionReceipt transactionReceipt) {
        Event event = new Event("eventDnsDB_newEntry", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        return extractEventParameters(event, transactionReceipt);
    }

    public static Future<DnsDB> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(DnsDB.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static DnsDB load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DnsDB(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }
}
