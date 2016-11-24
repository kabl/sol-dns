pragma solidity ^0.4.2;

import "./CmcEnabled.sol";

contract DnsDB is CmcEnabled {
    
     struct DnsEntry
     {
         address owner;
         bytes32 entry;
     }

     mapping (bytes32 => DnsEntry) dnsEntriesByName;
     event eventDnsDB_newEntry(bytes32 dnsName, bytes32 entry);

     function register(bytes32 dnsName, bytes32 entry) callAllowed returns (bool success)  {

        dnsEntriesByName[dnsName] = DnsEntry(msg.sender, entry);
        eventDnsDB_newEntry(dnsName, entry);

        success = true;
     }

     function deleteEntryByName(bytes32 name) callAllowed returns (bool success) {
        if(dnsEntriesByName[name].owner != address(0x0)){
            delete dnsEntriesByName[name].owner;
            delete dnsEntriesByName[name];
            success = true;
        }else{
            success = false;
        }
     }

     function getEntryByName(bytes32 name) callAllowed constant returns (bytes32 entry) {
         if(dnsEntriesByName[name].owner != address(0x0))
             return dnsEntriesByName[name].entry;
         else
             return "404";
     }
}
