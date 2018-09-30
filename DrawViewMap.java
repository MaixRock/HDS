package com.example.gearquicker.hydrodinamicsimulator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class DrawViewMap extends View {

    DrawViewMap drawView;
    ModelData modelData;

    float lastTouchX;
    float lastTouchY;

    public DrawViewMap(Context context) {
        super(context);
        drawView = this;
        modelData = ModelData.getInstance();

        drawView.setOnTouchListener(new OnTouchListener (){

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                lastTouchX = event.getX();
                lastTouchY = event.getY();

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Well well = getTouchedWell(lastTouchX, lastTouchY);
                    if (well == null) {
                        modelData.getWells().add(new Well(modelData.getCurentDebit(), lastTouchX, lastTouchY));
                    }else{
                        well.setDebit(modelData.getCurentDebit());
                    }
                    drawView.invalidate();
                }

                return false;
            }
        });

        drawView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Well well = getTouchedWell(lastTouchX, lastTouchY);
                if (well != null) {
                    modelData.getWells().remove(well);
                    drawView.invalidate();
                }
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius = 20;
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(radius * 2);

        for (Well well : modelData.getWells()) {
            canvas.drawCircle(well.getX(), well.getY(), radius, paint);
            canvas.drawText(String.valueOf(well.getDebit()), well.getX(), well.getY() - radius, paint);
        }
    }

    private Well getTouchedWell(float x, float y) {
        Well ans = null;
        for (Well well : modelData.getWells()) {
            double rad = Math.sqrt(Math.pow(well.getX() - x, 2) + Math.pow(well.getY() - y, 2));
            if (rad < 40) {
                ans = well;
                break;
            }
        }
        return ans;
    }

}