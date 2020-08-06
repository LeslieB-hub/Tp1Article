package com.example.tp1article.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tp1article.bo.Article;
import com.example.tp1article.repository.ArticleBddRepository;
import com.example.tp1article.repository.InterfaceArticleRepository;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {
    private InterfaceArticleRepository repo;
    /**
     * Permet de n'avoir qu'un seul observateur pour toute l'application
     */
    private LiveData<List<Article>> observateur = null;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        repo = new ArticleBddRepository(application);
        observateur = repo.getArticles();
    }

    public LiveData<List<Article>> getArticles(){
        return observateur;
    }

    void insert(Article article){
        repo.insert(article);
    }

    void update(Article article){
        repo.update(article);
    }

    void delete(Article article){
        repo.delete(article);
    }

    void delete(){
        repo.delete();
    }
}
