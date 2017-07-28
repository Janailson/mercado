package br.com.jsadev.mercado.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orm.SugarRecord;

import java.util.List;

import br.com.jsadev.mercado.CategoriaCriarActivity;
import br.com.jsadev.mercado.R;
import br.com.jsadev.mercado.common.Extras;
import br.com.jsadev.mercado.model.Categoria;

/**
 * Created by janailson on 27/07/2017.
 */

public class CategoriaAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Categoria> categorias;

    public CategoriaAdapter(Context context, List<Categoria> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_categoria, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        final Categoria categoria = categorias.get(position);

        // fields
        holder.nome.setText(categoria.getNome());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoriaCriarActivity.class);
                intent.putExtra(Extras.CATEGORIA.getCode(), categoria);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorias.size();
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
