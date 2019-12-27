package com.threeDays.Utils.Delivery.deliverPojo;

public class deliverpojo {
    private String LogisticCode;
    private String ShipperCode;
    private lichen[] Traces;
    private String State;
    private String EBusinessID;
    private boolean Success;

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    public lichen[] getTraces() {
        return Traces;
    }

    public void setTraces(lichen[] traces) {
        Traces = traces;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }
}
