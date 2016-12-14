
package com.guidovezzoni.mvplibrary.model;

import java.util.List;

/**
 * From http://openweathermap.org/forecast5
 * decoded with http://www.jsonschema2pojo.org/
 */
public class Forecast {

    public City city;
    public String cod;
    public Double message;
    public Integer cnt;
    public List<List_> list = null;

}
