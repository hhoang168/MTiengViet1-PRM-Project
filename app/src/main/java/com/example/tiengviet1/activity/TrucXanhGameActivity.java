package com.example.tiengviet1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.example.tiengviet1.R;
import com.example.tiengviet1.VolleySingleton;
import com.example.tiengviet1.dto.AlphabetDTO;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class TrucXanhGameActivity extends AppCompatActivity {

    private static final String TAG = "TrucXanhGameActivity";
    private String url = "https://prm391.herokuapp.com/api/alphabet";
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20;
    private List<AlphabetDTO> listAlphabet = null;
    private List<AlphabetDTO> result = null;
    private List<String> listImage = null;
    private List<Integer> listRanImg = new ArrayList<>();
    private List<ImageButton> listImgBtn = new ArrayList<>();
    private static ArrayList<Integer> listRandomNum = new ArrayList<>();
    HashMap<Integer, ArrayList> coupleImg = new HashMap<Integer, ArrayList>();
    private static ArrayList<String> listCoupleImg = new ArrayList<>();
    private static HashMap<ImageButton, String> imgOfButton = new HashMap<ImageButton, String>();
    private static int countImgBtn = 0;
    private static boolean allowClick = true;
    private static ImageButton imageButton1 = null;
    private static ImageButton imageButton2 = null;
    private static String backgroundBtn1 = "";
    private static String backgroundBtn2 = "";
    private static int key1;
    private static int key2;
    private boolean state = true;
    final int faceUpTime = 130;
    private LinearLayout layoutMain;
    private static int mark = 0;
    private TextView txtMark, txtTimer;
    private static final long START_TIME_IN_MILLIS = 180000;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truc_xanh_game);
        layoutMain = findViewById(R.id.layoutMain);
        txtMark = findViewById(R.id.txtMark);
        txtTimer = findViewById(R.id.txtTimer);
        setupView();
        addListImgBtn();
        volleyJsonArrayRequest(url);
        startTimer();

    }

    private void volleyJsonArrayRequest(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                List<AlphabetDTO> listDisplay = new ArrayList<>();
                listAlphabet = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        AlphabetDTO alphalbetDTO = new Gson().fromJson(jsonObject.toString(), AlphabetDTO.class);
                        listAlphabet.add(alphalbetDTO);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 10; i++) {
                    int s = random(listAlphabet.size());
                    listDisplay.add(listAlphabet.get(s));
                }
                listImage = new ArrayList<>();

                for (int i = 0; i < listDisplay.size(); i++) {
                    listCoupleImg = new ArrayList<>();
                    listCoupleImg.add(listDisplay.get(i).getThumbnail());
                    listCoupleImg.add(listDisplay.get(i).getListImages().get(1).getImgPath());
                    listImage.add(listDisplay.get(i).getThumbnail());
                    listImage.add(listDisplay.get(i).getListImages().get(1).getImgPath());
                    coupleImg.put(i, listCoupleImg);
                }
                java.util.Collections.shuffle(listImage);
                for (int i = 0; i < listImgBtn.size(); i++) {
                    //   Glide.with(getApplicationContext()).load(listImage.get(i).toString()).into(listImgBtn.get(i));
                    imgOfButton.put(listImgBtn.get(i), listImage.get(i).toString());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TrucXanhGameActivity.this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(this).getRequestQueue().add(jsonArrayRequest);
    }

    public int random(int size) {
        int s = new Random().nextInt(size);
        if (listRandomNum.size() > 0) {
            for (int i = 0; i < listRandomNum.size(); i++) {
                if (s == listRandomNum.get(i)) {
                    s = new Random().nextInt(size);
                    i = -1;
                }
            }
        }
        listRandomNum.add(s);
        return s;
    }

    public void setupView() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn20 = findViewById(R.id.btn20);

    }

    public void addListImgBtn() {
        listImgBtn.add(btn1);
        listImgBtn.add(btn2);
        listImgBtn.add(btn3);
        listImgBtn.add(btn4);
        listImgBtn.add(btn5);
        listImgBtn.add(btn6);
        listImgBtn.add(btn7);
        listImgBtn.add(btn8);
        listImgBtn.add(btn9);
        listImgBtn.add(btn10);
        listImgBtn.add(btn11);
        listImgBtn.add(btn12);
        listImgBtn.add(btn13);
        listImgBtn.add(btn14);
        listImgBtn.add(btn15);
        listImgBtn.add(btn16);
        listImgBtn.add(btn17);
        listImgBtn.add(btn18);
        listImgBtn.add(btn19);
        listImgBtn.add(btn20);
    }

    public void changeSelected(ImageButton buttonName) {
        if (buttonName.isSelected()) {
            buttonName.setImageResource(R.drawable.btn_chucai);
            buttonName.setSelected(false);
            state = false;
        } else {
            buttonName.setSelected(true);
            state = true;
            openImgButton(buttonName);
        }
    }

    public void openImgButton(ImageButton imageButton) {
        String imgDisplay = imgOfButton.get(imageButton);
        Glide.with(getApplicationContext()).load(imgDisplay).into(imageButton);
        imageButton.setTag(imgDisplay);
    }

    public void check(ImageButton imageButton) {
        if (countImgBtn == 2) {
            allowClick = false;
        }
        if (state && countImgBtn < 2) {
            countImgBtn++;
            compare(imageButton);
        }

    }

    public void compare(ImageButton imageButton) {
        if (countImgBtn == 1) {
            imageButton1 = imageButton;
            backgroundBtn1 = String.valueOf(imageButton1.getTag());
        }
        if (countImgBtn == 2) {
            imageButton2 = imageButton;
            if (imageButton1 == imageButton2) {
                countImgBtn = 1;
                allowClick = true;
                return;
            }
            listImgBtn.remove(imageButton1);
            listImgBtn.remove(imageButton2);


            for (int i = 0; i < listImgBtn.size(); i++) {
                listImgBtn.get(i).setEnabled(false);
            }

            backgroundBtn2 = String.valueOf(imageButton.getTag());

            for (Map.Entry<Integer, ArrayList> entry : coupleImg.entrySet()) {
                for (int i = 0; i < 2; i++) {
                    String s = entry.getValue().get(i).toString();
                    if (entry.getValue().get(i).equals(backgroundBtn1)) {
                        key1 = entry.getKey();
                    }
                    if (entry.getValue().get(i).equals(backgroundBtn2)) {
                        key2 = entry.getKey();
                    }
                }

            }
            if (key1 == key2) {
                mark += 10;
                txtMark = findViewById(R.id.txtMark);
                txtMark.setText(mark + "/100");
                samePic();
                listImgBtn.add(imageButton1);
                listImgBtn.add(imageButton2);
                for (int i = 0; i < listImgBtn.size(); i++) {
                    listImgBtn.get(i).setEnabled(true);
                }
                checkMark();

            } else {
                differentPic();
                listImgBtn.add(imageButton1);
                listImgBtn.add(imageButton2);
                for (int i = 0; i < listImgBtn.size(); i++) {
                    listImgBtn.get(i).setEnabled(true);
                }
            }
        }
    }

    public void bt1Click(View view) {
        if (allowClick) {
            changeSelected(btn1);
            new Handler().postDelayed(() -> {
                check(btn1);
            }, faceUpTime);
        }

    }

    public void bt2Click(View view) {
        if (allowClick) {
            changeSelected(btn2);
            new Handler().postDelayed(() -> {
                check(btn2);
            }, faceUpTime);
        }

    }

    public void bt3Click(View view) {
        if (allowClick) {
            changeSelected(btn3);
            new Handler().postDelayed(() -> {
                check(btn3);
            }, faceUpTime);
        }

    }

    public void bt4Click(View view) {
        if (allowClick) {
            changeSelected(btn4);
            new Handler().postDelayed(() -> {
                check(btn4);
            }, faceUpTime);
        }

    }

    public void bt5Click(View view) {
        if (allowClick) {
            changeSelected(btn5);
            new Handler().postDelayed(() -> {
                check(btn5);
            }, faceUpTime);
        }

    }

    public void bt6Click(View view) {
        if (allowClick) {
            changeSelected(btn6);
            new Handler().postDelayed(() -> {
                check(btn6);
            }, faceUpTime);
        }

    }

    public void bt7Click(View view) {
        if (allowClick) {
            changeSelected(btn7);
            new Handler().postDelayed(() -> {
                check(btn7);
            }, faceUpTime);
        }

    }

    public void bt8Click(View view) {
        if (allowClick) {
            changeSelected(btn8);
            new Handler().postDelayed(() -> {
                check(btn8);
            }, faceUpTime);
        }

    }

    public void bt9Click(View view) {
        if (allowClick) {
            changeSelected(btn9);
            new Handler().postDelayed(() -> {
                check(btn9);
            }, faceUpTime);
        }

    }

    public void bt10Click(View view) {
        if (allowClick) {
            changeSelected(btn10);
            new Handler().postDelayed(() -> {
                check(btn10);
            }, faceUpTime);
        }

    }

    public void bt11Click(View view) {
        if (allowClick) {
            changeSelected(btn11);
            new Handler().postDelayed(() -> {
                check(btn11);
            }, faceUpTime);
        }

    }

    public void bt12Click(View view) {
        if (allowClick) {
            changeSelected(btn12);
            new Handler().postDelayed(() -> {
                check(btn12);
            }, faceUpTime);
        }

    }

    public void bt13Click(View view) {
        if (allowClick) {
            changeSelected(btn13);
            new Handler().postDelayed(() -> {
                check(btn13);
            }, faceUpTime);
        }

    }

    public void bt14Click(View view) {
        if (allowClick) {
            changeSelected(btn14);
            new Handler().postDelayed(() -> {
                check(btn14);
            }, faceUpTime);
        }

    }

    public void bt15Click(View view) {
        if (allowClick) {
            changeSelected(btn15);
            new Handler().postDelayed(() -> {
                check(btn15);
            }, faceUpTime);
        }

    }

    public void bt16Click(View view) {
        if (allowClick) {
            changeSelected(btn16);
            new Handler().postDelayed(() -> {
                check(btn16);
            }, faceUpTime);
        }

    }

    public void bt17Click(View view) {
        if (allowClick) {
            changeSelected(btn17);
            new Handler().postDelayed(() -> {
                check(btn17);
            }, faceUpTime);
        }

    }

    public void bt18Click(View view) {
        if (allowClick) {
            changeSelected(btn18);
            new Handler().postDelayed(() -> {
                check(btn18);
            }, faceUpTime);
        }

    }

    public void bt19Click(View view) {
        if (allowClick) {
            changeSelected(btn19);
            new Handler().postDelayed(() -> {
                check(btn19);
            }, faceUpTime);
        }
    }

    public void bt20Click(View view) {
        if (allowClick) {
            changeSelected(btn20);
            new Handler().postDelayed(() -> {
                check(btn20);
            }, faceUpTime);
        }

    }

    public void handleImgButton() {
        new Handler().postDelayed(() -> {
        }, faceUpTime);
    }

    public void samePic() {
        imageButton1.setVisibility(View.GONE);
        imageButton2.setVisibility(View.GONE);
        countImgBtn = 0;
        state = true;
        allowClick = true;
    }

    public void differentPic() {
        imageButton1.setSelected(false);
        imageButton2.setSelected(false);
        imageButton1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageButton2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageButton1.setImageResource(R.drawable.btn_chucai);
        imageButton2.setImageResource(R.drawable.btn_chucai);
        countImgBtn = 0;
        state = true;
        allowClick = true;
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), GameResultActivity.class);
                intent.putExtra("MARK", mark);
                startActivity(intent);
            }
        }.start();
    }

    public void updateCountDownText() {
//        txtTimer = findViewById(R.id.txtTimer);
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int second = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatter = String.format(Locale.getDefault(), "%02d:%02d", minutes, second);
        Log.d(TAG, "updateCountDownText: " +timeLeftFormatter);
        txtTimer.setText(timeLeftFormatter);
    }

    public void checkMark() {
        if (mark == 100) {
            Intent intent = new Intent(getApplicationContext(), GameResultActivity.class);
            intent.putExtra("MARK", mark);
            startActivity(intent);
        }
    }
}
