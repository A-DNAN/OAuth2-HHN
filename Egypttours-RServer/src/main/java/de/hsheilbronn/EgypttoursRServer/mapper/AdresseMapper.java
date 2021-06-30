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

import de.hsheilbronn.EgypttoursRServer.dto.AdresseDTO;
import de.hsheilbronn.EgypttoursRServer.model.Adresse;
import org.mapstruct.*;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Mapper(componentModel = "spring" ,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR ,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface AdresseMapper {



    @Mapping(source = "id", target = "id")
    @Mapping(source = "stadt", target = "stadt")
    @Mapping(source = "strasse", target = "strasse")
    @Mapping(source = "xKoordinate", target = "xKoordinate")
    @Mapping(source = "yKoordinate", target = "yKoordinate")
    @Mapping(source = "alternative", target = "alternative")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public AdresseDTO toDTO (Adresse adresse);

    @InheritInverseConfiguration(name = "toDTO")
    public Adresse toAdresse (AdresseDTO adresseDTO);

}
