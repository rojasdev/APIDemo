package com.demo.apidemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class DashboardFragment extends DialogFragment implements DialogListener{

    private static final String JSON_URL = "/cafeapi/seat/read.php";
    private GridViewAdapter adapter;

    private FloatingActionButton fabMenu;

    private GridView gridView;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        gridView = view.findViewById(R.id.gridView);
        adapter = new GridViewAdapter(requireActivity());
        //adapter = new GridViewAdapter(requireContext());
        gridView.setAdapter(adapter);

        fabMenu = view.findViewById(R.id.fab_menu);

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        connectToService();

        return view;
    }

    private void connectToService(){
        if(getIp() != "null") {
            String ipAPI = "http://" + getIp() + JSON_URL;
            Log.d("IP Recorded", ipAPI);
            new FetchDataAsyncTask(adapter).execute(ipAPI);
        }
    }
    private String getIp(){
        // Get the SharedPreferences object
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        // Retrieve data using keys, providing default values in case the key is not found
        String ip = sharedPreferences.getString("ip", "null");

        return ip;
    }
    private void showDialog() {
        // Create and show the AlertDialog
        FragmentCustomDialog dialogFragment = new FragmentCustomDialog();
        dialogFragment.setDialogDismissListener(this); // Set this fragment as the listener
        dialogFragment.show(getParentFragmentManager(), "MyDialogFragment");
    }

    @Override
    public void onDialogDismissed() {
        refreshFragment();
    }

    private void refreshFragment() {
        // Implement your refresh logic here, such as reloading data in the fragment
        //Log.e("REFRESH","Reffff");

        connectToService();

    }
}
