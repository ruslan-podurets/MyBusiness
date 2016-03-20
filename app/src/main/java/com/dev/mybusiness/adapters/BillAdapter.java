package com.dev.mybusiness.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.mybusiness.App;
import com.dev.mybusiness.R;
import com.dev.mybusiness.models.Bill;

import java.util.List;

/**
 * Created by Rusik on 19.03.2016.
 */
public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private List<Bill> adapterList;
    private String outGoings;
    private String noDescription;

    public BillAdapter(List<Bill> adapterList) {
        this.adapterList = adapterList;
        outGoings = App.getApp().getString(R.string.outgoings);
        noDescription = App.getApp().getString(R.string.no_description);
    }

    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BillViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        Bill bill = adapterList.get(position);
        holder.textViewHeader.setText(bill.getName());
        String description = TextUtils.isEmpty(bill.getDescription()) ? noDescription : bill.getDescription();
        holder.textViewAdditionalInfo.setText(description);
        holder.textViewCash.setText(outGoings +" "+ String.valueOf(bill.getMoney()));
    }


    @Override
    public int getItemCount() {
        return adapterList == null ? 0 : adapterList.size();
    }

    protected static class BillViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewHeader;
        private TextView textViewAdditionalInfo;
        private TextView textViewCash;

        public BillViewHolder(View itemView) {
            super(itemView);
            textViewHeader = (TextView) itemView.findViewById(R.id.textViewHeader);
            textViewAdditionalInfo = (TextView) itemView.findViewById(R.id.textViewAdditionalInfo);
            textViewCash = (TextView) itemView.findViewById(R.id.textViewCash);
        }
    }
}
