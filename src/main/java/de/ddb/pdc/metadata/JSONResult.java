package de.ddb.pdc.metadata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONResult {

    private ArrayList<LinkedHashMap> results;
    private LinkedHashMap rdf;

    public ArrayList<LinkedHashMap> getResults() {
        return results;
	}
	
	public LinkedHashMap getRdf() {
		return rdf;
	}
    
}
