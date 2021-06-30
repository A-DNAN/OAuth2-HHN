import React, { Component } from 'react'
import '../../component/cardSectionComponent/CardSectionComponent.css';
import CardItem from '../../component/cardSectionComponent/CardItemComponent';
import Footer from "../../component/footerComponent/FooterComponent";
import Navbar from "../../component/navBarComponent/NavBarComponent";

export default class AddOfferComponent extends Component {




    render() {
        return (
            <>
            <Navbar />
            <div className='cards'>
            <h1>Add new Offer</h1>
            {/* <div className='cards__container'> */}
              <div className='cards__wrapper'>
                <ul className='cards__items'>
                  <CardItem
                    src='images/img-hotel.jpg'
                    text='Add Hotel'
                    label='Hotel'
                    path='/new_hotel'
                  />
                   <CardItem
                    src='images/img-museum.jpg'
                    text='Add Museum'
                    label='Museum'
                    path='/new_museum'
                  />
                     <CardItem
                    src='images/img-restaurant.jpg'
                    text='Add Restaurant'
                    label='Restaurant'
                    path='/new_restaurant'
                  />
                     <CardItem
                src='images/img-aegs.jpg'
                text='Add Ancient Egyptian Site'
                label='Aegs'
                path='/new_aegs'
              />
            </ul>
              </div>
          </div>
         <Footer />
           </>
            // <div>
            //     <AddHotelComponent/>
            //     {/* <AddMuseumComponent/> */}
            // </div>
        )
    }
}
