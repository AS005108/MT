package com.example.lab1;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {

    Integer[] map;
    Integer first, second;
    ImageView[] images;
    boolean mutex;

    private Context context;
    public GridAdapter(Context c, Integer[] _map){
        context = c;
        map = _map;
        first = -1;
        second = -1;
        images = new ImageView[map.length];
        mutex = false;
    }
    @Override
    public int getCount() {
        return map.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
        } else {
            imageView = (ImageView) convertView;
        }
        images[position] = imageView;

        imageView.setImageResource(R.drawable.cover);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(images[position].getImageAlpha() == 0 || mutex)
                   return;
                if(first == -1){
                    first = position;
                    if(map[position]==1)
                        images[first].setImageResource(R.drawable.first);
                    else
                        images[first].setImageResource(R.drawable.second);
                } else if (position != first) {
                    mutex = true;
                    second = position;
                    if (map[position] == 1)
                        images[second].setImageResource(R.drawable.first);
                    else
                        images[second].setImageResource(R.drawable.second);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            if(map[first] == map[second]){
                                images[first].setImageURI(null);
                                images[first].setImageAlpha(0);
                                images[second].setImageAlpha(0);
                            } else {
                                images[first].setImageResource(R.drawable.cover);
                                images[second].setImageResource(R.drawable.cover);
                            }
                            first = -1;
                            second = -1;
                            mutex = false;
                        }
                    }, 800);
                }
            }
        });
        return imageView;
    }
}