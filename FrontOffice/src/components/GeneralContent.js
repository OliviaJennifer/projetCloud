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
import "../assets/advancedSearch.css";
delete L.Icon.Default.prototype._getIconUrl;

var getIcon = (color) => {
  var icon = new L.Icon({
    iconRetinaUrl: require("../assets/img/" + color + "-marker-x2.png"),
    iconUrl: require("../assets/img/" + color + "-marker.png"),
    shadowUrl: require("leaflet/dist/images/marker-shadow.png"),
    iconAnchor: [5, 55],
    popupAnchor: [10, -44],
    iconSize: [25, 41],
  });
  return icon;
};

function Markers(props) {
  
  const map = useMap();

  return props.signalements.map((signalement, index) => (
    <Marker
      icon={getIcon(signalement.couleur)}
      position={[signalement.latitude, signalement.longitude]}
      key={index}
      eventHandlers={{
        click: (e) => {
          map.flyTo(e.latlng, 14);
        },
      }}
    >
      <Popup>
        <Card sx={{ maxWidth: 200 }}>
          <CardMedia
            component="img"
            alt="green iguana"
            height="440"
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
            <Button size="small" href={"/fiche/"+signalement.idSignalement} >Voir</Button>
          </CardActions>
        </Card>
      </Popup>
    </Marker>
  ));
}

function Carte(props) {
    const [center, setCenter] = useState({
      lat: -16.2349278,
      lng: 46.1292672,
    });
    const [zoomLevel, setZoomLevel] = useState(8);
    const MIN_ZOOM_LEVEL = 6;
    const mapRef = useRef();
  
    return (
      <div className="carte">
        <MapContainer center={center} zoom={zoomLevel} minZoom={MIN_ZOOM_LEVEL}>
          <TileLayer
            url={osm.maptiler.url}
            attribution={osm.maptiler.attribution}
            ref={mapRef}
          />
          <Markers signalements={props.signalements}></Markers>
        </MapContainer>
      </div>
    );
  }

function GeneralContent() {

   const[idType,setIdType] = useState(null);
   const[idStatus,setIdStatus] = useState(null); 
   const[search,setSearch] = useState(false);

  const [types, setTypes] = useState([]);
  const [status, setStatus] = useState([]);

  useEffect(() => {
    getTypes();
    getStatus();
  }, []);

  const [signalements, setSignalements] = useState([]);

  useEffect(() => {
    getSignalements(null,null);
  }, []);

  const getSignalements = (idtype,idstatus) => {
    api
      .get("/signalementdetails", {
        headers: {
          Accept: "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        params : {
            idtype,
            idstatus,
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

  const handleSearch = (event) => {
    event.preventDefault();
    console.log(idType);
    console.log(idStatus);
    if(idType === "")
        setIdType(null);
    if(idStatus === "")
        setIdStatus(null);
    getSignalements(idType,idStatus);
  }

  const getTypes = () => {
    api
      .get("/typesignalements", {
        headers: {
          Accept: "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then((res) => {
        setTypes(res.data);
        console.log(types);
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

  const getStatus = () => {
    api
      .get("/status", {
        headers: {
          Accept: "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      })
      .then((res) => {
        setStatus(res.data);
        console.log(status);
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

  return (
    <div>
      <div className="searchBox">
        <h4>Recherche avancée</h4>
        <form onSubmit={handleSearch}>
          <label for="type">Type :</label>
          <select name="type" id="idType" onChange={(e) => setIdType(e.target.value)}>
            <option value="">Tous</option>
            {types.map((typeSignal, index) => (
              <option key={index} value={typeSignal.id}>
                {typeSignal.type}
              </option>
            ))}
          </select>
          <label for="status">Status :</label>
          <select name="status" id="idStatus" onChange={(e) => setIdStatus(e.target.value)}>
            <option value="">Tous</option>
            {status.map((stat, index) => (
              <option key={index} value={stat.id}>
                {stat.status}
              </option>
            ))}
          </select>
          <label for="dateDebut">Date entre :</label>
          <input type="date" className="form-input" id="dateDebut" />
          <label for="dateFin">au :</label>
          <input type="date" className="form-input" id="dateFin" />
          <input type="submit" value="Rechercher" />
        </form>
      </div>
      <div><Carte signalements={signalements}></Carte></div>
      <div className="search-content">
        <h3>Liste des signalements</h3>
        <h4>Région : Boeny</h4>
        <p>Type : Tous</p>
        <p>Status : Tous</p>
        <p>Date : Tous</p>
        <br />
        <p>Nombres: signalements</p>
      </div>
    </div>
  );
}

export default GeneralContent;
