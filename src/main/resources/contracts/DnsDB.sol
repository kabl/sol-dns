pragma solidity ^0.4.2;

contract DnsDB {

     struct DnsEntry
     {
         address owner;
         bytes32 entry;
     }

     mapping (bytes32 => DnsEntry) dnsEntriesByName;
     event eventDnsDB_newEntry(bytes32 dnsName, bytes32 entry);


     function register(bytes32 dnsName, bytes32 entry) {

        dnsEntriesByName[dnsName] = DnsEntry(msg.sender, entry);
        eventDnsDB_newEntry(dnsName, entry);
     }

     function getEntryByName(bytes32 name) constant returns (bytes32 entry){
         if(dnsEntriesByName[name].owner != address(0x0))
             return dnsEntriesByName[name].entry;
         else
             return "404";
     }
}
