package com.example.tp1article.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp1article.R;
import com.example.tp1article.bo.Article;
import com.example.tp1article.repository.ArticleBddRepository;
import com.example.tp1article.repository.InterfaceArticleRepository;
import com.facebook.stetho.Stetho;

public class InsertArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_article);
        Stetho.initializeWithDefaults(this);
    }
    public void onClickSave(View view){
        InterfaceArticleRepository repoArticle = new ArticleBddRepository(this);

        EditText etName = findViewById(R.id.et_name_article);
        String name = etName.getText().toString();

        EditText etDescription = findViewById(R.id.et_descriptionM);
        String description = etDescription.getText().toString();

        EditText etPrice = findViewById(R.id.et_price_articleM);
        float price = Float.parseFloat(etPrice.getText().toString());

        EditText etLink = findViewById(R.id.et_link_articleM);
        String link = etLink.getText().toString();

        Article articleInsert = new Article(name, price, description, 0f,false, link); //id, String name, String description, Float rating, Boolean isBought, String link
        repoArticle.insert(articleInsert);

        Toast.makeText(this, name + description, Toast.LENGTH_LONG).show();
        //permet de retourner à la page précédente sans utiliser les intent
        finish();

    }

    //ICI : ON LIE L’ACTION BARRE À L'ACTIVITÉ
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //on décompresse le xml du menu
        getMenuInflater().inflate(R.menu.mon_menu, menu);
        return true;
    }

    //ICI : ON DÉFINIT LES ACTIONS DE L’ACTION BARRE
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"Préférences", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_recherche:
                Toast.makeText(this,"Recherche", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}