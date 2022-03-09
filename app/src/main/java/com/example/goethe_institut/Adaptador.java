package com.example.goethe_institut;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewDataHolder> {

    ArrayList<ModeloItem> lista;


    public Adaptador(ArrayList<ModeloItem> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.style_option_menu, parent, false);
        return new ViewDataHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDataHolder holder, int position) {
        /*
        Se realiza el cargado
         */
        holder.ivFoto.setImageResource(lista.get(position).getFoto());
        holder.tvTitulo.setText(lista.get(position).getTitulo());
        holder.tvDetalle.setText(lista.get(position).getDetalle());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder {

        ImageView ivFoto;
        TextView tvTitulo, tvDetalle;
        LinearLayout lila;




        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDetalle = itemView.findViewById(R.id.tvDetalle);

            lila = itemView.findViewById(R.id.lilaContainer);
        }
    }
}
