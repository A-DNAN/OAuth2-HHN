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


import React, { Component } from 'react'
import './ListSectionComponent.css';
import ListItemComponent from './ListItemComponent';
import AngebotService from '../../service/AngebotService'
import AuthService from '../../service/AuthService';



/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

export class ListSectionComponent extends Component { 


  constructor(props){
    super(props);

    this.nextPage = this.nextPage.bind(this);
    this.previousPage = this.previousPage.bind(this);
    this.updateList = this.updateList.bind(this);
    
    this.state = {
      angebote : [],
      firstAngebot : {},
      page : 0,
      totalPages : 0
      }
 
    }

    async nextPage () {
      let unmounted = false;
      if(!unmounted){
      await AngebotService.getAngebots(this.state.page,9).then(response => {
        this.setState({
          angebote: response.data.content,
          totalPages: response.data.totalPages,
          page: response.data.pageable.pageNumber + 1,
      })
      }).catch(error => {
        // console.log(error);
        if(error.response?.status === 401){
          AuthService.refreshTheToken();}
    });

    window.scrollTo(0,0);
    }
     return () => {
      unmounted = true;
    } 
    
  }


    async previousPage () {
      let unmounted = false;
      if(!unmounted){
      await AngebotService.getAngebots( (this.state.page - 2),9).then(response => {
        this.setState({
          angebote: response.data.content,
          totalPages: response.data.totalPages,
          page: response.data.pageable.pageNumber + 1,
      })
      }).catch(error => {
        // console.log(error);
    });
    window.scrollTo(0,0);
  }
  return () => {
    unmounted = true;
  } 
  }

      componentWillUnmount(){
        window.removeEventListener("click",this.previousPage)
        window.removeEventListener("click",this.nextPage)
        window.removeEventListener("change",this.updateList)
      }

  async componentDidMount(){
    let unmounted = false;
      if(!unmounted){

   let nameValue = AngebotService.getTripsUrlparms()
   nameValue===''?  
    await AngebotService.getAngebots(0,9).then(response => {
      this.setState({
        angebote: response.data.content,
        totalPages: response.data.totalPages,
        page: response.data.pageable.pageNumber + 1,
    })
    }).catch(error => {
      this.setState({
        angebote: '',
        totalPages: 0,
        page: 0,
    })
    if(error.response?.status === 401){
    AuthService.refreshTheToken();}
      // console.log(error);
  })
   :
   await AngebotService.getAngebotsByName(0,9,nameValue).then(response => {
    this.setState({
      angebote: response.data.content,
      totalPages: response.data.totalPages,
      page: response.data.pageable.pageNumber + 1,
  })
  }).catch(error => {
    this.setState({
      angebote: '',
        totalPages: 0,
        page: 0,
  })
  if(error.response?.status === 401){
    AuthService.refreshTheToken();}
    // console.log(error);
})

}
return () => {
 unmounted = true;
} 
  }


  async updateList(event){
    let unmounted = false;
      if(!unmounted){

    await AngebotService.getAngebotsByName(this.page,this.size,event.target.value).then(response => {
      this.setState({
        angebote: response.data.content,
        totalPages: response.data.totalPages,
        page: response.data.pageable.pageNumber + 1,
    })
    }).catch(error => {
      this.setState({
        angebote: '',
        totalPages: 0,
        page: 0,
    })  // console.log(error);
    if(error.response.status === 401){
      AuthService.refreshTheToken();}
   })
  window.scrollTo(0,0);
    // console.log(event.target.value)
  }
  return () => {
   unmounted = true;
  } 
  }


render() {
  return (

    <>

        <div className='list'>
  <div className='list-input-div'>
      
        <input 
    className= 'list-input'
    name='search'
    type='text'
    placeholder='Search Offers' 
    onChange={this.updateList}
  />
   {/* <input 
    className= 'list-input'
    name='search'
    type='text'
    placeholder='Search ' 
    
  /> */}
</div>
 <div className='list__wrapper'>

               <ul className='list__items'>

{this.state.angebote !=='' && this.state.angebote.slice(0,3).map(element => 

<ListItemComponent key={element.id}  aId={element.id} aType={element.typeName} preferred={element.preferredBy}
src={element.pictureUrls[0]!==undefined?element.pictureUrls[0].path:""}
label={element.art !==undefined?element.art:element.typeName!==undefined?element.typeName:""}
text= {element.name !==undefined?element.name:""}
price= {element.geldBetrag !==undefined?element.geldBetrag:""}
path={'/offer?uuid='+element.id+'&type='+element.typeName} 
/>
)}
              </ul>

               <ul className='list__items'>
             
 {this.state.angebote !=='' && this.state.angebote.slice(3,6).map(element => 
 <ListItemComponent key={element.id}  aId={element.id} aType={element.typeName} preferred={element.preferredBy}
 src={element.pictureUrls[0]!==undefined?element.pictureUrls[0].path:""}
 label={element.art !==undefined?element.art:element.typeName!==undefined?element.typeName:""}
 text= {element.name !==undefined?element.name:""}
 price= {element.geldBetrag !==undefined?element.geldBetrag:""}
 path={'/offer?uuid='+element.id+'&type='+element.typeName} 
 />
 )}
              </ul>

              <ul className='list__items'>

{this.state.angebote !=='' && this.state.angebote.slice(6).map(element => 
  <ListItemComponent key={element.id}  aId={element.id} aType={element.typeName} preferred={element.preferredBy}
src={element.pictureUrls[0]!==undefined?element.pictureUrls[0].path:""}
label={element.art !==undefined?element.art:element.typeName!==undefined?element.typeName:""}
text= {element.name !==undefined?element.name:""}
price= {element.geldBetrag !==undefined?element.geldBetrag:""}
path={'/offer?uuid='+element.id+'&type='+element.typeName} 
/>
)}
              </ul>
          </div>
        </div>

        {/* id='icon-disable' */}
        <div className='pagination-div'>{this.state.page}/{this.state.totalPages}  
          <i onClick={this.state.page<=1?null:this.previousPage}
          id={this.state.page<=1? 'icon-disable':""} className='fa fa-arrow-circle-left'/> 
          <i onClick={this.state.page===this.state.totalPages?null:this.nextPage}
          id={this.state.page===this.state.totalPages? 'icon-disable':""} className='fa fa-arrow-circle-right'/></div>
         </>
  )
}

} export default ListSectionComponent
