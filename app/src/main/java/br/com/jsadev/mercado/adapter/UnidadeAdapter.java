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

import br.com.jsadev.mercado.R;
import br.com.jsadev.mercado.UnidadeCriarActivity;
import br.com.jsadev.mercado.common.Extras;
import br.com.jsadev.mercado.model.Unidade;

/**
 * Created by janailson on 27/07/2017.
 */

public class UnidadeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Unidade> unidades;

    public UnidadeAdapter(Context context, List<Unidade> unidades) {
        this.context = context;
        this.unidades = unidades;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_unidade, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        final Unidade unidade = unidades.get(position);

        // fields
        holder.nome.setText(unidade.getNome());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UnidadeCriarActivity.class);
                intent.putExtra(Extras.UNIDADE_MEDIDA.getCode(), unidade);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return unidades.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView nome;
        final ImageView edit;

        public ViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.label_nome);
            edit = (ImageView) view.findViewById(R.id.image_edit);
        }
    }
}
