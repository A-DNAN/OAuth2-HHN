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


 import React, { useState } from 'react';
 import { Link } from 'react-router-dom';
 import axios from "axios";
import AlertComponent from '../alertComponent/AlertComponent';



/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


function CardItemComponent(props) {
   
  const [click, setClick] = useState(props.preferred);
  const [alertUpdate, setAlert] = useState(
    {alertOpen: false,
    alertMessage: "",
    alertSeverity: "",});

  function handleClick() {
    
    setAlert({alertOpen: false,
      alertMessage: "",
      alertSeverity: "",});

    axios.post(`${process.env.REACT_APP_RSERVER_URL}/angebot/preferred`,null,{
    params:{
      angebotId: props.aId,
      angebotType: props.aType,
      preferred: !click,
    },
    headers:{
      Authorization: 'Bearer ' + localStorage.getItem("access_token")
      }
    })
    .then((response) => {
      
        setAlert({alertOpen: true,
        alertMessage: !click?"Added to Favorites successfully":"Removed from Favorites successfully",
        alertSeverity: !click?"success":"info"});

        setClick(!click);

    // console.log(response);

    })
    .catch((error) => {
      // console.log(error);
    });
    

    
  }


  return (
    <>
   
      <li className='cards__item'>
        <Link className='cards__item__link' to={props.path}>
          <figure className='cards__item__pic-wrap' data-category={props.label}>
            <img
              className='cards__item__img'
              alt='Travel'
              src={props.src}
            />
          </figure>
          <div className='cards__item__info'>
            <h5 className='cards__item__text'>{props.text}</h5>
          </div>
        </Link>

        <i onClick={handleClick} className={click ? 'fa fa-star filled-f-color' : 'far fa-star'}/>
      </li>
      
      <div>
      {alertUpdate.alertOpen && <AlertComponent message={alertUpdate.alertMessage} severity={alertUpdate.alertSeverity} />  } 
      </div>

    </>
  );
}

export default CardItemComponent;
