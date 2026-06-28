package com.davinci.misdestinos; // ← tu paquete real

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DESTINO_ID = "destino_id";
    public static ArrayList<Destino> listaDestinos = new ArrayList<>();
    public static ArrayList<Destino> listaFavoritos = new ArrayList<>();

    private LinearLayout llListaDestinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargamos los destinos solo la primera vez
        if (listaDestinos.isEmpty()) {
            cargarDestinos();
        }

        llListaDestinos = findViewById(R.id.llListaDestinos);

        Button btnFavoritos = findViewById(R.id.btnFavoritos);
        Button btnAgregar = findViewById(R.id.btnAgregar);

        btnFavoritos.setOnClickListener(v ->
                startActivity(new Intent(this, FavoritesActivity.class)));

        btnAgregar.setOnClickListener(v ->
                startActivity(new Intent(this, AddDestinationActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        pintarLista();
    }

    private void pintarLista() {
        llListaDestinos.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < listaDestinos.size(); i++) {
            Destino destino = listaDestinos.get(i);
            int indice = i;

            View fila = inflater.inflate(R.layout.item_destino, llListaDestinos, false);

            TextView txtNombre = fila.findViewById(R.id.txtItemNombre);
            TextView txtPais = fila.findViewById(R.id.txtItemPais);
            ImageView img = fila.findViewById(R.id.imgItem);

            txtNombre.setText(destino.nombre);
            txtPais.setText(destino.pais);

            Glide.with(this).load(destino.imagenUrl).centerCrop().into(img);

            fila.setOnClickListener(v -> {
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra(EXTRA_DESTINO_ID, indice);
                startActivity(intent);
            });

            llListaDestinos.addView(fila);
        }
    }

    private void cargarDestinos() {




        listaDestinos.add(new Destino(
                "Machu Picchu", "Perú",
                "Antigua ciudadela inca construida en el siglo XV a más de 2400 metros de altura en los Andes peruanos. Permaneció oculta para el mundo occidental hasta 1911.",
                "https://commons.wikimedia.org/wiki/Special:FilePath/Machu_Picchu,_Peru.jpg"));

        listaDestinos.add(new Destino(
                "Coliseo Romano", "Italia",
                "El anfiteatro más grande construido por el Imperio Romano, terminado en el año 80 d.C. Podía albergar a más de 50.000 espectadores en combates de gladiadores.",
                "https://commons.wikimedia.org/wiki/Special:FilePath/Colosseum_in_Rome,_Italy_-_April_2007.jpg"));

        listaDestinos.add(new Destino(
                "Gran Muralla China", "China",
                "Sistema de fortificaciones construido a lo largo de más de 2000 años para proteger las fronteras del norte de China. Se extiende por miles de kilómetros.",
                "https://commons.wikimedia.org/wiki/Special:FilePath/Great_Wall_of_China_July_2006.JPG"));

        listaDestinos.add(new Destino(
                "Taj Mahal", "India",
                "Mausoleo de mármol blanco mandado a construir en 1632 por el emperador mogol Shah Jahan en memoria de su esposa. Patrimonio de la Humanidad y una de las maravillas del mundo.",
                "https://commons.wikimedia.org/wiki/Special:FilePath/Taj_Mahal_in_March_2004.jpg"));

        listaDestinos.add(new Destino(
                "Cristo Redentor", "Brasil",
                "Estatua art déco de 30 metros en la cima del cerro Corcovado en Río de Janeiro. Inaugurada en 1931, es uno de los símbolos más reconocidos del mundo.",
                "https://commons.wikimedia.org/wiki/Special:FilePath/Christ_the_Redeemer_-_Cristo_Redentor.jpg"));



        listaDestinos.add(new Destino(
                "Sagrada Familia", "España",
                "Basílica diseñada por Antoni Gaudí en Barcelona, en construcción desde 1882. Combina elementos góticos y modernistas en un estilo único en el mundo.",
                "https://commons.wikimedia.org/wiki/Special:FilePath/Sagrada_Familia_01.jpg"));

        listaDestinos.add(new Destino(
                "Estatua de la Libertad", "Estados Unidos",
                "Regalo de Francia a Estados Unidos en 1886, ubicada en la bahía de Nueva York. Recibió a millones de inmigrantes que llegaban al país a comienzos del siglo XX.",
                "https://commons.wikimedia.org/wiki/Special:FilePath/Statue_of_Liberty_1_New_York.JPG"));
    }
}