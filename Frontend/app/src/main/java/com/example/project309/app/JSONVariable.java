package com.example.project309.app;

public class JSONVariable {

    private String id;
    private String value;

    public JSONVariable(String id, String value){


        this.id = id;
        this.value = value;


    }

    public String getId(){
        return id;
    }
    public String getValue(){
        return value;
    }
    public String toString(){
        return getId() + ":" + getValue();
    }

}
