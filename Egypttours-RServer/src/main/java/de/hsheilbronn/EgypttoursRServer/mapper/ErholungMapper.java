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

package de.hsheilbronn.EgypttoursRServer.mapper;

import de.hsheilbronn.EgypttoursRServer.dto.ErholungDTO;
import de.hsheilbronn.EgypttoursRServer.model.Erholung;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import de.hsheilbronn.EgypttoursRServer.service.implementation.AngebotService;
import org.mapstruct.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


@Mapper(componentModel = "spring" , uses = {AngebotService.class, AdresseMapper.class, ReviewMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR ,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface ErholungMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "beschreibung", target = "beschreibung")
    @Mapping(source = "geldBetrag", target = "geldBetrag")
    @Mapping(source = "website", target = "website")
    @Mapping(source = "typeName", target = "typeName")
    @Mapping(source = "art", target = "art")
    @Mapping(source = "bezahlarts", target = "bezahlarts")
    @Mapping(source = "waehrung", target = "waehrung")
    @Mapping(source = "pictureUrls", target = "pictureUrls")
    @Mapping(source = "adresse", target = "adresse")
    @Mapping(source = "oeffnungszeiten", target = "oeffnungszeiten")
    @Mapping(source = "reviews", target = "reviews")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public ErholungDTO toDTO (Erholung erholung);

    @InheritInverseConfiguration(name = "toDTO")
    public Erholung toErholung (ErholungDTO erholungDTO);



    default List<User> map2(Boolean value){
        return null;
    }


}
