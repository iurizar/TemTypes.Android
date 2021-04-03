package com.iurizar.temtypesandroid.calculatorutils;

import com.iurizar.temtypesandroid.model.Type;
import com.iurizar.temtypesandroid.model.TypeWeakness;

import java.lang.reflect.Field;

public class WeaknessCalculator {

    private static final int TYPE_NUM = 12;
    private static final String[] TYPE_LIST = {"Neutral", "Fire", "Water", "Nature",
                                                "Electric", "Earth", "Mental", "Wind",
                                                "Digital", "Melee", "Crystal", "Toxic"};
    private static TypeWeakness response;
    private static String[] selectedTypes;

    public WeaknessCalculator(TypeWeakness response, String[] selectedTypes) {
        this.response = response;
        this.selectedTypes = selectedTypes;
    }

    public static Type calculateAttackingWeakness() {
        return calculateAttacking(selectedTypes[0]);
    }

    public static Type calculateDefendingWeakness() {
        return calculateDefending();
    }

    private static Type calculateAttacking(String selected) {
        switch(selected) {
            case "Neutral":
                return response.getNeutral();
            case "Fire":
                return response.getFire();
            case "Water":
                return response.getWater();
            case "Nature":
                return response.getNature();
            case "Electric":
                return response.getElectric();
            case "Earth":
                return response.getEarth();
            case "Mental":
                return response.getMental();
            case "Wind":
                return response.getWind();
            case "Digital":
                return response.getDigital();
            case "Melee":
                return response.getMelee();
            case "Crystal":
                return response.getCrystal();
            case "Toxic":
                return response.getToxic();
        }
        return null;
    }

    private static Type calculateDefending() {
        Type newType;

        if(selectedTypes.length == 2) {
            Double[] firstTypeValueArray = new Double[TYPE_LIST.length];
            Double[] secondTypeValueArray = new Double[TYPE_LIST.length];
            for(int i = 0; i < TYPE_LIST.length; i++) {
                try {
                    Field field = response.getClass().getDeclaredField(TYPE_LIST[i].toLowerCase());
                    field.setAccessible(true);
                    Type type = (Type) field.get(response);
                    firstTypeValueArray[i] = getValue(type, selectedTypes[0]);
                    secondTypeValueArray[i] = getValue(type, selectedTypes[1]);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            return new Type(
                firstTypeValueArray[0] * secondTypeValueArray[0],
                firstTypeValueArray[1] * secondTypeValueArray[1],
                firstTypeValueArray[2] * secondTypeValueArray[2],
                firstTypeValueArray[3] * secondTypeValueArray[3],
                firstTypeValueArray[4] * secondTypeValueArray[4],
                firstTypeValueArray[5] * secondTypeValueArray[5],
                firstTypeValueArray[6] * secondTypeValueArray[6],
                firstTypeValueArray[7] * secondTypeValueArray[7],
                firstTypeValueArray[8] * secondTypeValueArray[8],
                firstTypeValueArray[9] * secondTypeValueArray[9],
                firstTypeValueArray[10] * secondTypeValueArray[10],
                firstTypeValueArray[11] * secondTypeValueArray[11]
            );

        } else if (selectedTypes.length == 1) {
            Double[] valueArray = new Double[TYPE_LIST.length];
            for(int i = 0; i < TYPE_LIST.length; i++) {
                try {
                    Field field = response.getClass().getDeclaredField(TYPE_LIST[i].toLowerCase());
                    field.setAccessible(true);
                    Type type = (Type) field.get(response);
                    valueArray[i] = getValue(type, selectedTypes[0]);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            return new Type(
                valueArray[0],
                valueArray[1],
                valueArray[2],
                valueArray[3],
                valueArray[4],
                valueArray[5],
                valueArray[6],
                valueArray[7],
                valueArray[8],
                valueArray[9],
                valueArray[10],
                valueArray[11]
            );
        }
        return null;
    }

    private static Double getValue(Type type, String selectedType) {
        switch(selectedType) {
            case "Neutral":
                return type.getNeutral();
            case "Fire":
                return type.getFire();
            case "Water":
                return type.getWater();
            case "Nature":
                return type.getNature();
            case "Electric":
                return type.getElectric();
            case "Earth":
                return type.getEarth();
            case "Mental":
                return type.getMental();
            case "Wind":
                return type.getWind();
            case "Digital":
                return type.getDigital();
            case "Melee":
                return type.getMelee();
            case "Crystal":
                return type.getCrystal();
            case "Toxic":
                return type.getToxic();
        }
        return null;
    }

}
