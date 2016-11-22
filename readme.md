
```
personal.unlockAccount(web3.eth.accounts[0], "yourPassword", 36000);
var simpleDnsInstance = eth.contract([{"constant":true,"inputs":[],"name":"getValue","outputs":[{"name":"_value","type":"string"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_value","type":"string"}],"name":"setValue","outputs":[],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"name","type":"string"}],"name":"getIpByName","outputs":[{"name":"ip","type":"string"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"name","type":"string"},{"name":"ip","type":"string"}],"name":"registerName","outputs":[],"payable":false,"type":"function"},{"anonymous":false,"inputs":[{"indexed":false,"name":"name","type":"string"},{"indexed":false,"name":"ip","type":"string"}],"name":"newEntry","type":"event"},{"anonymous":false,"inputs":[],"name":"functionCalled","type":"event"}]).at("ADDRESS");
simpleDnsInstance.getIpByName("google.com");

simpleDnsInstance.registerName("demo.ch", "1.1.1.1", {from: eth.accounts[0]});
```