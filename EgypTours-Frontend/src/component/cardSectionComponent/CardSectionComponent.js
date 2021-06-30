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
 import './CardSectionComponent.css';
import CardItem from './CardItemComponent';
import AngebotService from '../../service/AngebotService'
import AuthService from '../../service/AuthService'


/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 */

 export class CardComponent extends Component {

  constructor(props){
    super(props);

    
    this.state = {
      angebote : [],
      }
 
    }

  async componentDidMount(){
  await AngebotService.getAngebots(0,5).then(response => {
    
    this.setState({
      angebote: response.data.content,
  })
  }).catch(error => {
    this.setState({
      angebote: '',
  })
  if(error.response?.status === 401){
    AuthService.refreshTheToken();}
    // console.log(error);
})

}

render() {
  return (
    <div className='cards'>
      <h1>Check out these Destinations</h1>
        <div className='cards__wrapper'>
          <ul className='cards__items'>

{this.state.angebote !=='' && this.state.angebote.slice(0,2).map(element => 

<CardItem key={element.id} aId={element.id} aType={element.typeName} preferred={element.preferredBy}
src={element.pictureUrls[0]!==undefined?element.pictureUrls[0].path:""}
label={element.art !==undefined?element.art:element.typeName!==undefined?element.typeName:""}
text= {element.name !==undefined?element.name:""}
price= {element.geldBetrag !==undefined?element.geldBetrag:""}
path={'/offer?uuid='+element.id+'&type='+element.typeName} 
/>
)}
          </ul>
          <ul className='cards__items'>
          
          {this.state.angebote !=='' && this.state.angebote.slice(2,5).map(element => 

<CardItem key={element.id} aId={element.id} aType={element.typeName} preferred={element.preferredBy}
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
  );
}
 }
export default CardComponent;
