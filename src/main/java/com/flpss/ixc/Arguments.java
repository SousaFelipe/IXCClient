package com.flpss.ixc;


import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;


public class Arguments {



    Map<String, String> common = new HashMap<>();
    Map<String, String> params = new HashMap<>();



    public Arguments(@NotNull IXCModel model) {
        String defaultQtype = model.getTarget().concat(".").concat("id");

        common.put("qtype", defaultQtype);
        common.put("oper", "LE");
        common.put("query", "");
        common.put("page", "1");
        common.put("rp", "20");
        common.put("sortname", defaultQtype);
        common.put("sortorder", "ASC");
    }



    public Arguments add(String name, String value) {

        if (common.containsKey(name)) {
            String oldValue = this.params.get(name);
            this.params.replace(name, oldValue, value);
        }

        return this;
    }



    public BodyPublisher publisher() {
        return HttpRequest.BodyPublishers.ofString(this.params.toString());
    }
}
