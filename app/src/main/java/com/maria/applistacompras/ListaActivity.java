package com.maria.applistacompras;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.maria.applistacompras.dao.ProdutoDAO;
import com.maria.applistacompras.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private EditText produto;
    private ListView lvLista;

    private List<Produto> listaDeProdutos;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        produto = (EditText) findViewById(R.id.etProduto);
        lvLista = (ListView) findViewById(R.id.lvLista);

        //INICIO---Linhas para carregar o ListView--- ( QUANDO ESTAVAMOS USANDO STRINGS, AGORA PEGAMOS A LISTA DO BANCO )
//        listaDeProdutos = new ArrayList<>();
//        adapter = new ArrayAdapter(ListaActivity.this, android.R.layout.simple_list_item_1, listaDeProdutos);
//
//        lvLista.setAdapter(adapter);
        //FIM ---Linhas para carregar o ListView---

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListaActivity.this, FormularioActivity.class);
// Intent server para enviar para outra tela

                startActivity(intent);

//                listaDeProdutos.add(produto.getText().toString());
//                adapter.notifyDataSetChanged();
//                produto.setText("");
//
//                Snackbar.make(view, "Produto Salvo!", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int posicao, long l) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaActivity.this);
                alerta.setTitle("Atenção");
                alerta.setIcon(android.R.drawable.ic_dialog_alert);
                alerta.setMessage("Confirma a exclusão do produto " + listaDeProdutos.get(posicao).getNome() + "?");

                alerta.setNeutralButton("Cancelar" , null);
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        listaDeProdutos.remove(posicao);
//                        adapter.notifyDataSetChanged();

                        excluirProduto(posicao);
                    }
                });
                alerta.show();
                return true;
            }
        });
        carregarProdutos();
        produto.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void carregarProdutos(){
        listaDeProdutos = ProdutoDAO.getProduto(this);
//        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaDeProdutos);
            AdapterProdutos adapter = new AdapterProdutos(this, listaDeProdutos);
            lvLista.setAdapter(adapter);
    }

    private void excluirProduto(int posicao){
        Produto prod = (Produto) lvLista.getItemAtPosition(posicao);
        ProdutoDAO.excluir(this, prod.getId());
        carregarProdutos();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarProdutos();
    }
}
