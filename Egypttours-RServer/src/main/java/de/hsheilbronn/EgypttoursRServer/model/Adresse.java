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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Entity
public class Adresse {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String stadt;
	private String strasse;
	private double xKoordinate;
	private double yKoordinate;
	private String alternative;
	
	@OneToOne (mappedBy = "adresse")
	private Angebot angebot;
	
	
	public Adresse() {}	
	public Adresse(String stadt, String strasse, double xKoordinate, double yKoordinate, String alternative) {
		this.stadt = stadt;
		this.strasse = strasse;
		this.xKoordinate = xKoordinate;
		this.yKoordinate = yKoordinate;
		this.alternative = alternative;
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
	 * @return the stadt
	 */
	public String getStadt() {
		return stadt;
	}
	/**
	 * @param stadt the stadt to set
	 */
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}
	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse;
	}
	/**
	 * @param strasse the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	/**
	 * @return the xKoordinate
	 */
	public double getxKoordinate() {
		return xKoordinate;
	}
	/**
	 * @param xKoordinate the xKoordinate to set
	 */
	public void setxKoordinate(double xKoordinate) {
		this.xKoordinate = xKoordinate;
	}
	/**
	 * @return the yKoordinate
	 */
	public double getyKoordinate() {
		return yKoordinate;
	}
	/**
	 * @param yKoordinate the yKoordinate to set
	 */
	public void setyKoordinate(double yKoordinate) {
		this.yKoordinate = yKoordinate;
	}
	/**
	 * @return the alternative
	 */
	public String getAlternative() {
		return alternative;
	}
	/**
	 * @param alternative the alternative to set
	 */
	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}
	
	
	
	
	
	
	

}
