import { WS } from "../constants/eventConstants";
import eventBus from "../utils/eventbus";
import * as authService from "../services/authorizationService";
import * as routerRedux from "react-router-redux";

export default {
  namespace: "userSession",

  state: {
    id: null,
    userId: null
  },

  subscriptions: {
    setup({ dispatch, history }) {
      eventBus.on(WS.CONNECTED, () => {
        authService.subscribeEvent((message) => {
          let event = JSON.parse(message.body);

          if (event.name === "UserSessionCreatedEvent") {
            dispatch({
              type: "signInSucceeded",
              payload: event.data
            });
          }
        });
      });
    }
  },

  effects: {
    * signInSucceeded({ payload }, { put }) {
      yield put({ type: "save", payload: { id: payload.userSessionId, userId: payload.userId } });
      yield put(routerRedux.push("/sign-in-success"));
    }
  },

  reducers: {
    save(state, action) {
      return { ...state, ...action.payload };
    }
  }

};
