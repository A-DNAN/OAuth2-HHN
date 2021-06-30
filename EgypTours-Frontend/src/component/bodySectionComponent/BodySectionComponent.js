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
import '../../App.css';
import { ButtonComponent } from '../buttonComponent/ButtonComponent';
import './BodySectionComponent.css';


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


function BodySectionComponent() {
  return (
    <div className='body-container' style={{background: "url('images/img-home.jpg') center center/cover no-repeat"}}>
      {/* <video src='/videos/video-1.mp4' autoPlay loop muted /> */}
    
      {!localStorage.getItem('access_token')?
       <>
      <h1>Find the good out there</h1>
      <p>Join now for Special Travel Offers</p>
      <div className='body-btns'>
       
       <ButtonComponent
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--large'
        >
          JOIN US
        </ButtonComponent> 
        
       

        {/* <ButtonComponent
          className='btns'
          buttonStyle='btn--primary'
          buttonSize='btn--large'
          onClick={console.log('hey')}
        >
          WATCH TRAILER <i className='far fa-play-circle' />
        </ButtonComponent> */}
      </div>

      </>:
      <h1>Welcome To Egyp<i className="fas fa-campground"></i>ours</h1>
      }

    </div>
  );
}

export default BodySectionComponent;
