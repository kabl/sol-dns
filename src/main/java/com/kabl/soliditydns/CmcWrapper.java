package com.kabl.soliditydns;

import com.kabl.soliditydns.generated.Cmc;
import com.kabl.soliditydns.generated.DnsManager;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class CmcWrapper {

    private static final int TX_WAIT_MAX_SEC = 120;

    private final Cmc cmc;

    public CmcWrapper(Cmc cmc) {
        this.cmc = cmc;
    }

    public void bootstrap() {
        try {
            BigInteger blockNumber = cmc.bootstrap().get(TX_WAIT_MAX_SEC, TimeUnit.SECONDS).getBlockNumber();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getAddress(String contractName) {
        try {
            BigInteger address = cmc.getContract(ContractUtil.toBytes32(contractName)).
                    get(TX_WAIT_MAX_SEC, TimeUnit.SECONDS).getValue();

            return "0x" + address.toString(16);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
