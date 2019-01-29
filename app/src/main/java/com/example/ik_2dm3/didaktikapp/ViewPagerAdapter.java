package com.example.ik_2dm3.didaktikapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.KeyEvent;
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
    private MyOpenHelper db;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.komikia_1,R.drawable.komikia_2,R.drawable.komikia_3,R.drawable.komikia_4};
    private Button button;
    private GifImageView gifImageView;
    private boolean gif = false;
    private int pag_anterior;


    public ViewPagerAdapter(Context context, int e) {
        this.context = context;
        this.pag_anterior = e;
    }


    @Override
    public int getCount(){
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view , Object object){

        return view == object;
    }

    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.komikia_layout, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView3);
        imageView.setImageResource(images[position]);


        button = (Button) view.findViewById(R.id.button);
        button.setEnabled(false);
        button.setVisibility(View.INVISIBLE);

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);

        Context cont;

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



        if(position == 3 && pag_anterior == 0){

            //ConstraintLayout ln = (ConstraintLayout) view.findViewById(R.id.id_layoutKomikia);
            button.setEnabled(true);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                   //finishUpdate(container);
                    //Komikia_8.FinalizarLayout();
                }
            });



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
