let eventConstants = require("../constants/eventConstants");
let SockJS = require("sockjs-client");
let Stomp = require("stompjs");
let eventBus = require("./eventbus");
let _ = require("lodash");

let socket = new SockJS("http://127.0.0.1:9580/ws-cmrs");
let stompClient = Stomp.over(socket);

let wsHeader = {};
let token = localStorage.getItem("token");
if (!_.isEmpty(token)) {
  wsHeader["X-TOKEN"] = token;
}

stompClient.connect(
  wsHeader,
  (response) => {
    localStorage.setItem("userId", response.headers["user-name"]);
    eventBus.emit(eventConstants.WS.CONNECTED);
  },
  (error) => {
    console.log("error----->>>", error);
  }
);

module.exports = stompClient;
