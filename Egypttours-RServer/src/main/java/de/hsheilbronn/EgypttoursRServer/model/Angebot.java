/**
 *  Copyright (C) 2021  the original author or authors.
 *
 * 		This program is free software: you can redistribute it and/or modify
 * 		it under the terms of the GNU General Public License as published by
 * 		the Free Software Foundation, either version 3 of the License, or
 * 		(at your option) any later version.
 *
 * 		This program is distributed in the hope that it will be useful,
 * 		but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 		GNU General Public License for more details.
 *
 * 		You should have received a copy of the GNU General Public License
 * 		along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.hsheilbronn.EgypttoursRServer.model;

import de.hsheilbronn.EgypttoursRServer.model.user.User;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Angebot {

	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Column(columnDefinition = "TEXT")
	private String beschreibung;
	private double geldBetrag;
	private String website;
	private String typeName;

	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = EBezahlart.class)
	private List<EBezahlart> bezahlarts;
	@Enumerated(EnumType.STRING)
	private EWaehrung waehrung;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<FilePath> pictureUrls;

	@OneToOne (cascade = CascadeType.PERSIST)
	private Adresse adresse;
	
	@OneToMany (cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Oeffnungszeiten> oeffnungszeiten ; //= new ArrayList<Oeffnungszeiten>();
	
	@ManyToOne (fetch = FetchType.LAZY)
	private User user;
	
	
	public Angebot() {}
	public Angebot(String name, String beschreibung, double geldBetrag, String website, Adresse adresse, User betreiber) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.geldBetrag = geldBetrag;
		this.website = website;
		this.adresse = adresse;
		this.user = betreiber;
	}
	@Deprecated
	public Angebot(String name, String beschreibung, double geldBetrag, String website, Adresse adresse) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.geldBetrag = geldBetrag;
		this.website = website;
		this.adresse = adresse;
	}
	public Angebot(String name, String beschreibung, double geldBetrag, String website, Adresse adresse, List<Oeffnungszeiten> oeffnungszeiten) {
		this.name = name;
		this.beschreibung = beschreibung;
		this.geldBetrag = geldBetrag;
		this.website = website;
		this.adresse = adresse;
		this.oeffnungszeiten = oeffnungszeiten;
		
	}
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
	/**
	 * @param beschreibung the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	/**
	 * @return the geldBetrag
	 */
	public double getGeldBetrag() {
		return geldBetrag;
	}
	/**
	 * @param geldBetrag the geldBetrag to set
	 */
	public void setGeldBetrag(double geldBetrag) {
		this.geldBetrag = geldBetrag;
	}
	
	
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the oeffnungszeiten
	 */
	public List<Oeffnungszeiten> getOeffnungszeiten() {
		if(oeffnungszeiten == null) {oeffnungszeiten = new ArrayList<Oeffnungszeiten>();}
		return oeffnungszeiten;
	}
	/**
	 * @param oeffnungszeiten the oeffnungszeiten to set
	 */
	public void setOeffnungszeiten(List<Oeffnungszeiten> oeffnungszeiten) {
		this.oeffnungszeiten = oeffnungszeiten;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return
	 */
	public List<FilePath> getPictureUrls() {
		return pictureUrls;
	}

	/**
	 *
	 * @param pictureUrls
	 */
	public void setPictureUrls(List<FilePath> pictureUrls) {
		this.pictureUrls = pictureUrls;
	}

	/**
	 *
	 * @return
	 */
	public List<EBezahlart> getBezahlarts() {
		return bezahlarts;
	}

	/**
	 *
	 * @param bezahlarts
	 */
	public void setBezahlarts(List<EBezahlart> bezahlarts) {
		this.bezahlarts = bezahlarts;
	}

	/**
	 *
	 * @return
	 */
	public EWaehrung getWaehrung() {
		return waehrung;
	}

	/**
	 *
	 * @param waehrung
	 */
	public void setWaehrung(EWaehrung waehrung) {
		this.waehrung = waehrung;
	}


	public String getTypeName() {
		if(typeName == null || typeName.isEmpty())
			typeName = getClass().getName();
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
