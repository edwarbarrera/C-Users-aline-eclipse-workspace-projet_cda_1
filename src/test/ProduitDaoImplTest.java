package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.ConnectBd;
import dao.ProduitDao;
import dao.ProduitDaoImpl;
import entity.Produit;




class ProduitDaoImplTest {
	
	//avant chaque test on reset la Bdd
	@BeforeEach 
	void testreset() throws SQLException {
		ConnectBd.reset();
	}

	// Apres chaque test on rest la Bdd
	@AfterEach
	void resetAfterTest() throws SQLException {
		ConnectBd.reset();
	}
	
	// Test d'un insert simple
	@Test
	void testInsert() throws SQLException { 
		Produit produit = new Produit(22, "test", 50, "blabla", "url", 1, 10);
		ProduitDao dao = new ProduitDaoImpl();
		dao.insert(produit);
		assertEquals(22, produit.getId()); 
		assertEquals(produit.toString(), dao.findById(22).toString());  
	}
	
	 //Test du Trigger interdisant la quantit� negative
	@Test
	void testInsertNeg() {
		try {
		Produit produit = new Produit(23, "test", -5, "blabla", "url", 1, 10);
		ProduitDao dao = new ProduitDaoImpl();
		dao.insert(produit);
		
		} catch (SQLException exc) {
		assertEquals(ConnectBd.QUANTITE_NEGATIVE, exc.getErrorCode());
		}
	}
	
	// Test de la m�thode permettant de trouver un produit par mot cl� 
	@Test
	void testFindByMotCle() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit(6, "Tout JavaScript", 10, "Ce livre s�adresse � tous les d�veloppeurs web, qu�ils soient  d�butants ou avanc�s."
				+ " Le JavaScript sert avant tout � rendre les pages web interactives  et dynamiques du c�t� de l�utilisateur, mais il est  �galement de plus en plus utilis� pour cr�er des applications  compl�tes, y compris c�t� serveur",
                "urlimage", 2, 22.9);
		Produit p2 = new Produit(8, "Le tour du monde en 80 jours", 85, "En 1872, un riche gentleman londonien, Phileas Fogg, parie vingt mille livres qu'il fera le tour du monde en quatre-vingts jours. "
				+ "Accompagn� de son valet de chambre, le d�vou� Passepartout, il quitte Londres pour une formidable course contre la montre. Au prix de mille aventures, notre h�ros va s'employer � gagner ce pari.",
	            "urlimage", 1, 5.5);
		produitsExpected.put(1, p1);
		produitsExpected.put(2, p2);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByMotCle("tou");
		assertEquals(produitsExpected.toString(), actual.toString());
	}

	//
	@Test
	void testFindByPrice() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p = new Produit(7, "M�mento CSS3", 36, "Aussi l�ger que riche, ind�chirable et imperm�able, voici la 4e �dition mise � jour du m�mento CSS 3 de Rapha�l Goetter, avec une lisibilit� am�lior�e !",
	            "urlimage", 2, 5);
		produitsExpected.put(1, p);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByPrice(5);
		assertEquals(produitsExpected.toString(), actual.toString());
	}

	//Test recherche par categorie
	@Test
	void testFindByCategorie() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit(14, "One Piece - �dition originale - Tome 01: � l'aube d'une grande aventure", 50, "Nous sommes � l'�re des pirates. Luffy, un gar�on espi�gle, r�ve de devenir le roi des pirates en trouvant le �One Piece�, un fabuleux tr�sor. Seulement, Luffy a aval� un fruit du d�mon qui l'a transform� en homme �lastique.",
	            "urlimage", 3, 6.9);
		Produit p2 = new Produit (15, "One piece - Edition originale Vol.2", 32, "Luffy atterrit sur une �le occup�e par Baggy et son groupe, un �quipage de pirates redoutable. Il y rencontre Nami, une � voleuse de pirates �. Apprenant la nature de Luffy, elle d�cide de l�utiliser pour infiltrer les rangs de Baggy et d�rober ses tr�sors�",
	            "urlimage", 3, 6.9);
		Produit p3 = new Produit(16, "One Piece - �dition originale - Tome 03: Une v�rit� qui blesse", 0, "Luffy affronte Baggy. Ce dernier admet vouer une haine sans nom envers Shanks. En effet, tous deux ont appris les rudiments de la piraterie sur le m�me navire, et un malencontreux incident a provoqu� la descente aux enfers de Baggy�",
	            "urlimage", 3, 6.9);
		produitsExpected.put(1, p1);
		produitsExpected.put(2, p2);
		produitsExpected.put(3, p3);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByCategorie(3);
		assertEquals(produitsExpected.toString(), actual.toString());
	}
	
	// Test par nom et cat�gorie
	@Test
	void testFindByNameCategorie() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit(14, "One Piece - �dition originale - Tome 01: � l'aube d'une grande aventure", 50, "Nous sommes � l'�re des pirates. Luffy, un gar�on espi�gle, r�ve de devenir le roi des pirates en trouvant le �One Piece�, un fabuleux tr�sor. Seulement, Luffy a aval� un fruit du d�mon qui l'a transform� en homme �lastique.",
	            "urlimage", 3, 6.9);
		produitsExpected.put(1, p1);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByNameCategorie("aventure", 3);
		assertEquals(produitsExpected.toString(), actual.toString());
	}
	
  // Test recheche par nom et prix
	@Test
	void testFindByNamePrice() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit (2, "Programmer en Java", 29, "De la programmation objet en Java au d�veloppement d'applications web. Dans cet ouvrage, Claude Delannoy applique au langage Java la d�marche p�dagogique qui a fait le succ�s de ses livres sur le C et le C++. Il insiste tout particuli�rement sur la bonne compr�hension des concepts objet et sur l'acquisition de m�thodes de programmation rigoureuses.",
	            "urlimage", 2, 12.69);
		produitsExpected.put(1, p1);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByNamePrice("java", 15);
		assertEquals(produitsExpected.toString(), actual.toString());
	}

//	@Test
//	void testFindByPriceCategorie() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByAll() {
//		fail("Not yet implemented");
//	}

}
