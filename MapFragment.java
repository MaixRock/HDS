package com.example.gearquicker.hydrodinamicsimulator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MapFragment extends Fragment {

    ModelData modelData;
    EditText debit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.map_layout);
        layout.addView(new DrawViewMap(view.getContext()));

        modelData = ModelData.getInstance();

        debit = (EditText) view.findViewById(R.id.debit);
        debit.setText(String.valueOf(modelData.getCurentDebit()));
        debit.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                modelData.setCurentDebit(getNewValue(modelData.getCurentDebit(), debit.getText().toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        return view;
    }

    private double getNewValue(double oldValue, String newValue) {
        double ans = oldValue;
        try {
            ans = Double.valueOf(newValue);
        } catch (Exception e) {
        }

        return ans;
    }
}