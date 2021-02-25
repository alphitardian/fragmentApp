package com.alphitardian.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlinesSeletectedListener {

    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.main_container) != null) {
            if (savedInstanceState != null) return;

            HeadlinesFragment headlinesFragment = new HeadlinesFragment();
            headlinesFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, headlinesFragment).commit();
        }
    }

    @Override
    public void onHeadlineSelected(int position) {
        ArticleFragment articleFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.articles_container);

        orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            articleFragment.updateArticleView(position);
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(ArticleFragment.ARG_POSITION, position);

            ArticleFragment articleFragment1 = new ArticleFragment();
            articleFragment1.setArguments(bundle);

            // start fragment transaction
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, articleFragment1);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}