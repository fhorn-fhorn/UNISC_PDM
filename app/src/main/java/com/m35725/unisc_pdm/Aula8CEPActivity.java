package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Aula8CEPActivity extends AppCompatActivity {

    private ListView                lstViewWeather;
    private Aula8ListViewAdaptador  lstViewWeatherAdapter;
    private List<HashMap<String, String>> infoWeather;
    String[] de = {"datahora", "temperature", "humidity", "dewpoint", "pressure"};
    int[] para = {R.id.txtViewAula8Lista_DataHora, R.id.txtViewAula8Lista_Temperatura,
            R.id.txtViewAula8Lista_Umidade, R.id.txtViewAula8Lista_PontoOrvalho,
            R.id.txtViewAula8Lista_Pressao };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula8_cep);

        ProgressBar prgBar= findViewById(R.id.prgBarAula8);
        prgBar.setVisibility(View.GONE);

        infoWeather= new ArrayList<>();
        lstViewWeatherAdapter= new Aula8ListViewAdaptador(this, infoWeather,
                        R.layout.aula8_lista_weather, de, para );
        lstViewWeather= findViewById(R.id.lstViewAula8Weather);
        lstViewWeather.setAdapter(lstViewWeatherAdapter);

    }

    public void btnAula8BuscarCEP(View view) {
        TextView    txtViewCEP= findViewById(R.id.edtTextAula8CEP);
        String      cep= txtViewCEP.getText().toString();
        HttpAsyncTask_CEP chamadaURL= new HttpAsyncTask_CEP();
        chamadaURL.txtViewLogradouro= findViewById(R.id.txtViewAula8Logradouro);
        chamadaURL.txtViewComplemento= findViewById(R.id.txtViewAula8Complemento);
        chamadaURL.txtViewBairro= findViewById(R.id.txtViewAula8Bairro);
        chamadaURL.txtViewLocalidade= findViewById(R.id.txtViewAula8Localidade);
        chamadaURL.txtViewUf= findViewById(R.id.txtViewAula8UF);
        chamadaURL.prgBar= findViewById(R.id.prgBarAula8);
        chamadaURL.execute(cep);
    }

    public void btnAula8Weather(View view) {
        HttpAsyncTask_Weather chamadaURL= new HttpAsyncTask_Weather();
        chamadaURL.txtViewMediaTemperaturas= findViewById(R.id.txtViewAula8Media_Temperaturas);
        chamadaURL.txtViewMediaUmidades= findViewById(R.id.txtViewAula8Media_Umidades);
        chamadaURL.txtViewMediaPontoOrvalho= findViewById(R.id.txtViewAula8Media_PontoOrvalho);
        chamadaURL.txtViewMediaPressao= findViewById(R.id.txtViewAula8Media_Pressao);
        chamadaURL.prgBar= findViewById(R.id.prgBarAula8);
        chamadaURL.lstViewWeather= findViewById(R.id.lstViewAula8Weather);
        chamadaURL.infoWeather= infoWeather;
        chamadaURL.execute("Teste");
    }
}

class HttpAsyncTask_CEP extends AsyncTask<String, Integer, String>{

    TextView txtViewLogradouro;
    TextView txtViewComplemento;
    TextView txtViewBairro;
    TextView txtViewLocalidade;
    TextView txtViewUf;
    ProgressBar prgBar;

    @Override
    protected void onPreExecute(){
        this.txtViewLogradouro.setText("-");
        this.txtViewComplemento.setText("-");
        this.txtViewBairro.setText("-");
        this.txtViewLocalidade.setText("-");
        this.txtViewUf.setText("-");
        this.prgBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... params){
        try {
            URL url= new URL("https://viacep.com.br/ws/" + params[0] + "/json/");
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int status= urlConnection.getResponseCode();
            if( status == 200 ){
                InputStream stream= new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder= new StringBuilder();
                String inputString;
                while ( (inputString = bufferedReader.readLine()) != null ){
                    builder.append(inputString);
                }
                urlConnection.disconnect();
                return builder.toString();
            }
        } catch (Exception ex){
            Log.e("URL", ex.toString());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        if( progress[0] < prgBar.getMax() )
            prgBar.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result){
        if( prgBar!= null )
            prgBar.setProgress(0);
        if( result != null){
            try{
                JSONObject obj= new JSONObject(result);
                this.txtViewLogradouro.setText(obj.getString("logradouro"));
                this.txtViewComplemento.setText(obj.getString("complemento"));
                this.txtViewBairro.setText(obj.getString("bairro"));
                this.txtViewLocalidade.setText(obj.getString("localidade"));
                this.txtViewUf.setText(obj.getString("uf"));
                this.prgBar.setVisibility(View.GONE);
            }catch (Exception ex){
                ex.printStackTrace();
                Log.e("URL", ex.toString());
            }
        }
    }

}

class HttpAsyncTask_Weather extends AsyncTask<String, Integer, String>{

    TextView txtViewMediaTemperaturas;
    TextView txtViewMediaUmidades;
    TextView txtViewMediaPontoOrvalho;
    TextView txtViewMediaPressao;
    ProgressBar prgBar;
    ListView lstViewWeather;
    List<HashMap<String, String>> infoWeather;

    @Override
    protected void onPreExecute(){
        this.txtViewMediaTemperaturas.setText("-");
        this.txtViewMediaUmidades.setText("-");
        this.txtViewMediaPontoOrvalho.setText("-");
        this.txtViewMediaPressao.setText("-");
        this.prgBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... params){
        try {
            URL url= new URL("http://ghelfer.net/la/weather.json");
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int status= urlConnection.getResponseCode();
            if( status == 200 ){
                InputStream stream= new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder= new StringBuilder();
                String inputString;
                while ( (inputString = bufferedReader.readLine()) != null ){
                    builder.append(inputString);
                }
                urlConnection.disconnect();
                return builder.toString();
            }
        } catch (Exception ex){
            Log.e("URL", ex.toString());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        if( progress[0] < prgBar.getMax() )
            prgBar.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result){
        if( prgBar!= null )
            prgBar.setProgress(0);
        if( result != null){
            try{
                //Para obter o objeto "weather" que um array
                JSONObject obj= new JSONObject(result);
                //Para obter o array de elementos
                JSONArray weatherArray= obj.getJSONArray("weather");
                //Para fazer as médias
                float mediaTempearatura= 0;
                float mediaUmidade= 0;
                float mediaPontoOrvalho= 0;
                float mediaPressao= 0;
                //Dados da Lista
                infoWeather.clear();
                HashMap<String, String> weatherItem;
                //Passa pelos elementos do array
                for( int i = 0; i < weatherArray.length(); i++ ){
                    //Retira elemento do array
                    JSONObject tempObj= weatherArray.getJSONObject(i);
                    //Media Temperatura
                    mediaTempearatura= mediaTempearatura + Float.valueOf(tempObj.getString("temperature"));
                    //Media Umidade
                    mediaUmidade= mediaUmidade + Float.valueOf(tempObj.getString("humidity"));
                    //Media Ponto de Orvalho
                    mediaPontoOrvalho= mediaPontoOrvalho + Float.valueOf(tempObj.getString("dewpoint"));
                    //Media Pressao
                    mediaPressao= mediaPressao + Float.valueOf(tempObj.getString("pressure"));
                    //Lista
                    weatherItem= new HashMap<String, String>();
                    weatherItem.put("datahora", tempObj.getString("datetime"));
                    weatherItem.put("temperature", tempObj.getString("temperature"));
                    weatherItem.put("humidity", tempObj.getString("humidity"));
                    weatherItem.put("dewpoint", tempObj.getString("dewpoint"));
                    weatherItem.put("pressure", tempObj.getString("pressure"));
                    infoWeather.add(weatherItem);
                }
                //Media Temperatura
                mediaTempearatura= mediaTempearatura / weatherArray.length();
                this.txtViewMediaTemperaturas.setText(String.valueOf(mediaTempearatura));
                //Media Umidade
                mediaUmidade= mediaUmidade / weatherArray.length();
                this.txtViewMediaUmidades.setText(String.valueOf(mediaUmidade));
                //Media Ponto de Orvalho
                mediaPontoOrvalho= mediaPontoOrvalho / weatherArray.length();
                this.txtViewMediaPontoOrvalho.setText(String.valueOf(mediaPontoOrvalho));
                //Media Pressao
                mediaPressao= mediaPressao / weatherArray.length();
                this.txtViewMediaPressao.setText(String.valueOf(mediaPressao));
                //Atualiza a lista
                lstViewWeather.deferNotifyDataSetChanged();
                //Termina ProgressBar
                this.prgBar.setVisibility(View.GONE);
            }catch (Exception ex){
                ex.printStackTrace();
                Log.e("URL", ex.toString());
            }
        }
    }

}
