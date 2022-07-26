package fr.eni.projetEncheres.bo;

import java.io.Serializable;

public class Retrait implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String rue;
	private String codePostal;
	private String ville;
	private Article article;
	/**
	 * 
	 */
	public Retrait() {
	}
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", article=" + article + "]";
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
}
