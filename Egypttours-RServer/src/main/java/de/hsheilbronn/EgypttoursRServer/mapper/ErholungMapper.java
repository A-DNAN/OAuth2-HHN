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
import org.mapstruct.*;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


@Mapper(componentModel = "spring" ,
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
    public ErholungDTO toDTO (Erholung erholung);

}
