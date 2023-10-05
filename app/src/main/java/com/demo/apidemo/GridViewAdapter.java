package com.demo.apidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private List<TableModel> itemList;
    private final LayoutInflater inflater;

    private Context context;

    public GridViewAdapter(FragmentActivity activity) {
        this.context = activity;
        inflater = LayoutInflater.from(activity);
    }

    public void setData(List<TableModel> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList != null ? itemList.size() : 0;
    }

    @Override
    public TableModel getItem(int tableId) {
        return itemList != null ? itemList.get(tableId) : null;
    }

    @Override
    public long getItemId(int tableId) {
        return tableId;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(TableModel tableId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item_layout, parent, false);
            holder = new ViewHolder();
            holder.itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TableModel item = getItem(position);
        if (item != null) {
            holder.itemNameTextView.setText(item.getTableLabel());
        }

        // Handle item click event
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableModel selectedItem = getItem(position);
                if (selectedItem != null) {
                    // Pass the selected item's ID to the new fragment
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    Fragment newFragment = TableDetailFragment.newInstance(selectedItem.getTableId());
                    ft.replace(R.id.fragment_container, newFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView itemNameTextView;
    }
}

