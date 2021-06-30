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

import axios from 'axios';

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

class AngebotService {

    async getAngebots(page,size){

        if (page === undefined || page < 0){
         page = 0;
        }
        if(size === undefined || size <= 1) {
            size= 9;
        }

     return await axios.get(`${process.env.REACT_APP_RSERVER_URL}/angebot/all`,{
            headers:{
                Authorization: 'Bearer ' + localStorage.getItem("access_token")
            },
        params: {
            page,
            size,
        }
        })
        // .then( response => {
        //     // console.log(response.data)
        // })
        // .catch ( error => {
        //     console.log(error)
        // } )

        // return response;
    }
   
    async getAngebotsByName(page,size,name){

        if (page === undefined || page < 0){
         page = 0;
        }
        if(size === undefined || size <= 1) {
            size= 9;
        }

     return await axios.get(`${process.env.REACT_APP_RSERVER_URL}/angebot/byname`,{
            headers:{
                Authorization: 'Bearer ' + localStorage.getItem("access_token")
            },
        params: {
            name,
            page,
            size,
        }
        })
    }

    async getAngebotById(id, type){

     return await axios.get(`${process.env.REACT_APP_RSERVER_URL}/angebot/${id}`,{
            headers:{
                Authorization: 'Bearer ' + localStorage.getItem("access_token")
            },
        params: {
            type
        }
        })
    }
    

    getTripsUrlparms(){
        let search = window.location.search;
        let myparms = new URLSearchParams(search);
        return myparms.get('name')===null?'':myparms.get('name');
     } 


    // ikUploadImage(){

    // }



    render() {
        return null;
    }
}


export default new AngebotService(); 

