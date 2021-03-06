package com.kabl.soliditydns.generated;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.Contract;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
    private static final String BINARY = "pragma solidity ^0.4.4;\n"
            + "\n"
            + "import \"./CmcEnabled.sol\";\n"
            + "\n"
            + "contract DnsDB is CmcEnabled {\n"
            + "    \n"
            + "     struct DnsEntry\n"
            + "     {\n"
            + "         address owner;\n"
            + "         bytes32 entry;\n"
            + "     }\n"
            + "\n"
            + "     mapping (bytes32 => DnsEntry) dnsEntriesByName;\n"
            + "     event eventDnsDB_newEntry(bytes32 dnsName, bytes32 entry);\n"
            + "\n"
            + "     function register(bytes32 dnsName, bytes32 entry, address owner) callAllowed returns (bool _success)  {\n"
            + "\n"
            + "        dnsEntriesByName[dnsName] = DnsEntry(owner, entry);\n"
            + "        eventDnsDB_newEntry(dnsName, entry);\n"
            + "\n"
            + "        return true;\n"
            + "     }\n"
            + "\n"
            + "     function deleteEntryByName(bytes32 dnsName) callAllowed returns (bool _success) {\n"
            + "        delete dnsEntriesByName[dnsName].owner;\n"
            + "        delete dnsEntriesByName[dnsName].entry;\n"
            + "        delete dnsEntriesByName[dnsName];\n"
            + "        return true;\n"
            + "     }\n"
            + "\n"
            + "     function getEntryByName(bytes32 dnsName) callAllowed constant returns (bytes32 _entry) {\n"
            + "         if(dnsEntriesByName[dnsName].owner != address(0x0))\n"
            + "             return dnsEntriesByName[dnsName].entry;\n"
            + "         else\n"
            + "             return \"404\";\n"
            + "     }\n"
            + "\n"
            + "     function getOwnerByName(bytes32 dnsName) callAllowed constant returns (address _address){\n"
            + "         return dnsEntriesByName[dnsName].owner;\n"
            + "     }\n"
            + "}\n";

    private DnsDB(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public Future<Address> getCmcAddress() {
        Function function = new Function("getCmcAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> register(Bytes32 dnsName, Bytes32 entry, Address owner) {
        Function function = new Function("register", Arrays.<Type>asList(dnsName, entry, owner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bytes32> getSeniorContract() {
        Function function = new Function("getSeniorContract", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> getOwnerByName(Bytes32 dnsName) {
        Function function = new Function("getOwnerByName", 
                Arrays.<Type>asList(dnsName), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> init(Address _cmcAddress, Bytes32 _seniorContractName) {
        Function function = new Function("init", Arrays.<Type>asList(_cmcAddress, _seniorContractName), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bytes32> getEntryByName(Bytes32 dnsName) {
        Function function = new Function("getEntryByName", 
                Arrays.<Type>asList(dnsName), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> deleteEntryByName(Bytes32 dnsName) {
        Function function = new Function("deleteEntryByName", Arrays.<Type>asList(dnsName), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> remove() {
        Function function = new Function("remove", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> getContract(Bytes32 name) {
        Function function = new Function("getContract", 
                Arrays.<Type>asList(name), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
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
