package com.example.vova.budjet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vova.budjet.AddAction;
import com.example.vova.budjet.R;
import com.example.vova.budjet.classes.Info;
import com.example.vova.budjet.classes.InfoAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InfoFragment extends android.support.v4.app.Fragment {


    Button costs, income, balance;
    Info mInfo = new Info();
    double costsD = 0d, balanceD = 0d, incomeD = 0d;
    private RecyclerView infoList;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Info> mInfos = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_info, container, false);
        infoList = (RecyclerView) view.findViewById(R.id.actions);
        costs = (Button) view.findViewById(R.id.costs);
        income = (Button) view.findViewById(R.id.income);
        balance = (Button) view.findViewById(R.id.balance);


        infoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new InfoAdapter(mInfos);
        infoList.setAdapter(mAdapter);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAction.class);
                startActivityForResult(intent, 1);

            }
        });

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((data == null)||(!data.getExtras().getBoolean("used"))) return;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        Info info = (Info) data.getExtras().getSerializable("NEW_ITEM");
        if (info.getChoise().equals("Costs")) {

            costsD += Math.abs(Double.valueOf(decimalFormat.format(Double.valueOf(info.getMoneyChange()))));
            costs.setText("Costs: " + decimalFormat.format(costsD));
            balanceD -= costsD;
            balance.setText("Balance: " + decimalFormat.format(balanceD));
            addNewItem(info);
        } else {
            incomeD += Math.abs(Double.valueOf(decimalFormat.format(Double.valueOf(info.getMoneyChange()))));
            income.setText("Income: " + decimalFormat.format(incomeD));
            balanceD += incomeD;
            balance.setText("Balance: " + decimalFormat.format(balanceD));
            addNewItem(info);
        }
    }

    private void addNewItem(Info info){
        if (info!=null){
            info.setDate(new Date());
            mInfos.add(info);
            mAdapter.notifyDataSetChanged();
        }
    }


}
