package com.kabl.soliditydns;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

public class CodeGenerator {

    public static void main(String[] args) throws Exception {

        System.out.println("Generate the code!");
        String contract = "./src/main/resources/contracts/DnsDB.sol";
        String abi = "./src/main/resources/abi/DnsDB.abi";
        
        String[] args2 = {
            contract,
            abi,
            "-p",
            "com.kabl.soliditydns.generated",
            "-o",
            "./src/main/java",};

        SolidityFunctionWrapperGenerator.main(args2);
    }
}
