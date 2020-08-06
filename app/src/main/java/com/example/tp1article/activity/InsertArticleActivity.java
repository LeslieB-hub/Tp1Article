package com.example.tp1article.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        Article articleInsert = new Article(name, price, description, (float) 0,false, link); //id, String name, String description, Float rating, Boolean isBought, String link
        repoArticle.insert(articleInsert);

        Toast.makeText(this, name + description, Toast.LENGTH_LONG).show();

    }



}