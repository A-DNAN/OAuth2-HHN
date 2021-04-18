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
import de.hsheilbronn.EgypttoursRServer.dao.ErholungRepository;
import de.hsheilbronn.EgypttoursRServer.dao.MuseumRepository;
import de.hsheilbronn.EgypttoursRServer.dao.RestaurantRepository;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.mapper.AltaegyptischeStaetteMapper;
import de.hsheilbronn.EgypttoursRServer.mapper.ErholungMapper;
import de.hsheilbronn.EgypttoursRServer.mapper.MuseumMapper;
import de.hsheilbronn.EgypttoursRServer.mapper.RestaurantMapper;
import de.hsheilbronn.EgypttoursRServer.model.AltaegyptischeStaette;
import de.hsheilbronn.EgypttoursRServer.model.Erholung;
import de.hsheilbronn.EgypttoursRServer.model.Museum;
import de.hsheilbronn.EgypttoursRServer.model.Restaurant;
import de.hsheilbronn.EgypttoursRServer.service.IAngebotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Service("AngebotService")
public class AngebotService implements IAngebotService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MuseumRepository museumRepository;
    @Autowired
    ErholungRepository erholungRepository;
    @Autowired
    AltaegyptischeStaetteRepository altaegyptischeStaetteRepository;

    @Autowired
    ErholungMapper erholungMapper;
    @Autowired
    MuseumMapper museumMapper;
    @Autowired
    RestaurantMapper restaurantMapper;
    @Autowired
    AltaegyptischeStaetteMapper altaegyptischeStaetteMapper;


    @Override
    public Page<Object> findAll(Integer page, Integer size) throws NotFoundException {
// parallelStream to -> sequential
        List<Object> angebots = new ArrayList<>();
        angebots.addAll(restaurantRepository.findAll().parallelStream().map(restaurantMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(museumRepository.findAll().parallelStream().map(museumMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(erholungRepository.findAll().parallelStream().map(erholungMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(altaegyptischeStaetteRepository.findAll().parallelStream().map(altaegyptischeStaetteMapper::toDTO).collect(Collectors.toList()));
        if(angebots.isEmpty()){
            throw new NotFoundException("Empty List");
        }
        Page<Object> pAngebots =  new PageImpl<Object>(
                angebots,
                PageRequest.of(page==null || page < 0?0:page,
                        size==null || size <= 1?9:size),angebots.size()
        );
    return pAngebots;
    }


    public Page<Object> filterByName(Integer page, Integer size,String nameFilter) throws NotFoundException {
        List<Object> angebots = new ArrayList<>();
        angebots.addAll(restaurantRepository.findByNameContaining(nameFilter).parallelStream().map(restaurantMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(museumRepository.findByNameContaining(nameFilter).parallelStream().map(museumMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(erholungRepository.findByNameContaining(nameFilter).parallelStream().map(erholungMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(altaegyptischeStaetteRepository.findByNameContaining(nameFilter).parallelStream().map(altaegyptischeStaetteMapper::toDTO).collect(Collectors.toList()));
        if(angebots.isEmpty()){
            throw new NotFoundException("Empty List");
        }
        Page<Object> pAngebots =  new PageImpl<Object>(
                angebots,
                PageRequest.of(page==null || page < 0?0:page,
                        size==null || size <= 1?9:size),angebots.size()
        );
        return pAngebots;
    }


    @Override
    public Object findById(long id,String type) throws NotFoundException, OperationNotAllowedException {

        if(type == null || type.isEmpty())
            throw new OperationNotAllowedException("Type is required");

        Object angebot = null;
        switch (type){
            case "ERHOLUNG": {
                Optional<Erholung> erholung =  erholungRepository.findById(id);
                if(!erholung.isPresent())
                    throw new NotFoundException();
                angebot = erholungMapper.toDTO(erholung.get());
                break;
            }
            case "MUSEUM": {
                Optional<Museum> museum =  museumRepository.findById(id);
                if(!museum.isPresent())
                    throw new NotFoundException();
                angebot = museumMapper.toDTO(museum.get());
                break;
            }
            case "RESTAURANT": {
                Optional<Restaurant> restaurant =  restaurantRepository.findById(id);
                if(!restaurant.isPresent())
                    throw new NotFoundException();
                angebot = restaurantMapper.toDTO(restaurant.get());
                break;
            }
            case "AEST": {
                Optional<AltaegyptischeStaette> altaegyptischeStaette =  altaegyptischeStaetteRepository.findById(id);
                if(!altaegyptischeStaette.isPresent())
                    throw new NotFoundException();
                angebot = altaegyptischeStaetteMapper.toDTO(altaegyptischeStaette.get());
                break;
            }
            default: {
                throw new NotFoundException();
            }
        }
        return angebot;
    }


    }
