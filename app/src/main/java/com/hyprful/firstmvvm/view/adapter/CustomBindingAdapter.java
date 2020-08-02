package com.hyprful.firstmvvm.view.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.hyprful.firstmvvm.R;

import java.text.DecimalFormat;

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.no_photo)
                .error(R.mipmap.no_photo)
//                .apply(new RequestOptions().circleCrop())
                .into(view);
    }
}