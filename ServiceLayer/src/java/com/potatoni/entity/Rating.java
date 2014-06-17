
package com.potatoni.entity;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Rating {

    @Expose
    private Integer max;
    @Expose
    private Integer numRaters;
    @Expose
    private String average;
    @Expose
    private Integer min;

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getNumRaters() {
        return numRaters;
    }

    public void setNumRaters(Integer numRaters) {
        this.numRaters = numRaters;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

}
