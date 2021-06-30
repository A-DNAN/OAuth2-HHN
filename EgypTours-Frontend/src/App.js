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
import AddOfferComponent from './page/addOffer/AddOfferComponent';
import Terms from './page/terms/Terms';
import AddHotelComponent from './page/addOffer/hotelComponent/AddHotelComponent';
import AddMuseumComponent from './page/addOffer/museumComponent/AddMuseumComponent';
import Profile from './page/profile/profile';
import AddRestaurantComponent from './page/addOffer/restaurantComponent/AddRestaurantComponent';
import AddAncientEgyptianSiteComponent from './page/addOffer/ancientEgyptianSiteComponent/AddAncientEgyptianSiteComponent';
// import UserService from './service/UserService'



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
       loggedUser: {}
    }
  }


//  componentDidMount(){
    
//    UserService.getUser().then(response => {
//     this.setState({
//     loggedUser: response.data
//     })
//     localStorage.setItem("pictureUrl",this.state.loggedUser.pictureUrl)
//     // console.log(this.state.user);
//     }).catch(error => {
//       console.log(error);
//   })

//   }

render() {
  return (
    <>
     <BrowserRouter basename={process.env.REACT_APP_ROUTER_BASE || ''}>
        {/* <Navbar /> */}
        <Switch>
          <Route path='/' exact component={Home} />
         
          {!localStorage.getItem("access_token") &&
          <Route path='/join'  
          
          render = { props => (
            <SignUp  {...props}/> 
          )}
          />
           }
        {localStorage.getItem("access_token") &&
          <Route path='/trips' exact component={Angebot} />
        }
        {localStorage.getItem("access_token") &&
          <Route path='/offer' exact component={Offer} />
         }
          {localStorage.getItem("access_token") &&
          <Route path='/new_offer' exact  component={AddOfferComponent} />
        }
        {localStorage.getItem("access_token") &&
          <Route path='/new_hotel' exact component={AddHotelComponent} />
        }
        {localStorage.getItem("access_token") &&
          <Route path='/new_museum' exact component={AddMuseumComponent} />
        }
        {localStorage.getItem("access_token") &&
          <Route path='/new_restaurant' exact component={AddRestaurantComponent} />
        }
        {localStorage.getItem("access_token") &&
          <Route path='/new_aegs' exact component={AddAncientEgyptianSiteComponent} />
        }
        {localStorage.getItem("access_token") &&
          <Route path='/user' exact component={Profile} />
        }
      
          
          {/* component={profile} /> */}
          <Route path='/terms' exact component={Terms} />
          <Route path='/login' exact
          render = { () => {
            window.location.href = 
            `${process.env.REACT_APP_AS_AUTHORIZE_PATH}?response_type=code&client_id=client&scope=openid&redirect_uri=${process.env.REACT_APP_REDIRECT_URL}`
          }
          } />
          <Route path='/authorized' exact
          
          render = { props => (
            <Authorized  {...props}/> 
          )}
          />
          
          {/* 404: to be edited later */}
        <Route path='/:other' component={Home}></Route>
        
        </Switch>
    </BrowserRouter>
    </>
  );
}


}
export default App;


