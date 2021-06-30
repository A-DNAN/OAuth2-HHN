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

import de.hsheilbronn.EgypttoursRServer.dao.AltaegyptischeStaetteRepository;
import de.hsheilbronn.EgypttoursRServer.dto.AltaegyptischeStaetteDTO;
import de.hsheilbronn.EgypttoursRServer.dto.MuseumDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.mapper.AltaegyptischeStaetteMapper;
import de.hsheilbronn.EgypttoursRServer.model.AltaegyptischeStaette;
import de.hsheilbronn.EgypttoursRServer.model.Museum;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import de.hsheilbronn.EgypttoursRServer.service.IAltaegyptischeStaetteService;
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

@Service("AltaegyptischeStaetteService")
public class AltaegyptischeStaetteService implements IAltaegyptischeStaetteService {

    private final Logger logger = LoggerFactory.getLogger(AltaegyptischeStaetteService.class);

    @Autowired
    AltaegyptischeStaetteMapper altaegyptischeStaetteMapper;

    @Autowired
    private AltaegyptischeStaetteRepository altaegyptischeStaetteRepository;

    /**
     *
     * @return
     * @throws NotFoundException
     */
    @Override
    public Page<AltaegyptischeStaetteDTO> findAll(Integer page, Integer size) throws NotFoundException {
        List<AltaegyptischeStaette> AltaegyptischeStaettes = altaegyptischeStaetteRepository.findAll();
        List<AltaegyptischeStaetteDTO> AltaegyptischeStaetteDTOs = new ArrayList<>();
        AltaegyptischeStaettes.stream().parallel().forEach(museum -> {
            AltaegyptischeStaetteDTOs.add(altaegyptischeStaetteMapper.toDTO(museum));
        });

        Page<AltaegyptischeStaetteDTO> angebots = new PageImpl<AltaegyptischeStaetteDTO>(
                AltaegyptischeStaetteDTOs,
                PageRequest.of(page==null || page < 0?0:page,
                        size==null || size <= 1?9:size),AltaegyptischeStaetteDTOs.size()
        );

        if(AltaegyptischeStaettes == null || AltaegyptischeStaettes.isEmpty())
            throw new NotFoundException("No Element Found");

        return angebots;
    }

    /**
     * @param altaegyptischeStaetteDTO
     * @throws SQLException
     */
    @Override
    public void save(AltaegyptischeStaetteDTO altaegyptischeStaetteDTO, Authentication authentication) throws SQLException {

        if(altaegyptischeStaetteDTO == null)
        {
            throw new OperationNotAllowedException("AltaegyptischeStaette is Required");
        }

        altaegyptischeStaetteDTO.setId(null);
//        eRequiredAttributesCheck(altaegyptischeStaetteDTO);
        AltaegyptischeStaette altaegyptischeStaette = altaegyptischeStaetteMapper.toAltaegyptischeStaette(altaegyptischeStaetteDTO);
        User user = new User();
        user.setUsername(authentication.getName());
        altaegyptischeStaette.setUser(user);
        altaegyptischeStaetteRepository.save(altaegyptischeStaette);
    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @Override
    public AltaegyptischeStaette findById(Long id) throws NotFoundException {
        Optional<AltaegyptischeStaette> altaegyptischeStaette = altaegyptischeStaetteRepository.findById(id);
        if(!altaegyptischeStaette.isPresent()){
            throw new NotFoundException("AltaegyptischeStaette Not Found");
        }
        return altaegyptischeStaette.get();
    }

    /**
     * @param updatedAltaegyptischeStaette
     * @throws SQLException
     */
    @Override
    public void update(AltaegyptischeStaette updatedAltaegyptischeStaette) throws SQLException {

    }

    /**
     * @param altaegyptischeStaette
     * @throws SQLException
     */
    @Override
    public void delete(AltaegyptischeStaette altaegyptischeStaette) throws SQLException {
        if(altaegyptischeStaette == null) throw new OperationNotAllowedException("AltaegyptischeStaette can't be null");
        altaegyptischeStaetteRepository.delete(altaegyptischeStaette);
    }
}
