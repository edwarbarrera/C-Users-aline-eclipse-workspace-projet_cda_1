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
	
	 //Test du Trigger interdisant la quantité negative
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
	
	// Test de la méthode permettant de trouver un produit par mot clé 
	@Test
	void testFindByMotCle() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit(6, "Tout JavaScript", 10, "Ce livre s’adresse à tous les développeurs web, qu’ils soient  débutants ou avancés."
				+ " Le JavaScript sert avant tout à rendre les pages web interactives  et dynamiques du côté de l’utilisateur, mais il est  également de plus en plus utilisé pour créer des applications  complètes, y compris côté serveur",
                "urlimage", 2, 22.9);
		Produit p2 = new Produit(8, "Le tour du monde en 80 jours", 85, "En 1872, un riche gentleman londonien, Phileas Fogg, parie vingt mille livres qu'il fera le tour du monde en quatre-vingts jours. "
				+ "Accompagné de son valet de chambre, le dévoué Passepartout, il quitte Londres pour une formidable course contre la montre. Au prix de mille aventures, notre héros va s'employer à gagner ce pari.",
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
		Produit p = new Produit(7, "Mémento CSS3", 36, "Aussi léger que riche, indéchirable et imperméable, voici la 4e édition mise à jour du mémento CSS 3 de Raphaël Goetter, avec une lisibilité améliorée !",
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
		Produit p1 = new Produit(14, "One Piece - Édition originale - Tome 01: À l'aube d'une grande aventure", 50, "Nous sommes à l'ère des pirates. Luffy, un garçon espiègle, rêve de devenir le roi des pirates en trouvant le “One Piece”, un fabuleux trésor. Seulement, Luffy a avalé un fruit du démon qui l'a transformé en homme élastique.",
	            "urlimage", 3, 6.9);
		Produit p2 = new Produit (15, "One piece - Edition originale Vol.2", 32, "Luffy atterrit sur une île occupée par Baggy et son groupe, un équipage de pirates redoutable. Il y rencontre Nami, une « voleuse de pirates ». Apprenant la nature de Luffy, elle décide de l’utiliser pour infiltrer les rangs de Baggy et dérober ses trésors…",
	            "urlimage", 3, 6.9);
		Produit p3 = new Produit(16, "One Piece - Édition originale - Tome 03: Une vérité qui blesse", 0, "Luffy affronte Baggy. Ce dernier admet vouer une haine sans nom envers Shanks. En effet, tous deux ont appris les rudiments de la piraterie sur le même navire, et un malencontreux incident a provoqué la descente aux enfers de Baggy…",
	            "urlimage", 3, 6.9);
		produitsExpected.put(1, p1);
		produitsExpected.put(2, p2);
		produitsExpected.put(3, p3);
		ProduitDao dao = new ProduitDaoImpl();
		HashMap<Integer, Produit> actual = dao.findByCategorie(3);
		assertEquals(produitsExpected.toString(), actual.toString());
	}
	
	// Test par nom et catégorie
	@Test
	void testFindByNameCategorie() {
		HashMap<Integer, Produit> produitsExpected = new HashMap<Integer, Produit>();
		Produit p1 = new Produit(14, "One Piece - Édition originale - Tome 01: À l'aube d'une grande aventure", 50, "Nous sommes à l'ère des pirates. Luffy, un garçon espiègle, rêve de devenir le roi des pirates en trouvant le “One Piece”, un fabuleux trésor. Seulement, Luffy a avalé un fruit du démon qui l'a transformé en homme élastique.",
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
		Produit p1 = new Produit (2, "Programmer en Java", 29, "De la programmation objet en Java au développement d'applications web. Dans cet ouvrage, Claude Delannoy applique au langage Java la démarche pédagogique qui a fait le succès de ses livres sur le C et le C++. Il insiste tout particulièrement sur la bonne compréhension des concepts objet et sur l'acquisition de méthodes de programmation rigoureuses.",
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
