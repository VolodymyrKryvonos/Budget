package com.example.vova.budjet;


import com.example.vova.budjet.fragments.FragmentAdd;


public class AddAction extends SingleFragmentActivity {

    @Override
    protected FragmentAdd createFragment() {
        return new FragmentAdd();
    }

}
