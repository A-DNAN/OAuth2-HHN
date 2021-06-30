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

import de.hsheilbronn.EgypttoursRServer.dto.MuseumDTO;
import de.hsheilbronn.EgypttoursRServer.exception.NotFoundException;
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.service.IMuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@RestController
@RequestMapping("/museum")
@CrossOrigin(origins = {"https://seserver.se.hs-heilbronn.de:9443","http://localhost:8081"})
public class MuseumController {

    @Autowired
    @Qualifier("MuseumService")
    IMuseumService museumService;



    @GetMapping("/all")
    public ResponseEntity<Page<MuseumDTO>> getMuseums (
            @RequestParam("page") Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {

        Page<MuseumDTO> museums = null;
        try {
            museums =  museumService.findAll(page, size);
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(museums);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(Authentication authentication,
                                      @RequestBody(required = true) MuseumDTO museumDTO
    ){
        try {
            museumService.save(museumDTO, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (SQLException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Please try again later");
        }catch (OperationNotAllowedException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Please try again later");
        }
    }

}
