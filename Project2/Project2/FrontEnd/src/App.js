import logo from './logo.svg';
import './App.css';
import React, {useState} from "react";
import Login from './Components/Login';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Homepage from './Components/Homepage';
import TestLogin from './Components/TestLogin'
import Signup from './Components/Signup';
import Account from './Components/Account';
import Browse from './Components/Browse';
import Bids from './Components/Bids';
import { PrivateRoute } from './Components/PrivateRoute';
import PostNewItem from "./Components/PostNewItem";
import MyAcceptedBids from "./Components/MyAcceptedBids";
import UpdateProfile from "./Components/UpdateProfile";

function App() {

  return (
    <Router>
    <Switch>
      <Route exact path="/" component={TestLogin} />
      <PrivateRoute exact path="/Homepage" component={Homepage} />
      <Route exact path="/Signup" component={Signup} />
      <PrivateRoute exact path="/Account" component={Account} />
      <PrivateRoute exact path="/Browse" component={Browse} />
      <PrivateRoute exact path="/Bids" component={Bids} />
      <PrivateRoute exact path="/MyAcceptedBids" component={MyAcceptedBids} />
      <PrivateRoute exact path="/UpdateProfile" component={UpdateProfile} />
      <PrivateRoute exact path="/PostNewItem" component={PostNewItem} />
    </Switch>
    </Router>
  );
}

export default App;
