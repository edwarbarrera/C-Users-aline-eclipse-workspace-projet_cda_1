package dao;

import java.util.HashMap;

import entity.Produit;

public interface ProduitDao extends Dao <Produit> {
	public Produit findByName(String nom);
	public Produit findByPrice(Double prix);
	public HashMap<Integer, Produit> findAll();
}
