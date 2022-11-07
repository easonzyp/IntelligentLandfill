package com.wise.develop.Landfill.base;


import org.json.JSONObject;

import java.io.Serializable;

public abstract class BaseRequest implements Serializable {

    public abstract JSONObject toJson();

    public abstract String toJsonString();

}
