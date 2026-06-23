package com.davinci.misdestinos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private Destino destino;
    private Button btnFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int indice = getIntent().getIntExtra(MainActivity.EXTRA_DESTINO_ID, 0);
        destino = MainActivity.listaDestinos.get(indice);

        ImageView img = findViewById(R.id.imgDetalle);
        TextView txtNombre = findViewById(R.id.txtDetalleNombre);
        TextView txtPais = findViewById(R.id.txtDetallePais);
        TextView txtDescripcion = findViewById(R.id.txtDetalleDescripcion);
        Button btnVolver = findViewById(R.id.btnVolver);
        btnFavorito = findViewById(R.id.btnFavorito);

        txtNombre.setText(destino.nombre);
        txtPais.setText(destino.pais);
        txtDescripcion.setText(destino.descripcion);

        Glide.with(this).load(destino.imagenUrl).centerCrop().into(img);

        actualizarBoton();

        btnVolver.setOnClickListener(v -> finish());

        btnFavorito.setOnClickListener(v -> {
            if (MainActivity.listaFavoritos.contains(destino)) {
                MainActivity.listaFavoritos.remove(destino);
                Toast.makeText(this, R.string.msg_quitado, Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.listaFavoritos.add(destino);
                Toast.makeText(this, R.string.msg_agregado, Toast.LENGTH_SHORT).show();
            }
            actualizarBoton();
        });
    }

    private void actualizarBoton() {
        if (MainActivity.listaFavoritos.contains(destino)) {
            btnFavorito.setText(R.string.btn_quitar_favorito);
            btnFavorito.setBackgroundTintList(
                    getColorStateList(R.color.rojo_favorito));
        } else {
            btnFavorito.setText(R.string.btn_agregar_favorito);
            btnFavorito.setBackgroundTintList(
                    getColorStateList(R.color.naranja_acento));
        }
    }
}