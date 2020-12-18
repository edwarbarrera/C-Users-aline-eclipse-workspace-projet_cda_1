package dao;

import CategorieDao;

public class CategorieDaoImpl implements CategorieDao {
	
	public void insert(Categorie t) {
		// TODO Auto-generated method stub
		
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Categorie findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public HashMap<Integer, Categorie> findAll() {
		HashMap<Integer, Categorie> lesCategories = new HashMap<Integer, Categorie>();
		int i = 0;
		try {
			Connection con = ConnectBd.con;
            Statement canal = con.createStatement();
            ResultSet res = canal.executeQuery("select * from categorie");
			while( res.next() ){
				Categorie c = new Categorie();
				c.setId( res.getInt("id_categorie") );
				c.setLibelle ( res.getString("libelle") );
				i+=1;
				lesCategories.put(i, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lesCategories;
	}

	public Categorie findByLibelle(String libelle) {
		Categorie c = new Categorie();
		try {
			Connection con = ConnectBd.con;
            String sql = "SELECT * FROM categorie WHERE libelle =?";                     
            PreparedStatement canal = con.prepareStatement(sql);                         
            canal.setString(1, libelle);                                                         
            ResultSet res = canal.executeQuery();                                         
			res.next();
			c.setId( res.getInt("id_categorie") );
			c.setLibelle ( res.getString("libelle") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
}
