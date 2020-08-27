package com.task.login.model;

public enum APIConstant {
	
	// Enums for providing Status in API Response
	SUCCESS(200),
	FAILURE(0),
	INTERNAL_SERVER_ERROR(500),
	UNAUTHORISED(401);
	
	int value;
    String stringVal;

    private APIConstant(int value) {
        this.value = value;
        this.stringVal = String.valueOf(value);
    }
     public int intValue() {
        return this.value;
    }
    public String stringValue() {
        return this.stringVal;
    }

}
