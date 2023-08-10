package com.example.quizgame;
import com.example.quizgame.Adapters.SetAdapter;
import com.example.quizgame.Models.SetModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.quizgame.databinding.ActivitySetsBinding;

import java.util.ArrayList;

public class SetsActivity extends AppCompatActivity {
    ActivitySetsBinding binding;
    ArrayList<SetModel>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        list= new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.setsRecy.setLayoutManager(linearLayoutManager);
        list.add(new SetModel("SET-1"));
        list.add(new SetModel("SET-2"));
        list.add(new SetModel("SET-3"));
        list.add(new SetModel("SET-4"));
        list.add(new SetModel("SET-5"));
        list.add(new SetModel("SET-6"));
        list.add(new SetModel("SET-7"));
        list.add(new SetModel("SET-8"));
        list.add(new SetModel("SET-9"));
        list.add(new SetModel("SET-10"));
        SetAdapter adapter=new SetAdapter(this,list);
        binding.setsRecy.setAdapter(adapter);

    }
}