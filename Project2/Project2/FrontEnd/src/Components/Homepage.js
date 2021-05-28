import React, {useState} from 'react';
import axios from "axios";
import Navbar from "./Navbar";
import Bids from "./Bids";
import Browse from "./Browse";
import Account from "./Account";
import { useLocation } from "react-router-dom";

function Homepage(props){
    const [currentPage, setCurrentPage] = useState("Homepage");
    const [bidsPage, setBidsPage] = useState((localStorage.getItem("bidsPage")=="true")||false);
    const location = useLocation()
    const username = sessionStorage.getItem("username")
    

    React.useEffect(()=>{
        console.log(bidsPage+"should be saved");
        console.log(username)
        localStorage.setItem("bidsPage", bidsPage.toString());
    },[bidsPage]);

    return (
        <div>
            <Navbar logOut={props.logOut}/>
            <h3 className="welcome">Welcome {username} !</h3>
            <Browse/>
        </div>
    )

}

export default Homepage;