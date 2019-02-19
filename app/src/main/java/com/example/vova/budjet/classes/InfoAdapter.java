package com.example.vova.budjet.classes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.vova.budjet.R;
import java.util.ArrayList;


public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolderInfo> {

    private ArrayList<Info> infos = new ArrayList<>();

    public InfoAdapter(ArrayList<Info> infos) {
        this.infos = infos;
    }



    @NonNull
    @Override
    public ViewHolderInfo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolderInfo(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInfo holder, int position) {

        Info info = infos.get(position);
        holder.choise.setText(info.getChoise());
        holder.inform.setText(info.getShortDescribe());
        holder.monneyChange.setText(info.getMoneyChange());
        holder.date.setText(info.getDate().toString());

    }

    @Override
    public int getItemCount() {
        return infos == null ? 0 :infos.size();
    }

    public class ViewHolderInfo extends RecyclerView.ViewHolder {

        TextView monneyChange,date,inform,choise;


        public ViewHolderInfo(View itemView) {
            super(itemView);
            monneyChange = itemView.findViewById(R.id.money);
            date = itemView.findViewById(R.id.date);
            inform = itemView.findViewById(R.id.info);
            choise = itemView.findViewById(R.id.action);
        }
    }
}

