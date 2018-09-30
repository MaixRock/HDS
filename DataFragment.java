package com.example.gearquicker.hydrodinamicsimulator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DataFragment extends Fragment {

    ModelData modelData;

    EditText layerWidth;
    EditText porosity;
    EditText volume_factor;
    EditText viscosity;
    EditText compressibility;
    EditText permeability;
    EditText pressure;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.data_fragment, container, false);

        modelData = ModelData.getInstance();

        layerWidth = (EditText) view.findViewById(R.id.layer_width);
        porosity = (EditText) view.findViewById(R.id.porosity);
        volume_factor = (EditText) view.findViewById(R.id.volume_factor);
        viscosity = (EditText) view.findViewById(R.id.viscosity);
        compressibility = (EditText) view.findViewById(R.id.compressibility);
        permeability = (EditText) view.findViewById(R.id.permeability);
        pressure = (EditText) view.findViewById(R.id.pressure);

        layerWidth.setText(String.valueOf(modelData.getLayerWidth()));
        porosity.setText(String.valueOf(modelData.getPorosity()));
        volume_factor.setText(String.valueOf(modelData.getVolume_factor()));
        viscosity.setText(String.valueOf(modelData.getViscosity()));
        compressibility.setText(String.valueOf(modelData.getCompressibility()));
        permeability.setText(String.valueOf(modelData.getPermeability()));
        pressure.setText(String.valueOf(modelData.getPressure()));

        layerWidth.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                modelData.setLayerWidth(getNewValue(modelData.getLayerWidth(), layerWidth.getText().toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        porosity.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                modelData.setPorosity(getNewValue(modelData.getPorosity(), porosity.getText().toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        volume_factor.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                modelData.setVolume_factor(getNewValue(modelData.getVolume_factor(), volume_factor.getText().toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        viscosity.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                modelData.setViscosity(getNewValue(modelData.getViscosity(), viscosity.getText().toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        compressibility.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                modelData.setCompressibility(getNewValue(modelData.getCompressibility(), compressibility.getText().toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        permeability.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                modelData.setPermeability(getNewValue(modelData.getPermeability(), permeability.getText().toString()));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        pressure.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                modelData.setPressure(getNewValue(modelData.getPressure(), pressure.getText().toString()));
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
