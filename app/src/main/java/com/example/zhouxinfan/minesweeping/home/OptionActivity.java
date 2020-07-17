package com.example.zhouxinfan.minesweeping.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.zhouxinfan.minesweeping.R;
import com.example.zhouxinfan.minesweeping.common.Util;
import com.example.zhouxinfan.minesweeping.main.MainActivity;

public class OptionActivity extends AppCompatActivity {

    @BindView(R.id.et_width)
    EditText etWidth;
    @BindView(R.id.et_height)
    EditText etHeight;
    @BindView(R.id.et_mine)
    EditText etMine;
    @BindView(R.id.start)
    Button start;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        ButterKnife.bind(this);
        etWidth.setText("10");
        etHeight.setText("10");
        etMine.setText("20");
    }

    @OnClick(R.id.start)
    public void onViewClicked() {
        int width = parseInt(etWidth.getText().toString());
        int height = parseInt(etHeight.getText().toString());
        int mine = parseInt(etMine.getText().toString());
        MainActivity.start(this, width, height, mine);
    }

    private int parseInt(String s) {
        return Util.notEmpty(s) ? Integer.parseInt(s) : 0;
    }
}
