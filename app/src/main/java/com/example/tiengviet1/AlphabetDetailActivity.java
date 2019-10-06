package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AlphabetDetailActivity extends AppCompatActivity {
    TextView txtChuThuong, txtChuHoa, txtLetter;
    ImageView imgChuThuong, imgChuHoa;
    ImageButton imgSound;
    String[] listLetter =
            {"a", "ă", "â", "b","c", "d", "đ", "e",
            "ê", "g", "h", "i", "k", "l", "m", "n",
            "o", "ô", "ơ", "p", "q", "r", "s", "t",
            "u", "ư", "v", "x", "y",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_detail);
        createView();
    }

    private void createView() {
        txtChuThuong = findViewById(R.id.txtChuthuong);
        txtChuHoa = findViewById(R.id.txtChuhoa);
        txtLetter = findViewById(R.id.txtLetter);
        imgChuThuong = findViewById(R.id.imgVietthuong);
        imgChuHoa = findViewById(R.id.imgViethoa);
        imgSound = findViewById(R.id.imgBtnDanhvan);
    }
}
