package com.example.saveqq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.renderscript.ProgramFragmentFixedFunction.Builder.Format;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {
    private static final String TAG = "LoginActivity";
    private EditText et_qq;
    private EditText et_pw;
    private CheckBox cb_cb;
    private Button bt_login;
    //1.声明sp
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_qq=(EditText)findViewById(R.id.et_qq);
        et_pw=(EditText)findViewById(R.id.et_pw);
        cb_cb=(CheckBox)findViewById(R.id.cb_cb);
        bt_login=(Button)findViewById(R.id.bt_login);
        //2.获得一个参数
        sp=this.getSharedPreferences("context", 0);
        String qq=sp.getString("qq", "");
        String pw=sp.getString("pw", "");
        et_qq.setText(qq);
        et_pw.setText(pw);
    }

    public void login(View v){
        String qq=et_qq.getText().toString().trim();
        String pw=et_pw.getText().toString().trim();
        if(TextUtils.isEmpty(qq)||TextUtils.isEmpty(pw)){
            Toast.makeText(LoginActivity.this, "密码账号不能为空", 0).show();
            return;
        }
        if(cb_cb.isChecked()){//记住密码
            Log.i(TAG,"记住密码");
            //3.得到sp的文件编辑器
            Editor editor=sp.edit();
            editor.putString("qq", qq);
            editor.putString("pw", pw);
            //4.保存数据完毕一定要记的调用commit();
            editor.commit();
        }else{//不需要记住密码
            Log.i(TAG,"不需要记住密码");
        }
    }
}