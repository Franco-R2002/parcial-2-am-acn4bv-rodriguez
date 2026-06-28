package com.davinci.misdestinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import android.content.Intent;

public class FavoritesActivity extends AppCompatActivity {

    private LinearLayout llListaFavoritos;
    private TextView txtSinFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        llListaFavoritos = findViewById(R.id.llListaFavoritos);
        txtSinFavoritos = findViewById(R.id.txtSinFavoritos);
        TextView txtTituloFavoritos = findViewById(R.id.txtTituloFavoritos);

        Button btnVolver = findViewById(R.id.btnVolverFavoritos);
        btnVolver.setOnClickListener(v -> finish());

        pintarFavoritos();
    }
    @Override
    protected void onResume() {
        super.onResume();
        pintarFavoritos();
    }

    private void pintarFavoritos() {
        llListaFavoritos.removeAllViews();

        if (MainActivity.listaFavoritos.isEmpty()) {
            txtSinFavoritos.setVisibility(View.VISIBLE);
            return;

        }

        txtSinFavoritos.setVisibility(View.GONE);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Destino favorito : new java.util.ArrayList<>(MainActivity.listaFavoritos)) {
            View fila = inflater.inflate(R.layout.item_favorito, llListaFavoritos, false);

            TextView txtNombre = fila.findViewById(R.id.txtFavoritoNombre);
            TextView txtPais = fila.findViewById(R.id.txtFavoritoPais);
            ImageView img = fila.findViewById(R.id.imgFavoritoItem);
            Button btnQuitar = fila.findViewById(R.id.btnQuitarFavorito);

            txtNombre.setText(favorito.nombre);
            txtPais.setText(favorito.pais);
            Glide.with(this).load(favorito.imagenUrl).centerCrop().into(img);

            fila.setOnClickListener(v -> {
                int indice = MainActivity.listaDestinos.indexOf(favorito);
                if (indice != -1) {
                    Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);
                    intent.putExtra(MainActivity.EXTRA_DESTINO_ID, indice);
                    startActivity(intent);
                }
            });

            btnQuitar.setOnClickListener(v -> {
                MainActivity.listaFavoritos.remove(favorito);
                llListaFavoritos.removeView(fila);
                if (MainActivity.listaFavoritos.isEmpty()) {
                    txtSinFavoritos.setVisibility(View.VISIBLE);
                }
            });

            llListaFavoritos.addView(fila);
        }
        int cantidad = MainActivity.listaFavoritos.size();
        TextView titulo = findViewById(R.id.txtTituloFavoritos);
        if (cantidad > 0) {
            titulo.setText("⭐ Mis favoritos (" + cantidad + ")");
        } else {
            titulo.setText("⭐ Mis favoritos");
        }
    }
}