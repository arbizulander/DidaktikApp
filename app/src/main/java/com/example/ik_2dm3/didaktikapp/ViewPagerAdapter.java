package com.example.ik_2dm3.didaktikapp;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

import pl.droidsonroids.gif.GifImageView;

public class ViewPagerAdapter extends PagerAdapter {


    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.komikia_1,R.drawable.komikia_2,R.drawable.komikia_3,R.drawable.komikia_4};
    private Button button;
    private GifImageView gifImageView;
    private boolean gif = false;


    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount(){
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view , Object object){

        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.komikia_layout, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView3);
        imageView.setImageResource(images[position]);


        Button button = (Button) view.findViewById(R.id.button);
        button.setEnabled(false);
        button.setVisibility(View.INVISIBLE);

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);



        if(position == 0 && !gif){

            gifImageView = (GifImageView) view.findViewById(R.id.GifImageView);
            gifImageView.setImageResource(R.drawable.swipe);

            Animation animacion = AnimationUtils.loadAnimation(context, R.anim.animation_gif);
            gifImageView.startAnimation(animacion);
            gifImageView.setZ(1111);


            new CountDownTimer(8000, 1000) {

                public void onTick(long millisUntilFinished) {

                    //here you can have your logic to set text to edittext
                }

                public void onFinish() {
                    gifImageView.setVisibility(View.INVISIBLE);
                    gif=true;

                }

            }.start();





        }

        if(position == 3){

            button.setEnabled(true);
            button.setVisibility(View.VISIBLE);
        }
        return view;

    }




    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
