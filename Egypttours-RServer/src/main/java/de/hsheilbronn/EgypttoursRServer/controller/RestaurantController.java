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

package de.hsheilbronn.EgypttoursRServer.controller;

import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.model.Restaurant;
import de.hsheilbronn.EgypttoursRServer.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = {"https://seserver.se.hs-heilbronn.de:9443","http://localhost:8081"})
public class RestaurantController {

    @Autowired
    @Qualifier("RestaurantService")
    IRestaurantService restaurantService;


    @GetMapping("/all")
    public ResponseEntity<Page<Restaurant>> getRestaurants (
            @RequestParam("page") Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {

     Page<Restaurant> restaurants = null;
      try {
         restaurants =  restaurantService.findAll(page, size);
        }catch (NotFoundException e) {
        return ResponseEntity.notFound().build();
       }
      catch (IllegalArgumentException e){
          return ResponseEntity.badRequest().build();
      }catch (Exception e){
          return ResponseEntity.badRequest().build();
      }
      return ResponseEntity.ok().body(restaurants);
    }



}
