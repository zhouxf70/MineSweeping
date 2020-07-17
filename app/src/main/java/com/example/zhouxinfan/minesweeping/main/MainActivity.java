package com.example.zhouxinfan.minesweeping.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.zhouxinfan.minesweeping.R;
import com.example.zhouxinfan.minesweeping.common.Logger;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final static String INTENT_KEY_WIDTH = "width";
    private final static String INTENT_KEY_HEIGHT = "height";
    private final static String INTENT_KEY_MINE_NUMBER = "mineNumber";

    private int allNum;//所有格子数量
    private int width, height;//宽高
    private int mineNumber;//地雷数量
    private int[] all;
    private boolean isMark = false;

    public static void start(Context context, int width, int height, int mineNumber) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(INTENT_KEY_WIDTH, width);
        intent.putExtra(INTENT_KEY_HEIGHT, height);
        intent.putExtra(INTENT_KEY_MINE_NUMBER, mineNumber);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        final TextView tv_mine = findViewById(R.id.text);
        tv_mine.setText(String.valueOf(mineNumber));

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, width));
        final MyAdapter adapter = new MyAdapter(MainActivity.this, all);
        recyclerView.setAdapter(adapter);
        adapter.setSweepListener(new MyAdapter.SweepListener() {
            @Override
            public void onSweep(int mineRemaining, boolean isOver) {
                if (isOver) {
                    tv_mine.setText("0");
                } else {
                    tv_mine.setText(String.valueOf(mineRemaining));
                }
            }
        });

        final ImageView img_mark = findViewById(R.id.img_mark);
        img_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMark = !isMark;
                img_mark.setBackgroundResource(isMark ? R.drawable.mark_focus : R.drawable.mark_unfocus);
                adapter.setMark(isMark);
            }
        });

    }

    private void initData() {
        Intent intent = getIntent();
        width = intent.getIntExtra(INTENT_KEY_WIDTH, 10);
        height = intent.getIntExtra(INTENT_KEY_HEIGHT, 10);
        mineNumber = intent.getIntExtra(INTENT_KEY_MINE_NUMBER, 20);

        allNum = width * height;
        all = new int[allNum];
        Logger.d(allNum + "," + width + "," + height + "," + mineNumber);
        Set<Integer> mineSet = createMine(allNum, mineNumber);
        for (int mine : mineSet) {
            all[mine] = -1;
        }
        //将周围不是地雷的格子的数字全部加1
        for (int minePosition : mineSet) {
            if (all[minePosition] == -1) {
                if (minePosition % width != 0) {
                    addOne(minePosition - 1 - width);
                    addOne(minePosition - 1);
                    addOne(minePosition - 1 + width);
                }
                if ((minePosition + 1) % width != 0) {
                    addOne(minePosition + 1 - width);
                    addOne(minePosition + 1);
                    addOne(minePosition + 1 + width);
                }
                addOne(minePosition - width);
                addOne(minePosition + width);
            }
        }
    }

    /**
     * 从 0-bound 中随机获取 number 个不同的数
     */
    private Set<Integer> createMine(int bound, int number) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < number) {
            int i = random.nextInt(bound);
            set.add(i);
        }
        return set;
    }

    private void addOne(Integer position) {
        if (position >= 0 && position < allNum && all[position] != -1)
            all[position] = all[position] + 1;
    }

}
