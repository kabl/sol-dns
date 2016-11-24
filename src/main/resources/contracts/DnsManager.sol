pragma solidity ^0.4.2;

import "./Cmc.sol";
import "./DnsDB.sol";
import "./CmcEnabled.sol";

contract DnsManager is CmcEnabled {

    bytes32 dnsDbName = "dnsdb";

    function register(bytes32 dnsName, bytes32 entry) returns (bool success)  { //callAllowed?
    
        address dnsDbAddress = getContract(dnsDbName);
        if(dnsDbAddress == 0x0)
            return false;

        return DnsDB(dnsDbAddress).register(dnsName, entry);
    }

    function deleteEntryByName(bytes32 name)  returns (bool success) { //callAllowed?
        
        address dnsDbAddress = getContract(dnsDbName);
        if(dnsDbAddress == 0x0)
            return false;

        return DnsDB(dnsDbAddress).deleteEntryByName(name);
    }

    function getEntryByName(bytes32 name) constant returns (bytes32 entry) {
        address dnsDbAddress = getContract(dnsDbName);
        if(dnsDbAddress == 0x0)
            return 0xff;

        return DnsDB(dnsDbAddress).getEntryByName(name);
    }
}