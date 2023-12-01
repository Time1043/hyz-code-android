package com.example.viewdemo.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.viewdemo.R;


import androidx.annotation.DrawableRes;


public class GlideLoader {

    public static void laodImageRound(Context context,
                                    ImageView icon,
                                    String url){
        int radius = 10;

        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().fitCenter().transform(new GlideRoundTransform(context, radius)))
                .thumbnail(loadTransform(context, R.drawable.pictures_no,radius))
                .into(icon);

    }
    public static void laodImageRound(Context context,
                                      ImageView icon,
                                      int res,int radius){
        Glide.with(context)
                .load(res)
                .apply(new RequestOptions().fitCenter().transform(new GlideRoundTransform(context, radius)))
                .thumbnail(loadTransform(context, R.drawable.pictures_no,radius))
                .into(icon);

    }

    public static void loadImage(Context context,
                                    ImageView icon,
                                    String url){

        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(R.drawable.pictures_no))
                .into(icon);

    }

    private static RequestBuilder<Drawable> loadTransform(Context context, @DrawableRes int placeholderId, int radius) {

        return Glide.with(context)
                .load(placeholderId)
                .apply(new RequestOptions().fitCenter().
                        transform(new GlideRoundTransform(context, radius)));

    }
}