import React, { useState } from 'react'
import {NotificationManager, NotificationContainer} from "react-notifications";
import axios from "axios";
import Navbar from "./Navbar";


function UpdateProfile(props) {
    const [username, setUsername] = useState(sessionStorage.getItem("username") || "")
    const [country, setCountry] = useState(sessionStorage.getItem("country") || "")
    const [newUsername, setNewUsername] = useState("")
    const [newCountry, setNewCountry] = useState("")

    const user = {
        username: username,
        country: country
    }

    async function postUpdate(path, updatedInfo) {

        await axios.post(path,  null, {params: {updatedInfo, username}})
            .then(response => {
                NotificationManager.success('Successfully Updated Profile!');
            })
            .catch(error => {
                console.log(error)
            })
    }

    

    const handleUpdateUsername = (e) => {
        e.preventDefault()
        setUsername(newUsername)
        console.log(newUsername)
        postUpdate("http://localhost:8080/user/update_username", newUsername)
        sessionStorage.setItem('username', newUsername)
    }

    const handleUpdateCountry = (e) => {
        e.preventDefault()
        setCountry(newCountry)
        console.log(newCountry)
        postUpdate("http://localhost:8080/user/update_country", newCountry)
        sessionStorage.setItem('country', newCountry)
    }

    return (
        <div>
            <Navbar goHome={props.goHome} goAccount={props.goAccount} goPayments={props.goPayments}
                    goViewBalance={props.goViewBalance} username={props.username} logOut={props.logOut}
                    goPostNewItem={props.goPostNewItem}/>

            <NotificationContainer/>
                <form onSubmit={handleUpdateUsername}>
                    <h3>Username: {username}</h3>
                    <input type="text" placeholder="new username" onChange={e => setNewUsername(e.target.value)} />
                    <button type="submit" >update</button>
                </form> 
                <form onSubmit={handleUpdateCountry}>
                    <h3>Country: {country}</h3>
                <select onChange={e=>setNewCountry(e.target.value)}>
                    <option value=""></option>
                    <option value="USA">USA</option>
                    <option value="United Kingdom">United Kingdom</option>
                    <option value="China">China</option>
                    <option value="Japan">Japan</option>
                </select>
                    <button type="submit" >update</button>
                </form> <br></br>
        </div>
    )
}


export default UpdateProfile
