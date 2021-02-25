package com.alphitardian.fragmentapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class HeadlinesFragment extends ListFragment {

    private int layout;

    OnHeadlinesSeletectedListener callback;

    // handle item selected
    public interface OnHeadlinesSeletectedListener {
        public void onHeadlineSelected(int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = android.R.layout.simple_list_item_1; // android basic listview
        setListAdapter(new ArrayAdapter(getActivity(), layout, TempData.headlines)); // simple array adapter
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        callback.onHeadlineSelected(position);
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getFragmentManager().findFragmentById(R.id.articles_container) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    // interface implementation checking
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (OnHeadlinesSeletectedListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement interface");
        }
    }
}
