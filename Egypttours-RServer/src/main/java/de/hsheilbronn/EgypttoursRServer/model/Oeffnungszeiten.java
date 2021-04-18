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

import javax.persistence.*;
import java.time.LocalTime;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


@Entity
public class Oeffnungszeiten {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private LocalTime von;
    private LocalTime bis;

    public Oeffnungszeiten() {}

    public Oeffnungszeiten(LocalTime von, LocalTime bis) {
        this.von = von;
        this.bis = bis;
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

	public LocalTime getVon() {
        return von;
    }

    public void setVon(LocalTime von) {
        this.von = von;
    }

    public LocalTime getBis() {
        return bis;
    }

    public void setBis(LocalTime bis) {
        this.bis = bis;
    }

}