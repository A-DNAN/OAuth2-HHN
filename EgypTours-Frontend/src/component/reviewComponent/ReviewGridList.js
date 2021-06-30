import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import GridList from '@material-ui/core/GridList';
import ReviewComponent from './ReviewComponent';
import "./ReviewComponent.css"


const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    overflow: 'hidden',
    width: "50vh",
    margin: 'auto'
  },
  gridList: {
    height: '50vh',
    // Promote the list into his own layer on Chrome. This cost memory but helps keeping high FPS.
    transform: 'translateZ(0)',
  },
  titleBar: {
    background:'none',
  },
}));


export default function ReviewGridList(props) {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <GridList spacing={0} cols= {0} className={classes.gridList}>
        {props.tileData.map((tile) => (
            <ReviewComponent key={tile.id} pictureUrl={tile.pictureUrl} createdAt={tile.createdAt} ratingValue={tile.ratingValue} reviewComment={tile.reviewComment} ></ReviewComponent>
        ))}
        
      </GridList>
      
    
    </div>
  );
}
