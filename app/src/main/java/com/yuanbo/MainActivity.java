package com.yuanbo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private int mPosition ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        initRvStep();
    }

    private void initRvStep() {
        final NiuStepAdapter niuStepAdapter = new NiuStepAdapter(MainActivity.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(niuStepAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int itemCount_page = niuStepAdapter.getMeaSureItemCount();

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                    if (manager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearManager = (LinearLayoutManager) manager;
                        //获取最后一个可见view的位置
                        int lastItemPosition = linearManager.findLastVisibleItemPosition();
                        //获取第一个可见view的位置
                        int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                        if (lastItemPosition - firstItemPosition < itemCount_page) {
                            if (firstItemPosition == 0) {
                                niuStepAdapter.setCenter_index(1);
                            } else {
                                niuStepAdapter.setCenter_index(niuStepAdapter.getItemCount() - 2);
                            }
                        } else {
                            niuStepAdapter.setCenter_index(firstItemPosition + itemCount_page / 2);
                        }
                    }
                }
            }
        });
        niuStepAdapter.setItemClickListener(new NiuStepAdapter.ViewItemClickListener() {
            @Override
            public void onViewItemClick(View view, int position) {
                if (position == mPosition) {
                    return;
                }
                if (position > mPosition) {
                    mRecyclerView.smoothScrollToPosition(position + 1);
                } else if (position < mPosition) {
                    mRecyclerView.smoothScrollToPosition(position - 1);
                }
                mPosition = position;
            }
        });
    }
}
