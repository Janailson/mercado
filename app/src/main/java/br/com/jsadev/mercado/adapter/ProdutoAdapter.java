package br.com.jsadev.mercado.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jsadev.mercado.ProdutoCriarActivity;
import br.com.jsadev.mercado.R;
import br.com.jsadev.mercado.common.Extras;
import br.com.jsadev.mercado.model.Produto;

/**
 * Created by janailson on 27/07/2017.
 */

public class ProdutoAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Produto> produtos;

    public ProdutoAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_produto, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        final Produto produto = produtos.get(position);

        // fields
        Picasso.with(context).load(produto.getFoto()).into(holder.foto);
        holder.nome.setText(produto.getNome());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProdutoCriarActivity.class);
                intent.putExtra(Extras.PRODUTO.getCode(), produto);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView foto;
        final TextView nome;
        final ImageView edit;

        public ViewHolder(View view) {
            super(view);
            foto = (ImageView) view.findViewById(R.id.image_foto);
            nome = (TextView) view.findViewById(R.id.label_nome);
            edit = (ImageView) view.findViewById(R.id.image_edit);
        }
    }
}
