package com.lcarino.bucketlist.event;

/**
 * Base model object used to post service call result events in the bus.
 *
 * @author luis.carino
 */

public abstract class ResultEvent<T> {

    private T payload;
    private boolean isSuccess;

    public ResultEvent() {
    }

    public ResultEvent(T payload) {
        this.payload = payload;
        isSuccess = true;
    }

    public void setPayload(T payload) {
        this.payload = payload;
        isSuccess = true;
    }

    public T getPayload() {
        return payload;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
