package com.ina.ina.Data;

/**
 * Created by 100487239 on 4/3/2017.
 */

public class Solution {
    public String objective;
    public String[] foods;
    public String[] values;

    public Solution(String objective, String[] foods, String[] values){
        this.objective = objective;
        this.foods = foods;
        this.values = values;
    }

    public String getObjective(){return objective;}
    public String[] getFoodsolution(){return foods;}
    public String[] getValuesSolution(){return values;}
}
