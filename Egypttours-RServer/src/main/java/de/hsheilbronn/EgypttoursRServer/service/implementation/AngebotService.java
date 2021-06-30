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

import de.hsheilbronn.EgypttoursRServer.dao.*;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.mapper.AltaegyptischeStaetteMapper;
import de.hsheilbronn.EgypttoursRServer.mapper.ErholungMapper;
import de.hsheilbronn.EgypttoursRServer.mapper.MuseumMapper;
import de.hsheilbronn.EgypttoursRServer.mapper.RestaurantMapper;
import de.hsheilbronn.EgypttoursRServer.model.*;
import de.hsheilbronn.EgypttoursRServer.model.user.UProfile;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import de.hsheilbronn.EgypttoursRServer.service.IAngebotService;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UProfileRepository uProfileRepository;

    @Override
    public Page<Object> findAll(Integer page, Integer size) throws NotFoundException {
// parallelStream to -> sequential
        List<Object> angebots = new ArrayList<>();
        angebots.addAll(restaurantRepository.findAll().stream().sequential().map(restaurantMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(museumRepository.findAll().stream().sequential().map(museumMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(erholungRepository.findAll().stream().sequential().map(erholungMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(altaegyptischeStaetteRepository.findAll().stream().sequential().map(altaegyptischeStaetteMapper::toDTO).collect(Collectors.toList()));
        if(angebots.isEmpty()){
            throw new NotFoundException("Empty List");
        }
        PagedListHolder<Object> pHAngebots = new PagedListHolder<>(angebots);
        pHAngebots.setPage(page==null || page < 0?0:page);
        pHAngebots.setPageSize(size==null || size <= 1?9:size);
        Page<Object> pAngebots =  new PageImpl<Object>(
                pHAngebots.getPageList(),
                PageRequest.of(page==null || page < 0?0:page,
                        size==null || size <= 1?9:size),angebots.size()
        );
    return pAngebots;
    }


    public String addAngebotReview(
            long angebotId,
            String angebotType,
            String description,
            double rating,
            String createdAt,
            Authentication authentication
    ){
        Review review = new Review();
        review.setRating(rating);
        review.setDescription(description);
        review.setCreatedAt(LocalDateTime.parse(createdAt, DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")));
        User user = new User();
        user.setUsername(authentication.getName());
        review.setCreatedBy(user);

        switch (angebotType) {
            case "ERHOLUNG": {
                Erholung erholung = new Erholung();
                erholung.setId(angebotId);
                review.setAngebot(erholung);
                break;
            }
            case "MUSEUM": {
                Museum museum = new Museum();
                museum.setId(angebotId);
                review.setAngebot(museum);
                break;
            }
            case "RESTAURANT": {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(angebotId);
                review.setAngebot(restaurant);
                break;
            }
            case "ALTAEGYPTISCHESTAETTE": {
                AltaegyptischeStaette altaegyptischeStaette = new AltaegyptischeStaette();
                altaegyptischeStaette.setId(angebotId);
                review.setAngebot(altaegyptischeStaette);
                break;
            }
        }
        reviewRepository.save(review);
        Optional<UProfile> uProfile = uProfileRepository.findByUser(user);
        if(uProfile.isPresent())
        return uProfile.get().getPictureUrl();

        return "";
    }

    @Override
    public void updateAngebotFavorite(long angebotId, Boolean preferred, String angebotType, Authentication authentication) {

         User user = new User();
         user.setUsername(authentication.getName());

        switch (angebotType.toUpperCase()){
            case "ERHOLUNG": {
                Optional<Erholung> erholung =  erholungRepository.findById(angebotId);
                if(!erholung.isPresent())
                    throw new NotFoundException();

                if(preferred &&
                        !erholung.get().getPreferredBy().parallelStream().distinct()
                        .map(User::getUsername).anyMatch(u->u.equalsIgnoreCase(user.getUsername())))
                    erholung.get().getPreferredBy().add(user);
                    else{
                    for (int i = 0; i < erholung.get().getPreferredBy().size(); i++) {
                        if(erholung.get().getPreferredBy().get(i).getUsername().equalsIgnoreCase(user.getUsername())){
                            erholung.get().getPreferredBy().remove(i);
                        }
                    }
                }

                erholungRepository.save(erholung.get());
                break;
            }
            case "MUSEUM": {

                Optional<Museum> museum =  museumRepository.findById(angebotId);
                if(!museum.isPresent())
                    throw new NotFoundException();

                if(preferred &&
                        !museum.get().getPreferredBy().parallelStream().distinct()
                         .map(User::getUsername).anyMatch(u->u.equalsIgnoreCase(user.getUsername())))
                    museum.get().getPreferredBy().add(user);
                else {
                    for (int i = 0; i < museum.get().getPreferredBy().size(); i++) {
                        if(museum.get().getPreferredBy().get(i).getUsername().equalsIgnoreCase(user.getUsername())){
                            museum.get().getPreferredBy().remove(i);
                        }
                    }
                }

//                museum.get().getPreferredBy().forEach(u-> {
//
//                    System.out.println(" - " + u.getUsername());
//                });

                museumRepository.save(museum.get());
                break;
            }
            case "RESTAURANT": {
                Optional<Restaurant> restaurant =  restaurantRepository.findById(angebotId);
                if(!restaurant.isPresent())
                    throw new NotFoundException();
                if(preferred &&
                        !restaurant.get().getPreferredBy().parallelStream().distinct()
                         .map(User::getUsername).anyMatch(u->u.equalsIgnoreCase(user.getUsername()))){
                    restaurant.get().getPreferredBy().add(user);}
                else{
                    for (int i = 0; i < restaurant.get().getPreferredBy().size(); i++) {
                        if(restaurant.get().getPreferredBy().get(i).getUsername().equalsIgnoreCase(user.getUsername())){
                            restaurant.get().getPreferredBy().remove(i);
                        }
                    }
                }
                restaurantRepository.save(restaurant.get());
                break;
            }
            case "ALTAEGYPTISCHESTAETTE": {
                Optional<AltaegyptischeStaette> altaegyptischeStaette =  altaegyptischeStaetteRepository.findById(angebotId);
                if(!altaegyptischeStaette.isPresent())
                    throw new NotFoundException();

                if(preferred &&
                        !altaegyptischeStaette.get().getPreferredBy().parallelStream().distinct()
                        .map(User::getUsername).anyMatch(u->u.equalsIgnoreCase(user.getUsername())))
                    altaegyptischeStaette.get().getPreferredBy().add(user);
                else {
                    for (int i = 0; i < altaegyptischeStaette.get().getPreferredBy().size(); i++) {
                        if(altaegyptischeStaette.get().getPreferredBy().get(i).getUsername().equalsIgnoreCase(user.getUsername())){
                            altaegyptischeStaette.get().getPreferredBy().remove(i);
                        }
                    }
                }
                altaegyptischeStaetteRepository.save(altaegyptischeStaette.get());
                break;
            }
            default: {
                throw new NotFoundException();
            }
        }

    }

    public Page<Object> filterByName(Integer page, Integer size,String nameFilter) throws NotFoundException {
        List<Object> angebots = new ArrayList<>();
        angebots.addAll(restaurantRepository.findByNameContaining(nameFilter).stream().sequential().map(restaurantMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(museumRepository.findByNameContaining(nameFilter).stream().sequential().map(museumMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(erholungRepository.findByNameContaining(nameFilter).stream().sequential().map(erholungMapper::toDTO).collect(Collectors.toList()));
        angebots.addAll(altaegyptischeStaetteRepository.findByNameContaining(nameFilter).stream().sequential().map(altaegyptischeStaetteMapper::toDTO).collect(Collectors.toList()));
        if(angebots.isEmpty()){
            throw new NotFoundException("Empty List");
        }
        PagedListHolder<Object> pHAngebots = new PagedListHolder<>(angebots);
        pHAngebots.setPage(page==null || page < 0?0:page);
        pHAngebots.setPageSize(size==null || size <= 1?9:size);
        Page<Object> pAngebots =  new PageImpl<Object>(
                pHAngebots.getPageList(),
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
            case "ALTAEGYPTISCHESTAETTE": {
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


    public static Map<String, String> getAuthenticatedParams(String token, long expire, String privateKey){
        if (token==null){
            token= UUID.randomUUID().toString();
        }
        if (expire==0){
            expire= Instant.now().plus(2, ChronoUnit.MINUTES).getEpochSecond();
        }
        if (privateKey==null) throw new RuntimeException("Private key can't be null.");

        String signature = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, privateKey).hmacHex(token + expire);

        Map<String, String> auth=new HashMap<>();
        auth.put("token",token);
        auth.put("expire", String.valueOf(expire));
        auth.put("signature",signature);
        return auth;
    }


    public static Boolean map(List<User> users) {

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for (User user:users) {
            if(authentication!=null)
             if(authentication.getName().equalsIgnoreCase(user.getUsername()))
                 return true;
        }
        return false;

    }


}
