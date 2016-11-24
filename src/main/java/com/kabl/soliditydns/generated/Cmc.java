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
public final class Cmc extends Contract {
    private static final String BINARY = "pragma solidity ^0.4.2;\n"
            + "\n"
            + "import \"./CmcEnabled.sol\";\n"
            + "import \"./DnsManager.sol\";\n"
            + "\n"
            + "contract CmcReader {\n"
            + "  function getContract(bytes32 name) constant returns (address _address) {}\n"
            + "}\n"
            + "\n"
            + "contract Cmc is CmcReader{\n"
            + "\n"
            + "    address owner;\n"
            + "\n"
            + "    // This is where we keep all the contracts.\n"
            + "    mapping (bytes32 => address) public contracts;\n"
            + "\n"
            + "    modifier onlyOwner { //a modifier to reduce code replication\n"
            + "        if (msg.sender == owner) // this ensures that only the owner can access the function\n"
            + "            _;\n"
            + "    }\n"
            + "    // Constructor\n"
            + "    function Cmc(){\n"
            + "        owner = msg.sender;\n"
            + "    }\n"
            + "\n"
            + "    function bootstrap() onlyOwner returns (bool result){\n"
            + "        DnsManager dnsManager = new DnsManager();\n"
            + "        DnsDB dnsdb = new DnsDB();\n"
            + "\n"
            + "        if(!addContract(\"dnsmanager\", dnsManager, 0x0))\n"
            + "            throw;\n"
            + "        \n"
            + "        if(!addContract(\"dnsdb\", dnsdb, \"dnsmanager\"))\n"
            + "            throw;\n"
            + "    }\n"
            + "\n"
            + "    // Add a new contract to CMC. This will overwrite an existing contract.\n"
            + "    function addContract(bytes32 name, address addr, bytes32 seniorName) onlyOwner returns (bool result) {\n"
            + "        CmcEnabled cmcEnabled = CmcEnabled(addr);\n"
            + "        // Don't add the contract if this does not work.\n"
            + "        if(!cmcEnabled.init(address(this), seniorName)) {\n"
            + "            return false;\n"
            + "        }\n"
            + "        contracts[name] = addr;\n"
            + "        return true;\n"
            + "    }\n"
            + "\n"
            + "    function getContract(bytes32 name) constant returns (address _address){\n"
            + "        return contracts[name];\n"
            + "    }\n"
            + "\n"
            + "    // Remove a contract from CMC. We could also selfdestruct if we want to.\n"
            + "    function removeContract(bytes32 name) onlyOwner returns (bool result) {\n"
            + "        if (contracts[name] == 0x0){\n"
            + "            return false;\n"
            + "        }\n"
            + "        contracts[name] = 0x0;\n"
            + "        return true;\n"
            + "    }\n"
            + "\n"
            + "    function remove() onlyOwner {\n"
            + "        address dnsDb = contracts[\"dnsdb\"];\n"
            + "        address dnsManager = contracts[\"dnsmanager\"];\n"
            + "\n"
            + "        // Remove everything.\n"
            + "        if(dnsDb != 0x0){ CmcEnabled(dnsDb).remove(); }\n"
            + "        if(dnsManager != 0x0){ CmcEnabled(dnsManager).remove(); }\n"
            + "\n"
            + "        selfdestruct(owner);\n"
            + "    }\n"
            + "\n"
            + "}";

    private Cmc(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> removeContract(Bytes32 name) {
        Function function = new Function("removeContract", Arrays.<Type>asList(name), Collections.<TypeReference<?>>emptyList());
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

    public Future<Address> contracts(Bytes32 param0) {
        Function function = new Function("contracts", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> bootstrap() {
        Function function = new Function("bootstrap", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> addContract(Bytes32 name, Address addr, Bytes32 seniorName) {
        Function function = new Function("addContract", Arrays.<Type>asList(name, addr, seniorName), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<Cmc> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(Cmc.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Cmc load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Cmc(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }
}
