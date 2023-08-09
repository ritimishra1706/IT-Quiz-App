package com.example.quizgame.Adapters;
import com.example.quizgame.QuestionsActivity;
import com.example.quizgame.R;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import  android.view.View;
import com.example.quizgame.Models.SetModel;
import com.example.quizgame.databinding.ItemSetBinding;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
public class SetAdapter extends RecyclerView.Adapter<SetAdapter.viewHolder> {
    Context context;
    ArrayList<SetModel> list;
    public SetAdapter(Context context, ArrayList<SetModel> list){
        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_set,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        SetModel model= list.get(position);
        holder.binding.setName.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, QuestionsActivity.class);
                intent.putExtra("set",model.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        ItemSetBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemSetBinding.bind(itemView);
        }
    }
}


