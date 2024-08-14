package com.example.acpaccounting.core.utilities.results;

public class FailResult extends Result{
    public FailResult(){
        super(false);
    }
    public FailResult(String message){
        super(false,message);
    }
}
