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

import { Component } from 'react'
import axios from "axios";

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */


export class UserService extends Component {

//    constructor(props){
//        super(props);
//    }

async updateUser(){
   return await axios.put(`${process.env.REACT_APP_RSERVER_URL}/user`,{
    //   firstname,
    //   lastname,
    //   middle_name,
    //   pictureUrl,
    //   gender,
    //   birthdate,
    //   email,
    //   phone_number,
    }, {
    headers:{
      Authorization: 'Bearer ' + localStorage.getItem("access_token")
          }
    });

    }

    async getUser(){
        return await axios.get(`${process.env.REACT_APP_RSERVER_URL}/user`,{
         headers:{
           Authorization: 'Bearer ' + localStorage.getItem("access_token")
               }
         });
         }
   

    render() {
        return null;
    }
}

export default new UserService();
