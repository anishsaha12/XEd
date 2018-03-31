package com.rising.dots.xed;

import java.util.Date;

/**
 * Created by dell on 31-03-2018.
 */

public class trans {
    private String Reason;
    private String Amount;
    private String Dat;

    public trans(String reason, String amount, String dat) {
        Reason = reason;
        Amount = amount;
        Dat = dat;
    }
    public trans(){}

    public void setReason(String reason) {
        Reason = reason;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public void setDat(String dat) {
        Dat = dat;
    }

    public String getReason() {
        return Reason;
    }

    public String getAmount() {
        return Amount;
    }

    public String getDat() {
        return Dat;
    }
}
