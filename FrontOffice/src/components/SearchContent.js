import React from 'react';
import api from './api'

function SearchContent(){

    return (
        <div className="search-content">
            <h3>Liste des signalements</h3>
            <h4>Région : Boeny</h4>
            <p>Type : Tous</p>
            <p>Status : Tous</p>
            <p>Date : Tous</p>
            <br />
            <p>Nombres: 32 signalements</p>
        </div>
    );
}

export default SearchContent;