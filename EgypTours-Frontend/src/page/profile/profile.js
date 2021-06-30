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

 import React, { Component, Fragment } from "react";
 import "../../App.css";
 import "./profile.css";
 import Footer from "./../../component/footerComponent/FooterComponent";
 import Navbar from "./../../component/navBarComponent/NavBarComponent";
 import UserService from '../../service/UserService'

 import { Input, NativeSelect } from "@material-ui/core";
 import axios from "axios";

import { IKContext, IKUpload } from 'imagekitio-react';
import AlertComponent from "../../component/alertComponent/AlertComponent";

const publicKey = 'public_ylg6c6eU8AOAbcLhIDm3VfKkpg4=';
const urlEndpoint = 'https://ik.imagekit.io/720kiwfusxf';
const authenticationEndpoint =`${process.env.REACT_APP_RSERVER_URL}/ik-auth`
;
 
 /**
  * @author ADNAN <ADNAN.E@TUTANOTA.DE>
  * @author rbiebl <rbiebl@stud.hs-heilbronn.de>
  */
 
 export class Profile extends Component {
  
  constructor(props) {
    super(props);

    this.state = {
     firstname: "",
     lastname: "",
     middle_name: "",
     pictureUrl: "",
     gender: "",
    //  birthdate: "",
     email: "",
     phone_number: "",
     alertOpen: false,
     alertMessage: "",
     alertSeverity: "",
     }

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.ikOnError = this.ikOnError.bind(this);
    this.ikOnSuccess = this.ikOnSuccess.bind(this);
  }

  handleChange(e) {
    this.setState({
      [e.target.name]: e.target.value,
    });
  }

  async componentDidMount(){
  
    await UserService.getUser().then(response => {
      this.setState({
     firstname: response.data.firstname===null || response.data.firstname===undefined ?'':response.data.firstname,
     lastname: response.data.lastname===null || response.data.lastname===undefined ?'':response.data.lastname,
     middle_name: response.data.middle_name===null || response.data.middle_name===undefined ?'':response.data.middle_name,
     pictureUrl: response.data.pictureUrl===null || response.data.pictureUrl===undefined ?'':response.data.pictureUrl,
     gender: response.data.gender===null || response.data.gender===undefined ?'':response.data.gender,
    //  birthdate: response.data.birthdate===null || response.data.birthdate===undefined ?'':response.data.birthdate,
     email: response.data.email===null || response.data.email===undefined ?'':response.data.email,
     phone_number: response.data.phone_number===null || response.data.phone_number===undefined ?'':response.data.phone_number,
    })
    if(response.data.pictureUrl !== null){
    localStorage.setItem("pictureUrl",response.data.pictureUrl)}
    // console.log(response);
    }).catch(error => {
      console.log(error);
  })
  }
 
  ikOnError(err){
    this.setState({
      alertOpen: false,
    });

    this.setState({
      alertOpen: true,
       alertMessage: "Profile picture can't be added please try again later",
       alertSeverity: "error"
    });
   console.log("Error", err);
  }

   ikOnSuccess(res){

    this.setState({
      alertOpen: false,
    });

    if(res.fileType === "image" ){
   this.setState({
    pictureUrl: res.url,

     alertOpen: true,
      alertMessage: "Profile Picture added successfully",
      alertSeverity: "info"
   });
   localStorage.setItem("pictureUrl",this.state.pictureUrl)
  }else {
    this.setState({
      alertOpen: true,
       alertMessage: "only images are allowed",
       alertSeverity: "error"
    });
    document.getElementById("wizardPicturePreview").value = "";
  }
  }


   handleSubmit(e) {
    this.setState({
      alertOpen: false,
    });

    e.preventDefault();

    axios.put(`${process.env.REACT_APP_RSERVER_URL}/user`,{
      firstname: e.target.firstname.value===''?this.state.firstname:e.target.firstname.value,
      lastname: e.target.lastname.value===''?this.state.lastname:e.target.lastname.value,
      middle_name: e.target.middle_name.value===''?this.state.middle_name:e.target.middle_name.value,
      pictureUrl: this.state.pictureUrl,
      gender: this.state.gender,
      // birthdate: this.state.birthdate,
      email: e.target.email.value===''?this.state.email:e.target.email.value,
      phone_number: e.target.phone_number.value===''?this.state.phone_number:e.target.phone_number.value,
    }, {
    headers:{
      Authorization: 'Bearer ' + localStorage.getItem("access_token")
  },
    })
    .then((response) => {
      // console.log(response);
      e.target.firstname.value = '';
      e.target.lastname.value = '';
      e.target.middle_name.value = '';
      e.target.email.value = '';
      e.target.phone_number.value = '';
      // window.location.reload();
      this.setState({
        alertOpen: true,
        alertMessage: "Changes are saved successfully",
        alertSeverity: "success"
      });
      this.componentDidMount();
    }).catch((error) => {
      this.setState({
        alertOpen: true,
        alertMessage: "Changes can't be saved, Please try again later",
        alertSeverity: "error"
      });
      console.log(error);
    });

    this.setState({
      alertOpen: false,
    });

   }

   render() {
     return (
       <>
         <Navbar />
         <form onSubmit={this.handleSubmit}>
           <div className="ProfilePage">
             <div className="ProfileBox">
 
                     <div className="Pic">
                       <img alt=""
                         src={this.state.pictureUrl===''?'images/user.png':this.state.pictureUrl}
                         className="picture-src"
                         id="wizardPicturePreview"
                         title=""
                       ></img>

             <IKContext 
        publicKey={publicKey} 
        urlEndpoint={urlEndpoint} 
        authenticationEndpoint={authenticationEndpoint} 
      >
        <IKUpload
          d="pictureUrl"
          name="pictureUrl"
          className="pictureUrl"
          onError={this.ikOnError}
          onSuccess={this.ikOnSuccess}
        />
             </IKContext>
                     </div>

 
               <div className="wrappers">
                 <div className="wrapper1">
                   <div id="FirstName" className="label">
                     First Name
                   </div>
                   <div id="MiddleName" className="label">
                     Middle Name
                   </div>
                   <div id="LastName" className="label">
                     Last Name
                   </div>
                   <div id="Gender" className="label">
                     Gender
                   </div>
                   {/* <div id="Birthdate" className="label">
                     Birthdate
                   </div> */}
                   <div id="EMail" className="label">
                     E-Mail
                   </div>
                   <div id="phoneNumber" className="label">
                     Phone number
                   </div>
                 </div>
 
                 <div className="wrapper2">
                     <Input className="firstName" noValidate
                       name="firstname"
                       pattern="^[a-zA-Z]+$"
                       title="Only characters are allowed"
                       type="text"
                       placeholder={this.state.firstname===""||
                       this.state.firstname===null?"Max":this.state.firstname}
                     />
                     <Input className="middleName" noValidate 
                       type="text"
                       name="middle_name"
                       pattern="^[a-zA-Z]+$"
                       title="Only characters are allowed"
                       placeholder={this.state.middle_name===""||
                       this.state.middle_name===null?"Theo":this.state.middle_name}
                     />
 
                     <Input className="lastname" noValidate  
                       type="text"
                       name="lastname"
                       pattern="^[a-zA-Z]+$"
                       title="Only characters are allowed"
                       placeholder={this.state.lastname===""||
                       this.state.lastname===null?"Mustermann":this.state.lastname}
                     />
 
                     <NativeSelect className="gender" noValidate  
                       type="text"
                       name="gender"
                      //  defaultValue=""
                      value={this.state.gender}
                      onChange={this.handleChange}
                     >
                       <option value="Female"> Female </option>
                       <option value="Male"> Male </option>
                     </NativeSelect>
 
                {/*     <Input className="birthdate" noValidate  
                       type="date"
                       name="birthdate"
                      //  placeholder="dd-mm-yyyy"
                      //  value=""
                      //  min="1997-01-01"
                      //  max="2030-12-31"
                      value={this.state.birthdate}
                      onChange={this.handleChange}
                     ></Input>*/}
 
                     <Input className="Email" noValidate  
                       type="email"
                       name="email"
                       placeholder={this.state.email===""||
                       this.state.email===null
                       ?"mmuster@mustermann.com":this.state.email}
                     />
                     <Input className="phoneNumber" noValidate 
                       type="text" 
                       name="phone_number"
                       placeholder={this.state.phone_number===''||
                       this.state.phone_number===null
                       ?"+49 1111111111":this.state.phone_number}
                     />
                 </div>
               </div>
          {this.state.alertOpen && <AlertComponent message={this.state.alertMessage} severity={this.state.alertSeverity} />  } 
               <div className="Button">
                 <button type="save" className="FormField__Button">
                   Save Changes
                 </button>
               </div>

             </div>
           </div>
         </form>
         <Footer />
       </>
     );
   }
 }
 
 export default Profile;