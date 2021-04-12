package com.example.newsapp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SavedFragment extends Fragment implements SavedNewsItemAdapter.OnNewsListener {
    private SavedNewsViewModel viewModel;
    SavedNewsItemAdapter adapter;
    List<SavedNews> newsListInFrag = new ArrayList<>();
    LiveData<List<SavedNews>> demoLiveList;
    SavedNewsViewModel savedNewsViewModel;
    ProgressBar progressBar;
    TextView emptyListTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_saved,container,false);
        emptyListTextView=v.findViewById(R.id.emptyListInformerTextView);
        progressBar=v.findViewById(R.id.circular_progress2_id);
        savedNewsViewModel=new ViewModelProvider(getActivity()).get(SavedNewsViewModel.class);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view_saved);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new SavedNewsItemAdapter(getActivity(), this, new SavedNewsItemAdapter.OnDeleteButtonListener() {
            @Override
            public void onDeleteButtonClick(int position) {
                Toast.makeText(getContext(), "Article Deleted", Toast.LENGTH_SHORT).show();
                savedNewsViewModel.delete(newsListInFrag.get(position));
                if(newsListInFrag.size()==1){
                    emptyListTextView.setVisibility(TextView.VISIBLE);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(getActivity()).get(SavedNewsViewModel.class);
        viewModel.getListOfSavedNews().observe(getViewLifecycleOwner(), new Observer<List<SavedNews>>() {
            @Override
            public void onChanged(List<SavedNews> savedNews) {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                if(savedNews.isEmpty()){
                    emptyListTextView.setText("No article is saved yet");
                    adapter.setNews(savedNews);// we called it here so that everytime live data changes our recycler view also changes
                    newsListInFrag = savedNews;
                }else{
                    emptyListTextView.setVisibility(TextView.INVISIBLE);
                    adapter.setNews(savedNews);
                    newsListInFrag = savedNews;
                }

            }
        });

        return v;
    }

    @Override
    public void onNewsClick(int position) {
        Intent intent = new Intent(getActivity(),WebViewsActivity.class);
        intent.putExtra("SavedNews Selected",newsListInFrag.get(position));
        startActivity(intent);
    }
}
