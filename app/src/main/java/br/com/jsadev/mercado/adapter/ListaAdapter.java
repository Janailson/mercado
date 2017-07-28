package br.com.jsadev.mercado.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.jsadev.mercado.ListaCriarActivity;
import br.com.jsadev.mercado.R;
import br.com.jsadev.mercado.common.Extras;
import br.com.jsadev.mercado.model.Lista;

/**
 * Created by janailson on 26/07/2017.
 */

public class ListaAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Lista> listas;

    public ListaAdapter(Context context, List<Lista> listas) {
        this.context = context;
        this.listas = listas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_lista, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        final Lista lista = listas.get(position);

        // fields
        holder.status.setImageResource(lista.isStatus() ? R.drawable.box_green : R.drawable.box_yellow);
        holder.nome.setText(lista.getNome());
        holder.subtitulo.setText(lista.getSubtitulo());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListaCriarActivity.class);
                intent.putExtra(Extras.LISTA.getCode(), lista.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView status;
        final TextView nome;
        final TextView subtitulo;
        final ImageView edit;

        public ViewHolder(View view) {
            super(view);
            status = (ImageView) view.findViewById(R.id.item_status);
            nome = (TextView) view.findViewById(R.id.item_nome);
            subtitulo = (TextView) view.findViewById(R.id.item_subtitulo);
            edit = (ImageView) view.findViewById(R.id.image_edit);
        }

    }

}
