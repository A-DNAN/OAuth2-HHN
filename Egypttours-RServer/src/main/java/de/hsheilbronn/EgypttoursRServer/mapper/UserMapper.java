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

import de.hsheilbronn.EgypttoursRServer.dto.UserDTO;
import de.hsheilbronn.EgypttoursRServer.model.user.UProfile;
import org.mapstruct.*;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Mapper(componentModel = "spring" , uses = {AdresseMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR ,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface UserMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "middle_name", target = "middle_name")
    @Mapping(source = "pictureUrl", target = "pictureUrl")
    @Mapping(source = "gender", target = "gender")
    public UserDTO toDTO (UProfile uProfile);

}
