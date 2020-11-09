package com.example.project309.app;

/**
 * This class is used for easy way to make params and body request with an array list of these instances
 *
 * @author Devin Milligan
 */
public class JSONVariable {

    /**
     * The id value that is a JSON id
     */
    private String id;
    /**
     * The value related to the id
     */
    private String value;

    /**
     * Constructor to set the id and value
     *
     * @param id json id
     * @param value value related to the id
     */
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
