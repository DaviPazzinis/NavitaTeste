package com.example.navitateste.model;

import java.io.Serializable;
import java.util.ArrayList;

public class BodyResponseModel implements Serializable {

    ArrayList<MovieModel> results = new ArrayList<> ();

    public ArrayList<MovieModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieModel> results) {
        this.results = results;
    }
}