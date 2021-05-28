import React, {useState} from 'react';
import image from '../images.jpeg'
import axios from "axios";
import Bid from "./Bid";
import Navbar from "./Navbar";

function Bids(props){
    const [itemBids, setItemBids] = useState([])
    const [bids, setBids] = useState([])
    const [items, setItems] = useState([])
//let bids = [];

    let username = sessionStorage.getItem("username");
    React.useEffect(()=>{
      //  getBids().then(r => console.log(r));
        getItemsByUsername();
    },[]);



    async function getItemsByUsername(){
        console.log(username+" is the user name correct here?");
        await axios({
            method: 'get',
            url: `http://localhost:8080/item/owner_items/${username}`,
            headers : {
                'Content-Type': 'application/json'
            }
        }).then(response => {
        //console.log(response.data);
        let result = response.data;
        console.log(result);
            setItems(result);
            console.log(items);
        })
        .catch(error => console.error(error));
    }

//    function sortBids(title){
//        let array = [];
//        for(let i = 0; i < bids.length; i++){
//            if(bids[i].item === title){
//                array.push(bids[i]);
//            }
//        }
//        setItemBids(array);
//    }

//{items.map(i =>{
//    sortBids(i.title);
//    return(
//    <div className="bid-section">
//            <div><img className="item-image" src={i.image}/></div>
//                <p>Current bids</p>
//                <input type="text" className="filter" placeholder="Filter by name"/>
//
//                <div className="scroll">
//                    {bids.map(d=>{
//                        return (
//                            <Bid key={d.id} bidder={d.bidder} amount={d.amount} date={d.date}/>
//                        )
//                    })}
//                </div>
//            </div>
//        )
//})}

    return (
        <div >
            <Navbar goHome={props.goHome} goAccount={props.goAccount} goPayments={props.goPayments}
                    goViewBalance={props.goViewBalance} username={props.username} logOut={props.logOut}/>
                    <div className="sale">

                        {items.map(i => {
                            return(
                                <div className="for-sale">
                                    <h3>{i.title}</h3>
                                    <div><img className="item-image" src={i.image}/></div>
                                    <Bid key={i.id} item={i}/>
                                </div>

                            )
                        })}
                    </div>

        </div>
    )

}

export default Bids;