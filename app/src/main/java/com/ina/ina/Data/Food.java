package com.ina.ina.Data;

/**
 * Created by 100522340 on 12/5/2016.
 * Created by 100487239 on 12/3/2016.
 */
public class Food {
    public int id;
    public String name;
    public double cost;
    public double vitaminA;
    public double vitaminD;
    public double vitaminE;
    public double vitaminK;
    public double vitaminC;
    public double thiamin;
    public double riboflavin;
    public double niacin;
    public double vitaminB;
    public double folate;
    public double vitaminB12;
    public double pantothenicAcid;
    public double choline;
    public double calcium;
    public double copper;
    public double fluoride;
    public double iron;
    public double magnesium;
    public double maganese;
    public double phosphorus;
    public double selenium;
    public double zinc;
    public double potassium;
    public double sodium;
    public double carbohydrate;
    public double protein;
    public double fibre;
    public double energy;
    public double fat;

    public Food(int id, String name, double cost, double vitaminA, double vitaminD, double vitaminE,
                double vitaminK, double vitaminC, double thiamin, double riboflavin, double niacin,
                double vitaminB, double folate, double vitaminB12, double pantothenicAcid,
                double choline, double calcium, double copper, double fluoride, double iron,
                double magnesium, double maganese, double phosphorus, double selenium, double zinc,
                double potassium, double sodium, double carbohydrate, double protein, double fibre, double energy, double fat){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.vitaminA = vitaminA;//
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
        this.vitaminK = vitaminK;
        this.vitaminC = vitaminC;
        this.thiamin = thiamin;//
        this.riboflavin = riboflavin;
        this.niacin = niacin;
        this.vitaminB = vitaminB;
        this.folate = folate;
        this.vitaminB12 = vitaminB12;
        this.pantothenicAcid = pantothenicAcid;
        this.choline = choline;
        this.calcium = calcium;
        this.copper = copper;
        this.fluoride = fluoride;
        this.iron = iron;
        this.magnesium = magnesium;
        this.maganese = maganese;
        this.phosphorus = phosphorus;
        this.selenium = selenium;
        this.zinc = zinc;
        this.potassium = potassium;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fibre = fibre;
        this.energy = energy;
        this.fat = fat;
    }

    public double getFibre() {
        return fibre;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getSodium() {
        return sodium;
    }

    public double getPotassium() {
        return potassium;
    }

    public double getZinc() {
        return zinc;
    }

    public double getSelenium() {
        return selenium;
    }

    public double getPhosphorus() {
        return phosphorus;
    }

    public double getMaganese() {
        return maganese;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public double getIron() {
        return iron;
    }

    public double getFluoride() {
        return fluoride;
    }

    public double getCopper() {
        return copper;
    }

    public double getCalcium() {
        return calcium;
    }

    public double getCholine() {
        return choline;
    }

    public double getPantothenicAcid() {
        return pantothenicAcid;
    }

    public double getVitaminB12() {
        return vitaminB12;
    }

    public double getFolate() {
        return folate;
    }

    public double getVitaminB() {
        return vitaminB;
    }

    public double getNiacin() {
        return niacin;
    }

    public double getRiboflavin() {
        return riboflavin;
    }

    public double getThiamin() {
        return thiamin;
    }

    public double getVitaminC() {
        return vitaminC;
    }

    public double getVitaminK() {
        return vitaminK;
    }

    public double getVitaminE() {
        return vitaminE;
    }

    public double getVitaminD() {
        return vitaminD;
    }

    public double getVitaminA() {
        return vitaminA;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getEnergy() {
        return energy;
    }

    public double getFat(){
        return fat;
    }

}

