package com.example.tommy;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.tommy.share_lib.ShareActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
public class RecyclerMyListAdapter extends RecyclerView.Adapter<RecyclerMyListAdapter.ViewHolder> {
    private int layout;
    private Context context;
    private ArrayList<Data> mData;
    private OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public static interface OnItemClickListener {
        void onItemClick(int position, int layout);
    }
    public RecyclerMyListAdapter(List<Data> data, Context context, int layout) {
        this.mData = (ArrayList<Data>) data;
        this.context = context;
        this.layout = layout;
    }
    public void updateData(ArrayList<Data> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (layout == R.layout.list_item02) {
            holder.title.setText(mData.get(position).poem_title);
            holder.author.setText(mData.get(position).poem_author);
            holder.shortcut.setText(mData.get(position).poem_shortcut);
            holder.title.setText(mData.get(position).poem_title);
            if (mOnItemClickListener != null) {
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(position, layout);
                    }
                });
            }
        } else if (layout == R.layout.list_item01) {
            holder.date.setText(mData.get(position).date);
            holder.lunar.setText(mData.get(position).lunar);
            holder.command.setText(mData.get(position).poem_shortcut);
            holder.title.setText(mData.get(position).poem_title);
            holder.author.setText(mData.get(position).poem_author);
            holder.content.setText(mData.get(position).poem_content);
        } else if (layout == R.layout.list_item03) {
            String[] values = mData.get(position).poem_content.split("Time:");
            holder.title.setText(values[1]);
            holder.content.setText(values[0]);
            holder.userName.setText(mData.get(position).title);
            Glide.with(context).load(mData.get(position).poem_url).into(holder.userLogo);
            holder.page_focus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("".equals(holder.dianzang.getText().toString())) {
                        holder.dianzang.setText("+1");
                    } else {
                        String str = holder.dianzang.getText().toString().replaceAll("/+", "");
                        int num = Integer.valueOf(str).intValue() + 1;
                        holder.dianzang.setText("+" + num);
                    }
                }
            });
        } else if (layout == R.layout.list_choose_main) {
            holder.title.setText(mData.get(position).title);
            if (mOnItemClickListener != null) {
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(position, layout);
                    }
                });
            }
        }
    }
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView shortcut;
        LinearLayout linearLayout;
        TextView command;
        TextView date;
        TextView lunar;
        TextView content;
        ImageView page_focus;
        TextView dianzang;
        CircleImageView userLogo;
        TextView userName;
        public ViewHolder(View itemView) {
            super(itemView);
            if (layout == R.layout.list_item02) {
                linearLayout = itemView.findViewById(R.id.item02);
                title = itemView.findViewById(R.id.title);
                author = itemView.findViewById(R.id.author);
                shortcut = itemView.findViewById(R.id.shortcut);
            } else if (layout == R.layout.list_item01) {
                command = itemView.findViewById(R.id.command);
                lunar = itemView.findViewById(R.id.lunar);
                date = itemView.findViewById(R.id.date);
                title = itemView.findViewById(R.id.title);
                author = itemView.findViewById(R.id.author);
                content = itemView.findViewById(R.id.content);
            } else if (layout == R.layout.list_item03) {
                title = itemView.findViewById(R.id.page_time);
                content = itemView.findViewById(R.id.page_comments);
                page_focus = itemView.findViewById(R.id.page_focus);
                dianzang = itemView.findViewById(R.id.dianzang);
                userLogo = itemView.findViewById(R.id.detail_page_userLogo);
                userName = itemView.findViewById(R.id.page_userName);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println();
                        Intent intent = new Intent(context, ShareActivity.class);
                        String data = content.getText().toString();
                        intent.putExtra("data", data);
                        context.startActivity(intent);
                    }
                });
            } else if (layout == R.layout.list_choose_main) {
                title = itemView.findViewById(R.id.sortTitle);
            } else if (layout == R.layout.list_item05) {
            }
        }
    }
}