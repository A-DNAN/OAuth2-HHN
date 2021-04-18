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
import CardSection from './../../component/cardSectionComponent/CardSectionComponent';
import BodySection from './../../component/bodySectionComponent/BodySectionComponent';
import Footer from './../../component/footerComponent/FooterComponent';
import Navbar from './../../component/navBarComponent/NavBarComponent';


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


function Home() {
  return (
    <>
       <Navbar navStyle='navbar-home'/>
      <BodySection />
      <CardSection />
      <Footer />
    </>
  );
}

export default Home;
