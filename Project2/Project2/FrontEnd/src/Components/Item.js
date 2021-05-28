import React, {useState} from 'react';
import axios from "axios";
import {NotificationManager} from "react-notifications";

function Item(props){
    const [price, setPrice] = useState(props.price);
    // const [itemID, setItemID] = useState(0);
    async function placeBid(e){
        e.preventDefault();
        console.log("bid placed " + price);
        let item =props.title;
        let bidder = sessionStorage.getItem("username");
        let owner = props.owner;
        let date = new Date();
        // if(amount < mi){
        //    
        // }
        let amount = (price/props.coefficient);
        console.log(item);
        console.log(owner);
        await axios({
            method: 'post',
            url: "http://localhost:8080/bid/post_bid",
            data:{item,owner,amount, date,bidder},
            headers : {
                'Content-Type': 'application/json'
            }
        }).then(res => {
            let status = res.data;
            if(status===200){

            }
            else{
                NotificationManager.error('Unsuccessful', 'Sorry, we couldn\'t login with the provided credentials');
                // console.log("response was not 200"+res.data);
            }
        })
            .catch(err => alert(err));
    }


return (
    <div className="item-display">
        <div>{props.title}</div>

        <img className="item-image" src={props.image} alt="image"/>
        <div>Price: {props.price}</div>
        <div>Min Bid: {props.increment}</div>
        <input type="number" onChange={e=>{setPrice(e.target.value)}} />
        <button onClick={(e)=>{ placeBid(e)}}>Bid</button>
    </div>

);
}
export default Item;

