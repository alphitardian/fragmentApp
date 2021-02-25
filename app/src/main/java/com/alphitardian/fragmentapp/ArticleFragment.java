package com.alphitardian.fragmentapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArticleFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int currentPosition = 0;

    private TextView txtArticle;
    private View view;

    // check state when there is screen rotation
    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else {
            updateArticleView(currentPosition);
        }
    }

    public void updateArticleView(int position) {
        txtArticle.setText(TempData.articles[position]);
        currentPosition = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // load state when there is screen rotation
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_article, container, false);

        txtArticle = view.findViewById(R.id.txtArticle);

        return view;
    }

    // save state when there is screen rotation
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, currentPosition);
    }
}