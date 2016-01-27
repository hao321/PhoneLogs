package com.melo.phonelogs.phonelogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LogContentActivity extends AppCompatActivity {

    private TextView logsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_content);
        initView();
        setText();
    }

    private void initView() {
        logsTv = (TextView) findViewById(R.id.logs_tv);
    }

    private void setText() {
        String logsContent = getLogsContent();
        if(logsContent != null){
            logsTv.setText(logsContent );
        }
    }

    private String getLogsContent() {
        ArrayList<String> cmdList = new ArrayList<String>();
        cmdList.add("logcat");
        cmdList.add("- d");
        try {
            Process process = Runtime.getRuntime().exec(cmdList.toArray(new String[cmdList.size()]));
            BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while((line = bf.readLine()) != null){
                sb.append(line + "\b");
            }
            return  sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
