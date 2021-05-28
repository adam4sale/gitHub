import {Route, Redirect} from 'react-router-dom'
import React from "react";

export const PrivateRoute = ({ component: Component, ...props }) => (
    <Route
      {...props}
      render={routeProps => {
        const item = sessionStorage.getItem("username");
        console.log(sessionStorage.getItem("username"))
        // Do all your conditional tests here
        return item !== null ? (
          <Component {...routeProps} />
        ) : (
          <Redirect to="/" />
        );
      }}
    />
  );