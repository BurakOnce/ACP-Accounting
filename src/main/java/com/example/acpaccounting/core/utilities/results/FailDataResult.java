package com.example.acpaccounting.core.utilities.results;
public class FailDataResult extends DataResult{
    public FailDataResult(String message, Object data) {
        super(false, message, data);
    }

    public FailDataResult(String message) {
        super(false, message);
    }


}
