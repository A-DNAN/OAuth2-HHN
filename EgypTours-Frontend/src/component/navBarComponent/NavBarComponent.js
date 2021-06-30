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
// import { ButtonComponent } from '../buttonComponent/ButtonComponent';
import { Link , useHistory} from 'react-router-dom';
import './NavBarComponent.css';


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */
let searchValue = '';
function NavbarComponent(props) {

  const history = useHistory();

  const [click, setClick] = useState(false);
  // const [button, setButton] = useState(true);
  const [searchinput, setSearchInput] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  // const showButton = () => {
  //   if (window.innerWidth <= 1075) {
  //     setButton(false);
  //   } else {
  //     setButton(true);
  //   }
  // };

  const handleKeyPress = (event) => {
    if (event.keyCode === 13) {
      searchRedirect();
    }
  }

  function logoutClick (){
    localStorage.clear();
    window.location.reload();
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
    if (window.innerWidth <= 1075) {
      setSearchInput(false)
    } else {
      setSearchInput(true);
    }
  };

  useEffect(() => {
    // showButton();
    showSearchInput();
  }, []);

  // window.addEventListener('resize', showButton);
  window.addEventListener('resize', showSearchInput);

  return (
    <>
      <nav className= {(props.navStyle === undefined || click)?'navbar' :props.navStyle}>
      
        <div className='navbar-container'>
          <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
            Egyp<i className="fas fa-campground"></i>ours
          </Link>
          <div className='menu-icon' >
          
          
          {!searchinput && localStorage.getItem('access_token') &&
              <Link
              to='/user'
              title="My Profile">
            <img alt="" 
            className='nav-profile-circle-mobile' 
            src={localStorage?.pictureUrl !== undefined && localStorage?.pictureUrl !== "null"?localStorage.pictureUrl:"images/user.png"}></img>
            </Link>
            }


            <i onClick={handleClick} className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>
         
       

          {/* <li className='nav-item'> */}
    
   
{/* </li> */}


          <ul className={click ? 'nav-menu active' : 'nav-menu'}>

            {localStorage.getItem('access_token')?
<>
      {!window.location.pathname.match("trips") && searchinput &&  
      <li className='nav-item'>
          <div className="div-input-icon">
         <i onClick={searchRedirect} className="fa fa-search div-icon" />
          <input
              className= 'nav-input'
              name='search'
              type='text'
              placeholder='Search ' 
              onChange= {getSearchValue}
              onKeyUp={handleKeyPress}
              title="Search an offer"
            /> 
            </div>
            
            </li> }

            { searchinput && 
  <li className='nav-item'>
    <Link
    to='/user'
    title="My Profile">
  <img alt="" 
  className='nav-profile-circle' 
  src={localStorage?.pictureUrl !== undefined && localStorage?.pictureUrl !== "null"?localStorage.pictureUrl:"images/user.png"}></img>
  </Link>
</li>
}

{ !searchinput && 
<li className='nav-item'>
<Link
  to='/user'
  className='nav-links'
>
  My Profile
</Link>
</li>
}

 <li className='nav-item'>
              <Link
                to='/trips'
                className='nav-links'
                onClick={closeMobileMenu}
                title="Show all offers"
              >
                Trips
              </Link>
            </li>

            <li className='nav-item'>
              <Link
                to='/new_offer'
                className='nav-links'
                onClick={closeMobileMenu}
                title="Add new offer"
              >
      <i className="fas fa-plus-square"></i>
              </Link>
            </li>

<li className='nav-item'>
<Link
  to='/'
  className='nav-links'
  onClick={logoutClick}
  title="Sign out"
>
<i className="fas fa-sign-out-alt fa-icon-in-Out"></i>
</Link>
</li>

</>
            :
            <>

           { !window.location.pathname.match("join") && searchinput &&
            <li>
              <Link
                to='/join'
                className='nav-links'
                // onClick={closeMobileMenu}
              >
                Sign up
              </Link>
            </li>}
             <li className='nav-item'>
              <Link
                to='/login'
                className='nav-links'
                onClick={closeMobileMenu}
                title="Sign in"
              >
                <i className="fas fa-sign-in-alt fa-icon-in-Out"></i>
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
          {/* { !window.location.pathname.match("join") && button && !localStorage.getItem('access_token') && <ButtonComponent buttonStyle='btn--outline'>SIGN UP</ButtonComponent>} */}
        </div>
      </nav>
    </>
  );
}

export default NavbarComponent;
