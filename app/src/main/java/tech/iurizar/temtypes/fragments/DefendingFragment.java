package tech.iurizar.temtypes.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import tech.iurizar.temtypes.R;
import tech.iurizar.temtypes.activities.MainActivity;
import tech.iurizar.temtypes.calculatorutils.WeaknessCalculator;
import tech.iurizar.temtypes.model.Type;
import tech.iurizar.temtypes.model.TypeWeakness;
import tech.iurizar.temtypes.utils.FragmentUtils;
import tech.iurizar.temtypes.utils.StyleUtils;

import org.jetbrains.annotations.NotNull;


public class DefendingFragment extends Fragment {
    
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Button type1;
    private Button type2;
    private ImageView refreshIcon;
    private String selectedTypeOne;
    private String selectedTypeTwo;
    private int selectedButton;
    private TextView neutralText, fireText, waterText, natureText, electricText, earthText, mentalText, windText,
            digitalText, meleeText, crystalText, toxicText;
    private TextView[] textViews;

    public DefendingFragment() {
        // Required empty public constructor
    }

    public static DefendingFragment newInstance(String param1, String param2) {
        DefendingFragment fragment = new DefendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_defending, container, false);
        type1 = (Button) view.findViewById(R.id.button_type1);
        type2 = (Button) view.findViewById(R.id.button_type2);
        refreshIcon = (ImageView) view.findViewById(R.id.refresh_icon);
        refreshIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(200);
                rotate.setInterpolator(new LinearInterpolator());
                refreshIcon.startAnimation(rotate);
                resetFragment();
            }
        });
        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = 1;
                ((MainActivity)getActivity()).ShowBottomSheet();
            }
        });
        type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton = 2;
                ((MainActivity)getActivity()).ShowBottomSheet();
            }
        });
        if(selectedTypeOne == null) {
            selectedTypeOne = "";
        }
        if (selectedTypeTwo == null) {
            selectedTypeTwo = "";
        }
        findViews(view);
        textViews = getTextViews();
        if(!selectedTypeOne.equalsIgnoreCase("") && selectedTypeTwo.equalsIgnoreCase("")) {
            StyleUtils style = setFragmentView(selectedTypeOne);
            type1.setText(selectedTypeOne);
            type1.setBackgroundColor(style.changeButtonColor(selectedTypeOne, getContext()));
        } else if(!selectedTypeTwo.equalsIgnoreCase("") && selectedTypeOne.equalsIgnoreCase("")) {
            StyleUtils style = setFragmentView(selectedTypeTwo);
            type2.setText(selectedTypeTwo);
            type2.setBackgroundColor(style.changeButtonColor(selectedTypeTwo, getContext()));
        } else if(!selectedTypeTwo.equalsIgnoreCase("") && !selectedTypeOne.equalsIgnoreCase("")) {
            StyleUtils style = setDoubleTypeFragmentView();
            type1.setText(selectedTypeOne);
            type1.setBackgroundColor(style.changeButtonColor(selectedTypeOne, getContext()));
            type2.setText(selectedTypeTwo);
            type2.setBackgroundColor(style.changeButtonColor(selectedTypeTwo, getContext()));
        }
        return view;
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (FragmentUtils.sDisableFragmentAnimations) {
            Animation a = new Animation() {};
            a.setDuration(0);
            return a;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @NotNull
    private StyleUtils setFragmentView(String selectedType) {
        MainActivity act = (MainActivity) getActivity();
        String[] selectedArray = {selectedType};
        WeaknessCalculator cal = new WeaknessCalculator(act.responseType, selectedArray);
        Type selType = cal.calculateDefendingWeakness();
        assignText(selType);
        return new StyleUtils();
    }

    private StyleUtils setDoubleTypeFragmentView() {
        MainActivity act = (MainActivity) getActivity();
        String[] selectedArray = {selectedTypeOne, selectedTypeTwo};
        WeaknessCalculator cal = new WeaknessCalculator(act.responseType, selectedArray);
        Type selType = cal.calculateDefendingWeakness();
        assignText(selType);
        return new StyleUtils();
    }

    private void findViews(View view) {
        neutralText = (TextView) view.findViewById((R.id.def_neutral_text));
        fireText = (TextView) view.findViewById((R.id.def_fire_text));
        waterText = (TextView) view.findViewById((R.id.def_water_text));
        natureText = (TextView) view.findViewById((R.id.def_nature_text));
        electricText = (TextView) view.findViewById((R.id.def_electric_text));
        earthText = (TextView) view.findViewById((R.id.def_earth_text));
        mentalText = (TextView) view.findViewById((R.id.def_mental_text));
        windText = (TextView) view.findViewById((R.id.def_wind_text));
        digitalText = (TextView) view.findViewById((R.id.def_digital_text));
        meleeText = (TextView) view.findViewById((R.id.def_melee_text));
        crystalText = (TextView) view.findViewById((R.id.def_crystal_text));
        toxicText = (TextView) view.findViewById((R.id.def_toxic_text));
    }

    private TextView[] getTextViews() {
        TextView[] array = {
                neutralText, fireText, waterText, natureText, electricText, earthText, mentalText, windText,
                digitalText, meleeText, crystalText, toxicText
        };
        return array;
    }

    public void setType(String s, String z, int i) {
        if(i == 1) {
            selectedTypeOne = s;
        } else if (i==2) {
            selectedTypeTwo = z;
        }
    }

    public void resetFragment() {
        type1.setBackgroundColor(getResources().getColor(R.color.purple_500, null));
        type1.setText("Type 1");
        type2.setBackgroundColor(getResources().getColor(R.color.purple_500, null));
        type2.setText("Type 2");
        selectedTypeTwo = "";
        selectedTypeOne = "";
        selectedButton = 0;
        for(int i=0; i<textViews.length;i++) {
            textViews[i].setText("x");
            textViews[i].setTextColor(getResources().getColor(R.color.text_color,null));
            textViews[i].setTypeface(Typeface.DEFAULT);
        }
    }

    public void resetFragmentSameTypesSelected() {
        selectedTypeTwo = "";
        type2.setBackgroundColor(getResources().getColor(R.color.purple_500, null));
        type2.setText("Type 2");
    }

    public void setDoubleType(String s, String z) {
        selectedTypeOne = s;
        selectedTypeTwo = z;
    }

    public String getFirstType() {
        return selectedTypeOne;
    }

    public String getSecondType() {
        return selectedTypeTwo;
    }

    public int getSelectedButton() {
        return selectedButton;
    }


    private void assignText(Type type) {
        StyleUtils style = new StyleUtils();
        Double[] valueArray = style.typeToArray(type);

        for(int i=0;i<textViews.length;i++) {
            style.setTextStyle(textViews[i], valueArray[i], getContext());
        }
    }

}