package com.example.hb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;

public class MyInformationDetails extends AppCompatActivity {

    private String id, name;
    private TextView textView20, textView22;
    private EditText et_PassWord, et_PassWord2, et_Phone;
    private Button bt_PassWordCheck;


    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information_details);
        getSupportActionBar().setTitle("내 정보 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("Name");

        textView20 = (TextView)findViewById(R.id.textView20);
        textView22 = (TextView)findViewById(R.id.textView22);

        textView20.setText(name);
        textView22.setText(id);

        //이름, 아이디 보이기
        et_PassWord = findViewById(R.id.et_PassWord);
        et_PassWord2 = findViewById(R.id.et_PassWord2);
        bt_PassWordCheck = findViewById(R.id.bt_PassWordCheck3);
        et_Phone = findViewById(R.id.et_Phone);

        findViewById(R.id.bt_Modify).setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MyInformationDetails.this);
            dialog = builder.setMessage("내 정보 수정이 완료 되었습니다.")
                    .setNegativeButton("취소", null)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new JSONTask().execute("http://210.115.230.153:32430/infoc");
                            Intent intent1 = new Intent(MyInformationDetails.this, MyInformation.class);
                            startActivity(intent1);
                            //password, phone바꾸기
                        }
                    })
                    .create();
            dialog.show();

        });

    }

    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", id);
                jsonObject.put("Password", et_PassWord.getText().toString());
                jsonObject.put("Phone", et_Phone.getText().toString());


                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(urls[0]);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Cache-Control", "no-cache");
                    con.setRequestProperty("Content-Type", "application/json");
                    // con.setRequestProperty("Accept", "text/html");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.connect();

                    OutputStream outStream = con.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();

                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    return buffer.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(result);
        }
    }
    public void onButtonPwCheckClicked(View view){
        if(!et_PassWord2.getText().toString().equals(et_PassWord.getText().toString())){
            //Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(MyInformationDetails.this);
            dialog = builder.setMessage("비밀번호가 일치하지 않습니다.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
            bt_PassWordCheck.setTextColor(Color.RED);
        }

        else if(bt_PassWordCheck.getText().toString().equals("") || et_PassWord.getText().toString().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(MyInformationDetails.this);
            dialog = builder.setMessage("비밀번호를 입력하세요.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
        }

        else{
            bt_PassWordCheck.setTextColor(Color.BLACK);
            Toast.makeText(getApplicationContext(),"비밀번호가 일치합니다.",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MyInformationDetails.this);
            dialog = builder.setTitle("경고")
                    .setMessage("확인 버튼을 누르면 내 정보 수정이 취소됩니다.")
                    .setNegativeButton("취소", null)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();
                        }
                    })
                    .create();
            dialog.show();
        }

        switch (item.getItemId()){
            case R.id.action_main:
                playBtn();
                return true;

            case R.id.action_myinformation:
                playBtn1();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void playBtn() {
        findViewById(R.id.action_main).setOnClickListener(view -> {
            Intent intent1 = new Intent(MyInformationDetails.this, Main.class);
            startActivity(intent1);
        });
    }


    private void playBtn1() {
        findViewById(R.id.action_myinformation).setOnClickListener(view -> {
            Intent intent1 = new Intent(MyInformationDetails.this, MyInformation.class);
            startActivity(intent1);
        });
    }
}
