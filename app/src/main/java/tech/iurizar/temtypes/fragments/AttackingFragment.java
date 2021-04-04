package tech.iurizar.temtypes.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import tech.iurizar.temtypes.R;
import tech.iurizar.temtypes.activities.MainActivity;
import tech.iurizar.temtypes.calculatorutils.WeaknessCalculator;
import tech.iurizar.temtypes.model.Type;
import tech.iurizar.temtypes.utils.FragmentUtils;
import tech.iurizar.temtypes.utils.StyleUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AttackingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttackingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Button typeButton;
    private String selectedType;
    private TextView neutralText, fireText, waterText, natureText, electricText, earthText, mentalText, windText,
        digitalText, meleeText, crystalText, toxicText;
    private TextView[] textViews;

    public AttackingFragment() {
        // Required empty public constructor
    }

    public static AttackingFragment newInstance(String param1, String param2) {
        AttackingFragment fragment = new AttackingFragment();
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
        View view = inflater.inflate(R.layout.fragment_attacking, container, false);
        typeButton = (Button) view.findViewById(R.id.button_type_att);
        typeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).ShowBottomSheet();
            }
        });
        if(selectedType != null) {
            findViews(view);
            textViews = getTextViews();
            MainActivity act = (MainActivity) getActivity();
            String[] selectedArray = {selectedType};
            WeaknessCalculator cal = new WeaknessCalculator(act.responseType, selectedArray);
            Type selType = cal.calculateAttackingWeakness();
            assignText(selType);
            StyleUtils style = new StyleUtils();
            typeButton.setText(selectedType);
            typeButton.setBackgroundColor(style.changeButtonColor(selectedType, getContext()));

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

    private void findViews(View view) {
        neutralText = (TextView) view.findViewById((R.id.att_neutral_text));
        fireText = (TextView) view.findViewById((R.id.att_fire_text));
        waterText = (TextView) view.findViewById((R.id.att_water_text));
        natureText = (TextView) view.findViewById((R.id.att_nature_text));
        electricText = (TextView) view.findViewById((R.id.att_electric_text));
        earthText = (TextView) view.findViewById((R.id.att_earth_text));
        mentalText = (TextView) view.findViewById((R.id.att_mental_text));
        windText = (TextView) view.findViewById((R.id.att_wind_text));
        digitalText = (TextView) view.findViewById((R.id.att_digital_text));
        meleeText = (TextView) view.findViewById((R.id.att_melee_text));
        crystalText = (TextView) view.findViewById((R.id.att_crystal_text));
        toxicText = (TextView) view.findViewById((R.id.att_toxic_text));
    }

    private TextView[] getTextViews() {
        TextView[] array = {
                neutralText, fireText, waterText, natureText, electricText, earthText, mentalText, windText,
                digitalText, meleeText, crystalText, toxicText
        };
        return array;
    }

    public void setType(String s) {
        selectedType = s;
    }

    private void assignText(Type type) {
        StyleUtils style = new StyleUtils();
        Double[] valueArray = style.typeToArray(type);

        for(int i=0;i<textViews.length;i++) {
            style.setTextStyle(textViews[i], valueArray[i], getContext());
        }
    }




}