package com.maria.applistacompras;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maria.applistacompras.model.Produto;

import java.util.List;

public class AdapterProdutos extends BaseAdapter{

    private Context contexto;
    private List<Produto> listaDeProdutos;
    private LayoutInflater inflater;


    public AdapterProdutos(Context context, List<Produto> lista) {
        this.contexto = context;
        this.listaDeProdutos = lista;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return listaDeProdutos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaDeProdutos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaDeProdutos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Suporte item;

        if (view == null){
            view = inflater.inflate(R.layout.layout_lista_produtos, null);
            item = new Suporte();
            item.fundo = (LinearLayout) view.findViewById(R.id.layout_lista_fundo);
            item.tvNome = (TextView) view.findViewById(R.id.layout_lista_produto);
            item.tvQuantidade = (TextView) view.findViewById(R.id.layout_lista_quantidade);
            item.tvCategoria = (TextView) view.findViewById(R.id.layout_lista_categoria);
            item.ivLogo = (ImageView) view.findViewById(R.id.layout_lista_foto);

            view.setTag(item);
        }else {
            item = (Suporte) view.getTag();

        }

        Produto produto = listaDeProdutos.get(i);
        item.tvNome.setText( produto.getNome() );
        item.tvQuantidade.setText( String.valueOf(produto.getQuantidade()) );
        item.tvCategoria.setText( produto.getCategoria().getNome() );

        if(i % 2 == 0)
            item.fundo.setBackgroundColor(Color.rgb(240,240,240));
        else
            item.fundo.setBackgroundColor(Color.WHITE);



        return view;
    }

    private class Suporte{

        LinearLayout fundo;
        TextView tvNome, tvQuantidade, tvCategoria;
        ImageView ivLogo;

    }

}
