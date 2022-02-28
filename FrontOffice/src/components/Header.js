import React from 'react';
import {Link} from 'react-router-dom';

class Header extends React.Component {
    render() {
        return(
            <div className="header">
                <span className="site-title">Signalement</span>
                <Link to="/logout" ><span className="disconnectLink">Déconnexion</span></Link>
            </div>
        );
    }
}

export default Header;