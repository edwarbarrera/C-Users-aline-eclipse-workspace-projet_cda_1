package dao;

import java.util.HashMap;

import entity.Produit;

public interface ProduitDao extends Dao <Produit> {
	public Produit findByName(String nom);
	public Produit findByPrice(Double prix);
	public Produit findByCategorie(String libelle);
	public Produit findByNameCategorie(String nom, int ref);
	public Produit findByNamePrice(String nom, Double prix);
	public Produit findByPriceCategorie(Double prix, int ref);
	public Produit findByAll(String nom, Double prix, int ref);
	public HashMap<Integer, Produit> findAll();
}
