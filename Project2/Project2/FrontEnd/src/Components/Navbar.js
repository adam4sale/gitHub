import React from 'react';
import {Link} from 'react-router-dom'
import {useHistory} from 'react-router-dom'
function Navbar(props){
    const history = useHistory();
    function clear(){
    console.log("here");
        history.push("/TestLogin");
        localStorage.clear();
        sessionStorage.clear();

    }

    return (
      <div className="nav-bar scrolled">
          <ul>
              <li><Link to="/HomePage">Home</Link></li>
              <li><Link to="/Account">View Balance</Link></li>
              <li><Link to="/Bids">Bids</Link></li>
              <li><Link to="/PostNewItem">Post New Item</Link></li>
              <li><Link to="/UpdateProfile">Update Profile</Link></li>
              <li><Link to="/MyAcceptedBids">My Accepted Bids</Link></li>
               <li><Link to="/" onClick={clear}>Log Out</Link></li>
          </ul>
          <br></br>
      </div>
    );
}
export default Navbar;