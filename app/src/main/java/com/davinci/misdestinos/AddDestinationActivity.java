package com.davinci.misdestinos;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class AddDestinationActivity extends AppCompatActivity {

    private EditText etNombre, etPais, etDescripcion, etUrl;
    private ImageView imgPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);

        etNombre = findViewById(R.id.etNombre);
        etPais = findViewById(R.id.etPais);
        etDescripcion = findViewById(R.id.etDescripcion);
        etUrl = findViewById(R.id.etUrl);
        imgPreview = findViewById(R.id.imgPreview);

        Button btnPrevisualizar = findViewById(R.id.btnPrevisualizar);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        btnPrevisualizar.setOnClickListener(v -> previsualizarImagen());
        btnGuardar.setOnClickListener(v -> guardarDestino());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void previsualizarImagen() {
        String url = etUrl.getText().toString().trim();
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(this, R.string.msg_error_campos, Toast.LENGTH_SHORT).show();
            return;
        }
        imgPreview.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(url)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(AddDestinationActivity.this,
                                R.string.msg_error_imagen, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imgPreview);
    }

    private void guardarDestino() {
        String nombre = etNombre.getText().toString().trim();
        String pais = etPais.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String url = etUrl.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(pais) || TextUtils.isEmpty(url)) {
            Toast.makeText(this, R.string.msg_error_campos, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(descripcion)) {
            descripcion = "Sin descripción.";
        }

        Destino nuevo = new Destino(nombre, pais, descripcion, url);
        MainActivity.listaDestinos.add(0, nuevo);

        Toast.makeText(this, R.string.msg_guardado, Toast.LENGTH_SHORT).show();
        finish();
    }
}