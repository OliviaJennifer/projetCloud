import React,{useEffect} from 'react';
import Header from './Header';
import Footer from './Footer';
import Content from './Content';
import { useNavigate } from 'react-router-dom';
import '../assets/home.css';

function Home(){

    const navigate = useNavigate();

    useEffect(() =>{
        if(localStorage.getItem('token')===null){
            navigate('/');
        }
    },[]);

    return (
        <div className="container">
            <Header />
            <Content />
            <Footer />
            {/* home */}
        </div>
    );
}

export default Home;