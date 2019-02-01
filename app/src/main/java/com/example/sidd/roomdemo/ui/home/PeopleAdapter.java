package com.example.sidd.roomdemo.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sidd.roomdemo.R;
import com.example.sidd.roomdemo.data.Person;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PersonViewHolder>  {

    private final List<Person> data = new ArrayList<>();
    private static OnItemClickListener onItemClickListener;

    public PeopleAdapter(OnItemClickListener onItemClickListener) {
        PeopleAdapter.onItemClickListener = onItemClickListener;
    }

    public void setData(List<Person> people) {
        data.clear();
        data.addAll(people);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_person,
                viewGroup, false);
        return new PersonViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int position) {
        personViewHolder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvEmail;

        PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }

        void bind(Person person) {
            tvName.setText(person.name);
            tvEmail.setText(person.email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClicked(person);
                }
            });

        }
    }
}
