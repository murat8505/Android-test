package com.android.test.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.test.*;
import com.android.test.Model.DetailsModel;

import java.util.ArrayList;
import java.util.List;



public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsVh> {


    private List<DetailsModel> detailsModels = new ArrayList<>();

    private Context context;

    public DetailsAdapter(Context context, String[] value) {
        this.context = context;

        detailsModels = DetailsModel.prepareDesserts(
                context.getResources().getStringArray(R.array.item_names), value);
    }

    @Override
    public DetailsVh onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_details, parent, false);
        return new DetailsVh(view);
    }

    @Override
    public void onBindViewHolder(DetailsVh holder, int position) {
        DetailsModel detailsModel = detailsModels.get(position);
        holder.mName.setText(detailsModel.getName());
        holder.mDescription.setText(detailsModel.getDescription());
        holder.mFirstLetter.setText(String.valueOf(detailsModel.getFirstLetter()));
    }

    @Override
    public int getItemCount() {
        return detailsModels == null ? 0 : detailsModels.size();
    }

    public static class DetailsVh extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;
        private TextView mFirstLetter;

        public DetailsVh(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.txt_name);
            mDescription = (TextView) itemView.findViewById(R.id.txt_desc);
            mFirstLetter = (TextView) itemView.findViewById(R.id.txt_firstletter);
        }
    }
}
