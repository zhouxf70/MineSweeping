package com.example.zhouxinfan.minesweeping.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.zhouxinfan.minesweeping.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private int[] all;
    private final int[] textColors = new int[]{0xffffff, 0xff0404f2, 0xff28f204, 0xfff20410, 0xfff27304, 0xfff2e204, 0xffea04f2, 0xff02f9d8, 0xff7b04f2};
    private boolean[] focusPosition = new boolean[100];

    private boolean isMark = false;//是否是标记状态
    private SweepListener listener;

    public MyAdapter(Context context, int[] all) {
        this.context = context;
        this.all = all;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        switch (all[i]) {
            case -1:
                myViewHolder.textView.setText("");
                myViewHolder.textView.setBackgroundResource(R.drawable.mine);
                break;
            case 0:
                myViewHolder.textView.setText("");
                break;
            default:
                myViewHolder.textView.setText(String.valueOf(all[i]));
                myViewHolder.textView.setTextColor(textColors[all[i]]);
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return all.length;
    }

    public void setMark(boolean isMark) {
        this.isMark = isMark;
    }

    public void setSweepListener(SweepListener listener) {
        this.listener = listener;
    }

    public interface SweepListener {
        void onSweep(int mineRemaining, boolean isOver);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item);
        }
    }


}
