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
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.</p>
 */
public final class SimpleDnsABI extends Contract {
    private static final String BINARY = "contract SimpleDns {\n"
            + "\n"
            + "     struct DnsEntry\n"
            + "     {\n"
            + "         address owner;\n"
            + "         string ip;\n"
            + "     }\n"
            + "\n"
            + "     string value;\n"
            + "\n"
            + "     mapping (string => DnsEntry) entriesByName;\n"
            + "\n"
            + "     event newEntry(string name, string ip);\n"
            + "     event functionCalled();\n"
            + "\n"
            + "     function registerName(string name, string ip) {\n"
            + "         functionCalled();\n"
            + "         if(entriesByName[name].owner != address(0x0) &&\n"
            + "             entriesByName[name].owner != msg.sender)\n"
            + "             throw;\n"
            + "\n"
            + "        entriesByName[name] = DnsEntry(msg.sender, ip);\n"
            + "\n"
            + "        newEntry(name, ip);\n"
            + "     }\n"
            + "\n"
            + "     function getIpByName(string name) constant returns (string ip){\n"
            + "         if(entriesByName[name].owner != address(0x0))\n"
            + "             return entriesByName[name].ip;\n"
            + "         else\n"
            + "             return \"404\";\n"
            + "     }\n"
            + "\n"
            + "     function setValue(string _value){\n"
            + "         functionCalled();\n"
            + "         value = _value;\n"
            + "     }\n"
            + "\n"
            + "     function getValue() constant returns (string _value){\n"
            + "         _value = value;\n"
            + "     }\n"
            + "}\n";

    private SimpleDnsABI(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public Future<Utf8String> getValue() {
        Function function = new Function("getValue", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> setValue(Utf8String _value) {
        Function function = new Function("setValue", Arrays.<Type>asList(_value), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getIpByName(Utf8String name) {
        Function function = new Function("getIpByName", 
                Arrays.<Type>asList(name), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> registerName(Utf8String name, Utf8String ip) {
        Function function = new Function("registerName", Arrays.<Type>asList(name, ip), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public EventValues processNewEntryEvent(TransactionReceipt transactionReceipt) {
        Event event = new Event("newEntry", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return extractEventParameters(event, transactionReceipt);
    }

    public EventValues processFunctionCalledEvent(TransactionReceipt transactionReceipt) {
        Event event = new Event("functionCalled", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        return extractEventParameters(event, transactionReceipt);
    }

    public static Future<SimpleDnsABI> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(SimpleDnsABI.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static SimpleDnsABI load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleDnsABI(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }
}
