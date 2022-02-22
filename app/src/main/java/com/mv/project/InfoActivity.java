package com.mv.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    TextView naziv, izbacen, trajanje, ocena, glasovi, radnja;
    ImageView poster;
    RecyclerView cast;
    GlumciRecyclerAdapter adapter;
    RequestMenadzer menadzer;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        naziv = findViewById(R.id.txtNaziv);
        izbacen=findViewById(R.id.txtIzbacen);
        trajanje = findViewById(R.id.txtTrajanje);
        ocena = findViewById(R.id.txtOcena);
        glasovi = findViewById(R.id.txtGlasovi);
        radnja = findViewById(R.id.txtRadnja);
        poster = findViewById(R.id.imgPoster);
        cast = findViewById(R.id.glumackaPostava);
        menadzer = new RequestMenadzer(this);

        String filmID = getIntent().getStringExtra("data");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Ucitava se..");
        dialog.show();

        menadzer.pretraziInformacijeOFilmu(listener, filmID);


    }
    private OnInformacijeApiListener listener = new OnInformacijeApiListener() {
        @Override
        public void onResponse(InformacijeAPI info) {
            dialog.dismiss();
            if(info.equals(null)){
                Toast.makeText(InfoActivity.this, "Greska!", Toast.LENGTH_SHORT);
                return;
            }
            showResults(info);
        }

        @Override
        public void onError(String poruka) {
            dialog.dismiss();
            Toast.makeText(InfoActivity.this, "Greska!", Toast.LENGTH_SHORT);

        }
    };

    private void showResults(InformacijeAPI info) {
        naziv.setText(info.getTitle());
        izbacen.setText("Godina prvog prikazivanja: "+info.getYear());
        trajanje.setText("Trajanje: "+info.getLength());
        ocena.setText("Ocena: "+info.getRating());
        glasovi.setText(info.getRatingVotes()+" glasova");
        radnja.setText("Radnja na engleskom jeziku: "+info.getPlot());

        try {
            Picasso.get().load(info.getPoster()).into(poster);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        cast.setHasFixedSize(true);
        cast.setLayoutManager(new GridLayoutManager(this, 1));
        adapter= new GlumciRecyclerAdapter(this,info.getCast());
        cast.setAdapter(adapter);



    }
}