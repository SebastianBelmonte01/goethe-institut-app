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

public class AdaptadorImagen extends RecyclerView.Adapter<AdaptadorImagen.ViewDataHolderImagen> {

    ArrayList<ModeloItemImagen> lista;

    public AdaptadorImagen(ArrayList<ModeloItemImagen> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewDataHolderImagen onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.style_option_menu, parent, false);
        return new AdaptadorImagen.ViewDataHolderImagen(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDataHolderImagen holder, int position) {
        holder.ivFoto.setImageBitmap(lista.get(position).getImagen());
        holder.tvTitulo.setText(lista.get(position).getTitulo());
        holder.tvDetalle.setText(lista.get(position).getDetalle());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHolderImagen extends RecyclerView.ViewHolder {

        ImageView ivFoto;
        TextView tvTitulo, tvDetalle;
        LinearLayout lila;




        public ViewDataHolderImagen(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDetalle = itemView.findViewById(R.id.tvDetalle);

            lila = itemView.findViewById(R.id.lilaContainer);
        }
    }
}


