package com.kabl.soliditydns;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

public class CodeGenerator {

    public static void main(String[] args) throws Exception {

        System.out.println("Generate the code!");

        create("SolDnsApp.sol", "SolDnsApp.abi");
        create("Cmc.sol", "Cmc.abi");
        create("DnsDB.sol", "DnsDB.abi");
    }

    private static void create(String contractSource, String abi) throws Exception {
        String[] args2 = {
                "./src/main/resources/contracts/" + contractSource,
                "./src/main/resources/contracts/bin/" + abi,
                "-p",
                "com.kabl.soliditydns.generated",
                "-o",
                "./src/main/java",};

        SolidityFunctionWrapperGenerator.main(args2);
    }
}
