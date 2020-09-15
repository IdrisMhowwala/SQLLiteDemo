package com.e.sqllitedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<Subject> data;
    private SQLHelper db;

    public Adapter(Context context, List<Subject> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        db=new SQLHelper(context);
        View view= LayoutInflater.from(context).inflate(R.layout.item_subject,parent,false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Subject subject=data.get(position);
        holder.id.setText(subject.getId());
        holder.title.setText(subject.getTitle());
        holder.description.setText(subject.getDescription());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleted=db.deleteData(subject.getId());
                if (deleted>0){
                    Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context,"Some Error Occured..!",Toast.LENGTH_SHORT).show();
                }
                notifyItemRemoved(Integer.parseInt(subject.getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView id;
        public TextView title;
        public TextView description;
        public Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            title=itemView.findViewById(R.id.title_textView);
            description=itemView.findViewById(R.id.description_textView);
            delete=itemView.findViewById(R.id.delete);

        }
    }
}
