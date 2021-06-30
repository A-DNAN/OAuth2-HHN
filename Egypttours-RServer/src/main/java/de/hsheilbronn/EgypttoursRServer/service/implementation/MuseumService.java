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


import de.hsheilbronn.EgypttoursRServer.dao.MuseumRepository;
import de.hsheilbronn.EgypttoursRServer.dto.ErholungDTO;
import de.hsheilbronn.EgypttoursRServer.dto.MuseumDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.mapper.MuseumMapper;
import de.hsheilbronn.EgypttoursRServer.model.Erholung;
import de.hsheilbronn.EgypttoursRServer.model.Museum;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import de.hsheilbronn.EgypttoursRServer.service.IMuseumService;
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

@Service("MuseumService")
public class MuseumService implements IMuseumService {

    private final Logger logger = LoggerFactory.getLogger(MuseumService.class);

    @Autowired
    private MuseumRepository museumRepository;
    @Autowired
    private MuseumMapper museumMapper;

    /**
     *
     * @return
     * @throws NotFoundException
     */
    @Override
    public Page<MuseumDTO> findAll(Integer page, Integer size) throws NotFoundException{
        List<Museum> museums = museumRepository.findAll();
        List<MuseumDTO> museumDTOs = new ArrayList<>();
        museums.stream().parallel().forEach(museum -> {
            museumDTOs.add(museumMapper.toDTO(museum));
        });

        Page<MuseumDTO> angebots = new PageImpl<MuseumDTO>(
                museumDTOs,
                PageRequest.of(page==null || page < 0?0:page,
                        size==null || size <= 1?9:size),museumDTOs.size()
        );

        if(museums == null || museums.isEmpty())
            throw new NotFoundException("No Element Found");

        return angebots;
    }

    /**
     * @param museumDTO
     * @throws SQLException
     */
    @Override
    public void save(MuseumDTO museumDTO, Authentication authentication) throws SQLException {
        if(museumDTO == null)
        {
            throw new OperationNotAllowedException("Museum is Required");
        }

        museumDTO.setId(null);
//        eRequiredAttributesCheck(museumDTO);
        Museum museum = museumMapper.toMuseum(museumDTO);
        User user = new User();
        user.setUsername(authentication.getName());
        museum.setUser(user);
        museumRepository.save(museum);
    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @Override
    public Museum findById(Long id) throws NotFoundException {
        Optional<Museum> museum = museumRepository.findById(id);
        if(!museum.isPresent()){
            throw new NotFoundException("Museum Not Found");
        }
        return museum.get();
    }

    /**
     * @param updatedMuseum
     * @throws SQLException
     */
    @Override
    public void update(Museum updatedMuseum) throws SQLException {

    }

    /**
     * @param museum
     * @throws SQLException
     */
    @Override
    public void delete(Museum museum) throws SQLException {
        if(museum == null) throw new OperationNotAllowedException("Museum can't be null");
        museumRepository.delete(museum);
    }




}
