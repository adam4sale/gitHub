import React, {useState} from 'react';
import Navbar from "./Navbar";
import axios from "axios";
import {NotificationManager, NotificationContainer} from "react-notifications";
function PostNewItem(props){
    const [title, setTitle ] = useState("");
    const [price, setPrice] = useState(0);
    const [image, setImage] = useState("");
    const [increment, setIncrement] = useState(0);
    async function postItem(e){
        e.preventDefault();
        console.log(title);
        console.log(price);
        let owner = sessionStorage.getItem("username");

        await axios({
            method: 'post',
            url: "http://localhost:8080/item/addItem",
            data: {price, title, increment, image, owner},
            // params:{image},
            headers : {
                'Content-Type': 'application/json',
                // 'Content-Type': `multipart/form-data`
            }
        }).then(res => {
            let status = res.data;
            if(status===200){
                // clearInput();
                NotificationManager.success('Successful', 'Successfully added item!');

            }
            else{
                NotificationManager.error('Unsuccessful', 'Sorry, we couldn\'t add the item.');
                console.log("response was not 200"+res.data);
            }
        })
            .catch(err => alert(err));
    }

    function clearInput() {
        window.location.reload();
    }

    return(
        <div>
            <NotificationContainer/>
            <Navbar goHome={props.goHome} goAccount={props.goAccount} goPayments={props.goPayments}
                    goViewBalance={props.goViewBalance} username={props.username} logOut={props.logOut}
                    goPostNewItem={props.goPostNewItem}/>


            <form onSubmit={postItem}>
                <h4 className="title">Post new Item</h4>
                <label>Item Name: </label>
                <input type="text" onChange={e=>{setTitle(e.target.value)}}/>
                <label>Starting Price</label>
                <input type="number" onChange={e=>{setPrice(e.target.value)}}/>
                <label>Bids will increment by:</label>
                <input type="number" onChange={e=>{setIncrement(e.target.value)}}/>
                <label>Insert url of image</label>
                {/*<input type="file" onChange={e=>{setImage(e.target.files[0])}}/>*/}
                <input type="text" placeholder="url of image" onChange={e=>{setImage(e.target.value)}}/>
                <button>Submit</button>
                <button onClick={clearInput}>Clear</button>
            </form>
        </div>
    );
}
export default PostNewItem;