package com.iurizar.temtypesandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.pager);

        //Create the FragmentManager and Adapter for the ViewPager2
        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        //Add the tabs
        addTabs();

        //Add an OnTabSelectedListener
        tabLayout.addOnTabSelectedListener(getListener());

        //Add and OnPagerChangeCallback
        pager2.registerOnPageChangeCallback(getCallback());
    }

    private void addTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Defending").setIcon(R.drawable.shield));
        tabLayout.addTab(tabLayout.newTab().setText("Attacking").setIcon(R.drawable.flash));
    }

    private OnPageChangeCallback getCallback() {
        return new OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        };
    }

    private OnTabSelectedListener getListener() {
        return new OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }
}