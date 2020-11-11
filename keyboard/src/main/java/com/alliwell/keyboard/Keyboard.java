package com.alliwell.keyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alliwell.keyboard.callback.IResultCallback;

import java.util.ArrayList;
import java.util.List;

public class Keyboard extends View {

    private final static String TAG = Keyboard.class.getSimpleName();

    private Context context;

    IResultCallback iResultCallback;

    private Paint mPaint;
    private float mWidth, mHeight;
    private float xStep, yStep;

    //支付键盘变量
    private int keyboardPoint = 0;
    private boolean keyboardZero = false;
    private static int moneyLength = 6;
    private String[] keyboard = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
    private List<Integer> list = new ArrayList<>();
    private StringBuilder builder = new StringBuilder();

    public void setIResultCallback(IResultCallback iResultCallback) {
        this.iResultCallback = iResultCallback;
    }

    public Keyboard(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    public Keyboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        xStep = mWidth / 4;
        yStep = mHeight / 4;

        float xPoint = xStep / 2 - 30;
        float yPoint = yStep / 2 + 30;

        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(xStep * 4, 0);
        path.moveTo(0, yStep);
        path.lineTo(mWidth, yStep);
        path.moveTo(xStep * 3, yStep * 2);
        path.lineTo(0, yStep * 2);
        path.moveTo(0, yStep * 3);
        path.lineTo(xStep * 3, yStep * 3);

        path.moveTo(xStep, 0);
        path.lineTo(xStep, yStep * 3);
        path.moveTo(xStep * 2, yStep * 4);
        path.lineTo(xStep * 2, 0);
        path.moveTo(xStep * 3, 0);
        path.lineTo(xStep * 3, yStep * 4);
        canvas.drawPath(path, mPaint);

        Paint picPaint = new Paint();
        Path path1 = new Path();
        picPaint.setStyle(Paint.Style.STROKE);
        picPaint.setColor(Color.parseColor("#333333"));
        picPaint.setStrokeWidth(4);
        picPaint.setStrokeCap(Paint.Cap.ROUND);
        picPaint.setStrokeJoin(Paint.Join.ROUND);
        picPaint.setAntiAlias(true);
        path1.moveTo(xStep * 3 + xPoint, yPoint);
        path1.lineTo(xStep * 3 + xPoint + 60, yPoint);
        path1.moveTo(xStep * 3 + xPoint + 60, yPoint);
        path1.lineTo(xStep * 3 + xPoint + 60, yPoint - 60);
        path1.moveTo(xStep * 3 + xPoint + 60, yPoint - 60);
        path1.lineTo(xStep * 3 + xPoint, yPoint - 60);
        path1.moveTo(xStep * 3 + xPoint, yPoint - 60);
        path1.lineTo(xStep * 3 + xPoint - 25, yPoint - 30);
        path1.moveTo(xStep * 3 + xPoint - 25, yPoint - 30);
        path1.lineTo(xStep * 3 + xPoint, yPoint);
        path1.moveTo(xStep * 3 + xPoint + 10, yPoint - 45);
        path1.lineTo(xStep * 3 + xPoint + 45, yPoint - 15);
        path1.moveTo(xStep * 3 + xPoint + 45, yPoint - 45);
        path1.lineTo(xStep * 3 + xPoint + 10, yPoint - 15);
        canvas.drawPath(path1, picPaint);

        Paint textPaint = new Paint();          // 创建画笔
        textPaint.setColor(Color.parseColor("#333333"));        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(80);              // 设置字体大小
        textPaint.setStrokeJoin(Paint.Join.ROUND);
        picPaint.setAntiAlias(true);


        canvas.drawText("1", xPoint, yPoint, textPaint);
        canvas.drawText("2", xStep + xPoint, yPoint, textPaint);
        canvas.drawText("3", xStep * 2 + xPoint, yPoint, textPaint);
        ;
//        canvas.drawText("x", xStep * 3 + xPoint + 15, yPoint, textPaint);

        canvas.drawText("4", xPoint, yStep + yPoint, textPaint);
        canvas.drawText("5", xStep + xPoint, yStep + yPoint, textPaint);
        canvas.drawText("6", xStep * 2 + xPoint, yStep + yPoint, textPaint);

        canvas.drawText("7", xPoint, yStep * 2 + yPoint, textPaint);
        canvas.drawText("8", xStep + xPoint, yStep * 2 + yPoint, textPaint);
        canvas.drawText("9", xStep * 2 + xPoint, yStep * 2 + yPoint, textPaint);
        canvas.drawText("支付", xStep * 3 + xPoint - 35, yStep * 2 + yPoint, textPaint);

        canvas.drawText("0", xStep - 25, yStep * 3 + yPoint, textPaint);
        textPaint.setTextSize(100);
        canvas.drawText(".", xStep * 2 + 25 + xPoint, yStep * 3 + yPoint - 25, textPaint);

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#DECADB"));
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            whichButton(x, y);
            return true;
        }
        //这句话不要修改
        return super.onTouchEvent(event);
    }


    private void whichButton(float x, float y) {
        Log.d(TAG, "whichButton: " + "x:" + x + "/" + "y:" + y);
        if (x > 0 && x < xStep) {
            if (y > 0 && y < yStep) {
                Log.d(TAG, "whichButton: 1");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[1]);
                        list.add(1);
                    }
                }
            }
            if (y > yStep && y < yStep * 2) {
                Log.d(TAG, "whichButton: 4");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[4]);
                        list.add(4);
                    }
                }
            }
            if (y > yStep * 2 && y < yStep * 3) {
                Log.d(TAG, "whichButton: 7");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[7]);
                        list.add(7);
                    }
                }
            }
            if (y > yStep * 3 && y < yStep * 4) {
                Log.d(TAG, "whichButton: 0");
                if (builder.length() == 0) {
                    builder.append(keyboard[0]);
                    list.add(0);
                    keyboardZero = true;
                } else if (builder.length() == 1) {
                    if (!keyboardZero) {
                        builder.append(keyboard[0]);
                        list.add(0);
                    }
                } else if (builder.length() >= 2 && builder.length() < moneyLength) {
                    builder.append(keyboard[0]);
                    list.add(0);
                }
            }
        }
        if (x > xStep && x < xStep * 2) {
            if (y > 0 && y < yStep) {
                Log.d(TAG, "whichButton: 2");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[2]);
                        list.add(2);
                    }
                }
            }
            if (y > yStep && y < yStep * 2) {
                Log.d(TAG, "whichButton: 5");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[5]);
                        list.add(5);
                    }
                }
            }
            if (y > yStep * 2 && y < yStep * 3) {
                Log.d(TAG, "whichButton: 8");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[8]);
                        list.add(8);
                    }
                }
            }
            if (y > yStep * 3 && y < yStep * 4) {
                Log.d(TAG, "whichButton: 0");
                if (builder.length() == 0) {
                    builder.append(keyboard[0]);
                    list.add(0);
                    keyboardZero = true;
                } else if (builder.length() == 1) {
                    if (!keyboardZero) {
                        builder.append(keyboard[0]);
                        list.add(0);
                    }
                } else if (builder.length() >= 2 && builder.length() < moneyLength) {
                    builder.append(keyboard[0]);
                    list.add(0);
                }
            }
        }
        if (x > xStep * 2 && x < xStep * 3) {
            if (y > 0 && y < yStep) {
                Log.d(TAG, "whichButton: 3");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[3]);
                        list.add(3);
                    }
                }
            }
            if (y > yStep && y < yStep * 2) {
                Log.d(TAG, "whichButton: 6");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[6]);
                        list.add(6);
                    }
                }
            }
            if (y > yStep * 2 && y < yStep * 3) {
                Log.d(TAG, "whichButton: 9");
                if (builder.length() < moneyLength) {
                    if (!keyboardZero) {
                        builder.append(keyboard[9]);
                        list.add(9);
                    }
                }
            }
            if (y > yStep * 3 && y < yStep * 4) {
                Log.d(TAG, "whichButton: .");
                if (builder.length() > 0 && keyboardPoint < 1) {
                    builder.append(keyboard[10]);
                    list.add(10);
                    keyboardPoint += 1;
                    keyboardZero = false;
                    moneyLength = list.size() + 2;
                    Log.d(TAG, "onClick: ---moneyLength" + list.size());
                }
            }
        }
        if (x > xStep * 3 && x < xStep * 4) {
            if (y > 0 && y < yStep) {
                Log.d(TAG, "whichButton: x");
                if (builder.length() == 0) {
                    Toast.makeText(context, "没有输入金额", Toast.LENGTH_SHORT).show();
                } else {
                    if (list.get(builder.length() - 1) == 10) {
                        keyboardPoint = 0;
                        keyboardZero = list.get(0) == 0;
                        moneyLength = 6;
                    } else if (list.get(builder.length() - 1) == 0) {
                        keyboardZero = false;
                    }
                    list.remove(builder.length() - 1);
                    builder.deleteCharAt(builder.length() - 1);
                }
            }
            if (y > yStep && y < yStep * 4) {
                Log.d(TAG, "whichButton: 支付");
                iResultCallback.clickOk();
            }
        }
        iResultCallback.result(builder.toString());
    }
}