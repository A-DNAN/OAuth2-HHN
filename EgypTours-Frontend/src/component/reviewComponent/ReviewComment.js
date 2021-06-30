import React, { useState } from 'react';
import Accordion from "@material-ui/core/Accordion";
import AccordionActions from "@material-ui/core/AccordionActions";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Rating from "@material-ui/lab/Rating";
import ReviewGridList from "./ReviewGridList";
import { v4 as uuid} from "uuid";
import axios from "axios";
import AlertComponent from '../alertComponent/AlertComponent';


export default function ReviewComment(props) {
  const [reviewCommentData, updateList] = useState(props.reviews);
  const [reviewAlert, updateAlert] = useState(
    { alertOpen: false,
    alertMessage: '',
    alertSeverity: ''}
    );
  const date = new Date();


  // useEffect(() => {
  //   console.log("mounted")
  // }, []);


  const onclick = (e) => {
    updateAlert(
      { alertOpen: false,
       alertMessage: '',
       alertSeverity: ''}
      )

    e.preventDefault();
    const newComment = {
      id: uuid(),
      ratingValue: e.target.reviewRating.value,
      reviewComment: e.target.inputReviewComment.value,
      createdAt: date.toLocaleString("de-DE", { day:"2-digit", month:"2-digit", year:"numeric", hour:"2-digit", minute:"2-digit", second:"2-digit", hour12: false}),
      pictureUrl:'',
    };

    axios.post(`${process.env.REACT_APP_RSERVER_URL}/angebot/review`,null, {
    params:{
        angebotId: props.angebotId,
        angebotType: props.angebotType,
        description: newComment.reviewComment,
        rating: newComment.ratingValue===''?0:newComment.ratingValue,
        createdAt:newComment.createdAt,
      },
    headers:{
      Authorization: 'Bearer ' + localStorage.getItem("access_token")
  },
    })
    .then((response) => {
      newComment.pictureUrl = response.data;
      updateAlert(
         { alertOpen: true,
          alertMessage: 'Review added successfully',
          alertSeverity: 'success'}
         )

    e.target.inputReviewComment.value = '';
    updateList(reviewCommentData => [newComment, ...reviewCommentData])
    
    })
    .catch((error) => {
      // console.log(error)

      updateAlert(
        { alertOpen: true,
         alertMessage: "Review can't be added please try again later",
         alertSeverity: 'error'}
        )

    });

    
  }

  return (
    <>
    <div  >
    <ReviewGridList tileData= {reviewCommentData}></ReviewGridList>
      <form onSubmit={onclick} className="root-ReviewComment"> 
      <Accordion defaultExpanded>
            <TextField
              name="inputReviewComment"
              id="outlined-multiline-static"
              label="Comment"
              multiline
              rows={4}
              variant="outlined"
              required
            />
            <Rating precision={0.5}
             name="reviewRating"
            //  disabled
               />
        <AccordionActions>
          <Button type="submit" size="small" color="primary"> Post</Button>
        </AccordionActions>
      </Accordion>
      </form>

      {reviewAlert.alertOpen && <AlertComponent message={reviewAlert.alertMessage} severity={reviewAlert.alertSeverity} />  } 

    </div>
    </>
  );
}
