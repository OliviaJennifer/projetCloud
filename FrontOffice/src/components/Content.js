import React from 'react';
import MainContent from './MainContent';
import AdvancedSearch from './AdvancedSearch';
import GeneralContent from './GeneralContent';
import CurrentPage from './CurrentPage';

function Content(){
    return (
        <div className="content">
            <CurrentPage />
            <GeneralContent />
        </div>
    );
}

export default Content;