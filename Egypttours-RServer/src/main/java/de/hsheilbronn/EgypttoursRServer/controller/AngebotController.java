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
import de.hsheilbronn.EgypttoursRServer.exception.OperationNotAllowedException;
import de.hsheilbronn.EgypttoursRServer.model.Erholung;
import de.hsheilbronn.EgypttoursRServer.service.IAngebotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@RestController
@RequestMapping("/angebot")
@CrossOrigin(origins = {"https://seserver.se.hs-heilbronn.de:9443","http://localhost:8081"})
public class AngebotController {


    @Autowired
    @Qualifier("AngebotService")
    IAngebotService angebotService;


    @GetMapping("/all")
    public ResponseEntity<Page<Object>> getAngebots (
            @RequestParam("page") Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {

        Page<Object> angebots = null;
        try {
            angebots =  angebotService.findAll(page, size);
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(angebots);
    }


    @GetMapping("/byname")
    public ResponseEntity<Page<Object>> getAngebotsByName (
            @RequestParam("name") String name,
            @RequestParam("page") Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {

        Page<Object> angebots = null;
        try {
            angebots =  angebotService.filterByName(page, size,name.toLowerCase());
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(angebots);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getAngebotsById (
            @PathVariable("id") long id,
            @RequestParam("type") String type
    ) {
        Object angebot = null;
        try {
            angebot =  angebotService.findById(id, type.toUpperCase());
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (OperationNotAllowedException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
//        Erholung s = (Erholung) angebot;
//        System.out.println(s.getArt());
//        System.out.println(s.getName());
//        System.out.println(s.getBeschreibung());
        return ResponseEntity.ok().body(angebot);
    }

}
