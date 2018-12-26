import React from "react";
import { Route, Router, Switch } from "dva/router";
import IndexPage from "./routes/IndexPage";
import SignInWithPasswordPage from "./routes/authorization/SignInWithPasswordPage";
import SignUpWithPasswordPage from "./routes/authorization/SignUpWithPasswordPage";
import SignUpSuccessPage from "./routes/authorization/SignUpSuccessPage";
import SignInSuccessPage from "./routes/authorization/SignInSuccessPage";

function RouterConfig({ history }) {
  return (
    <Router history={history}>
      <Switch>
        <Route path="/" exact component={IndexPage}/>
        <Route path="/sign-up-with-password" exact component={SignUpWithPasswordPage}/>
        <Route path="/sign-in-with-password" exact component={SignInWithPasswordPage}/>
        <Route path="/sign-up-success" exact component={SignUpSuccessPage}/>
        <Route path="/sign-in-success" exact component={SignInSuccessPage}/>
      </Switch>
    </Router>
  );
}

export default RouterConfig;
