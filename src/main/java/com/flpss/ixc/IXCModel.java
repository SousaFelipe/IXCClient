package com.flpss.ixc;


import java.io.IOException;


public class IXCModel {



    IXCClient client;
    String target;



    public IXCModel(String target) {
        this.target = target;

        this.client = new IXCClient();
        this.client.setTarget(target);
    }



    public String getTarget() {
        return target;
    }



    protected String get(Arguments args) {
        String response = null;

        try {
            response = this.client.get(args);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }
}
