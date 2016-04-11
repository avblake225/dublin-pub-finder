package com.tonyblake.dublinpubfinder;

public class PubItem {

    private String pub_name = "";
    private String pub_address = "";

    public void setPubName(String pub_name){

        this.pub_name = pub_name;
    }

    public void setPubAddress(String pub_address){

        this.pub_address = pub_address;
    }

    public String getPubName(){

        return pub_name;
    }

    public String getPubAddress(){

        return pub_address;
    }
}
