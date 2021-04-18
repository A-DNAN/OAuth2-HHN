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

import javax.persistence.Entity;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Entity
public class AltaegyptischeStaette extends Angebot {

	
	private int erbauJahr;

	public AltaegyptischeStaette() {super();}

	public AltaegyptischeStaette( int erbauJahr) {
		super();
		this.erbauJahr = erbauJahr;
	}

	

	public AltaegyptischeStaette(String name,String beschreibung, double geldBetrag, String website, Adresse adresse, int erbauJahr, User betreiber) {
		super(name,beschreibung, geldBetrag, website, adresse, betreiber);
		this.erbauJahr = erbauJahr;
	}
	
	@Deprecated
	public AltaegyptischeStaette(String name,String beschreibung, double geldBetrag, String website, Adresse adresse, int erbauJahr) {
		super(name, beschreibung, geldBetrag, website, adresse);
		this.erbauJahr = erbauJahr;
	}



	/**
	 * @return the erbauJahr
	 */
	public int getErbauJahr() {
		return erbauJahr;
	}




	/**
	 * @param erbauJahr the erbauJahr to set
	 */
	public void setErbauJahr(int erbauJahr) {
		this.erbauJahr = erbauJahr;
	}

	
	
	
	
	
	
	
	
}
