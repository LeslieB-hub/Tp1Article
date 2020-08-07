package com.example.tp1article.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1article.R;
import com.example.tp1article.bo.Article;
import com.example.tp1article.repository.ArticleBddRepository;
import com.example.tp1article.repository.InterfaceArticleRepository;

public class ModifyArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_article);
        //récupérer l'article
        Article articleModify = getArticleIntent();

        //Afficher l'article ds la vue
        EditText etName = findViewById(R.id.et_name_modify);
        etName.setText(articleModify.getName());
        EditText etPrice = findViewById(R.id.et_price_modify);
        etPrice.setText(String.valueOf(articleModify.getPrice()));
        EditText etLink = findViewById(R.id.et_link_modify);
        etLink.setText(articleModify.getLink());
        EditText etDescription = findViewById(R.id.et_description_modify);
        etDescription.setText(articleModify.getDescription());

    }

    private Article getArticleIntent() {
        Intent intent = getIntent();
        return intent.getParcelableExtra("articleModify");
    }

    public void onClickModifyArticle(View view) {
        InterfaceArticleRepository repoArticle = new ArticleBddRepository(this);
        //récupérer l'article
        Article articleModify = getArticleIntent();

        //récupérer les modifications
        EditText etName = findViewById(R.id.et_name_modify);
        String name = etName.getText().toString();

        EditText etDescription = findViewById(R.id.et_description_modify);
        String description = etDescription.getText().toString();

        EditText etPrice = findViewById(R.id.et_price_modify);
        float price = Float.parseFloat(etPrice.getText().toString());

        EditText etLink = findViewById(R.id.et_link_modify);
        String link = etLink.getText().toString();

        Article articleModified = new Article(articleModify.getId(), name, price, description, articleModify.getRating(),articleModify.getIsBought(), link);
        repoArticle.update(articleModified);

        Toast.makeText(ModifyArticleActivity.this, "Votre article " + articleModified.getName() + " a été modifié.", Toast.LENGTH_LONG).show();
        //redigige vers la page d'accueil après modification
        Intent intentMain = new Intent(ModifyArticleActivity.this, MainActivity.class);
        startActivity(intentMain);

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