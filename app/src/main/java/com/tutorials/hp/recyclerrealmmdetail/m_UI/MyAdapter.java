package com.tutorials.hp.recyclerrealmmdetail.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutorials.hp.recyclerrealmmdetail.R;
import com.tutorials.hp.recyclerrealmmdetail.m_DetailActivity.DetailActivity;
import com.tutorials.hp.recyclerrealmmdetail.m_Realm.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/17/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public MyAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Spacecraft s=spacecrafts.get(position);

        final String name=s.getName();
        final String propellant=s.getPropellant();
        final String desc=s.getDescription();
        final String imageUrl=s.getImageUrl().replace("localhost","10.0.2.2");

        holder.nameTxt.setText(name);
        holder.propTxt.setText(propellant);
        holder.descTxt.setText(desc);
        ImageLoader.downloadImage(c,imageUrl,holder.img);

        //item click

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                //OPEN DETAIL ACTIVITY
                openDetailActivity(name,propellant,desc,imageUrl);
            }
        });

    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c, DetailActivity.class);

        //PACK DATA
        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("PROP_KEY",details[1]);
        i.putExtra("DESC_KEY",details[2]);
        i.putExtra("IMAGEURL_KEY",details[3]);

        //START
        c.startActivity(i);
    }
}


















