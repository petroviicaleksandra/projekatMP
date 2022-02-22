package com.mv.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnFilmClickListener{
    SearchView searchView;
    RecyclerView recyclerView;
    PocetnaRecyclerAdapter adapter;
    RequestMenadzer menadzer;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recycler_view_pocetna);

        dialog = new ProgressDialog(this);
        menadzer = new RequestMenadzer(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Molimo sacekajte...");
                dialog.show();
                menadzer.pretraziFilmove(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
    private final OnSearchAPIListener listener = new OnSearchAPIListener() {
        @Override
        public void onResponse(SearchAPIOdgovor odgovor) {
            dialog.dismiss();
            if(odgovor == null){
                Toast.makeText(MainActivity.this,"Nije nista uneto",Toast.LENGTH_SHORT).show();
                return;
            }
            prikaziRezultat(odgovor);
        }

        @Override
        public void onError(String greska) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this,"Greska se desila",Toast.LENGTH_SHORT).show();
        }
    };

    private void prikaziRezultat(SearchAPIOdgovor odgovor) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
            adapter = new PocetnaRecyclerAdapter(this, odgovor.getTitles(), this);
            recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFilmKlik(String id) {
        startActivity(new Intent(MainActivity.this, InfoActivity.class).putExtra("data", id));
    }
}