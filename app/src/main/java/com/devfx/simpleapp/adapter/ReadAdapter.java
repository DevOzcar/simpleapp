package com.devfx.simpleapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;
import com.devfx.simpleapp.R;
import com.devfx.simpleapp.models.Contacts;
import com.devfx.simpleapp.util.GenericConverter;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

//no supe pasarlo a kotlin o.O

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.MyViewHolder> {

    private List<Contacts> data;
    private OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtLastName, txtAge, txtPhone;
        CircleImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            txtName = itemView.findViewById(R.id.txtName);
            txtLastName = itemView.findViewById(R.id.txtLastName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            avatar = itemView.findViewById(R.id.imgAvatar);
        }
    }

    public ReadAdapter(List<Contacts> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ReadAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        return new ReadAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReadAdapter.MyViewHolder holder, int position) {
        Contacts item = data.get(position);
        holder.txtName.setText(item.name);
        holder.txtLastName.setText(item.lastname);
        holder.txtAge.setText(item.age);
        holder.txtPhone.setText(item.phone);

        if (item.image != null)
            holder.avatar.setImageBitmap(GenericConverter.getBitmap(item.image));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onItem(item.phone);
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onItem(String value);
    }
}