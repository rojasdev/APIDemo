package com.demo.apidemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class FragmentCustomDialog extends DialogFragment {

    private Context context;

    private DialogListener dialogDismissListener;

    public FragmentCustomDialog() {
        // Required empty public constructor
    }

    public void setDialogDismissListener(DialogListener listener) {
        this.dialogDismissListener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_ip_dialog, null);

        // Set the background with rounded corners
        //dialogView.setBackgroundResource(R.drawable.rounded_corner_dialog_bg);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.editTextDialogInput);
        Button dialogButton = dialogView.findViewById(R.id.buttonDialogOk);



        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, e.g., get text from editText
                String inputText = editText.getText().toString();
                // Do something with the inputText
                saveIp(inputText);


                dismiss(); // Close the dialog
            }
        });

        return builder.create();
    }

    public void saveIp(String IP){
        // Get the SharedPreferences object
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        // Create an editor to modify SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Store data using editor
        editor.putString("ip", IP);

        // Apply changes
        editor.apply();
    }
}
