package com.example.hb;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

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
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;

public class Registration extends AppCompatActivity {


    private EditText DetailInformation, Deposit, Location, Roomspace, Maintencecost, MonthlyRent, LandlordInformation; //상세정보,옵션,위치,방주인정보
    private RadioGroup grp_Type, grp_Room;
    Date date;
    SimpleDateFormat mFormat;
    private Dialog dialog;

    int[] list = new int[22]; //리스트 옵션 바꿔야됌

    private ImageView image1, image2, image3, image4, image5;
    private final int PICK_IMAGE1 = 0;
    private final int PICK_IMAGE2 = 1;
    private final int PICK_IMAGE3 = 2;
    private final int PICK_IMAGE4 = 3;
    private final int PICK_IMAGE5 = 4;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
            dialog = builder.setTitle("경고")
                    .setMessage("뒤로 가기")
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

        switch (item.getItemId()) {
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
            Intent intent1 = new Intent(Registration.this, Main.class);
            startActivity(intent1);
        });
    }


    private void playBtn1() {
        findViewById(R.id.action_myinformation).setOnClickListener(view -> {
            Intent intent1 = new Intent(Registration.this, MyInformation.class);
            startActivity(intent1);
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("매물 등록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetailInformation = (EditText) findViewById(R.id.DetailInformation);
        Deposit = (EditText) findViewById(R.id.Deposit);
        Location = (EditText) findViewById(R.id.Location);
        Maintencecost = (EditText) findViewById(R.id.Maintencecost);
        Roomspace = (EditText) findViewById(R.id.Roomspace);
        MonthlyRent = (EditText) findViewById(R.id.MonthlyRent);


        grp_Type = (RadioGroup) findViewById(R.id.grp_Type);
        grp_Room = (RadioGroup) findViewById(R.id.grp_Room);

        grp_Type.setOnCheckedChangeListener(radioGroupButtonChangeListener1);
        grp_Room.setOnCheckedChangeListener(radioGroupButtonChangeListener2);

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1); //복층 체크박스
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2); //에어컨 체크박스
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3); //엘리베이터 체크박스
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4); //냉장고 체크박스
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5); //세탁기 체크박스
        CheckBox checkBox6 = (CheckBox) findViewById(R.id.checkBox6); //책상 체크박스
        CheckBox checkBox7 = (CheckBox) findViewById(R.id.checkBox7); //전자레인지 체크박스
        CheckBox checkBox8 = (CheckBox) findViewById(R.id.checkBox8); //옷장 체크박스
        CheckBox checkBox9 = (CheckBox) findViewById(R.id.checkBox9); //인덕션 체크박스
        CheckBox checkBox10 = (CheckBox) findViewById(R.id.checkBox10); //신발장 체크박스
        CheckBox checkBox11 = (CheckBox) findViewById(R.id.checkBox11); //베란다 체크박스
        CheckBox checkBox12 = (CheckBox) findViewById(R.id.checkBox12); //와이파이 체크박스
        CheckBox checkBox13 = (CheckBox) findViewById(R.id.checkBox13); //흡연가능여부 체크박스


        //복층 체크박스1
        checkBox1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[2] = 1;
                } else {
                    list[2] = 0;
                }
            }
        });

        //에어컨 체크박스2
        checkBox2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[3] = 1;
                } else {
                    list[3] = 0;
                }
            }
        });

        //엘리베이터 체크박스3
        checkBox3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[4] = 1;
                } else {
                    list[4] = 0;
                }
            }
        });

        //냉장고 체크박스4
        checkBox4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[5] = 1;
                } else {
                    list[5] = 0;
                }
            }
        });

        //세탁기 체크박스5
        checkBox5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[6] = 1;
                } else {
                    list[6] = 0;
                }
            }
        });

        //책상 체크박스6
        checkBox6.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[7] = 1;
                } else {
                    list[7] = 0;
                }
            }
        });

        //전자레인지 체크박스7
        checkBox7.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[8] = 1;
                } else {
                    list[8] = 0;
                }
            }
        });

        //옷장 체크박스8
        checkBox8.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[9] = 1;
                } else {
                    list[9] = 0;
                }
            }
        });

        //인덕션 체크박스9
        checkBox9.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[10] = 1;
                } else {
                    list[10] = 0;
                }
            }
        });

        //신발장 체크박스10
        checkBox10.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[11] = 1;
                } else {
                    list[11] = 0;
                }
            }
        });

        //베란다 체크박스11
        checkBox11.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[12] = 1;
                } else {
                    list[12] = 0;
                }
            }
        });

        //와이파이 체크박스12
        checkBox12.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[13] = 1;
                } else {
                    list[13] = 0;
                }
            }
        });

        //흡연가능여부 체크박스13
        checkBox13.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    list[14] = 1;
                } else {
                    list[14] = 0;
                }
            }
        });

        image1 = (ImageView) findViewById(R.id.image1);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent1, PICK_IMAGE1); //PICK_IMAGE에는 본인이 원하는 상수넣으면된다.
            }
        });

        image2 = (ImageView) findViewById(R.id.image2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent2, PICK_IMAGE2); //PICK_IMAGE에는 본인이 원하는 상수넣으면된다.
            }
        });
        image3 = (ImageView) findViewById(R.id.image3);
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setType("image/*");
                intent3.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent3, PICK_IMAGE3); //PICK_IMAGE에는 본인이 원하는 상수넣으면된다.
            }
        });
        image4 = (ImageView) findViewById(R.id.image4);
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setType("image/*");
                intent4.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent4, PICK_IMAGE4); //PICK_IMAGE에는 본인이 원하는 상수넣으면된다.
            }
        });
        image5 = (ImageView) findViewById(R.id.image5);
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent();
                intent5.setType("image/*");
                intent5.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent5, PICK_IMAGE5); //PICK_IMAGE에는 본인이 원하는 상수넣으면된다.
            }
        });
    }


    @Override //갤러리에서 이미지 불러온 후 행동
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지뷰에 세팅

                    image1.setImageBitmap(img);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == PICK_IMAGE2) {
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지뷰에 세팅

                    image2.setImageBitmap(img);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == PICK_IMAGE3) {
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지뷰에 세팅

                    image3.setImageBitmap(img);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == PICK_IMAGE4) {
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지뷰에 세팅

                    image4.setImageBitmap(img);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == PICK_IMAGE5) {
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지뷰에 세팅

                    image5.setImageBitmap(img);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void WriteH(View view) {
        //Toast.makeText(getApplicationContext(), "로그인 이동", Toast.LENGTH_SHORT).show();
        new JSONTask().execute("http://210.115.230.153:32430/write");
        long now = System.currentTimeMillis();
        date = new Date(now);
        mFormat = new SimpleDateFormat("yyMMdd HH:mm:ss");
        //mFormat.format(date)
        Intent intent1 = new Intent(Registration.this, Information.class);
        startActivity(intent1);
    }

    //데베
    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                JSONObject jsonObject = new JSONObject();
                //jsonObject.accumulate("Deposit", 0); //보증금 쓸 필요없으니까 0
                //txtDepositMax.getText().toString()
                jsonObject.accumulate("Yearrent", list[0]); //월세 전세 선택하는거
                jsonObject.accumulate("Rooms", list[1]); //원룸투룸
                jsonObject.accumulate("Floor", list[2]); //복층

                jsonObject.accumulate("Deposit", Deposit.getText()); //보증금
                jsonObject.accumulate("Maintencecost", Maintencecost.getText()); //관리비
                jsonObject.accumulate("Roomspace", Roomspace.getText()); //평수
                jsonObject.accumulate("MonthlyRent", MonthlyRent.getText()); //월세 금액

                jsonObject.accumulate("Airconditioner", list[3]);
                jsonObject.accumulate("Elevator", list[4]);
                jsonObject.accumulate("Refrigerator", list[5]);
                jsonObject.accumulate("Washer", list[6]);
                jsonObject.accumulate("Desk", list[7]);
                jsonObject.accumulate("Microwave", list[8]);
                jsonObject.accumulate("Closet", list[9]);
                jsonObject.accumulate("Induction", list[10]);
                jsonObject.accumulate("Shoecloset", list[11]);
                jsonObject.accumulate("Veranda", list[12]);
                jsonObject.accumulate("Wifi", list[13]);
                jsonObject.accumulate("Smoke", list[14]);

                jsonObject.accumulate("Text", DetailInformation.getText().toString()); //상세정보내용
                jsonObject.accumulate("Location", Location.getText().toString()); //위치
                jsonObject.accumulate("TimeRegister", mFormat.format(date));

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(urls[0]);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Cache-Control", "no-cache");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestProperty("Accept", "text/html");
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
            super.onPostExecute(result);
            //et_NickName.setText(result);
        }
    }

    //월세전세
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            Deposit = (EditText) findViewById(R.id.Deposit);
            MonthlyRent = (EditText) findViewById(R.id.MonthlyRent);

            //막는코드
            if (i == R.id.radioButton2) { //전세
                Toast.makeText(Registration.this, "전세가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                list[0] = 1; //유형
                MonthlyRent.setText("0");
            } else if (i == R.id.radioButton) { //월세
                Toast.makeText(Registration.this, "월세가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                list[0] = 0; //유형
                Deposit.setClickable(true);
                MonthlyRent.setFocusable(true);
                Deposit.setText("");
                MonthlyRent.setText("");
            }
        }
    };

    //원룸투룸
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.radioButton6) { //원룸
                list[1] = 1;
            } else if (i == R.id.radioButton7) { //투룸
                list[1] = 0;
            }
        }
    };
}
