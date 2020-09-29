package com.alliwell.keyboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alliwell.keyboard.callback.IResultCallback;
import com.alliwell.keyboard.databinding.ActivityKeyboardBinding;

import java.util.ArrayList;
import java.util.List;

public class Keyboard extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = Keyboard.class.getSimpleName();

    ActivityKeyboardBinding binding;

    IResultCallback iResultCallback;

    //支付键盘变量
    private int keyboardPoint = 0;
    private boolean keyboardZero = false;
    private static int moneyLength = 6;
    private String[] keyboard = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
    private List<Integer> list = new ArrayList<>();
    final StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_keyboard);

        binding.payNum0.setOnClickListener(this);

    }

    /**
     * @param view 数字键盘点击监听事件
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.pay_num_0) {
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
        } else if (id == R.id.pay_num_1) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[1]);
                    list.add(1);
                }
            }
        } else if (id == R.id.pay_num_2) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[2]);
                    list.add(2);
                }
            }
        } else if (id == R.id.pay_num_3) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[3]);
                    list.add(3);
                }
            }
        } else if (id == R.id.pay_num_4) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[4]);
                    list.add(4);
                }
            }
        } else if (id == R.id.pay_num_5) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[5]);
                    list.add(5);
                }
            }
        } else if (id == R.id.pay_num_6) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[6]);
                    list.add(6);
                }
            }
        } else if (id == R.id.pay_num_7) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[7]);
                    list.add(7);
                }
            }
        } else if (id == R.id.pay_num_8) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[8]);
                    list.add(8);
                }
            }
        } else if (id == R.id.pay_num_9) {
            if (builder.length() < moneyLength) {
                if (!keyboardZero) {
                    builder.append(keyboard[9]);
                    list.add(9);
                }
            }
        } else if (id == R.id.pay_point) {
            if (builder.length() > 0 && keyboardPoint < 1) {
                builder.append(keyboard[10]);
                list.add(10);
                keyboardPoint += 1;
                keyboardZero = false;
                moneyLength = list.size() + 2;
                Log.d(TAG, "onClick: ---moneyLength" + list.size());
            }
        } else if (id == R.id.pay_delete) {
            if (builder.length() == 0) {
                Toast.makeText(this, "没有输入金额", Toast.LENGTH_SHORT).show();
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

        iResultCallback.result(builder.toString());
//        else if (id == R.id.pay_back) {
//            finish();
//        }
//        binding.payPrice.setText(builder.toString());
        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
    }
}