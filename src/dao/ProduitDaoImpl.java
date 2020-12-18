package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap; 

import entity.Produit;

public class ProduitDaoImpl implements ProduitDao {

	public void insert(Produit t) { //Remplacer toutes les requêtes par des prepared statements + rajouter des throws
		try {
			Connection con = ConnectBd.con;
			String sql = "INSERT INTO commande VALUES(?,?,?,?,?,?);";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setInt(1, t.getId());
			prep.setString(2, t.getNom());
			prep.setInt(3, t.getQuantite());
			prep.setString(4, t.getDescription());
			prep.setString(5, t.getUrl_image());
			prep.setDouble(6, t.getPrix());
			prep.executeUpdate();
			// recuperer le dernier index   ( = index du client)
			// mettre cet index dans l'idClient de la commande
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean delete(int id) {
		try {
			Connection con = ConnectBd.con;
			Statement canal = con.createStatement();
			canal.executeUpdate("delete produit where id_produit=" +id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public HashMap<Integer, Produit> findAll() {
		HashMap<Integer, Produit> lesProduits = new HashMap<Integer, Produit>();
		int i = 0;
		try {
			Connection con = ConnectBd.con;
            Statement canal = con.createStatement();
            ResultSet res = canal.executeQuery("select * from produit");
			while( res.next() ){
				Produit p = new Produit();
				p.setId( res.getInt("id_produit") );
				p.setNom ( res.getString("nom") );
				p.setQuantite ( res.getInt("quantite") );
				p.setDescription ( res.getString("description") );
				p.setUrl_image ( res.getString("url_image") );
				p.setPrix ( res.getDouble("prix_actuel") );
				i+=1;
				lesProduits.put(i, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lesProduits;
	}

	public Produit findById(int id) {
		Produit p = new Produit();
		try {
			Connection con = ConnectBd.con;
            String sql = "SELECT * FROM produit WHERE id_produit =?";                     
            PreparedStatement canal = con.prepareStatement(sql);                         
            canal.setInt(1, id);                                                         
            ResultSet res = canal.executeQuery();                                         
			res.next();
			p.setId( res.getInt("id_produit") );
			p.setNom ( res.getString("nom") );
			p.setQuantite ( res.getInt("quantite") );
			p.setDescription ( res.getString("description") );
			p.setUrl_image ( res.getString("url_image") );
			p.setPrix ( res.getDouble("prix_actuel") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public Produit findByName(String nom) { //à voir
		Produit p = new Produit();
		try {
			Connection con = ConnectBd.con;
            String sql = "SELECT * FROM produit WHERE nom =?";                    
            PreparedStatement canal = con.prepareStatement(sql);                         
            canal.setString(1, nom);                                                         
            ResultSet res = canal.executeQuery();                                         
			res.next();
			p.setId( res.getInt("id_produit") );
			p.setNom ( res.getString("nom") );
			p.setQuantite ( res.getInt("quantite") );
			p.setDescription ( res.getString("description") );
			p.setUrl_image ( res.getString("url_image") );
			p.setPrix ( res.getDouble("prix_actuel") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
		
	}

	public Produit findByPrice(Double prix) {
		Produit p = new Produit();
		try {
			Connection con = ConnectBd.con;
            String sql = "SELECT * FROM produit WHERE prix_actuel =?";                     
            PreparedStatement canal = con.prepareStatement(sql);                         
            canal.setDouble(1, prix);                                                         
            ResultSet res = canal.executeQuery();                                         
			res.next();
			p.setId( res.getInt("id_produit") );
			p.setNom ( res.getString("nom") );
			p.setQuantite ( res.getInt("quantite") );
			p.setDescription ( res.getString("description") );
			p.setUrl_image ( res.getString("url_image") );
			p.setPrix ( res.getDouble("prix_actuel") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
