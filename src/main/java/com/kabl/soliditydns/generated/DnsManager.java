package com.kabl.soliditydns.generated;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.Contract;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
public final class DnsManager extends Contract {
    private static final String BINARY = "pragma solidity ^0.4.2;\n"
            + "\n"
            + "import \"./Cmc.sol\";\n"
            + "import \"./DnsDB.sol\";\n"
            + "import \"./CmcEnabled.sol\";\n"
            + "\n"
            + "contract DnsManager is CmcEnabled {\n"
            + "\n"
            + "    bytes32 dnsDbName = \"dnsdb\";\n"
            + "\n"
            + "    function register(bytes32 dnsName, bytes32 entry) returns (bool success)  {\n"
            + "    \n"
            + "        address dnsDbAddress = getContract(dnsDbName);\n"
            + "        if(dnsDbAddress == 0x0)\n"
            + "            return false;\n"
            + "\n"
            + "        return DnsDB(dnsDbAddress).register(dnsName, entry);\n"
            + "    }\n"
            + "\n"
            + "    function deleteEntryByName(bytes32 name) callAllowed returns (bool success) {\n"
            + "        \n"
            + "        address dnsDbAddress = getContract(dnsDbName);\n"
            + "        if(dnsDbAddress == 0x0)\n"
            + "            return false;\n"
            + "\n"
            + "        return DnsDB(dnsDbAddress).deleteEntryByName(name);\n"
            + "    }\n"
            + "\n"
            + "    function getEntryByName(bytes32 name) callAllowed constant returns (bytes32 entry) {\n"
            + "        address dnsDbAddress = getContract(dnsDbName);\n"
            + "        if(dnsDbAddress == 0x0)\n"
            + "            return 0x0;\n"
            + "\n"
            + "        DnsDB(dnsDbAddress).getEntryByName(name);\n"
            + "    }\n"
            + "}";

    private DnsManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public Future<Address> getCmcAddress() {
        Function function = new Function("getCmcAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bytes32> getSeniorContract() {
        Function function = new Function("getSeniorContract", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> init(Address _cmcAddress, Bytes32 _seniorContractName) {
        Function function = new Function("init", Arrays.<Type>asList(_cmcAddress, _seniorContractName), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
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

    public static Future<DnsManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(DnsManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static DnsManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DnsManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }
}
