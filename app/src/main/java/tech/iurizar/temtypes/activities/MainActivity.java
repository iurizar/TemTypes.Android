package tech.iurizar.temtypes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import tech.iurizar.temtypes.R;
import tech.iurizar.temtypes.adapters.ListViewAdapter;
import tech.iurizar.temtypes.fragments.AttackingFragment;
import tech.iurizar.temtypes.fragments.DefendingFragment;
import tech.iurizar.temtypes.model.TypeWeakness;
import tech.iurizar.temtypes.networkutils.RetrofitBuilder;
import tech.iurizar.temtypes.networkutils.TypeService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TypeService typeService;
    BottomSheetBehavior bsb;
    LinearLayout bottomSheet;
    ListView typeListView;
    ImageView downIcon;
    public TypeWeakness responseType;
    private int position;
    private static final String[] TYPE_LIST = {"Neutral", "Fire", "Water", "Nature",
            "Electric", "Earth", "Mental", "Wind",
            "Digital", "Melee", "Crystal", "Toxic"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        tabLayout = findViewById(R.id.tab_layout);

        //Call API for data
        typeService = RetrofitBuilder.getRetrofit().create(TypeService.class);
        Call<TypeWeakness> call = typeService.getWeaknesses();
        call.enqueue(getCallbackWeakness());

        //Add the tabs
        addTabs();

        //Add an OnTabSelectedListener
        tabLayout.addOnTabSelectedListener(getListener());

        bottomSheet = findViewById(R.id.types_linear_layout);
        bsb = BottomSheetBehavior.from(bottomSheet);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, TYPE_LIST);
        typeListView = findViewById(R.id.types_list_view);
        typeListView.setAdapter(listViewAdapter);

        bsb.setState(BottomSheetBehavior.STATE_HIDDEN);

        downIcon = findViewById(R.id.down_icon);
        downIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bsb.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });


        Fragment fragment = new DefendingFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout,fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        position = 0;

        typeListView.setOnItemClickListener(getOnItemClickListener());
    }

    @NotNull
    private AdapterView.OnItemClickListener getOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String item = (String) adapterView.getItemAtPosition(i);
                DefendingFragment fragmentDef;
                AttackingFragment fragmentAtt;
                if(position == 0) {
                    String firstType;
                    String secondType;
                    int selectedButton;
                    DefendingFragment fr = (DefendingFragment)getSupportFragmentManager().getFragments().get(0);
                    firstType = fr.getFirstType();
                    secondType = fr.getSecondType();
                    selectedButton = fr.getSelectedButton();
                    if(selectedButton==1) {
                        firstType = item;
                    } else if(selectedButton==2) {
                        secondType = item;
                    }


                    if(!firstType.equalsIgnoreCase("") && secondType.equalsIgnoreCase("") && selectedButton ==1) {
                        fragmentDef = new DefendingFragment();
                        fragmentDef.setType(firstType,secondType,selectedButton);
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.simpleFrameLayout,fragmentDef);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.commit();
                    } else if (!secondType.equalsIgnoreCase("") && firstType.equalsIgnoreCase("") && selectedButton==2) {
                        fragmentDef = new DefendingFragment();
                        fragmentDef.setType(firstType,secondType,selectedButton);
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.simpleFrameLayout,fragmentDef);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.commit();
                    } else if (!secondType.equalsIgnoreCase("") && !firstType.equalsIgnoreCase("")) {
                        fragmentDef = new DefendingFragment();
                        fragmentDef.setDoubleType(firstType,secondType);
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.simpleFrameLayout,fragmentDef);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.commit();
                    }


                } else if(position == 1) {
                    fragmentAtt = new AttackingFragment();
                    fragmentAtt.setType(item);
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.simpleFrameLayout,fragmentAtt);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }

                view.animate().setDuration(2000);
                HideBottomSheet();
            }
        };
    }

    private void addTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Defending").setIcon(R.drawable.shield));
        tabLayout.addTab(tabLayout.newTab().setText("Attacking").setIcon(R.drawable.flash));
    }

    private Callback<TypeWeakness> getCallbackWeakness() {
        return new Callback<TypeWeakness>() {
            @Override
            public void onResponse(Call<TypeWeakness> call, Response<TypeWeakness> response) {
                if(response.isSuccessful()) {
                    responseType = response.body();
                }
                Log.d("DefendingFragment", responseType.getCrystal().getCrystal()+"");
            }

            @Override
            public void onFailure(Call<TypeWeakness> call, Throwable t) {
                t.printStackTrace();
                call.cancel();
            }
        };
    }

    private OnTabSelectedListener getListener() {
        return new OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch(tab.getPosition()) {
                    case 0:
                        position = tab.getPosition();
                        fragment = new DefendingFragment();
                        break;
                    case 1:
                        position = tab.getPosition();
                        fragment = new AttackingFragment();
                        break;
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout,fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    public void HideBottomSheet() {
        bsb.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public void ShowBottomSheet() {
        bsb.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}