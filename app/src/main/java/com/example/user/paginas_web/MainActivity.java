package com.example.user.paginas_web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private Spinner spinner, spinner2;
    private ImageButton btn1, btnfav;
    private EditText et1;
    private WebView wbc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wbc = (WebView)findViewById(R.id.wbc);
        btnfav = (ImageButton)findViewById(R.id.btnfav);
        btn1 = (ImageButton)findViewById(R.id.btn1);
        et1 = (EditText)findViewById(R.id.et1);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);

//para javascript
        WebSettings webSettings = wbc.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wbc.setWebViewClient(new WebViewClient());
        //wbc.loadUrl("");

      //string array con el primer elemento vacio
        String [] primerItem = new String[]{" ","Historial"};
        //se le pasa al list
        final List<String> historial = new ArrayList<>(Arrays.asList(primerItem));
        //inicializar el arrayadapter
        final ArrayAdapter<String> spnrArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, historial);
        spnrArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spnrArrayAdapter);

        //para elegir favorito
        String [] primerFav = new String[]{" ","Favoritos"};
        final List<String> favoritos = new ArrayList<>(Arrays.asList(primerFav));
        final ArrayAdapter<String> spnr2ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, favoritos);
        spnr2ArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner2.setAdapter(spnr2ArrayAdapter);
        //añadir el video a favoritos
        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cogemos el botonfav y lo pasamos al spinner de favoritos
                String favos = String.valueOf(et1.getText());
                wbc.loadUrl("https://" + favos);
                //añadimos el texto del et1 al spinner
                favoritos.add(favos);
                spnr2ArrayAdapter.notifyDataSetChanged();
            }
        });
//mostrar el video y pasar url a historial
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cogemos el edittext y lo pasamos al loadurl del webview
                String texto = String.valueOf(et1.getText());
                wbc.loadUrl("https://" + texto);

                //añadimos el texto del et1 al spinner
                historial.add(texto);
                spnrArrayAdapter.notifyDataSetChanged();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccion = spinner.getSelectedItem().toString();
                wbc.loadUrl("https://" + seleccion);
                et1.setText("");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Selecciona una URL valida", Toast.LENGTH_SHORT);
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccion2 = spinner2.getSelectedItem().toString();
                wbc.loadUrl("https://" + seleccion2);
                et1.setText("");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Selecciona una URL valida", Toast.LENGTH_SHORT);
            }
        });

}}
