package com.example.catapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.catapp.Interface.DataApi;
import com.example.catapp.databinding.FragmentHomeBinding;
import com.example.catapp.ui.Model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static DataApi API_ROUTE;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        final Button btnhome = binding.btnHome;
        btnhome.setOnClickListener(new View.OnClickListener() {
            final TextView txt1 = binding.text1;
            final TextView txt2 = binding.text2;
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://catfact.ninja/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API_ROUTE = retrofit.create(DataApi.class);
                Call<Data> call = API_ROUTE.getPosts();

                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        if(response.isSuccessful()){
                            Data data = response.body();
                            txt1.setText(data.getFact());
                            txt2.setText(data.getLength());
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {

                    }
                });
            }
        });
        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}