import React, { useState, useRef, useEffect } from "react";
import Grid from "react-css-grid";
import Item from "react-css-grid";
import { MapContainer, TileLayer, Marker, useMap, Popup } from "react-leaflet";
import osm from "./osm-provider";
import api from "./api";
import "../assets/carte.css";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
delete L.Icon.Default.prototype._getIconUrl;

// L.Icon.Default.mergeOptions({
//   iconRetinaUrl: require("../assets/img/green-marker-x2.png"),
//   iconUrl: require("../assets/img/green-marker.png"),
//   shadowUrl: require("leaflet/dist/images/marker-shadow.png"),
// });

var getIcon = (color) => {
   var icon = new L.Icon({
    iconRetinaUrl: require('../assets/img/'+color+'-marker-x2.png'),
    iconUrl: require('../assets/img/'+color+'-marker.png'),
    shadowUrl: require("leaflet/dist/images/marker-shadow.png"),
    iconAnchor: [5, 55],
    popupAnchor: [10, -44],
    iconSize: [25, 41],
  })
  return icon;
}

function Markers() {
  const [signalements, setSignalements] = useState([]);

  useEffect(() => {
    getSignalements();
  }, []);

  const getSignalements = () => {
    api
      .get("/signalementdetails", {
        headers: {
          Accept: "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then((res) => {
        setSignalements(res.data);
        console.log(signalements);
      })
      .catch(function (error) {
        if (error.response) {
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
        } else if (error.request) {
          console.log(error.request);
        } else {
          console.log("Error", error.message);
        }
        console.log(error.config);
      });
  };

  const map = useMap();

  return signalements.map((signalement, index) => (
    <Marker
      icon={getIcon(signalement.couleur)}
      position={[signalement.latitude, signalement.longitude]}
      key={index}
      eventHandlers={{
        click: (e) => {
          console.log(e.latlng);
          console.log(e)
          map.flyTo(e.latlng, 14);
          console.log(map.getZoom());
        },
      }}
    >
      <Popup>
        <Card sx={{ maxWidth: 200 }}>
          <CardMedia
            component="img"
            alt="green iguana"
            height="140"
            image="https://www.shutterbug.com/images/photo_post/32652/DSC02276_TAM60_M.jpg"
          />
          <CardContent>
            <Typography gutterBottom variant="h6" component="div">
              {signalement.type}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Status : {signalement.status}
              Date : {signalement.dateSignalement}
            </Typography>
          </CardContent>
          <CardActions>
            <Button size="small">Voir</Button>
          </CardActions>
        </Card>
      </Popup>
    </Marker>
  ));
}

function Carte() {
  const [center, setCenter] = useState({
    lat: -16.2349278,
    lng: 46.1292672,
  });
  const [zoomLevel, setZoomLevel] = useState(8);
  const MIN_ZOOM_LEVEL = 8;
  const mapRef = useRef();

  return (
    <div className="carte">
      <MapContainer center={center} zoom={zoomLevel} minZoom={MIN_ZOOM_LEVEL}>
        <TileLayer
          url={osm.maptiler.url}
          attribution={osm.maptiler.attribution}
          ref={mapRef}
        />
        <Markers></Markers>
      </MapContainer>
    </div>
  );
}
export default Carte;
