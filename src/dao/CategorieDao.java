package dao;

import entity.Categorie;
import entity.Produit;

public interface CategorieDao extends Dao <Categorie> {
	public Categorie findByLibelle(String libelle);
	public HashMap<Integer, Categorie> findAll();
}
