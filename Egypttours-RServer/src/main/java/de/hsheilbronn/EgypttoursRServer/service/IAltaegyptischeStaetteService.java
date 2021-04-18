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

package de.hsheilbronn.EgypttoursRServer.service;

import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.model.AltaegyptischeStaette;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */
public interface IAltaegyptischeStaetteService {

    /**
     *
     * @return
     * @throws NotFoundException
     */
    public List<AltaegyptischeStaette> findAll() throws NotFoundException;

    /**
     *
     * @param altaegyptischeStaette
     * @throws SQLException
     */
    public void save(AltaegyptischeStaette altaegyptischeStaette) throws SQLException;

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    public AltaegyptischeStaette findById(Long id) throws NotFoundException;

    /**
     *
     * @param updatedAltaegyptischeStaette
     * @throws SQLException
     */
    public void update(AltaegyptischeStaette updatedAltaegyptischeStaette) throws SQLException;

    /**
     *
     * @param altaegyptischeStaette
     * @throws SQLException
     */
    public void delete(AltaegyptischeStaette altaegyptischeStaette) throws SQLException;
}
