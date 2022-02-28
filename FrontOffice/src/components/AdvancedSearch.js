import React,{useEffect,useState} from "react";
import api from "./api";
import "../assets/advancedSearch.css";

function AdvancedSearch() {

  const [types,setTypes] = useState([]);
  const [status,setStatus] = useState([]);

  useEffect(()=>{
    getTypes();
    getStatus();
  },[]);

  const getTypes = () =>{
    api.get('http://localhost:9005/api/responsable/allTypeSignalement',{
      headers: {
        'Accept': 'application/json',
        'Authorization': 'Bearer '+localStorage.getItem('token')
      }
    }).then(res =>{
      setTypes(res.data);
      console.log(types);
    }).catch(function(error){
      if (error.response) {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        console.log(error.request);
      } else {
        console.log('Error', error.message);
      }
      console.log(error.config);
    });
  }

  const getStatus = () =>{
    api.get('/status',{
      headers: {
        'Accept': 'application/json',
        'Authorization': 'Bearer '+localStorage.getItem('token')
      }
    }).then(res =>{
      setStatus(res.data);
      console.log(status);
    }).catch(function(error){
      if (error.response) {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        console.log(error.request);
      } else {
        console.log('Error', error.message);
      }
      console.log(error.config);
    });
  }

  return (
    <div className="searchBox">
      <h4>Recherche avancée</h4>
      <form>
        <label for="type">Type :</label>
        <select name="type" id="id">
          { types.map((typeSignal,index) =>(
            <option key={index} value={typeSignal.id}>{typeSignal.type}</option>
            
          )) }
        </select>
        <label for="status">Status :</label>
        <select name="status" id="idStatus">
          { status.map((stat,index) =>(
            <option key={index} value={stat.id}>{stat.status}</option>
          )) }
        </select>
        <label for="dateDebut">Date entre :</label>
        <input type="date" className="form-input" id="dateDebut" />
        <label for="dateFin">au :</label>
        <input type="date" className="form-input" id="dateFin" />
        <input type="submit" value="Rechercher" />
      </form>
    </div>
  );
}

export default AdvancedSearch;
