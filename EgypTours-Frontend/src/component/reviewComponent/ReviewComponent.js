import React from 'react';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Avatar from '@material-ui/core/Avatar';
import "./ReviewComponent.css";
import Rating from "@material-ui/lab/Rating";



const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    overflow: 'hidden',
    width: "60vh",
  },
  paper: {
    maxWidth: '50vh',
    margin: `${theme.spacing(1)}px auto`,
    padding: theme.spacing(2),
  },
}));

export default function ReviewComponent(props) {
  const classes = useStyles();

  return (
    <>
    <div className={classes.root}>
      <Paper className={classes.paper}>
        <Grid container wrap="nowrap" spacing={2}>
          <Grid item>
            <Avatar src= {props.pictureUrl===null||props.pictureUrl===''||props.pictureUrl===undefined?"images/user.png":props.pictureUrl}>A</Avatar>
          </Grid>
          <Grid item xs>
           <Rating disabled precision={0.5}
             value= {Number(props.ratingValue)}
             name="rRating"
               />
            <p className="inner-box" >{props.reviewComment}</p>
            <p className="reviewComponent-pDate"> {props.createdAt}</p>
          </Grid>
        </Grid>
      </Paper>
    </div>
    
    </>

  );
}