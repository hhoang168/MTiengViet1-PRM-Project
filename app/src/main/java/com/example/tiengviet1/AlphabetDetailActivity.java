package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AlphabetDetailActivity extends AppCompatActivity {
    private TextView txtChuThuong, txtChuHoa, txtLetter;
    private ImageView imgChuThuong, imgChuHoa;
    private ImageButton imgSound;
    private AlphabetDTO alphabetDTO;
    private String urlChuThuong = "";
    private String urlChuHoa = "";
    private String[] listLetter =
            {"a", "ă", "â", "b", "c", "d", "đ", "e",
                    "ê", "g", "h", "i", "k", "l", "m", "n",
                    "o", "ô", "ơ", "p", "q", "r", "s", "t",
                    "u", "ư", "v", "x", "y",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_detail);
        createView();

        Intent intent = this.getIntent();
        alphabetDTO = (AlphabetDTO) intent.getSerializableExtra("dto");
        List<ImageDTO> tmpList = alphabetDTO.getListImages();
        for (ImageDTO dto : tmpList) {
            if (dto.getDescription().contains("viet_hoa")) {
                urlChuHoa = dto.getImgPath();
            } else if (dto.getDescription().contains("viet_thuong")) {
                urlChuThuong = dto.getImgPath();
            }
        }

        setUpView();
    }

    private void createView() {
        txtChuThuong = findViewById(R.id.txtChuthuong);
        txtChuHoa = findViewById(R.id.txtChuhoa);
        txtLetter = findViewById(R.id.txtLetter);
        imgChuThuong = findViewById(R.id.imgVietthuong);
        imgChuHoa = findViewById(R.id.imgViethoa);
        imgSound = findViewById(R.id.imgBtnDanhvan);
    }

    private void setUpView() {
        txtChuHoa.setText(alphabetDTO.getLetter());
        txtChuThuong.setText(alphabetDTO.getLetter());
        txtLetter.setText(alphabetDTO.getLetter());
        Glide.with(this).load(urlChuHoa).into(imgChuHoa);
        Glide.with(this).load(urlChuThuong).into(imgChuThuong);
    }

    public void playAudio(View view) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse(alphabetDTO.getAudioPath()));
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                try {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mediaPlayer.start();
    }

    public void getBack(View view) {
    }
}
