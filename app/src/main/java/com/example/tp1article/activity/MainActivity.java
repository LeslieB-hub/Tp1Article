package com.example.tp1article.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tp1article.R;
import com.example.tp1article.ViewModel.ArticleViewModel;
import com.example.tp1article.bo.Article;
import com.example.tp1article.repository.ArticleBddRepository;
import com.example.tp1article.repository.InterfaceArticleRepository;
import com.facebook.stetho.Stetho;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        InterfaceArticleRepository repoArticle = new ArticleBddRepository(this);
        ArticleViewModel vm = ViewModelProviders.of(this).get(ArticleViewModel.class);

/*        Article article1 = new Article("pain au chocolat", (float) 40.0, "Description",(float) 10, false, "www.google.com");
        repoArticle.insert(article1);*/

        LiveData<List<Article>> observateur = vm.getArticles();

        observateur.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                for (Article item : articles)
                {
                    Log.i("debog", "Article : "+ item);
                }
            }
        });

    }

    public void onClickAdd(View view) {
        Intent intentAdd = new Intent(this, InsertArticleActivity.class);
        startActivity(intentAdd);
    }

    public void onClickList(View view) {
        Intent intentList = new Intent(this, listeArticleActivity.class);
        startActivity(intentList);
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