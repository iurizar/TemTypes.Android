package com.iurizar.temtypesandroid.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

import com.iurizar.temtypesandroid.R;
import com.iurizar.temtypesandroid.model.Type;

public class StyleUtils {
    private static final String[] TYPE_LIST = {"Neutral", "Fire", "Water", "Nature",
            "Electric", "Earth", "Mental", "Wind",
            "Digital", "Melee", "Crystal", "Toxic"};

    public void setTextStyle(TextView textView, Double value, Context context) {
        setStyle(textView, value, context);
    }

    public Double[] typeToArray(Type type) {
        Double[] valueArray = {type.getNeutral(),
                type.getFire(),
                type.getWater(),
                type.getNature(),
                type.getElectric(),
                type.getEarth(),
                type.getMental(),
                type.getWind(),
                type.getDigital(),
                type.getMelee(),
                type.getCrystal(),
                type.getToxic()
        };
        return valueArray;
    }

    public int changeButtonColor(String selectedType, Context context) {
        Resources res = context.getResources();
        String packageName = context.getPackageName();
        for(int i=0;i<TYPE_LIST.length;i++) {
            if(selectedType.equalsIgnoreCase(TYPE_LIST[i])) {
                int colorId = res.getIdentifier(selectedType.toLowerCase(), "color", packageName);
                int color = res.getColor(colorId,null);
                return color;
            }
        }
        return 0;
    }

    private void setStyle(TextView textView, Double value, Context context) {
        textView.setText(getMultiplierString(value));
        textView.setTextColor(getColor(value, context));
        textView.setTypeface(getTypeFace(value));
    }

    private int getColor(Double value, Context context) {
        Resources res = context.getResources();
        String packageName = context.getPackageName();
        String effectiveness = null;
        if (value == 0.25) {
            effectiveness = "uneffective";
        } else if (value == 0.5) {
            effectiveness = "uneffective";
        } else if (value == 1.0) {
            effectiveness = "normal";
        } else if (value == 2.0) {
            effectiveness = "effective";
        } else if (value == 4.0) {
            effectiveness = "effective";
        }
        int colorId = res.getIdentifier(effectiveness, "color", packageName);
        int color = res.getColor(colorId,null);
        return color;
    }

    private Typeface getTypeFace(Double value) {
        if (value == 0.25) {
            return Typeface.defaultFromStyle(Typeface.BOLD);
        } else if (value == 0.5) {
            return Typeface.defaultFromStyle(Typeface.BOLD);
        } else if (value == 1.0) {
            return Typeface.defaultFromStyle(Typeface.NORMAL);
        } else if (value == 2.0) {
            return Typeface.defaultFromStyle(Typeface.BOLD);
        } else if (value == 4.0) {
            return Typeface.defaultFromStyle(Typeface.BOLD);
        }
        return Typeface.defaultFromStyle(Typeface.NORMAL);
    }

    private String getMultiplierString(Double value) {
        if (value == 0.25) {
            return "x"+value;
        } else if (value == 0.5) {
            return "x"+value;
        } else if (value == 1.0) {
            return String.format("x%.0f", value);
        } else if (value == 2.0) {
            return String.format("x%.0f", value);
        } else if (value == 4.0) {
            return String.format("x%.0f", value);
        }
        return null;
    }


}
