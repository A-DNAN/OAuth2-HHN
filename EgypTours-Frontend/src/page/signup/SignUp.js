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

import React, { Component, Fragment } from 'react'
import '../../App.css';
import Footer from './../../component/footerComponent/FooterComponent';
import Navbar from './../../component/navBarComponent/NavBarComponent';
import './SignUp.css';
import axios from "axios";

/**
 * @author ADNAN <ADNAN.E@TUTANOTA.DE>
 * @author rbiebl <rbiebl@stud.hs-heilbronn.de>
 */

export class SignUp extends Component {
  
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            password_confirmation : '',
            username: '',
            hasAgreed: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        let target = e.target;
        let value = target.type === 'checkbox' ? target.checked : target.value;
        let name = target.name;

        this.setState({
          [name]: value
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        const {
        email,
        username,
        password,
        password_confirmation } = this.state;
        axios.post(process.env.REACT_APP_JOIN_URL,{
            
            email : email,
            username : username,
            password : password,
            password_confirmation : password_confirmation
        }
        ).then(response => {
            // error.response.status
       if(response.status===201) {
       this.props.history.push("/login");
        }
        
        }).catch(error => {
            // console.log()
            console.log(error);
            // if (error.response.status===400){
            //     console.log("here");
            // }
        })
    }
  
    render() {
        return (
            <Fragment>
            <Navbar navStyle='navbar-home' />
                <form onSubmit={this.handleSubmit}>

                    <div className="sign-upPage">

                        <div className="sign-upForm">
                            <h1 className="FormH1">Sign Up</h1>
                           
                            <div className="FormField">
                                <input type="text"
                                 id="username"
                                 name="username" 
                                 className="FormField_input" 
                                 placeholder="Enter your username" 
                                 value={this.state.name}
                                 onChange={this.handleChange}
                                 required />
                            </div>
                            <div className="FormField">
                                <input type="email"
                                 id="email"
                                 name="email"
                                 className="FormField_input"
                                 placeholder="Enter your email"
                                 value={this.state.email}
                                 onChange={this.handleChange}
                                 required />
                            </div>
                            <div className="FormField">
                                <input type="password"
                                id="password"
                                name="password"
                                className="FormField_input"
                                placeholder="Enter your password"
                                value={this.state.password}
                                onChange={this.handleChange}
                                required />
                            </div>
                            <div className="FormField">
                                <input type="password"
                                 id="password_confirmation"
                                 name="password_confirmation"
                                 className="FormField_input"
                                 placeholder="Confirm password."
                                 value={this.state.password_confirmation}
                                 onChange={this.handleChange}
                                 required />
                            </div>

                            <div className="FormField">
                                <label className="checkboxContainer">
                     <input type="checkbox"
                      name="hasAgreed"
                      value={this.state.hasAgreed}
                      onChange={this.handleChange}
                      required
                      />
                      <span className="checkmark"></span>
                               </label>
                                <span className="FormField__Checkbox">Agree with the <a 
                                href="http://localhost:8081/term" 
                                className="FormField__TermsLink">terms of service</a></span>
                            </div>

                            <div className="FormField">
                                <button type="submit" className="FormField__Button">Sign Up</button>
                                </div>
                        </div>

                    </div>
                </form> 
             <Footer />
           </Fragment>
        );
    }
}

export default SignUp



