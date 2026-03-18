package com.example.onthi1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> implements Filterable {

    List<Student> list;
    List<Student> listFull;

    public StudentAdapter(List<Student> list) {
        this.list = list;
        listFull = new ArrayList<>(list);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Student> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {

                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Student sv : listFull) {
                    if (sv.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(sv);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewScore;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewScore = itemView.findViewById(R.id.textViewScore);

            itemView.setOnLongClickListener(v -> {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.getMenuInflater().inflate(R.menu.menu_student, popup.getMenu());

                popup.setOnMenuItemClickListener(item -> {

                    if(item.getItemId() == R.id.edit){
                        Toast.makeText(v.getContext(),"Edit",Toast.LENGTH_SHORT).show();
                    }

                    if(item.getItemId() == R.id.delete){
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("Xác nhận")
                                .setMessage("Bạn có chắc chắn muốn xóa học sinh này không?")
                                .setPositiveButton("Có", (dialog, which) -> {
                                    DBHelper db = new DBHelper(v.getContext());
                                    db.deleteStudent(list.get(getBindingAdapterPosition()).getId());

                                    List<Student> newList = db.getAllStudents();
                                    updateData(newList);
                                })
                                .setNegativeButton("Không", null)
                                .show();

                    }

                    return true;
                });

                popup.show();
                return true;
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student sv = list.get(position);

        holder.textViewName.setText(sv.getName());
        holder.textViewScore.setText(String.valueOf(sv.Sum()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<Student> newList){
        list.clear();
        list.addAll(newList);

        listFull.clear();
        listFull.addAll(newList);

        notifyDataSetChanged();
    }

}