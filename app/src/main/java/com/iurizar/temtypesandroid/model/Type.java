package com.iurizar.temtypesandroid.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("Neutral")
    @Expose
    private Double neutral;

    @SerializedName("Fire")
    @Expose
    private Double fire;

    @SerializedName("Water")
    @Expose
    private Double water;

    @SerializedName("Nature")
    @Expose
    private Double nature;

    @SerializedName("Electric")
    @Expose
    private Double electric;

    @SerializedName("Earth")
    @Expose
    private Double earth;

    @SerializedName("Mental")
    @Expose
    private Double mental;

    @SerializedName("Wind")
    @Expose
    private Double wind;

    @SerializedName("Digital")
    @Expose
    private Double digital;

    @SerializedName("Melee")
    @Expose
    private Double melee;

    @SerializedName("Crystal")
    @Expose
    private Double crystal;

    @SerializedName("Toxic")
    @Expose
    private Double toxic;

    public double getNeutral() {
        return neutral;
    }

    public double getFire() {
        return fire;
    }

    public double getWater() {
        return water;
    }

    public double getNature() {
        return nature;
    }

    public double getElectric() {
        return electric;
    }

    public double getEarth() {
        return earth;
    }

    public double getMental() {
        return mental;
    }

    public double getWind() {
        return wind;
    }

    public double getDigital() {
        return digital;
    }

    public double getMelee() {
        return melee;
    }

    public double getCrystal() {
        return crystal;
    }

    public double getToxic() {
        return toxic;
    }

    public void setNeutral(double neutral) {
        this.neutral = neutral;
    }

    public void setFire(double fire) {
        this.fire = fire;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public void setNature(double nature) {
        this.nature = nature;
    }

    public void setElectric(double electric) {
        this.electric = electric;
    }

    public void setEarth(double earth) {
        this.earth = earth;
    }

    public void setMental(double mental) {
        this.mental = mental;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public void setDigital(double digital) {
        this.digital = digital;
    }

    public void setMelee(double melee) {
        this.melee = melee;
    }

    public void setCrystal(double crystal) {
        this.crystal = crystal;
    }

    public void setToxic(double toxic) {
        this.toxic = toxic;
    }

    public Type(Double neutral, Double fire, Double water, Double nature, Double electric, Double earth, Double mental, Double wind, Double digital, Double melee, Double crystal, Double toxic) {
        this.neutral = neutral;
        this.fire = fire;
        this.water = water;
        this.nature = nature;
        this.electric = electric;
        this.earth = earth;
        this.mental = mental;
        this.wind = wind;
        this.digital = digital;
        this.melee = melee;
        this.crystal = crystal;
        this.toxic = toxic;
    }
}
