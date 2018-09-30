package com.example.gearquicker.hydrodinamicsimulator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class ModelFragment extends Fragment {

    ModelData modelData;
    DrawViewModel drawView;
    RelativeLayout waitPane;
    SeekBar seekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.model_fragment, container, false);
        RelativeLayout layout = view.findViewById(R.id.model_layout);
        waitPane = view.findViewById(R.id.waitPane);
        drawView = new DrawViewModel(view.getContext());
        layout.addView(drawView);

        modelData = ModelData.getInstance();

        seekBar = view.findViewById(R.id.seek_bar);
        seekBar.setMax(99);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                modelData.setCurrentT(seekBar.getProgress());
                drawView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calcMap();

        return view;
    }


    private void calcMap() {
        drawView.post(new Runnable() {
            @Override
            public void run() {
                waitPane.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.INVISIBLE);
                final double xStep = drawView.getWidth() / 20;
                final double yStep = drawView.getHeight() / 20;
                final double tStep = 0.36 * modelData.getPermeability() / modelData.getPorosity()
                        / modelData.getViscosity() / modelData.getCompressibility();

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        double P = 0;
                        double oldP = modelData.getPressure();
                        modelData.setMinP(modelData.getPressure());
                        modelData.setMaxP(modelData.getPressure());
                        for (int i = 0; i < 20; i++) {
                            for (int j = 0; j < 20; j++) {
                                for (int k = 0; k < 100; k++) {
                                    Formula formula = new Formula(i * xStep, j * yStep);
                                    P = GDIMath.calcPressure(modelData, formula, k * tStep);
                                    if (Double.isNaN(P)) {
                                        P = oldP;
                                    }
                                    modelData.setMinP(Math.min(modelData.getMinP(), P));
                                    modelData.setMaxP(Math.max(modelData.getMaxP(), P));
                                    modelData.getMap()[i][j][k] = P;
                                    oldP = P;
                                }
                            }
                        }
                        for (int i = 0; i < 20; i++) {
                            for (int j = 0; j < 20; j++) {
                                modelData.getMap()[i][j][0] = modelData.getPressure();
                            }
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                waitPane.setVisibility(View.INVISIBLE);
                                seekBar.setVisibility(View.VISIBLE);
                                drawView.invalidate();
                            }
                        });
                    }
                });
            }
        });
    }

}
