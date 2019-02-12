package com.example.vova.budjet;


import android.support.v4.app.Fragment;

import com.example.vova.budjet.fragments.InfoFragment;


public class InfoActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new InfoFragment();
    }





}

