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

import de.hsheilbronn.EgypttoursRServer.dto.ErholungDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.model.Erholung;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.sql.SQLException;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */
public interface IErholungService {


    /**
     *
     * @param page
     * @param size
     * @return
     * @throws NotFoundException
     */
    public Page<ErholungDTO> findAll(Integer page, Integer size) throws NotFoundException;

    /**
     *
     * @param erholungDTO
     * @throws SQLException
     */
    public void save(ErholungDTO erholungDTO, Authentication authentication) throws SQLException, OperationNotAllowedException;

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    public Erholung findById(Long id) throws NotFoundException;

    /**
     *
     * @param updatedErholung
     * @throws SQLException
     */
    public void update(Erholung updatedErholung) throws SQLException;

    /**
     *
     * @param erholung
     * @throws SQLException
     */
    public void delete(Erholung erholung) throws SQLException;


}
