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

 import { Component } from 'react';
 import AuthService from "../service/AuthService";
import UserService from '../service/UserService'

 
 
 /**
  * @author ADNAN <ADNAN.E@TUTANOTA.DE>
  */
 
 export class AuthorizedComponent extends Component {
    
     constructor(props) {
         super(props);
 
         this.state = {
           content: ""
         };
       }
    
      async componentDidMount(){
         AuthService.getAuthorizationCode();
       await AuthService.getTokens();
        //  AuthService.verifyToken();

          UserService.getUser().then(response => {
          //  this.setState({
          //  loggedUser: 
          //  })
           localStorage.setItem("pictureUrl",response.data.pictureUrl)
           // console.log(this.state.user);
           }).catch(error => {
             console.log(error);
         })

        this.props.history.push("/trips");
        window.location.reload();
        //  window.location.href = "http://localhost:8081";
       }
 
     render() {
         return null;
     }
 }
 
 export default AuthorizedComponent
 