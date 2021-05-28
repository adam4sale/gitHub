import axios from 'axios'
import React, { useEffect, useState } from 'react'
import Homepage from './Homepage';
import Navbar from "./Navbar";
import {NotificationManager, NotificationContainer} from "react-notifications";

function Account(props) {
    // const [balance, setBalance] = useState(localStorage.getItem("user").balance)
    const [balance, setBalance] = useState(0)
    const [deposit, setDeposit] = useState(0)
    const [withdrawal, setWithdrawal] = useState(0)
    const username = sessionStorage.getItem("username");


    React.useEffect(()=>{
        getBalance().then(r => console.log(r));
    },[])

    async function getBalance(){
        await axios({
            method: 'get',
            url: `http://localhost:8080/user/getBalance/${username}`,
            // params:{username, password},
            headers : {
                'Content-Type': 'application/json'
            }
        }).then(res => {
            setBalance(res.data);
        })
            .catch(err => alert(err));
    }

    async function postDeposit() {
        //axios.post('http://localhost:8080/balance', user)
        await axios.post('http://localhost:8080/user/deposit', null, {params: {deposit, username}})
            .then(response => {
                const newBalance = Number(balance) + Number(deposit)
                setBalance(newBalance)
                NotificationManager.success("Successful","Deposit successful");
            })
            .catch(error => {
                console.log(error);
                NotificationManager.error("Failed","Failed to deposit");
            })
    };

    async function postWithdrawal() {

        //axios.post('http://localhost:8080/balance', user)
        await axios.post('http://localhost:8080/user/withdrawal', null, {params: {withdrawal ,username}})
            .then(response => {
                console.log(response)
                const newBalance = Number(balance) - Number(withdrawal)
                setBalance(newBalance)
                // alert("account withdrawn from succesfully")
                NotificationManager.success("Successful","Withdrawn successful");
                //history.push('/EmployeeHome')
            })
            .catch(error => {
                alert("Balance can't be below 0!")
                console.log(error)
            })
    };

    const submitDeposit = (e) => {
        e.preventDefault()
        console.log("submit deposit")
        postDeposit()
    }

    const submitWithdrawal = (e) => {
        e.preventDefault()
        console.log("submit withdrawal")
        postWithdrawal()
    }


    return (
        <div className="account-page">
            <NotificationContainer/>
            <div>
            <Navbar></Navbar>
            </div>
            <div>
            <h4 className="title">Your current BidderUp Balance is: {balance}</h4>

            <form onSubmit={submitDeposit}>
                <p>Make a Deposit</p>
                <input type="text" onChange={e => { setDeposit(e.target.value) }} placeholder="e.g 100" />
                <button>Deposit</button>
            </form>
            <form onSubmit={submitWithdrawal}>
                <p>Make a Withdrawal</p>
                <input type="text" onChange={e => { setWithdrawal(e.target.value) }} placeholder="e.g 100" />
                <button>Withdraw</button>
            </form>
            </div>
        </div>
    )
}

export default Account