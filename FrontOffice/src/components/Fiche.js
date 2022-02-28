import React from "react";
import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import "../assets/fiche.css";
import {useLocation} from 'react-router-dom'

function Fiche(props) {
    console.log(props.id);

  return (
    <div>
      <Grid container className="header">
        <Grid item md={10} sm={8} xs={8}>
          Header Title
        </Grid>
        <Grid item md={2} sm={4} xs={4}>
          Deconnexion
        </Grid>
      </Grid>
      <Grid container className="content">
        <Grid item md={12}>
          Current page
        </Grid>
        <Grid item md={12}>
          <Grid style={{justifyContent: 'center',alignItems: 'center'}}>
              // eslint-disable-next-line jsx-a11y/alt-text
              <img
                src={
                  "https://m1.quebecormedia.com/emp/jdx-prod-images/cb689d99-be7d-4bba-b138-b358e6560f22_ORIGINAL.jpg?impolicy=resize&quality=80&width=968"
                }

                style={{width: '40%', height: '40vh'}}
              />
          </Grid>
          <Grid>
            <h2>Signalement</h2>
            <p>Type :</p>
            <p>Status :</p>
            <p>Date :</p>
            <p>Description :</p>
            <input type="submit" value="Carte" />
            <input type="submit" value="Traiter" />
          </Grid>
        </Grid>
      </Grid>
      <Grid className="footer">
        <Grid>Signal2Fix Copyright &copy; 2022</Grid>
      </Grid>
    </div>
  );
}

export default Fiche;
