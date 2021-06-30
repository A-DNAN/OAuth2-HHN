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
 import "../../../App.css";
 import Footer from "../../../component/footerComponent/FooterComponent";
 import Navbar from "../../../component/navBarComponent/NavBarComponent";
 import "./../AddOfferComponent.css";
 import axios from "axios";
 // import uploadFile from "../../../service/UploadService";
 
 
 import { IKContext, IKUpload } from 'imagekitio-react';
 import AlertComponent from "../../../component/alertComponent/AlertComponent";
 
 const publicKey = 'public_ylg6c6eU8AOAbcLhIDm3VfKkpg4=';
 const urlEndpoint = 'https://ik.imagekit.io/720kiwfusxf';
 const authenticationEndpoint = `${process.env.REACT_APP_RSERVER_URL}/ik-auth`;
 
 
 /**
  * @author rbiebl <rbiebl@stud.hs-heilbronn.de>
  * @author ADNAN <ADNAN.E@TUTANOTA.DE>
  */
 
 export class AddAncientEgyptianSiteComponent extends Component {
   constructor(props) {
     super(props);
 
     this.state = {
       name: "",
       geldBetrag: "",
       website: "",
       stadt: "",
       strasse: "",
       yKoordinate: "",
       xKoordinate: "",
       alternative: "",
       beschreibung: "",
       mainImageUrl: "",
       mainImage: "",
 
       alertOpen: false,
       alertMessage: "",
       alertSeverity: "",
     };
 
     this.handleChange = this.handleChange.bind(this);
     this.handleSubmit = this.handleSubmit.bind(this);
     this.ikOnError = this.ikOnError.bind(this);
     this.ikOnSuccess = this.ikOnSuccess.bind(this);
   }
 
 
   ikOnError(err) {
     this.setState({
       alertOpen: false,
     });
 
     this.setState({
       alertOpen: true,
       alertMessage: "Image can't be added please try again later",
       alertSeverity: "error"
     });
     console.log("Error", err);
   }
 
   ikOnSuccess(res) {
 
     this.setState({
       alertOpen: false,
     });
 
     if (res.fileType === "image") {
       this.setState({
         mainImageUrl: res.url,
         mainImage: "",
 
         alertOpen: true,
         alertMessage: "Image added successfully",
         alertSeverity: "info"
       });
     } else {
       this.setState({
         alertOpen: true,
         alertMessage: "only images are allowed",
         alertSeverity: "error"
       });
       document.getElementById("mainImage").value = "";
     }
   }
 
 
   handleChange(e) {
     this.setState({
       [e.target.name]: e.target.value,
     });
   }
 
 
   handleSubmit(e) {
 
     this.setState({
       alertOpen: false,
     });
 
 
     e.preventDefault();
     const {
       name,
       geldBetrag,
       website,
       stadt,
       strasse,
       yKoordinate,
       xKoordinate,
       alternative,
       beschreibung,
       mainImageUrl
     } = this.state;
 
     let adresse = {
       stadt: stadt,
       strasse: strasse,
       yKoordinate: yKoordinate,
       xKoordinate: xKoordinate,
       alternative: alternative
     }
     axios.post(`${process.env.REACT_APP_RSERVER_URL}/aegs/add`, {
       name: name,
       art: "Altaegyptische Staette",
       geldBetrag: geldBetrag,
       website: website,
       typeName: "AltaegyptischeStaette",
       adresse: adresse,
       beschreibung: beschreibung,
       pictureUrls: [{
         path: mainImageUrl,
         description: "Main_1",
         format: "jpg"
       }],
     }, {
       headers: {
         Authorization: 'Bearer ' + localStorage.getItem("access_token")
       },
     })
       .then((response) => {
 
         //  console.log(mainImage);
 
 
         this.setState({
           beschreibung: '',
           geldBetrag: '',
           website: '',
           stadt: "",
           strasse: "",
           yKoordinate: "",
           xKoordinate: "",
           alternative: "",
           name: '',
           mainImageUrl: "",
 
           alertOpen: true,
           alertMessage: "Ancient Egyptian site created successfully",
           alertSeverity: "success"
         });
         e.target.mainImage.value = "";
         // if (response.status === 201) {
         // this.props.history.push("/trips");
         // }
       })
       .catch((error) => {
 
         this.setState({
           alertOpen: true,
           alertMessage: "Ancient Egyptian site can't be created, Please try again later",
           alertSeverity: "error"
         });
         // console.log(error);
       });
 
     this.setState({
       alertOpen: false,
     });
   }
 
   render() {
     return (
       <Fragment>
         <Navbar />
         <form onSubmit={this.handleSubmit}>
           <div className="AaddPage" style={{ background: "url('images/img-aegs.jpg') center center/cover no-repeat" }} >
             <div className="AaddFormular">
               <h1 className="AFormH1">Add ancient Egyptian site</h1>
 
               <div className="AFormField">
                 <input
                   type="text"
                   id="name"
                   name="name"
                   className="AFormField_input"
                   placeholder="Name"
                   value={this.state.name}
                   onChange={this.handleChange}
                   required
                 />
               </div>
               <div className="AFormField">
                 <input
                   type="number"
                   id="geldBetrag"
                   name="geldBetrag"
                   className="AFormField_input"
                   placeholder="Costs (1,000.00)"
                   pattern="^\d{1,3}(,\d{3})*(\.\d+)?$"
                   value={this.state.geldBetrag}
                   onChange={this.handleChange}
                   required
                 />
               </div>
               <div className="AFormField">
                 <input
                   type="url"
                   id="website"
                   name="website"
                   className="AFormField_input"
                   placeholder="Website"
                   value={this.state.website}
                   onChange={this.handleChange}
                 />
               </div>
               <div className="AAddress">
                 <div className="Apostal">
                   <div className="AFormField">
                     <input
                       type="text"
                       id="stadt"
                       name="stadt"
                       className="AFormField_input"
                       placeholder="City"
                       value={this.state.stadt}
                       onChange={this.handleChange}
                       required
                     />
                   </div>
                   <div className="AFormField">
                     <input
                       type="text"
                       id="strasse"
                       name="strasse"
                       className="AFormField_input"
                       placeholder="Street"
                       value={this.state.strasse}
                       onChange={this.handleChange}
                       required
                     />
                   </div>
                 </div>
                 <div className="Ageographical">
                   <div className="AFormField">
                     <input
                       type="number"
                       id="yKoordinate"
                       name="yKoordinate"
                       className="AFormField_input"
                       placeholder="Latitude: 25.71951"
                       // pattern="^(\-?([0-8]?[0-9](\.\d+)?|90(.[0]+)?)\s?[,]\s?)+(\-?([1]?[0-7]?[0-9](\.\d+)?|180((.[0]+)?)))$"
                       value={this.state.yKoordinate}
                       onChange={this.handleChange}
                       required
                     />
                   </div>
                   <div className="AFormField">
                     <input
                       type="number"
                       id="xKoordinate"
                       name="xKoordinate"
                       className="AFormField_input"
                       placeholder="Longitude: 32.65741"
                       // pattern="^(\-?([0-8]?[0-9](\.\d+)?|90(.[0]+)?)\s?[,]\s?)+(\-?([1]?[0-7]?[0-9](\.\d+)?|180((.[0]+)?)))$"
                       value={this.state.xKoordinate}
                       onChange={this.handleChange}
                       required
                     />
                   </div>
                 </div>
               </div>
               <div className="AFormField">
                 <input
                   type="text"
                   id="alternative"
                   name="alternative"
                   className="AFormField_input"
                   placeholder="Additional address info"
                   value={this.state.alternative}
                   onChange={this.handleChange}
                 />
               </div>
               <div className="AFormField">
                 <textarea
                   id="beschreibung"
                   name="beschreibung"
                   className="AFormField_input"
                   placeholder="Description and Rules"
                   value={this.state.beschreibung}
                   onChange={this.handleChange}
                   cols="30"
                   rows="2"
                 ></textarea>
               </div>
               <div className="AFormField">
 
                 <IKContext
                   publicKey={publicKey}
                   urlEndpoint={urlEndpoint}
                   authenticationEndpoint={authenticationEndpoint}
                 >
                   {/* <p>Main image</p> */}
                   <IKUpload
                     id="mainImage"
                     name="mainImage"
                     className="AFormField_input"
                     onError={this.ikOnError}
                     onSuccess={this.ikOnSuccess}
                     required
                   />
                 </IKContext>
 
                 {this.state.alertOpen && <AlertComponent message={this.state.alertMessage} severity={this.state.alertSeverity} />}
 
               </div>
               <button type="add" className="FormField__Button">
                 Add
               </button>
             </div>
           </div>
         </form>
         <Footer />
       </Fragment>
     );
   }
 }
 
 export default AddAncientEgyptianSiteComponent;