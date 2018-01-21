package com.example.wei.tw.dialog1;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.lang.ref.WeakReference;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btn2, btn3, btn4, btn5, btn6,
            btn7, btn8, btn9, btn10;

    private int year, month, day;
    private int hour, minute;

    // 選項陣列
    private String[] items = {"Coffee", "Tea", "Water"};
    // 選項狀態陣列
    private boolean[] selection = {false, false, false};
    // 沒有預選
    private int choice = -1;

    private static final int MAX = 100;
    private ProgressDialog progressDialog;
    private Handler progressHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);

        // 取得時間與日期
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
    }

    public void btnOneClick(View view) {

        // 建立對話框並顯示樣式
        AlertDialog.Builder d = new AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Light_Dialog_Alert
        );
        // 設定標題
        d.setTitle(R.string.dialog_1_title_text);
        // 設定訊息
        d.setMessage(R.string.dialog_1_message_text);
        // 顯示對話框
        d.show();
    }

    public void btnTwoClick(View view) {

        // 建立對話框物件
        AlertDialog.Builder d = new AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Light_Dialog
        );

        // 設定標題，訊息與不可取消
        d.setTitle("Demo").setMessage("Are you hungry?")
                .setCancelable(false);

        // 加入確定按鈕
        d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn2.setText("Yes");
            }
        });

        // 加入中間按鈕
        d.setNeutralButton("Maybe", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn2.setText("Maybe");
            }
        });

        // 加入取消按鈕
        d.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn2.setText("No");
            }
        });

        // 顯示對話框
        d.show();
    }

    public void btnThreeClick(View view) {

        // 建立對話框物件
        AlertDialog.Builder d = new AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Dialog_Alert
        );

        // 設定標題與不可取消
        d.setTitle("Select...").setCancelable(false);

        // 設定選項
        // 第一個參數為提供選項的文字陣列
        // 第二個參數為選擇選項後的事件控制
        d.setItems(items, new DialogInterface.OnClickListener() {

            // int 值參數為操作的項目編號
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn3.setText(items[which]);
            }
        });

        // 顯示對話框
        d.show();
    }

    public void btnFourClick(View view) {

        AlertDialog.Builder d = new AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Dialog);

        d.setTitle("Select...");

        // 設定單選項目
        // 第一個參數為提供選項的字串陣列
        // 第二個參數為對話框出現時的預設選項，-1為沒有選擇
        // 第三個參數為選擇選項後的事件控制
        d.setSingleChoiceItems(items, choice,
                new DialogInterface.OnClickListener() {

            // 取得項目的操作編號
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice = which;
            }
        });

        d.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (choice != -1)
                    btn4.setText(items[choice]);
            }
        });

        // 加入取消鈕，事件指定為 null 值
        // 表示不作任何事情，只會關閉對話框
        d.setNegativeButton("Cancel", null);

        // 顯示對話框
        d.show();
    }

    public void btnFiveClick(View view) {
        AlertDialog.Builder d = new AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Light_Dialog_Alert);

        d.setTitle("Select...");

        // 設定多選項目
        // 第一個參數為提供選項的字串陣列
        // 第二個參數為對話框出現時的預設選項
        // 第三個參數為選擇選項後的事件控制
        d.setMultiChoiceItems(items, selection,
                new DialogInterface.OnMultiChoiceClickListener() {

                    // int 值參數為操作的項目編號
                    // boolean 值參數為是否勾選
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which, boolean isChecked) {
                        selection[which] = isChecked;
                    }
                });

        // 加入確認按鈕
        d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < selection.length; i++)
                    if (selection[i])
                        sb.append(items[i]).append(" ");

                if (sb.toString().equals(""))
                    btn5.setText(getResources()
                            .getString(R.string.btn_5));
                else
                    btn5.setText(sb.toString());
            }
        });

        d.show();
    }

    public void btnSixClick(View view) {

        // 取得載入畫面配置資源用的物件
        LayoutInflater inflater = getLayoutInflater();

        // 載入對話框使用畫面配置資源
        @SuppressLint("InflateParams")
        final View dialogView = inflater.inflate(
                R.layout.dialog_signin, null);

        // 建立對話框物件
        AlertDialog.Builder d = new AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Light_Dialog_Alert);

        // 設定對話框使用的畫面配置資源與標題
        d.setView(dialogView).setTitle(
                R.string.dialog_6_title_text);

        // 加入登入按鈕
        d.setPositiveButton("Sign in",
                new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(
                            DialogInterface dialog, int which) {

                        // 讀取帳號與密碼元件
                        EditText username =
                                dialogView.findViewById(R.id.userName);
                        EditText password =
                                dialogView.findViewById(R.id.password);

                        String usernameValue = username.getText().toString();
                        String passwordValue = password.getText().toString();

                        String simonUsername = getString(R.string.simon_username);
                        String simonPassword = getString(R.string.simon_password);

                        // 取得 Resources 物件
                        Resources r = getResources();

                        if (!(usernameValue.equals(simonUsername) &&
                                (passwordValue.equals(simonPassword)))) {

                            // 讀取顏色資源
                            int color = ContextCompat.getColor(
                                    MainActivity.this,
                                    R.color.failure_text_color
                            );
                            btn6.setTextColor(color);

                            // 讀取尺寸資源
                            float dimen = r.getDimension(R.dimen.failure_text_size);
                            btn6.setTextSize(dimen);

                            btn6.setText(R.string.sign_in_failure);
                        } else {

                            // 讀取顏色資源
                            int color = ContextCompat.getColor(
                                    MainActivity.this,
                                    R.color.btn_text_color
                            );
                            btn6.setTextColor(color);

                            // 讀取尺寸資源
                            float dimen = r.getDimension(R.dimen.btn_text_size);
                            btn6.setTextSize(dimen);

                            btn6.setText(R.string.sign_in_success);
                        }
                    }
                });

        // 加入取消按鈕
        d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn6.setText("Cancel");
            }
        });

        d.show();
    }

    // 選擇日期
    public void btnSevenClick(View view) {

        // 日期設定監聽類別
        DatePickerDialog.OnDateSetListener listener =
                new DatePickerDialog.OnDateSetListener() {


            // 三個 int 值參數依序為年、月(一月為 0、二月為 1…)、日
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker,
                                  int year, int monthOfYear,
                                  int dayOfMonth) {

                MainActivity.this.year = year;
                MainActivity.this.month = monthOfYear;
                MainActivity.this.day = dayOfMonth;
                btn7.setText(year + "/" + (monthOfYear+1) + "/" + dayOfMonth);
            }
        };

        // 建立日期對話框物件
        // 第一個參數是使狦對話框的 Activity 元件
        // 第二個參數是日期設定的監聽物件
        // 第三到第五個參數是預設的年、月、日
        DatePickerDialog d = new DatePickerDialog(
                MainActivity.this,
                listener, year, month, day
        );

        // 顯示日期對話框
        d.show();
    }

    public void btnEightClick(View view) {

        // 時間設定監聽類別
        TimePickerDialog.OnTimeSetListener listener =
                new TimePickerDialog.OnTimeSetListener() {

            // 兩個 int 值參數為時與分
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker,
                                  int hourOfDay, int minute) {
                MainActivity.this.hour = hourOfDay;
                MainActivity.this.minute = minute;

                btn8.setText(hourOfDay + "：" + minute);
            }
        };

        // 建立時間對話框物件
        // 第一個參數是使用對話框的 Activity 物件
        // 第二個參數是時間設定的監聽物件
        // 第三個、第四個參數是預設的時與分
        // 第五個參數設定為 true 表示使用 24 小時制
        // false 表示使用 12 小時制
        TimePickerDialog d = new TimePickerDialog(
                this,
                listener,
                hour,
                minute,
                false
        );

        d.show();
    }

    public void btnNineClick(View view) {
        ProgressDialog d = new ProgressDialog(
                this,
                android.R.style.Theme_Material_Light_Dialog_Alert
        );

        d.setTitle("Download");
        d.setMessage("Please wait for download...");
        d.setCancelable(true);

        // 加入取消按鈕
        // 第一個參數是按鈕的種類
        // 第二個參數是按鈕的文字
        // 第三個參數是處理按鈕事件的監聽物件
        d.setButton(DialogInterface.BUTTON_NEGATIVE,
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn9.setText("Cancel");
                    }
                });

        d.show();
    }

    public void btnTenClick(View view) {
        progressDialog = new ProgressDialog(MainActivity.this);

        // 設定進度對話框的標題、訊息與進度樣式
        progressDialog.setTitle("Download");
        progressDialog.setMessage("Please wait for download...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // 設定進度對話框的最大值為 100
        progressDialog.setMax(MAX);

        // 設定進兖對話框目前的進度
        progressDialog.setProgress(0);

        // 第一次執行 Handler
        progressHandler.sendEmptyMessage(0);

        // 加入取消按鈕
        progressDialog.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        btn10.setText("Cancel");
                        progressHandler.removeMessages(0);
                        progressDialog.setProgress(0);
                    }
                });

        progressDialog.show();
    }

    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mainActivity;

        private MyHandler(MainActivity activity) {
            mainActivity = new WeakReference<>(activity);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mainActivity.get();

            if (activity != null) {
                super.handleMessage(msg);

                if (activity.progressDialog.getProgress() >= MAX) {
                    // 已經處理完成，關閉對話框
                    activity.progressDialog.dismiss();
                    activity.btn10.setText("Done");
                } else {
                    // 增加進度對話框目前的進度
                    activity.progressDialog.incrementProgressBy(2);
                    // 0.1 秒後再執行一次 Handler
                    activity.progressHandler.sendEmptyMessageDelayed(0, 100);
                }
            }
        }
    }
}
