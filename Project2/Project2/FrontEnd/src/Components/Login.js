import React, {useState} from 'react';
import axios from "axios";
import Homepage from "./Homepage";
import Signup from "./Signup";
import 'react-notifications/lib/notifications.css';
import {NotificationManager, NotificationContainer} from "react-notifications";

function Login(){
    const [username, setUsername]= useState(localStorage.getItem("username") || "");
    const [password, setPassword]= useState("");
    const [loggedIn, setLoggedIn]= useState(localStorage.getItem("loggedIn")||false);
    const [page, setPage]=useState("login");
    console.log(loggedIn)

    React.useEffect(() => {
        let username1 = localStorage.getItem("username")
        console.log(username1+ "username??");
        // //currUser
        setUsername("")
        if(username1!==""){
            setUsername(username1)
            console.log("code was here");
        }
        //
        // logged in status
        let loggedStatus = (localStorage.getItem("loggedIn")=== 'true');
        setLoggedIn(loggedStatus)
    }, [])

    React.useEffect(()=>{
        console.log("should be saved to local"+username);
        localStorage.setItem("username", username);
        localStorage.setItem("loggedIn",loggedIn)
        // setPage("login");
    },[loggedIn, username]);

    function logOut(){
        console.log("logged in was set false here");
        localStorage.setItem("loggedIn","false");
        setLoggedIn(false);
        setUsername("");
    }

    function changePage(){
        setPage("signup")
    }

    async function submitForm(e){
        e.preventDefault();
        console.log(username);
        console.log(password);

        let id = null;
        let country = "country";
        let balance = 4;
        await axios({
            method: 'post',
            url: "http://localhost:8080/user/login",
            data: { username, password, id, country,balance},
            // params:{username, password},
            headers : {
                'Content-Type': 'application/json'
            }
        }).then(res => {
            let status = res.data;
            if(status===200){
                NotificationManager.success('Successful', 'Successfully logged in!');
                setLoggedIn(true);
                console.log(res.status);
            }
            else{
                NotificationManager.error('Unsuccessful', 'Sorry, we couldn\'t login with the provided credentials');
                // console.log("response was not 200"+res.data);
            }
        })
            .catch(err => alert(err));
    }

    if(loggedIn===true){
        // console.log(loggedIn)
        return (
            <div>
                <NotificationContainer/>
                <Homepage username={username} logOut={logOut}/>
            </div>
        );
    }
    else{
        if(page==="login"){
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
            )}
            else{
                return(
                    <div>
                        <Signup setPage={setPage} />
                    </div>
                );
            }
        }

}

export default Login;