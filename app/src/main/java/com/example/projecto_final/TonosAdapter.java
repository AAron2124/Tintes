package com.example.projecto_final;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TonosAdapter extends RecyclerView.Adapter<TonosAdapter.TonoViewHolder> {

    private List<Tono> tonosList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Tono tono);
    }

    public TonosAdapter(Context context, List<Tono> tonosList, OnItemClickListener listener) {
        this.context = context;
        this.tonosList = tonosList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TonoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tono, parent, false);
        return new TonoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TonoViewHolder holder, int position) {
        Tono tono = tonosList.get(position);
        holder.bind(tono);
    }

    @Override
    public int getItemCount() {
        return tonosList.size();
    }

    class TonoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreTono;
        View viewColorTono;

        public TonoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreTono = itemView.findViewById(R.id.tvNombreTono);
            viewColorTono = itemView.findViewById(R.id.viewColorTono);
        }

        public void bind(final Tono tono) {
            tvNombreTono.setText(tono.getNombre());

            // Establecer color si es válido
            try {
                int color = Color.parseColor(tono.getCodigoColor()); // debe ser formato "#RRGGBB"
                viewColorTono.setBackgroundColor(color);
            } catch (IllegalArgumentException e) {
                viewColorTono.setBackgroundColor(Color.GRAY);
            }

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(tono);
                }
            });
        }
    }
}
