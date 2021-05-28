import React,{useState, useEffect} from 'react';
import Navbar from "./Navbar";
import axios from "axios";
import Item from "./Item";


function Browse(props){
    const [data, setData] = useState([]);
    const [query, setQuery] = useState("");
    const [exchangeCoeff, setExchangeCoeff] = useState(0);
    const [from, setFrom] = useState("")
    const [symbol, setSymbol] = useState("")
    const [foreign, setForeign] = useState("USD")
    const [country, setCountry] = useState("")

    const fromCurr = "USD"
    const to = "PHP"
    const conversion = fromCurr+"_"+foreign
    const url = "https://free.currconv.com/api/v7/convert?q=" + conversion + "&compact=ultra&apiKey=79bbd7ca2e5ef55990e6"

    // grab the country to set the currency code
    useEffect(()=>{
        let country = sessionStorage.getItem("country");
        console.log(country);
        const url = "https://restcountries.eu/rest/v2/name/" + country;
        getCode(url)
        console.log(foreign)
    }, [])

    async function getCode(url) {
        await axios.get(url) 
        .then(res => {
           // return (res.data.currencies.code)
            console.log(res.data[0].currencies[0].symbol)
            console.log(res.data[0].currencies[0].code.toString())
            setSymbol(res.data[0].currencies[0].symbol)
            setForeign(res.data[0].currencies[0].code.toString())
            
            //return res.data[0].currencies[0].code.toString()
            //console.log(res.data.currencies.code)
        })
        .catch(err => alert(err))
    }

    // convert to other currency, set the exchange coefficient
    useEffect(() => {
        // await axios.get('https://free.currconv.com/api/v7/convert?q=+conversion+&compact=ultra&apiKey=79bbd7ca2e5ef55990e6')

        let url = "https://free.currconv.com/api/v7/convert?q=USD_" + foreign + "&compact=ultra&apiKey=79bbd7ca2e5ef55990e6"
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
            setExchangeCoeff(coeff)
        })
        .catch(err => alert(err))
    }

    async function searchData(e){
        let username = sessionStorage.getItem("username");
        console.log(username+" did it get from the sssio ");
        console.log(query);
        e.preventDefault();
        await axios({
            method: 'get',
            url: `http://localhost:8080/item/browse/${query}/${username}`,
            // data:username,
            headers : {
                'Content-Type': 'application/json'
            }
        }).then(res => {
            console.log(res.data)
            console.log(data[0]);
            //data[0].price.map(x => x * exchangeCoeff)
            console.log(exchangeCoeff)
            res.data.forEach(x => x.price = x.price*exchangeCoeff)
            console.log(res.data[0])
            setData(res.data)
        })
            .catch(err => alert(err));
    }

    function round(value, decimals) {
        return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
    }
    
    return(
        <div>
            {console.log("mapping")}
            <div className="browse-section">
                <div className="filter">
                    <label>Search for items</label>
                    <input type="text" placeholder="filter"
                           onChange={(e)=>{setQuery(e.target.value)}}/>
                    <button onClick={searchData}>Search</button>
                </div>
                <div className="browse-section-display">
                    {data.map(d=>{
                        return (
                            <Item key={d.id} title={d.title} price={String(symbol) + String(round(d.price,2))} owner={d.owner}
                                  image={d.image} coefficient={exchangeCoeff}
                                  increment={String(symbol) +
                                  String(round(Number(d.price)+Number(d.increment*exchangeCoeff),2)) }
                                  id={d.id}/>
                        )
                    })}
                </div>
            </div>
        </div>
    );
}
export default Browse;