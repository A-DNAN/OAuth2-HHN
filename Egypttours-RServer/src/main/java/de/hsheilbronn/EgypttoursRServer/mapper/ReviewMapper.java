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

import de.hsheilbronn.EgypttoursRServer.dto.ReviewDTO;
import de.hsheilbronn.EgypttoursRServer.model.Review;
import de.hsheilbronn.EgypttoursRServer.model.user.User;
import de.hsheilbronn.EgypttoursRServer.service.implementation.UserService;
import org.mapstruct.*;

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

@Mapper(componentModel = "spring" , uses = {UserService.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR ,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface ReviewMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "reviewComment")
    @Mapping(source = "rating", target = "ratingValue")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd.MM.yyyy, HH:mm:ss")
    @Mapping(source = "createdBy", target = "pictureUrl")
//    @Mapping(source = "createdBy", target = "createdBy")
//    @Mapping(source = "angebot", target = "angebot")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public ReviewDTO toDTO (Review review);

    @InheritInverseConfiguration(name = "toDTO")
    public Review toReview (ReviewDTO reviewDTO);

}
