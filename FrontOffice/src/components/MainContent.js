import React from 'react';
import Carte from './Carte';
import CustomMap from './CustomMap';
import SearchContent from './SearchContent';
import '../assets/mainContent.css';

function MainContent(){
    return (
        <div className="mainContent">
            <Carte />
            <SearchContent />
        </div>
    );
}

export default MainContent;