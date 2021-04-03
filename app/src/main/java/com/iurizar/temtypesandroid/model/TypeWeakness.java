
package com.iurizar.temtypesandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeWeakness {

    @SerializedName("Neutral")
    @Expose
    private Type neutral;

    @SerializedName("Fire")
    @Expose
    private Type fire;

    @SerializedName("Water")
    @Expose
    private Type water;

    @SerializedName("Nature")
    @Expose
    private Type nature;

    @SerializedName("Electric")
    @Expose
    private Type electric;

    @SerializedName("Earth")
    @Expose
    private Type earth;

    @SerializedName("Mental")
    @Expose
    private Type mental;

    @SerializedName("Wind")
    @Expose
    private Type wind;

    @SerializedName("Digital")
    @Expose
    private Type digital;

    @SerializedName("Melee")
    @Expose
    private Type melee;

    @SerializedName("Crystal")
    @Expose
    private Type crystal;

    @SerializedName("Toxic")
    @Expose
    private Type toxic;

    public Type getNeutral() {
        return neutral;
    }

    public void setNeutral(Type neutral) {
        this.neutral = neutral;
    }

    public Type getFire() {
        return fire;
    }

    public void setFire(Type fire) {
        this.fire = fire;
    }

    public Type getWater() {
        return water;
    }

    public void setWater(Type water) {
        this.water = water;
    }

    public Type getNature() {
        return nature;
    }

    public void setNature(Type nature) {
        this.nature = nature;
    }

    public Type getElectric() {
        return electric;
    }

    public void setElectric(Type electric) {
        this.electric = electric;
    }

    public Type getEarth() {
        return earth;
    }

    public void setEarth(Type earth) {
        this.earth = earth;
    }

    public Type getMental() {
        return mental;
    }

    public void setMental(Type mental) {
        this.mental = mental;
    }

    public Type getWind() {
        return wind;
    }

    public void setWind(Type wind) {
        this.wind = wind;
    }

    public Type getDigital() {
        return digital;
    }

    public void setDigital(Type digital) {
        this.digital = digital;
    }

    public Type getMelee() {
        return melee;
    }

    public void setMelee(Type melee) {
        this.melee = melee;
    }

    public Type getCrystal() {
        return crystal;
    }

    public void setCrystal(Type crystal) {
        this.crystal = crystal;
    }

    public Type getToxic() {
        return toxic;
    }

    public void setToxic(Type toxic) {
        this.toxic = toxic;
    }
}
