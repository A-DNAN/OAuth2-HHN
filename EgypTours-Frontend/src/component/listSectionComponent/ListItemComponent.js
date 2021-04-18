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


import React from 'react';
import { Link } from 'react-router-dom';


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


function ListItemComponent(props) {
  return (
    <>
      <li className='list__item'>
        <Link className='list__item__link' to={props.path}>
          <figure className='list__item__pic-wrap' data-category={props.label}>
            <img
              className='list__item__img'
              alt='Travel'
              src={props.src}
            />
          </figure>
          <div className='list__item__info'>
            <h5 className='list__item__text'>{props.text}</h5>
            <br/>
            <h5 className='list__item__text'>Price: {props.price}</h5>
          </div>
        </Link>
      </li>
    </>
  );
}

export default ListItemComponent;
