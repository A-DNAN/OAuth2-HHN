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

//  import React from 'react';
import axios from 'axios';
import createRemoteJWKSet from 'jose/dist/browser/jwks/remote';
import jwtVerify from 'jose/dist/browser/jwt/verify';

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

//  let tokenUrl = 'https://seserver.se.hs-heilbronn.de:9443/as-api/oauth2/token';
let grant_type = "authorization_code";
let code = "";
let scope = "openid";
//  let redirect_uri = "http://localhost:8081/authorized";

let JWKS = createRemoteJWKSet(new URL('https://seserver.se.hs-heilbronn.de:9443/as-api/oauth2/jwks'))

 class AuthService {
    

  getAuthorizationCode(){
        let search = window.location.search;
        let myparms = new URLSearchParams(search);
        code = myparms.get('code');
        return code;
     } 

   async getTokens() {
        await axios.post(process.env.REACT_APP_ASTOKENPATH,null,{
            headers: {
               "Authorization": `Basic ${process.env.REACT_APP_CLIENT_CREDENTIAL}`
           },
            params: {
            grant_type,
            code,
            scope,
            redirect_uri: process.env.REACT_APP_REDIRECT_URL
        }}).then(function (response){
           localStorage.setItem("access_token", response.data.access_token) // response.data.access_token
           localStorage.setItem("id_token", response.data.id_token) // response.data.id_token
           localStorage.setItem("refresh_token", response.data.refresh_token) // response.data.refresh_token
           //  console.log(response)
          
        }).catch(function (error){
            console.log(error)
        })
     }
   async refreshTheToken() {
       if(localStorage.getItem("refresh_token")){
        await axios.post(process.env.REACT_APP_ASTOKENPATH,null,{
            headers: {
               "Authorization": `Basic ${process.env.REACT_APP_CLIENT_CREDENTIAL}`
           },
            params: {
            grant_type:"refresh_token",
            refresh_token: "NqtsVLYTwdUF0TTrPiXCARic8St5jwZZmJZwJECAHk94zM4CmKuOEAne3iiZ1EKVrs0CGNzRvxq3kc1PscMcFz9B6aaQd5WXrCi49FIAk03AiZNAvBu0TywwlikBWiRN"
        }}).then(function (response){
           localStorage.setItem("access_token", response.data.access_token) // response.data.access_token
           localStorage.setItem("id_token", response.data.id_token) // response.data.id_token
        }).catch(function (error){
            if(error.response?.status === 400){
                localStorage.clear();
                window.location.reload();}
        })
    }else {// should logout 
        localStorage.clear();
        window.location.reload();
    }
     }

    verifyToken(){
       jwtVerify(localStorage.access_token , JWKS, {
           issuer: process.env.REACT_APP_TOKEN_ISSUER,
           audience: 'client'
           })
           .then(function (response){
               window.location.href = "http://localhost:8081";
               console.log(response)
               // TODO
           })
           .catch(function (error){
               console.log(error)
           })
     }


    render() {
        return  null;
      
    }
}


export default new AuthService();