package com.android.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.test.DetailsActivity;
import com.android.test.Model.Model;
import com.android.test.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.Serializable;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> implements Serializable {
    private Context context;
    //private List<Item> cartList;
    private List<Model> itemList;
    public Intent intent;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView name, email, ip_address, gender, employment_text;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);
            ip_address = view.findViewById(R.id.ip_address);
            gender = view.findViewById(R.id.gender);
            employment_text = view.findViewById(R.id.employment_text);
            thumbnail = view.findViewById(R.id.thumbnail);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }


    public ListAdapter(Context context, List<Model> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Model item = itemList.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.ip_address.setText(item.getIpAddress());
        holder.gender.setText(item.getGender());
        holder.employment_text.setText(item.getEmployment().getText());
        Glide.with(context)
                .load(item.getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original
                .into(holder.thumbnail);

        // intent = new Intent(context, DetailsActivity.class);

        //context.startActivity(intent);
       /* ArrayList<Model> arraylist = new ArrayList<Model>();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("test", (ArrayList<? extends Parcelable>) arraylist);
        intent.putExtras(bundle);*/
        // intent = intent.putExtra("test", item);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(context, DetailsActivity.class);
                intent.putExtra("item", position);
                intent.putExtra("name", item.getName());
                intent.putExtra("photo", item.getPhoto());
                /*Bundle bundle = new Bundle();
                bundle.putSerializable("test", (Serializable)item);
                intent.putExtras(bundle);*/
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void removeItem(int position) {
        itemList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Model item, int position) {
        itemList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
