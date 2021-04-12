package com.example.newsapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class HomeFragment extends Fragment implements NewsItemAdapter.OnNewsListener {
    FragmentViewModel fragmentViewModel = new FragmentViewModel();
    HomeFragListner listener;
    SavedNewsViewModel savedNewsViewModel;
    NewsItemAdapter adapter;
    List<News> newsListInFrag = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout = v.findViewById(R.id.refreshLayoutId);
        progressBar = v.findViewById(R.id.circular_progress_id);
        savedNewsViewModel = new ViewModelProvider(getActivity()).get(SavedNewsViewModel.class);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new NewsItemAdapter(getActivity(), this, new NewsItemAdapter.OnSaveButtonListener() {
            @Override
            public void onSaveButtonClick(int position) {
                Toast.makeText(getContext(), "Article Saved", Toast.LENGTH_SHORT).show();
                SavedNews savedNews = new SavedNews(newsListInFrag.get(position).getDescription(), newsListInFrag.get(position).getTitle(), newsListInFrag.get(position).getUrlToImage(), newsListInFrag.get(position).getUrlToArticle());
                savedNewsViewModel.insert(savedNews);

            }
        });
        recyclerView.setAdapter(adapter);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fragmentViewModel.fetchNews();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        listener.showInput();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();


            MutableLiveData<List<News>> newsList = fragmentViewModel.getNewsList();

            newsList.observe(this, new Observer<List<News>>() {

                @Override
                public void onChanged(List<News> news) {

                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    adapter.setNews(news);
                    newsListInFrag = news;
                }
            });


    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        if (context instanceof HomeFragListner) {
            listener = (HomeFragListner) context;

        } else {
            throw new RuntimeException(context.toString() + "Implement HomeFragListener method");
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (newsListInFrag.isEmpty()) {
            fragmentViewModel.fetchNews();
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onNewsClick(int position) {
        Intent intent = new Intent(getActivity(), WebViewsActivity.class);
        intent.putExtra("News Selected", newsListInFrag.get(position));
        startActivity(intent);

    }

    public interface HomeFragListner {
        void showInput();
    }

}
