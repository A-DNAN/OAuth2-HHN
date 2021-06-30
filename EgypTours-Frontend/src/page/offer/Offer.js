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

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

 import React, { Component } from "react";
 import "../../App.css";
 import Footer from "../../component/footerComponent/FooterComponent";
 import Navbar from "../../component/navBarComponent/NavBarComponent";
 import "./Offer.css";
 import Rating from "@material-ui/lab/Rating";
 import SliderComponent from '../../component/sliderComponent/SliderComponent';
 import '../../component/sliderComponent/SliderComponent.css';
 import AngebotService from '../../service/AngebotService';
 
 
 import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'
import ReviewComment from "../../component/reviewComponent/ReviewComment";
 
//  const hwgoMap =  `https://1.base.maps.ls.hereapi.com/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/512/png8?apiKey=yuV4qFABoh4XwxXeXPABll-PwQrTFRk2UnC9XmeR5Bk`

 
 export class Offer extends Component {
 
   constructor(props){
     super(props);
 
     this.state = {
       angebot : '',
       pictureList: [],
       address: {},
       position : '',
       rating: 0,
       angebotId: '',
       angebotType: '',
       reviews:[],
     }

    //  this.handleChange = this.handleChange.bind(this);
   
   }
 
  //  handleChange(e) {

  //   this.setState({
  //     [e.target.name]: e.target.value,
  //   });

  // }


  async componentDidMount(){

     let search = window.location.search;
     let myparms = new URLSearchParams(search);
     let id = myparms.get('uuid');
     let type = myparms.get('type');

     if(id === null || type === null){
      this.props.history.push("/trips");
     }else {
 
     await AngebotService.getAngebotById(id,type).then(response => {
      
     let reviewsSum = 0; 
     if(response.data.reviews.length !== 0){
     for (var i = 0; i < response.data.reviews.length; i++) {
        reviewsSum += Number(response.data.reviews[i]?.ratingValue)
     } 
     reviewsSum = reviewsSum/response.data.reviews.length}
    
       this.setState({
         angebot: response.data,
         pictureList: response.data.pictureUrls,
         address : response.data.adresse,
         reviews: response.data.reviews.reverse(),
         position : [response.data.adresse.xKoordinate, response.data.adresse.yKoordinate],
         angebotId: id,
         angebotType:type,
         rating:reviewsSum,
     })
        
     }).catch(error => {
       this.setState({
         angebote: '',
     })
   })
    
  }

   }
 
 
 
 
   render() {
     if (this.state.angebot === '') return null;
     return (
       <>
         <Navbar navStyle="navbar" />
  
        <div className="product-container">
  
            <div className="Details">
             <h1 id="element">{this.state.angebot?.name}</h1>
             <br/>
             <br/>
             <div id="element">
               {this.state.angebot?.art}
             </div>
             <div id="element">
               Overview
             </div>
             <div  id="element"> Price: {Number(this.state.angebot?.geldBetrag)?.toFixed(2)} € </div>
             <div className="Description" id="element">
             {this.state.angebot.beschreibung}
             </div>
             {/* <div  id="element"> Open: 8:00 am - 6:30 pm </div> */}
             {this.state.address.stadt &&
             <div id="element">
               Address: {this.state.address.stadt}, {this.state.address?.strasse} 
             </div> }
             {this.state.angebot?.website &&
             <div  id="element"> Homepage: <a href={this.state.angebot?.website} target="_blank" rel="noopener noreferrer" > Offer Official Homepage</a>  </div>
             }
            <br/>
            <br/>
               <Rating precision={0.5}
                 name="rating"
                 readOnly
                 value={Number(this.state.rating)}
                //  onChange={this.handleChange}
               />
       </div>
         <SliderComponent slides={this.state.pictureList} />
        </div>
 
        
         <div className="Map" >
  { this.state.position !== '' &&
   <MapContainer className="offerMapContainer" center={this.state.position} zoom={13} scrollWheelZoom={false}>
     <TileLayer
       attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
       url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
     />
     <Marker position={this.state.position}>
       <Popup>
       {this.state.angebot.name}
       </Popup>
     </Marker>
   </MapContainer>
   }
                   <ReviewComment angebotId={this.state.angebotId} angebotType={this.state.angebotType} reviews={this.state.reviews}></ReviewComment>

         </div>
 
         {/* <ReviewGridList></ReviewGridList> */}
        <Footer />
       </>
     );
   }
 }
 
 export default Offer;