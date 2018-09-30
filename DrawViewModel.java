package com.example.gearquicker.hydrodinamicsimulator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawViewModel extends View {

    ModelData modelData;

    public DrawViewModel(Context context) {
        super(context);
        modelData = ModelData.getInstance();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        double xStep = ((double) (this.getWidth())) / 20;
        double yStep = ((double) (this.getHeight())) / 20;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                paint.setColor(calcColor(modelData.getMap()[i][j][modelData.getCurrentT()]));
                canvas.drawRect((float) (i * xStep), (float) (j * yStep), (float) ((i + 1) * xStep), (float) ((j + 1) * yStep), paint);
            }
        }

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, (float) (2 * xStep), (float) (5 * yStep), paint);
        for (int i = 0; i < 20; i++) {
            paint.setColor(calcColor(modelData.getMaxP() - ((double) i / 19) * (modelData.getMaxP() - modelData.getMinP())));
            canvas.drawRect(0, (float) (i * yStep / 4), (float) xStep, (float) ((i + 1) * yStep / 4), paint);
        }
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText(String.valueOf((int) modelData.getMaxP()), (float) xStep, (float) (yStep / 2), paint);
        canvas.drawText(String.valueOf((int) modelData.getMinP()), (float) xStep, (float) (5 * yStep - yStep / 8), paint);
    }

    private int calcColor(double P) {
        double min = modelData.getMinP();
        double max = modelData.getMaxP();
        double step = (P - min) / (max - min);
        if (max == min) {
            return Color.rgb(127, 0, 127);
        } else {
            return Color.rgb((int) (step * 255), 0, (int) ((1 - step) * 255));
        }
    }

}
