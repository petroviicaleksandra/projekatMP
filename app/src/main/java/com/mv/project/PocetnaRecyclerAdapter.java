package com.mv.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PocetnaRecyclerAdapter extends RecyclerView.Adapter<PocetnaViewHolder>{
    Context context;
    List<SearchArrayObject> list;
    OnFilmClickListener listener;

    public PocetnaRecyclerAdapter(Context context, List<SearchArrayObject> list, OnFilmClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<SearchArrayObject> getList() {
        return list;
    }

    public void setList(List<SearchArrayObject> list) {
        this.list = list;
    }

    public OnFilmClickListener getListener() {
        return listener;
    }

    public void setListener(OnFilmClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PocetnaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PocetnaViewHolder(LayoutInflater.from(context).inflate(R.layout.filmovi_lista,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PocetnaViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_film.setText(list.get(position).getTitle());
        holder.txt_film.setSelected(true);
        Picasso.get().load(list.get(position).getImage()).into(holder.imageview_film);

        holder.pocetna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFilmKlik(list.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class PocetnaViewHolder extends RecyclerView.ViewHolder{

    ImageView imageview_film;
    TextView txt_film;
    CardView pocetna;

    public PocetnaViewHolder(@NonNull View itemView) {
        super(itemView);
        imageview_film = itemView.findViewById(R.id.imageView_slika);
        txt_film = itemView.findViewById(R.id.txtView_film);
        pocetna = itemView.findViewById(R.id.pocetna);

    }
}
