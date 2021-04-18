/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */
package de.hsheilbronn.EgypttoursRServer.model;


import de.hsheilbronn.EgypttoursRServer.model.user.User;

import javax.persistence.Entity;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Entity
public class Restaurant extends Angebot{

	
	private String kueche;
	private boolean zumMitnehmen;
	
	
	public Restaurant() {
		super();
	}

	public Restaurant(String name,String beschreibung, double geldBetrag, String website, Adresse adresse, String kueche, boolean zumMitnehmen, User betreiber) {
		super( name,beschreibung, geldBetrag, website, adresse,betreiber);
		this.kueche = kueche;
		this.zumMitnehmen = zumMitnehmen;
	}
	
	@Deprecated
	public Restaurant(String name, String beschreibung, double geldBetrag, String website, Adresse adresse, String kueche, boolean zumMitnehmen) {
		super(name,beschreibung, geldBetrag, website, adresse);
		this.kueche = kueche;
		this.zumMitnehmen = zumMitnehmen;
	}


	/**
	 * @return the kueche
	 */
	public String getKueche() {
		return kueche;
	}


	/**
	 * @param kueche the kueche to set
	 */
	public void setKueche(String kueche) {
		this.kueche = kueche;
	}


	/**
	 * @return the zumMitnehmen
	 */
	public boolean isZumMitnehmen() {
		return zumMitnehmen;
	}


	/**
	 * @param zumMitnehmen the zumMitnehmen to set
	 */
	public void setZumMitnehmen(boolean zumMitnehmen) {
		this.zumMitnehmen = zumMitnehmen;
	}
	
	
	
	
}
