package com.hull.test.autoMethod.camel;

/**
 * Created by hull on 2017/1/19.
 */
public class Message extends DataObject {
    private boolean terminateFlag = false;
    private Object payload;

    public Object getPayload() {
        return this.get("payload");
    }

    public void setPayload(Object payload) {
        this.set("payload", payload);
    }

    public boolean getTerminateFlag() {
        Object obj = this.get(UdmpCamelConstants.TERMINATE_FLAG);
        if(obj!=null){
            return (boolean) obj;
        }
        return false;
    }

    public void setTerminateFlag(boolean terminateFlag) {
        this.set(UdmpCamelConstants.TERMINATE_FLAG, terminateFlag);
    }
}
