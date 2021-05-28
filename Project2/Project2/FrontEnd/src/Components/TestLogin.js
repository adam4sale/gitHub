import React, {useState} from 'react';
import axios from "axios";
import Homepage from "./Homepage";
import Signup from "./Signup";
import 'react-notifications/lib/notifications.css';
import {NotificationManager, NotificationContainer} from "react-notifications";
import {useHistory} from 'react-router-dom'

function TestLogin(props){
    const [username, setUsername]= useState(localStorage.getItem("username") || "");
    const [password, setPassword]= useState("");
    const history = useHistory() 

    async function submitForm(e){
        e.preventDefault();
        console.log(username);
        console.log(password);

        await axios({
            method: 'post',
            url: "http://localhost:8080/user/login",
            data: { username, password},
            // params:{username, password},
            headers : {
                'Content-Type': 'application/json'
            }
        }).then(res => {
            NotificationManager.success('Successful', 'Successfully logged in!');
            localStorage.setItem("username", username);
            sessionStorage.setItem("username", username);
            console.log(res.data.country);
            sessionStorage.setItem("country", res.data.country);
            console.log(res.data.country)
            history.push('/HomePage')
            console.log(res.status);
            })
            .catch(err => alert(err));
    }

    function changePage(){
        history.push('Signup')
    }
    
    return (
        <div>
            <NotificationContainer/>
            <form onSubmit={submitForm}>
                <p>Login</p>
                <input type="text" onChange={e=>{setUsername(e.target.value)}} placeholder="Username"/>
                <br/>
                <input type="password" onChange={e=>{setPassword(e.target.value)}} placeholder="Password"/>
                <br/>
                <button>Login</button>
                <br/>
                <p>Don't have an account?</p>
                <button onClick={changePage}>Sign up here!</button>
            </form>
        </div>
    )

}

export default TestLogin;