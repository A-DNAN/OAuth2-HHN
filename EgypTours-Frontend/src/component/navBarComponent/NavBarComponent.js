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

import React, { useState, useEffect } from 'react';
import { ButtonComponent } from '../buttonComponent/ButtonComponent';
import { Link , useHistory} from 'react-router-dom';
import './NavBarComponent.css';


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */
let searchValue = '';
function NavbarComponent(props) {

  const history = useHistory();

  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);
  const [searchinput, setSearchInput] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };


  function logoutClick (){
    localStorage.clear();
  }


  function getSearchValue (event) {
    searchValue = event.target.value;
  }

  function searchRedirect(event) {
    if(searchValue !== ''){
    history.push(`/trips?name=${searchValue}`)
    searchValue = '';
  }
  }

  const showSearchInput = () => {
    if (window.innerWidth <= 960) {
      setSearchInput(false)
    } else {
      setSearchInput(true);
    }
  };

  useEffect(() => {
    showButton();
    showSearchInput();
  }, []);

  window.addEventListener('resize', showButton);
  window.addEventListener('resize', showSearchInput);

  return (
    <>
      <nav className= {props.navStyle === undefined? 'navbar' :props.navStyle}>
      {/* <nav className= 'navbar-home' > */}
      
        <div className='navbar-container'>
          <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
            Egyp<i className="fas fa-campground"></i>ours
          </Link>
          <div className='menu-icon' onClick={handleClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>
         
          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            {/* <li className='nav-item'>
              <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                Home
              </Link>
            </li> */}


            {localStorage.getItem('access_token')?
<>
<li className='nav-item'>
          {!window.location.pathname.match("trips") && searchinput &&  
          <div className="div-input-icon">
         <i onClick={searchRedirect} className="fa fa-search div-icon" />
          <input
              className= 'nav-input'
              name='search'
              type='text'
              placeholder='Search ' 
              onChange= {getSearchValue}
            /> 
            </div>
            }
            </li>
            
<li className='nav-item'>
              <Link
                to='/trips'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Trips
              </Link>
            </li>

<li className='nav-item'>
<Link
  to='/'
  className='nav-links'
  onClick={logoutClick}
>
  Sign out
</Link>
</li>
</>
            :
            <>
            
            <li className='nav-item'>
              <Link
                to='/login'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Sign in
              </Link>
            </li>
          { !window.location.pathname.match("join") &&
            <li>
              <Link
                to='/join'
                className='nav-links-mobile'
                onClick={closeMobileMenu}
              >
                Sign Up
              </Link>
            </li>}

            </>
             }
          </ul>
          { !window.location.pathname.match("join") && button && !localStorage.getItem('access_token') && <ButtonComponent buttonStyle='btn--outline'>SIGN UP</ButtonComponent>}
        </div>
      </nav>
    </>
  );
}

export default NavbarComponent;
