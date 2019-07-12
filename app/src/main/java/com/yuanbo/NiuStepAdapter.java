package com.yuanbo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class NiuStepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int center_index, last_index;
    private Context mContext;
    private final int PLACEHOLDER_CODE = -1;
    private int placeholder_width;//布局写死130dp
    private ViewItemClickListener mItemClickListener;

    public NiuStepAdapter(Context mContext) {
        this.mContext = mContext;
        placeholder_width = DisplayUtil.dip2px(mContext, 130);
        center_index = 1;
        last_index = 1;
    }

    /***
     * 正常一屏显示的个数
     * @return
     */
    public int getMeaSureItemCount() {
        return DisplayUtil.getWindowWidth(mContext) / placeholder_width + 1;
    }

    /***
     * 设置中心
     * @param index
     */
    public void setCenter_index(int index) {
        if (index == center_index)
            return;
        last_index = center_index;
        center_index = index;
        notifyItemChanged(last_index);
        //        notifyItemChanged(center_index);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == PLACEHOLDER_CODE) {
            View view = new View(mContext);
            int view_width = parent.getMeasuredWidth() / 2 - placeholder_width / 2;
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(view_width, RecyclerView.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            HollowViewHolder holder = new HollowViewHolder(view);
            return holder;
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_step_view, parent, false);
        StepViewHolder viewHolder = new StepViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof StepViewHolder) {
            if (position == center_index) {
            } else {
            }
            ((StepViewHolder) holder).tv_step.setImageResource(R.mipmap.exp_lv1);
            ((StepViewHolder) holder).tv_num.setText(position + "");
            if (position == 1) {
                ((StepViewHolder) holder).tv_line_left.setVisibility(View.INVISIBLE);
            } else {
                ((StepViewHolder) holder).tv_line_left.setVisibility(View.VISIBLE);
            }
            if (position == getItemCount() - 2) {
                ((StepViewHolder) holder).tv_line_right.setVisibility(View.INVISIBLE);
            } else {
                ((StepViewHolder) holder).tv_line_right.setVisibility(View.VISIBLE);
            }
            ((StepViewHolder) holder).tv_step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onViewItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == getItemCount() - 1) {
            return PLACEHOLDER_CODE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 11;
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        TextView tv_line_left;
        ImageView tv_step;
        TextView tv_line_right;
        TextView tv_num;

        public StepViewHolder(View itemView) {
            super(itemView);
            tv_line_left = itemView.findViewById(R.id.tv_line_left);
            tv_step = itemView.findViewById(R.id.tv_step);
            tv_line_right = itemView.findViewById(R.id.tv_line_right);
            tv_num = itemView.findViewById(R.id.tv_num);
        }
    }

    class HollowViewHolder extends RecyclerView.ViewHolder {

        public HollowViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setItemClickListener(ViewItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface ViewItemClickListener {
        void onViewItemClick(View view, int position);
    }

}
