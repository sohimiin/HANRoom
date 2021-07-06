package com.example.hb;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateReview extends AppCompatActivity {

    private ImageView image1, image2, image3, image4, image5;
    private RadioGroup OwnerGBRadio, HeatCoolRadio, OptionRadio, SoundRadio, WaterRadio, CCTVRadio;
    private RadioButton OwnerGB1,OwnerGB2,OwnerGB3,OwnerGB4,OwnerGB5,
            HeatCool1,HeatCool2,HeatCool3,HeatCool4,HeatCool5,
            Option1,Option2,Option3,Option4,Option5,
            Sound1,Sound2,Sound3,Sound4,Sound5,
            Water1,Water2,Water3,Water4,Water5,
            CCTV1,CCTV2,CCTV3,CCTV4,CCTV5;
    private RatingBar ratingBar;
    private EditText reviewtext;
    private AlertDialog dialog;
    
    private final int PICK_IMAGE1 = 0;
    private final int PICK_IMAGE2 = 1;
    private final int PICK_IMAGE3 = 2;
    private final int PICK_IMAGE4 = 3;
    private final int PICK_IMAGE5 = 4;
    int[] list = new int[6];

    Date date;
    SimpleDateFormat mFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);
        getSupportActionBar().setTitle("리뷰작성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        OwnerGBRadio = (RadioGroup) findViewById(R.id.OwnerGBRadio);
        HeatCoolRadio = (RadioGroup) findViewById(R.id.HeatCoolRadio);
        OptionRadio = (RadioGroup) findViewById(R.id.OptionRadio);
        SoundRadio = (RadioGroup) findViewById(R.id.SoundRadio);
        WaterRadio = (RadioGroup) findViewById(R.id.WaterRadio);
        CCTVRadio = (RadioGroup) findViewById(R.id.CCTVRadio);

        OwnerGB1 = findViewById(R.id.OwnerGB1);
        OwnerGB2 = findViewById(R.id.OwnerGB2);
        OwnerGB3 = findViewById(R.id.OwnerGB3);
        OwnerGB4 = findViewById(R.id.OwnerGB4);
        OwnerGB5 = findViewById(R.id.OwnerGB5);

        HeatCool1 = findViewById(R.id.HeatCool1);
        HeatCool2 = findViewById(R.id.HeatCool2);
        HeatCool3 = findViewById(R.id.HeatCool3);
        HeatCool4 = findViewById(R.id.HeatCool4);
        HeatCool5 = findViewById(R.id.HeatCool5);

        Option1 = findViewById(R.id.Option1);
        Option2 = findViewById(R.id.Option2);
        Option3 = findViewById(R.id.Option3);
        Option4 = findViewById(R.id.Option4);
        Option5 = findViewById(R.id.Option5);

        Sound1 = findViewById(R.id.Sound1);
        Sound2 = findViewById(R.id.Sound2);
        Sound3 = findViewById(R.id.Sound3);
        Sound4 = findViewById(R.id.Sound4);
        Sound5 = findViewById(R.id.Sound5);

        Water1 = findViewById(R.id.Water1);
        Water2 = findViewById(R.id.Water2);
        Water3 = findViewById(R.id.Water3);
        Water4 = findViewById(R.id.Water4);
        Water5 = findViewById(R.id.Water5);

        CCTV1 = findViewById(R.id.CCTV1);
        CCTV2 = findViewById(R.id.CCTV2);
        CCTV3 = findViewById(R.id.CCTV3);
        CCTV4 = findViewById(R.id.CCTV4);
        CCTV5 = findViewById(R.id.CCTV5);

        OwnerGBRadio.setOnCheckedChangeListener(radioGroupButtonChangeListener1);
        HeatCoolRadio.setOnCheckedChangeListener(radioGroupButtonChangeListener2);
        OptionRadio.setOnCheckedChangeListener(radioGroupButtonChangeListener3);
        SoundRadio.setOnCheckedChangeListener(radioGroupButtonChangeListener4);
        WaterRadio.setOnCheckedChangeListener(radioGroupButtonChangeListener5);
        CCTVRadio.setOnCheckedChangeListener(radioGroupButtonChangeListener6);

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

        Intent intent = getIntent();

        float ratingValue = intent.getFloatExtra("rating", 0);

        ratingBar = (android.widget.RatingBar) findViewById(R.id.ratingBar1);
        ratingBar.setRating(ratingValue);
        reviewtext = (EditText) findViewById(R.id.contentsInput);

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
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateReview.this);
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
            Intent intent1 = new Intent(CreateReview.this, Main.class);
            startActivity(intent1);
        });
    }


    private void playBtn1() {
        findViewById(R.id.action_myinformation).setOnClickListener(view -> {
            Intent intent1 = new Intent(CreateReview.this, MyInformation.class);
            startActivity(intent1);
        });
    }

    public void RButton(View view1){
        if(!OwnerGB1.isChecked() && !OwnerGB2.isChecked() && !OwnerGB3.isChecked() && !OwnerGB4.isChecked() && !OwnerGB5.isChecked()){
            //Toast.makeText(getApplicationContext(),"집주인 평가를 해주세요.",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateReview.this);
            dialog = builder.setMessage("집주인 평가를 해주세요.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
        }
        else if(!HeatCool1.isChecked() && !HeatCool2.isChecked() && !HeatCool3.isChecked() && !HeatCool4.isChecked() && !HeatCool5.isChecked()){
            //Toast.makeText(getApplicationContext(),"난방 및 냉방 평가를 해주세요.",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateReview.this);
            dialog = builder.setMessage("난방 및 냉방 평가를 해주세요.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
        }
        else if(!Option1.isChecked() && !Option2.isChecked() && !Option3.isChecked() && !Option4.isChecked() && !Option5.isChecked()){
            //Toast.makeText(getApplicationContext(),"옵션 상태 평가를 해주세요.",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateReview.this);
            dialog = builder.setMessage("옵션상태 평가를 해주세요.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
        }
        else if(!Sound1.isChecked() && !Sound2.isChecked() && !Sound3.isChecked() && !Sound4.isChecked() && !Sound5.isChecked()){
            //Toast.makeText(getApplicationContext(),"방음 평가를 해주세요.",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateReview.this);
            dialog = builder.setMessage("방음 평가를 해주세요.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
        }
        else if(!Water1.isChecked() && !Water2.isChecked() && !Water3.isChecked() && !Water4.isChecked() && !Water5.isChecked()){
            //Toast.makeText(getApplicationContext(),"수압 평가를 해주세요.",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateReview.this);
            dialog = builder.setMessage("수압 평가를 해주세요.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
        }
        else if(!CCTV1.isChecked() && !CCTV2.isChecked() && !CCTV3.isChecked() && !CCTV4.isChecked() && !CCTV5.isChecked()){
            //Toast.makeText(getApplicationContext(),"수압 평가를 해주세요.",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateReview.this);
            dialog = builder.setMessage("보안 평가를 해주세요.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
        }
        else{
            findViewById(R.id.writeButton).setOnClickListener(view -> {
                new JSONTask().execute("http://210.115.230.153:32430/review");
                long now = System.currentTimeMillis();
                date = new Date(now);
                mFormat = new SimpleDateFormat("yyMMdd HH:mm:ss");
                Toast.makeText(getApplicationContext(),"리뷰 작성이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(CreateReview.this, Information.class);
                startActivity(intent1);
            });
        }
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
        }
        else if(requestCode==PICK_IMAGE2){
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
        }
        else if(requestCode==PICK_IMAGE3){
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
        }
        else if(requestCode==PICK_IMAGE4){
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
        }
        else if(requestCode==PICK_IMAGE5){
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



    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Text", reviewtext.getText().toString());
                jsonObject.put("OwnerGB", list[0]);
                jsonObject.put("HeatCooling", list[1]);
                jsonObject.put("Optionsoperation", list[2]);
                jsonObject.put("Soundproof", list[3]);
                jsonObject.put("Waterpressure", list[4]);
                jsonObject.put("CCTV", list[5]);
                jsonObject.put("Rating", ratingBar.getRating());
                jsonObject.put("TimeRegister", mFormat.format(date));

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

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.OwnerGB1) {
                list[0] = 5; //매우 만족
            } else if (i == R.id.OwnerGB2) {
                list[0] = 4; //만족
            }else if (i == R.id.OwnerGB3) {
                list[0] = 3; //만족
            }else if (i == R.id.OwnerGB4) {
                list[0] = 2; //만족
            }else if (i == R.id.OwnerGB5) {
                list[0] = 1; //만족
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.HeatCool1) {
                list[1] = 5; //매우 만족
            } else if (i == R.id.HeatCool2) {
                list[1] = 4; //만족
            }else if (i == R.id.HeatCool3) {
                list[1] = 3; //만족
            }else if (i == R.id.HeatCool4) {
                list[1] = 2; //만족
            }else if (i == R.id.HeatCool5) {
                list[1] = 1; //만족
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.Option1) {
                list[2] = 5; //매우 만족
            } else if (i == R.id.Option2) {
                list[2] = 4; //만족
            }else if (i == R.id.Option3) {
                list[2] = 3; //만족
            }else if (i == R.id.Option4) {
                list[2] = 2; //만족
            }else if (i == R.id.Option5) {
                list[2] = 1; //만족
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener4 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.Sound1) {
                list[3] = 5; //매우 만족
            } else if (i == R.id.Sound2) {
                list[3] = 4; //만족
            }else if (i == R.id.Sound3) {
                list[3] = 3; //만족
            }else if (i == R.id.Sound4) {
                list[3] = 2; //만족
            }else if (i == R.id.Sound5) {
                list[3] = 1; //만족
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener5 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.Water1) {
                list[4] = 5; //매우 만족
            } else if (i == R.id.Water2) {
                list[4] = 4; //만족
            }else if (i == R.id.Water3) {
                list[4] = 3; //만족
            }else if (i == R.id.Water4) {
                list[4] = 2; //만족
            }else if (i == R.id.Water5) {
                list[4] = 1; //만족
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener6 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.CCTV1) {
                list[5] = 5; //매우 만족
            } else if (i == R.id.CCTV2) {
                list[5] = 4; //만족
            }else if (i == R.id.CCTV3) {
                list[5] = 3; //만족
            }else if (i == R.id.CCTV4) {
                list[5] = 2; //만족
            }else if (i == R.id.CCTV5) {
                list[5] = 1; //만족
            }
        }
    };
}
