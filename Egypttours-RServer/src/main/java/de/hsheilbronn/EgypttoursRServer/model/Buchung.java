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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */
@Entity
public class Buchung {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime datumTime;
	private boolean onlineBuchbar;
	private EBezahlart bezahlarts;
	private EWaehrung waehrung;
	
	
	@ManyToOne (fetch = FetchType.LAZY)
	private User user;
	
	@ManyToMany (fetch = FetchType.LAZY)
	private List<Angebot> angebote;
	

	public Buchung() {}

	/**
	 *
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime getDatumTime() {
		return datumTime;
	}

	/**
	 *
	 * @param datumTime
	 */
	public void setDatumTime(LocalDateTime datumTime) {
		this.datumTime = datumTime;
	}

	/**
	 * @return the onlineBuchbar
	 */
	public boolean isOnlineBuchbar() {
		return onlineBuchbar;
	}
	/**
	 * @param onlineBuchbar the onlineBuchbar to set
	 */
	public void setOnlineBuchbar(boolean onlineBuchbar) {
		this.onlineBuchbar = onlineBuchbar;
	}

	/**
	 *
	 * @return
	 */
	public EBezahlart getBezahlarts() {
		return bezahlarts;
	}

	/**
	 *
	 * @param bezahlarts
	 */
	public void setBezahlarts(EBezahlart bezahlarts) {
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
	 * @return the angbote
	 */
	/**
	 * @return the angebote
	 */
	public List<Angebot> getAngebote() {
		if(angebote == null) {angebote = new ArrayList<Angebot>();}
		return angebote;
	}
	/**
	 * @param angebote the angebote to set
	 */
	public void setAngebote(List<Angebot> angebote) {
		this.angebote = angebote;
	}
	
	
	
	

}
