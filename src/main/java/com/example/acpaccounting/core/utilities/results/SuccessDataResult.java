package com.example.acpaccounting.core.utilities.results;
public class SuccessDataResult extends DataResult {
    public SuccessDataResult(String message, Object data) {
        super(true, message, data);
    }

    public SuccessDataResult( Object data) {
        super(true, data);
    }
}
