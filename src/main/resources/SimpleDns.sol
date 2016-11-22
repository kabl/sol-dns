contract DnsDB {

     struct DnsEntry
     {
         address owner;
         string ip;
     }

     string value;

     mapping (string => DnsEntry) entriesByName;

     event newEntry(string name, string ip);
     event functionCalled();

     function registerName(string name, string ip) {
         functionCalled();
         if(entriesByName[name].owner != address(0x0) &&
             entriesByName[name].owner != msg.sender)
             throw;

        entriesByName[name] = DnsEntry(msg.sender, ip);

        newEntry(name, ip);
     }

     function getIpByName(string name) constant returns (string ip){
         if(entriesByName[name].owner != address(0x0))
             return entriesByName[name].ip;
         else
             return "404";
     }

     function setValue(string _value){
         functionCalled();
         value = _value;
     }

     function getValue() constant returns (string _value){
         _value = value;
     }
}
