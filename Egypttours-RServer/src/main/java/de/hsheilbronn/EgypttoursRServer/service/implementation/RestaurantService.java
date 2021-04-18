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

import de.hsheilbronn.EgypttoursRServer.dao.RestaurantRepository;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.model.*;
import de.hsheilbronn.EgypttoursRServer.service.IRestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Service("RestaurantService")
public class RestaurantService implements IRestaurantService {

    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     *
     * @return
     * @throws NotFoundException
     */
    @Override
    public Page<Restaurant> findAll(Integer page,Integer size) throws NotFoundException {
       Page<Restaurant> restaurants = restaurantRepository.findAll(
               PageRequest.of(page==null || page < 0?0:page,
                       size==null || size <= 1?9:size)
        );
        if(restaurants == null || restaurants.isEmpty())
            throw new NotFoundException("No Element Found");

        return restaurants;
    }

    /**
     * @param Restaurant
     * @throws SQLException
     */
    @Override
    public void save(Restaurant Restaurant) throws SQLException {

//        Restaurant restaurant = new Restaurant();
//        restaurant.setName("Akher Sa'a");
//        restaurant.setBeschreibung("Popular snack bar offering Arabic fuul and taamiyya specialties, mezze appetizers, but also simple omelet and meat dishes. Recommended is \"Kibda Iskanderani\", spicy fried liver. Alcohol is not served here.");
//        restaurant.setGeldBetrag(22.7);
//
//        Adresse adresse = new Adresse();
//        adresse.setStrasse("Mohammed Bek Al Alfi");
//        adresse.setStadt("Cairo");
//        adresse.setxKoordinate(30.05361);
//        adresse.setyKoordinate(31.24377);
//
//        restaurant.setKueche("regional");
//        restaurant.setZumMitnehmen(true);
//
//        Oeffnungszeiten oeffnungszeiten = new Oeffnungszeiten();
//        oeffnungszeiten.setVon(LocalTime.of(01,0,0,0));
//        oeffnungszeiten.setBis(LocalTime.of(23,59,0,0));
//
//        List<Oeffnungszeiten> oeffnungszeitens = new ArrayList<>();
//        oeffnungszeitens.add(oeffnungszeiten);
//
//
//        FilePath picture = new FilePath();
//        picture.setFormat("jpg");
//        picture.setPath("http://kpp");
//        picture.setDescription("r2d");
//
//        List<FilePath> pictures = new ArrayList<>();
//        pictures.add(picture);
//
//        restaurant.setOeffnungszeiten(oeffnungszeitens);
//        restaurant.setAdresse(adresse);
//        restaurant.setPictureUrls(pictures);
//
//        restaurantRepository.save(restaurant);


    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @Override
    public Restaurant findById(Long id) throws NotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(!restaurant.isPresent()){
            throw new NotFoundException("Restaurant Not Found");
        }
        return restaurant.get();
    }

    /**
     * @param updatedRestaurant
     * @throws SQLException
     */
    @Override
    public void update(Restaurant updatedRestaurant) throws SQLException {

    }

    /**
     * @param restaurant
     * @throws SQLException
     */
    @Override
    public void delete(Restaurant restaurant) throws SQLException {
        if(restaurant == null) throw new OperationNotAllowedException("Restaurant can't be null");
        restaurantRepository.delete(restaurant);
    }
}
