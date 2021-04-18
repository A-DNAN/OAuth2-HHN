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

import React, { Component } from 'react';
import Authorized from './component/AuthorizedComponent';
import './App.css';
import Home from './page/home/Home';
import SignUp from './page/signup/SignUp';
import Angebot from './page/angebot/Angebot';
import { BrowserRouter , Switch, Route} from 'react-router-dom';
import Offer from './page/offer/Offer';


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

class App extends Component{

  constructor () {
    super();

    this.state = {
      
       access_token: '',
       id_token:'',
       refresh_token:'',
       loggenIn: false,
       user: {}
    }
  }


render() {
  return (
    <>
     <BrowserRouter basename={process.env.REACT_APP_ROUTER_BASE || ''}>
        {/* <Navbar /> */}
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/join'  
          
          render = { props => (
            <SignUp  {...props}/> 
          )}

          />
          <Route path='/trips' component={Angebot} />
          <Route path='/offer' component={Offer} />
          <Route path='/login' 
          render = { () => {
            window.location.href = 
            `${process.env.REACT_APP_AS_AUTHORIZE_PATH}?response_type=code&client_id=client&scope=openid&redirect_uri=${process.env.REACT_APP_REDIRECT_URL}`
          }
          } />
          <Route path='/authorized' 
          
          render = { props => (
            <Authorized  {...props}/> 
          )}
          />

        </Switch>
    </BrowserRouter>
    </>
  );
}


}
export default App;


