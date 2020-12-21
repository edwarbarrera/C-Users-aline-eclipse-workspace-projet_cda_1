package test;

import java.sql.SQLException;
import java.util.HashMap;

import dao.ConnectBd;
import dao.ProduitDao;
import dao.ProduitDaoImpl;
import entity.Produit;


public class PG {
	public static void main(String[] args) throws SQLException {
		ConnectBd.connecte();

//		ProduitDaoImpl produitDao = new ProduitDaoImpl();
////		Produit produit = new Produit(22, "TEST   ", 50, " BLABLABLABLA  ", "urlimage", 1, 10);
//		ProduitDao dao = new ProduitDaoImpl();
////		dao.insert(produit);
//		Produit recherche = produitDao.findByCategorie("manga");
//		System.out.println(recherche);
	}
}