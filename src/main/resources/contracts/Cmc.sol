pragma solidity ^0.4.2;

import "./CmcEnabled.sol";
import "./DnsManager.sol";

contract CmcReader {
  function getContract(bytes32 name) constant returns (address _address) {}
}

contract Cmc is CmcReader{

    address owner;

    // This is where we keep all the contracts.
    mapping (bytes32 => address) public contracts;

    modifier onlyOwner { //a modifier to reduce code replication
        if (msg.sender == owner) // this ensures that only the owner can access the function
            _;
    }
    // Constructor
    function Cmc(){
        owner = msg.sender;
    }

    function bootstrap() onlyOwner returns (bool result){
        DnsManager dnsManager = new DnsManager();
        DnsDB dnsdb = new DnsDB();

        if(!addContract("dnsmanager", dnsManager, 0x0))
            return false;
        
        if(!addContract("dnsdb", dnsdb, "dnsmanager"))
            return false;

        return true;
    }

    // Add a new contract to CMC. This will overwrite an existing contract.
    function addContract(bytes32 name, address addr, bytes32 seniorName) onlyOwner returns (bool result) {
        CmcEnabled cmcEnabled = CmcEnabled(addr);
        // Don't add the contract if this does not work.
        if(!cmcEnabled.init(address(this), seniorName)) {
            return false;
        }
        contracts[name] = addr;
        return true;
    }

    function getContract(bytes32 name) constant returns (address _address){
        return contracts[name];
    }

    // Remove a contract from CMC. We could also selfdestruct if we want to.
    function removeContract(bytes32 name) onlyOwner returns (bool result) {
        if (contracts[name] == 0x0){
            return false;
        }
        contracts[name] = 0x0;
        return true;
    }

    function remove() onlyOwner {
        address dnsDb = contracts["dnsdb"];
        address dnsManager = contracts["dnsmanager"];

        // Remove everything.
        if(dnsDb != 0x0){ CmcEnabled(dnsDb).remove(); }
        if(dnsManager != 0x0){ CmcEnabled(dnsManager).remove(); }

        selfdestruct(owner);
    }

}