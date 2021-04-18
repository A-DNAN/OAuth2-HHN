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
    <div className='body-container'>
      {/* <video src='/videos/video-1.mp4' autoPlay loop muted /> */}
      <h1>Find the good out there</h1>
      <p>What are you waiting for?</p>
      <div className='body-btns'>
        <ButtonComponent
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--large'
        >
          GET STARTED
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
    </div>
  );
}

export default BodySectionComponent;
