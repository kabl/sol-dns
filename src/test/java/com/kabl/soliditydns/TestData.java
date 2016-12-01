/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kabl.soliditydns;

import java.util.Random;

public class TestData {
    
    private final String owner;
    private final String dnsName;
    private final String dnsEntry;

    private TestData(String owner, String dnsName, String dnsEntry) {
        this.owner = owner;
        this.dnsName = dnsName;
        this.dnsEntry = dnsEntry;
    }
    
    public static TestData staticGenerate(String owner){
        String RANDOM_SUFFIX = "" + new Random().nextInt(1_000_000);
        String name = "test.com" + RANDOM_SUFFIX;
        String entry = "1.1.1.1" + RANDOM_SUFFIX;
        return new TestData(owner, name, entry);
    }

    public String getDnsName() {
        return dnsName;
    }

    public String getDnsEntry() {
        return dnsEntry;
    }

    public String getOwner() {
        return owner;
    }
    
    
}
