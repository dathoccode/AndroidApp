package com.example.contactadvancedemotry1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter implements Filterable {
    // Activity chứa list view
    private Activity activity;

    // Array list chứa dữ liệu cho Adapter
    private ArrayList<User> data;

    // inflater để phân tích giao diện cho 1 phần tử
    private LayoutInflater inflater;

    private ArrayList<User> dataBackup;

    public UserAdapter(Activity activity, ArrayList<User> data) {
        this.activity = activity;
        this.data = data;
        this.dataBackup = data;
        inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.contact_layout, null);

            // tham chiếu tới các components trong UI
            ImageView ivAvatar = v.findViewById(R.id.ivAvatar);
            ImageView iv_ic_phone = v.findViewById(R.id.iv_ic_phone);
            ImageView iv_ic_send = v.findViewById(R.id.iv_ic_send);
            CheckBox cbStatus = v.findViewById(R.id.cbStatus);
            TextView tvName = v.findViewById(R.id.tvName);
            TextView tvPhoneNum = v.findViewById(R.id.tvPhoneNum);

            // hiển thị các thông tin của phần tử thứ position lên UL
            tvName.setText(data.get(position).getName());
            tvPhoneNum.setText(data.get(position).getPhoneNum());
            cbStatus.setChecked(data.get(position).getStatus());

            // set up listener events
            cbStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // change status of user on click
                    data.get(position).setStatus(!data.get(position).getStatus());
                }
            });
            iv_ic_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.get(position).getPhoneNum()));
                    activity.startActivity(in);
                }
            });
            iv_ic_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + data.get(position).getPhoneNum()));
                }
            });
        }
        else {
            ImageView ivAvatar = v.findViewById(R.id.ivAvatar);
            ImageView iv_ic_phone = v.findViewById(R.id.iv_ic_phone);
            ImageView iv_ic_send = v.findViewById(R.id.iv_ic_send);
            CheckBox cbStatus = v.findViewById(R.id.cbStatus);
            TextView tvName = v.findViewById(R.id.tvName);
            TextView tvPhoneNum = v.findViewById(R.id.tvPhoneNum);
            tvName.setText(data.get(position).getName());
            tvPhoneNum.setText(data.get(position).getPhoneNum());
            cbStatus.setChecked(data.get(position).getStatus());
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults fr = new FilterResults();

                // backup data
                if(dataBackup == null){
                    dataBackup = new ArrayList<>(data);
                }

                // restore data if there's no constaint
                if(constraint == null || constraint.length() == 0){
                    fr.count = dataBackup.size();
                    fr.values = dataBackup;
                    return fr;
                }
                else {  //do filtering
                    ArrayList<User> newData = new ArrayList<>();
                    for(User u : dataBackup) {
                        if(u.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                            newData.add(u);
                        }
                    }
                    fr.count = newData.size();
                    fr.values = newData;
                    return fr;
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = new ArrayList<User>((ArrayList<User>) results.values);
                notifyDataSetChanged();
            }
        };
        return null;
    }
}
