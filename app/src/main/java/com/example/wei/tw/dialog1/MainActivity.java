package com.example.wei.tw.dialog1;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // 畫面元件
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    // 選項陣列
    private String[] items = {"Coffee", "Tea", "Water"};
    // 選項狀態陣列
    private boolean[] selection = {false, false, false};
    // 沒有預選
    private int choice = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
    }

    public void btnOneClick(View view) {

        // 建立對話框並顯示樣式
        AlertDialog.Builder d = new AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Light_Dialog_Alert
        );
        // 設定標題
        d.setTitle("Demo");
        // 設定訊息
        d.setMessage("Dialog1 Activity");
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
                StringBuffer sb = new StringBuffer();

                for (int i = 0; i < selection.length; i++)
                    if (selection[i])
                        sb.append(items[i] + " ");

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
        final View dialogView = inflater.inflate(
                R.layout.dialog_signin, null);

        // 建立對話框物件
        AlertDialog.Builder d = new AlertDialog.Builder(
                this,
                android.R.style.Theme_Material_Light_Dialog_Alert);

        // 設定對話框使用的畫面配置資源與標題
        d.setView(dialogView).setTitle("Sign in");

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
                        btn6.setText(username.getText()
                                + "：" +
                                password.getText());
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
}
