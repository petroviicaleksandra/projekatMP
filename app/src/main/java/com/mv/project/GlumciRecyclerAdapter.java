package com.mv.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GlumciRecyclerAdapter extends RecyclerView.Adapter<GlumciViewHolder>{

    Context context;
    List<Cast> lista;

    public GlumciRecyclerAdapter(Context context, List<Cast> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public GlumciViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GlumciViewHolder(LayoutInflater.from(context).inflate(R.layout.cast_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GlumciViewHolder holder, int position) {
        holder.glumac.setText(lista.get(position).getactor());
        holder.lik.setText(lista.get(position).getcharacter());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
class GlumciViewHolder extends RecyclerView.ViewHolder{

    TextView glumac, lik;
    public GlumciViewHolder(@NonNull View itemView) {
        super(itemView);
        glumac = itemView.findViewById(R.id.actor);
        lik = itemView.findViewById(R.id.character);
    }
}