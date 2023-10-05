package com.demo.apidemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TableDetailFragment extends Fragment {
    private static final String ARG_ITEM_ID = "item_id";

    public TableDetailFragment() {
        // Required empty public constructor
    }

    public static TableDetailFragment newInstance(int itemId) {
        TableDetailFragment fragment = new TableDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_ID, itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_detail, container, false);

        // Retrieve item ID from arguments
        int itemId = getArguments().getInt(ARG_ITEM_ID);

        TextView tvTable = view.findViewById(R.id.detailTextView);
        tvTable.setText(String.valueOf(itemId));


        // Now, you can use the itemId to fetch detailed information about the item from your data source
        // and display it in the fragment's layout

        return view;
    }
}