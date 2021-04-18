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

import de.hsheilbronn.EgypttoursRServer.dao.ErholungRepository;
import de.hsheilbronn.EgypttoursRServer.dto.ErholungDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.mapper.ErholungMapper;
import de.hsheilbronn.EgypttoursRServer.model.Angebot;
import de.hsheilbronn.EgypttoursRServer.model.Erholung;
import de.hsheilbronn.EgypttoursRServer.model.Restaurant;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import de.hsheilbronn.EgypttoursRServer.service.IErholungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Service("ErholungService")
public class ErholungService implements IErholungService {

    private final Logger logger = LoggerFactory.getLogger(ErholungService.class);

    @Autowired
    private ErholungRepository erholungRepository;

    @Autowired
    private ErholungMapper erholungMapper;


    /**
     *
     * @return
     * @throws NotFoundException
     */
    @Override
    public Page<ErholungDTO> findAll(Integer page, Integer size) throws NotFoundException{

        List<Erholung> erholungs = erholungRepository.findAll();
        List<ErholungDTO> erholungDTOs = new ArrayList<>();
        erholungs.stream().parallel().forEach(erholung -> {
            erholungDTOs.add(erholungMapper.toDTO(erholung));
        });

        Page<ErholungDTO> angebots = new PageImpl<ErholungDTO>(
                erholungDTOs,
                PageRequest.of(page==null || page < 0?0:page,
                        size==null || size <= 1?9:size),erholungDTOs.size()
                );

        if(erholungs == null || erholungs.isEmpty())
            throw new NotFoundException("No Element Found");

        return angebots;
    }

    /**
     * @param erholung
     * @throws SQLException
     */
    @Override
    public void save(Erholung erholung, Authentication authentication) throws SQLException, OperationNotAllowedException {
        eRequiredAttributesCheck(erholung);
        User user = new User();
        user.setUsername(authentication.getName());
        erholung.setUser(user);
        erholungRepository.save(erholung);
    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @Override
    public Erholung findById(Long id) throws NotFoundException {
        Optional<Erholung> erholung = erholungRepository.findById(id);
        if(!erholung.isPresent()){
            throw new NotFoundException("Erholung Not Found");
        }
        return erholung.get();
    }

    /**
     * @param updatedErholung
     * @throws SQLException
     */
    @Override
    public void update(Erholung updatedErholung) throws SQLException {

    }

    /**
     * @param erholung
     * @throws SQLException
     */
    @Override
    public void delete(Erholung erholung) throws SQLException {
        if(erholung == null) throw new OperationNotAllowedException("Erholung can't be null");
        erholungRepository.delete(erholung);
    }


    private void eRequiredAttributesCheck(Erholung erholung) throws OperationNotAllowedException{
        if(erholung == null)
        {
            throw new OperationNotAllowedException("Erholung is Required");
        }

        if (    erholung.getName() == null
               || erholung.getName().isEmpty()
        ){
        throw new OperationNotAllowedException("Name is Required");
        }
        if (    erholung.getBeschreibung() == null
               || erholung.getBeschreibung().isEmpty()
        ){
            throw new OperationNotAllowedException("Beschreibung is Required");
        }
        if (    erholung.getPictureUrls() == null
        ){
            throw new OperationNotAllowedException("PictureUrl is Required");
        }
    }


}
