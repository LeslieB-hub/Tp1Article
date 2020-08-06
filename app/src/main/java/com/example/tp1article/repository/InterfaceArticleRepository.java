package com.example.tp1article.repository;

import androidx.lifecycle.LiveData;


import com.example.tp1article.bo.Article;

import java.util.List;

/**
 * On recréé une interface pour que la couche repository ne soit pas lié directement à la bdd. On décrit les mêmes méthodes que dans ArticleDao.
 *
 * Interface permettant de mettre en place le design pattern DAO.
 * On descrit tte les méthodes dt on a besoin cad le CRUD
 * Appele les fct créer par Room ds la class ArticleDao_Impl
 */
public interface InterfaceArticleRepository {
    //create insérer un article
    void insert(Article article);
    //create insérer des articles
    //void insert(List<Article> articles);

    //read récupérer la liste des articles de la table Article.
    LiveData<List<Article>> getArticles();

    //read récupérer l'article choisi de la table Article.
    Article get(int id);

    //update MAJ un article de la bdd
    void update(Article article);

    //delete supprimer un article de la table article de la bdd
    void delete(Article article);

    //delete supprimer toute la table article de la bdd
    void delete();

}
