package com.flpss.ixc;


import org.json.JSONObject;


public class IXCResponse {



    private String responseBody;




    public IXCResponse(String responseBody) {
        this.responseBody = responseBody;
    }



    public JSONObject toJSON() {
        return new JSONObject(this.responseBody);
    }
}
