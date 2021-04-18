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

package de.hsheilbronn.EgypttoursRServer.service.implementation;

import de.hsheilbronn.EgypttoursRServer.dao.BuchungRepository;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.model.Buchung;
import de.hsheilbronn.EgypttoursRServer.model.Restaurant;
import de.hsheilbronn.EgypttoursRServer.service.IBuchungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Service("BuchungService")
public class BuchungService implements IBuchungService {

    private final Logger logger = LoggerFactory.getLogger(BuchungService.class);

    @Autowired
    private BuchungRepository buchungRepository;

    /**
     *
     * @return
     * @throws NotFoundException
     */
    @Override
    public List<Buchung> findAll() {
        List<Buchung> buchungs = buchungRepository.findAll();
        if(buchungs == null || buchungs.isEmpty())
            throw new NotFoundException("No Element Found");

        return buchungs;
    }

    /**
     * @param buchung
     * @throws SQLException
     */
    @Override
    public void save(Buchung buchung) throws SQLException {

    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @Override
    public Buchung findById(Long id) throws NotFoundException {
        Optional<Buchung> buchung = buchungRepository.findById(id);
        if(!buchung.isPresent()){
            throw new NotFoundException("Buchung Not Found");
        }
        return buchung.get();
    }

    /**
     * @param updatedBuchung
     * @throws SQLException
     */
    @Override
    public void update(Buchung updatedBuchung) throws SQLException {

    }

    /**
     * @param buchung
     * @throws SQLException
     */
    @Override
    public void delete(Buchung buchung) throws SQLException {
        if(buchung == null) throw new OperationNotAllowedException("Buchung can't be null");
        buchungRepository.delete(buchung);
    }
}
