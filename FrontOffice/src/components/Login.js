import React,{useState,useEffect} from 'react';
import api from './api';
import {useNavigate} from 'react-router';
import "../assets/login-style.css";
import  axios  from "axios";


function Login() {

    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword]  = useState('');
    const [error, setError] = useState('');

    useEffect(()=>{
        if(localStorage.getItem('token')){          
            // navigate('/home');
        }
    },[])

    const connect =  (event) => {
        event.preventDefault();
        const form = {
            email: email,
            mdp: password       
        }  
        console.log("SE LOGER");
        axios.post("http://localhost:9005/api/admin/login",  form )
        .then(             
        res=>{
            localStorage.setItem('token', res.data.token);
            navigate("/home");
        }).catch(function(error){
            if (error.response) {
                if(error.response.status===403){
                    setError("Echec de la connexion.Verifier vos informations de connexion.");
                }
              } else if (error.request) {
                console.log(error.request);
              } else {
                // Something happened in setting up the request that triggered an Error
                console.log('Error', error.message);
              }
              console.log(error.config);
            console.log("Not connected!");
        });
    }



    return(
        <div className="grid-container" id="container">
            <div className="grid-item">
                <span className="site-title">Signalement</span>
            </div>
            <div className="grid-item">
                <span className="connect-title">Connectez-vous</span>
            </div>
            <div className="grid-item" id="login-form-container">
                <form onSubmit={connect}>
                    <div className="login-form">
                        <div className="grid-item login-input">
                            <label>Adresse Email</label>
                            <input type="text" className="form-control" onChange={e => setEmail(e.target.value)} />
                        </div >
                        <div className="grid-item login-input">
                            <label>Mot de passe</label>
                            <input type="password" className="form-control" onChange={e => setPassword(e.target.value)}/>
                        </div>
                        <div className="grid-item" style={{justifyContent: 'center'}}>
                            <input type="submit"  value="Se connecter" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login;