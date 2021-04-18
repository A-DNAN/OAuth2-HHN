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
 * @author rbiebl <rbiebl@stud.hs-heilbronn.de>
 */

import React, { Component } from "react";
import "../../App.css";
import Footer from "../../component/footerComponent/FooterComponent";
import Navbar from "../../component/navBarComponent/NavBarComponent";
import "./Offer.css";
import Rating from "@material-ui/lab/Rating";
import Box from "@material-ui/core/Box";
import SliderComponent from '../../component/sliderComponent/SliderComponent';
import '../../component/sliderComponent/SliderComponent.css';
import AngebotService from '../../service/AngebotService'


export class Offer extends Component {

  constructor(props){
    super(props);

    this.state = {
      angebot : {},
      pictureList: [],
      address: {},
      
    }
 
    }

 async componentDidMount(){
    
    let search = window.location.search;
    let myparms = new URLSearchParams(search);
    let id = myparms.get('uuid');
    let type = myparms.get('type');

    // console.log("this " +type + " id " +id)
    await AngebotService.getAngebotById(id,type).then(response => {
      this.setState({
        angebot: response.data,
        pictureList: response.data.pictureUrls,
        address : response.data.adresse,
    })
    }).catch(error => {
      this.setState({
        angebote: '',
    })
      // console.log(error);
  })

  console.log(this.state.angebot)
  }




  render() {
    return (
      <>
        <Navbar navStyle="navbar" />
 
       <div className="product-container">
 
           <div className="Details">
            <h1 id="element">{this.state.angebot.name}</h1>
            <br/>
            <br/>
            <div id="element">
              {this.state.angebot.art}
            </div>
            <div id="element">
              Overview
            </div>
            <div className="Description" id="element">
            {this.state.angebot.beschreibung}
            </div>
            <div  id="element"> Open: 6am - 5.30pm </div>

            <div id="element">
              Address: {this.state.address.stadt}, {this.state.address.strasse} 
              {/* Al Sheikh Awad Allah Street, Old Karnak, Luxor */}
            </div>
           <br/>
           <br/>
            <Box
              className="Rating"
              component="fieldset"
              mb={3}
              borderColor="transparent"
              id="element"
            >
              <Rating
                name="simple-controlled"
                // value={this.state.name}
                onChange={this.handleChange}
              />
            </Box>
      </div>

        <SliderComponent slides={this.state.pictureList} />

       </div>

        <div className="Map"></div>

        <Footer />
      </>
    );
  }
}

export default Offer;
