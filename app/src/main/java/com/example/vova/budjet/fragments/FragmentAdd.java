package com.example.vova.budjet.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vova.budjet.R;
import com.example.vova.budjet.classes.Info;



public class FragmentAdd extends Fragment {

    Info mInfo;
    Spinner choise;
    EditText monneyChange, shortDiscribe, discribe;
    Button add, editDate;
    CheckBox isNeedDiscribe;



    boolean isNeedDisc = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfo = new Info();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_fragment, container, false);

        editDate = (Button) view.findViewById(R.id.date);
        choise = (Spinner) view.findViewById(R.id.choise);
        monneyChange = (EditText) view.findViewById(R.id.money_change);
        shortDiscribe = (EditText) view.findViewById(R.id.short_describe);
        discribe = (EditText) view.findViewById(R.id.more_information);
        add = (Button) view.findViewById(R.id.Add);
        isNeedDiscribe = (CheckBox) view.findViewById(R.id.is_need_describe);

        monneyChange.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mInfo.setMoneyChange(monneyChange.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        shortDiscribe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mInfo.setShortDescribe(s.toString());
        }

            @Override
            public void afterTextChanged(Editable s) {

            }
    });


        choise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mInfo.setChoise(choise.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        isNeedDiscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNeedDisc = !isNeedDisc;
                if (isNeedDisc) discribe.setVisibility(View.VISIBLE);
                else discribe.setVisibility(View.INVISIBLE);
            }
        });

        editDate.setText(mInfo.getDate().toString());


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(monneyChange.getText().toString().equals("")){
                  intent.putExtra("used",false);
                }
                else intent.putExtra("used",true);
                intent.putExtra("Money_change",monneyChange.getText().toString());
                intent.putExtra("Short_description",shortDiscribe.getText().toString());
                intent.putExtra("Choise",choise.getSelectedItem().toString());
                if (isNeedDisc!=false){
                    intent.putExtra("Describe",discribe.getText().toString());
                }
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        });

        return view;
    }



}
