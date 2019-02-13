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
import com.example.vova.budjet.classes.InfoLab;

import java.text.DecimalFormat;
import java.util.List;

public class InfoFragment extends android.support.v4.app.Fragment {

    private List<Info> mInfos;
    Button costs, income, balance;
    Info mInfo = new Info();
    double costsD = 0d, balanceD = 0d, incomeD = 0d;
    private RecyclerView infoList;
    private InfoAdapter mAdapter;
    InfoLab infoLab = InfoLab.get(getActivity());

    private class InfoHolder extends RecyclerView.ViewHolder {
        private TextView mMoneyChange;
        private TextView mDateTextView;
        private TextView mDescriptionTextView;
        private TextView mActionTextView;

        public InfoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item, parent, false));
            mMoneyChange = (TextView) itemView.findViewById(R.id.money);
            mDateTextView = (TextView) itemView.findViewById(R.id.date);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.info);
            mActionTextView = (TextView) itemView.findViewById(R.id.action);


            mMoneyChange.setText(mInfo.getMoneyChange());
            mDescriptionTextView.setText(mInfo.getShortDescribe());
            mActionTextView.setText(mInfo.getChoise());
            mDateTextView.setText(mInfo.getDate().toString());

        }
    }


    private class InfoAdapter extends RecyclerView.Adapter<InfoHolder> {


        public InfoAdapter(List<Info> infos) {
            mInfos = infos;
        }


        @Override
        public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new InfoHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(InfoHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mInfos.size();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_info, container, false);
        infoList = (RecyclerView) view.findViewById(R.id.actions);
        costs = (Button) view.findViewById(R.id.costs);
        income = (Button) view.findViewById(R.id.income);
        balance = (Button) view.findViewById(R.id.balance);


        infoList.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAction.class);
                startActivityForResult(intent, 2);

            }
        });


        updateUI();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((data == null)||(!data.getExtras().getBoolean("used"))) return;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        if (data.getStringExtra("Choise").equals("Costs")) {
            infoLab.mInfos.add(new Info(data.getStringExtra("Money_change"),data.getStringExtra("Short_description"),data.getStringExtra("Describe"),data.getStringExtra("Choise")));
            costsD += Math.abs(Double.valueOf(decimalFormat.format(Double.valueOf(data.getStringExtra("Money_change")))));
            costs.setText("Costs: " + decimalFormat.format(costsD));
            balanceD -= costsD;
            balance.setText("Balance: " + decimalFormat.format(balanceD));
        } else {
            infoLab.mInfos.add(new Info(data.getStringExtra("Money_change"),data.getStringExtra("Short_description"),data.getStringExtra("Describe"),data.getStringExtra("Choise")));            incomeD += Math.abs(Double.valueOf(decimalFormat.format(Double.valueOf(data.getStringExtra("Money_change")))));
            income.setText("Income: " + decimalFormat.format(incomeD));
            balanceD += incomeD;
            balance.setText("Balance: " + decimalFormat.format(balanceD));
        }
    }


    private void updateUI() {

        List<Info> infos = infoLab.getInfos();
        mAdapter = new InfoAdapter(infos);
        infoList.setAdapter(mAdapter);
    }

}
