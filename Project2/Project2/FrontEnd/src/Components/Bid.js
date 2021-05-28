import React, {useState} from 'react';
import axios from "axios";
function Bid(props){
    const [bids, setBids] = useState([]);
    const [exchangeCoeff, setExchangeCoeff] = useState(0);
    const [symbol, setSymbol] = useState("");
    const [foreign, setForeign] = useState("USD");
    let obj = "";

    const fromCurr = "USD"
    const to = "PHP"
    const conversion = fromCurr+"_"+foreign
    const url = "https://free.currconv.com/api/v7/convert?q=" + conversion + "&compact=ultra&apiKey=79bbd7ca2e5ef55990e6"

    React.useEffect(()=>{
        let country = sessionStorage.getItem("country");
        const url = "https://restcountries.eu/rest/v2/name/" + country;
        // console.log(url+" url to send to get code");
        getCode(url);
        console.log(foreign+"should be jpn");
    },[]);

    async function getCode(url) {
        await axios.get(url)
            .then(res => {
                // return (res.data.currencies.code)

                setSymbol(res.data[0].currencies[0].symbol)
                console.log(symbol + " symbol of bid???");
                setForeign(res.data[0].currencies[0].code.toString())
                console.log(foreign + " foreign of bid???");

                //return res.data[0].currencies[0].code.toString()
                //console.log(res.data.currencies.code)
            })
            .catch(err => alert(err))
    }
    // convert to other currency, set the exchange coefficient
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


    const getBidsByItemName = () => {
        let item= props.item;
        let itemName = item.title;
        let owner = item.owner;
            axios({
                method: 'get',
                url: `http://localhost:8080/bid/bids/${owner}/${itemName}`,
                headers : {
                    'Content-Type': 'application/json'
                }
            }).then(res => {
                console.log(exchangeCoeff+" this is the exchange rate");
                console.log(item.price+" this is the price");
                res.data.forEach(x => {x.amount = round((x.amount*exchangeCoeff),2);console.log(x.amount+"this is the new price")})
                setBids(res.data);
            }).then(rest => {
                console.log(bids);
            })
                .catch(err => alert(err));
        }

    async function changeStatus(e){
        let value = e.target.value;
        //let bid = e.target.bid;
        console.log(value);
        //console.log(objId);
        let id = obj;
        await axios({
            method: "post",
            url: `http://localhost:8080/bid/bid_status/${value}`,
            data: id,
            headers : {
                'Content-Type': 'application/json'
            }
        }).then(res => {alert(res.data + " submitted");window.location.reload();})
            .catch(err => alert(err));
    }
    function find(){
        getBidsByItemName();
    }

    return(
    <div className="bids">
        <button onClick={find}>find</button>
         {bids.map(b => {
            return(
                <div className="bid" key={b.id}>
                    <div>
                        <p>Bidder: {b.bidder}</p>
                        <p>Price: {symbol}{b.amount}</p>
                        <p>Date submitted: {b.date}</p>
                    </div>
                    <button value="accept" onClick={(e) => {obj = b.id; changeStatus(e) }} className="accept">accept</button>
                    <button value="deny" onClick={(e) => {obj = b.id; changeStatus(e)}} className="deny">deny</button>
                </div>
            )
         })}
    </div>
   )
}
export default Bid;