import React, {useState} from 'react';
import Navbar from "./Navbar";
import axios from "axios";
import {NotificationManager} from "react-notifications";

function MyAcceptedBids(props){

    const [bids, setBids] = useState([]);
    const [symbol, setSymbol]= useState("");
    const [query, setQuery]= useState("*");
    const [foreign, setForeign] = useState("USD");
    const [exchangeCoeff,setExchangeCoeff] = useState(0);
    const fromCurr = "USD"
    const to = "PHP"
    const conversion = fromCurr+"_"+foreign

    React.useEffect(()=>{
        let country = sessionStorage.getItem("country");
        const url = "https://restcountries.eu/rest/v2/name/" + country;
        // console.log(url+" url to send to get code");
        getCode(url);
    })

    async function getCode() {
        let country = sessionStorage.getItem("country");
        const url = "https://restcountries.eu/rest/v2/name/" + country;

        await axios.get(url)
            .then(res => {
                // return (res.data.currencies.code)
                setSymbol(res.data[0].currencies[0].symbol)
                console.log(symbol + " symbol of accepted bids");
                console.log(  res.data[0].currencies[0].code.toString()+ " code of  accepted bids");
                setForeign(res.data[0].currencies[0].code.toString());
                // console.log(foreign + " foreign of bid???");

                //return res.data[0].currencies[0].code.toString()
                //console.log(res.data.currencies.code)
            })
            .catch(err => alert(err))
    }

    // React.useEffect(()=>{
    //     searchData();
    // },[]);
    React.useEffect(() => {

        let url = "https://free.currconv.com/api/v7/convert?q=USD_" + foreign + "&compact=ultra&apiKey=eb66ab8060bb5c80adc4"
        //free.currconv.com/api/v7/convert?q=USD_PHP&compact=ultra&apiKey=eb66ab8060bb5c80adc4
        getConvert(url)

    }, [foreign])

    async function getConvert(url) {
        await axios.get(url)
            .then(response => {
                console.log(url)
                console.log(foreign)
                let coeff = response.data[conversion]
                // var cc = require('currency-codes');
                // console.log(cc.country('United States of America (The)'));
                console.log(response.data)
                console.log(coeff+" coeff is set here");
                setExchangeCoeff(coeff)
            })
            .catch(err => alert(err))
    }


    function round(value, decimals) {
        return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
    }

    async function searchData(e) {
        if(query===""){setQuery("*")}
        e.preventDefault();
        console.log("the item is sent out for query here" +query);
        let username = sessionStorage.getItem("username");

        await axios({
            method: 'get',
            url: `http://localhost:8080/bid/accepted/${username}/${query}`,

            headers : {
                'Content-Type': 'application/json'
            }
        }).then(res => {

            console.log(res.data);
            res.data.forEach(x => {x.amount = round((x.amount*exchangeCoeff),2);console.log(x.amount+"this is the new price")})

            setBids(res.data);
        })
            .catch(err => alert(err));
    }

    return (
        <div>
            <Navbar goHome={props.goHome} goAccount={props.goAccount} goPayments={props.goPayments}
                    goViewBalance={props.goViewBalance} username={props.username} logOut={props.logOut}/>


            <div className="accepted-filter">
                <input type="text" onChange={(e)=>{setQuery(e.target.value)}}/>
                <button onClick={searchData}>Search</button>
            </div>

            <div className="view-pending-bids">

                <div>
                    {bids.map(b => {
                        return(
                            <div className="bid" key={b.id}>
                                <div className="bid-info">
                                    <p>Owner: {b.owner}</p>
                                    <p>Item: {b.item}</p>
                                    <p>Bidder: {b.bidder}</p>
                                    <p>Price: {symbol} {b.amount}</p>

                                    <p>Date submitted: {b.date}</p>
                                </div>
                            </div>
                        )
                    })}
                </div>
            </div>
        </div>
    );
}
export default MyAcceptedBids;