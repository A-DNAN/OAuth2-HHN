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
import de.hsheilbronn.EgypttoursRServer.dto.ErholungDTO;
import de.hsheilbronn.EgypttoursRServer.dto.MuseumDTO;
import de.hsheilbronn.EgypttoursRServer.dto.RestaurantDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.mapper.RestaurantMapper;
import de.hsheilbronn.EgypttoursRServer.model.*;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import de.hsheilbronn.EgypttoursRServer.service.IRestaurantService;
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

@Service("RestaurantService")
public class RestaurantService implements IRestaurantService {

    private final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantMapper restaurantMapper;

    /**
     *
     * @return
     * @throws NotFoundException
     */
    @Override
    public Page<RestaurantDTO> findAll(Integer page,Integer size) throws NotFoundException {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        restaurants.stream().parallel().forEach(restaurant -> {
            restaurantDTOs.add(restaurantMapper.toDTO(restaurant));
        });

        Page<RestaurantDTO> angebots = new PageImpl<RestaurantDTO>(
                restaurantDTOs,
                PageRequest.of(page==null || page < 0?0:page,
                        size==null || size <= 1?9:size),restaurantDTOs.size()
        );

        if(restaurants == null || restaurants.isEmpty())
            throw new NotFoundException("No Element Found");

        return angebots;
    }

    /**
     * @param restaurantDTO
     * @throws SQLException
     */
    @Override
    public void save(RestaurantDTO restaurantDTO, Authentication authentication) throws SQLException {

        if(restaurantDTO == null)
        {
            throw new OperationNotAllowedException("Museum is Required");
        }

        restaurantDTO.setId(null);
//        eRequiredAttributesCheck(restaurantDTO);
        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantDTO);
        User user = new User();
        user.setUsername(authentication.getName());
        restaurant.setUser(user);
        restaurantRepository.save(restaurant);
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
