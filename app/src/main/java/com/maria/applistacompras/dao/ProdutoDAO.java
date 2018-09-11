package com.maria.applistacompras.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maria.applistacompras.model.Categoria;
import com.maria.applistacompras.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static void inserir(Context contexto, Produto produto){

        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome" , produto.getNome());
        valores.put("quantidade" , produto.getQuantidade());
        valores.put("codCategoria", produto.getCategoria().getId());

        banco.insert("produtos", null, valores);

    }

    public static List<Produto> getProduto(Context contexto){

        List<Produto> listaDeProdutos = new ArrayList<>();

        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getReadableDatabase();

        String sql = "SELECT p.id, p.nome, p.quantidade, p.codCategoria, c.nome FROM produtos p INNER JOIN categorias c ON c.id = p.codCategoria ORDER BY p.nome";
        Cursor tabela = banco.rawQuery(sql, null);

        if(tabela.getCount() > 0 ){
            tabela.moveToFirst();

            do{

                Produto produto = new Produto();
                produto.setId( tabela.getInt( 0 ));
                produto.setNome( tabela.getString( 1 ));
                produto.setQuantidade( tabela.getDouble( 2 ));

                Categoria cat = new Categoria();
                cat.setId( tabela.getInt( 3 ) );
                cat.setNome( tabela.getString(4 ) );

                produto.setCategoria( cat );

                listaDeProdutos.add( produto );

            }while (tabela.moveToNext());

        }



        return listaDeProdutos;

    }

    public static void excluir (Context contexto, int codProduto){
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        banco.delete("produtos", "id = " + codProduto, null);
    }

}
