package entity;

public class Produit {
	
private int id;
private String nom;
private int quantite;
private String description;
private String url_image;
private double prix;

public Produit() {
	super();
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public int getQuantite() {
	return quantite;
}
public void setQuantite(int quantite) {
	this.quantite = quantite;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getUrl_image() {
	return url_image;
}
public void setUrl_image(String url_image) {
	this.url_image = url_image;
}
public double getPrix() {
	return prix;
}
public void setPrix(double prix) {
	this.prix = prix;
}

@Override
public String toString() {
	return "Produit [id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", description=" + description
			+ ", url_image=" + url_image + ", prix=" + prix + "]";
}


}
