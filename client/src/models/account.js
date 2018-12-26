import { WS } from "../constants/eventConstants";
import eventBus from "../utils/eventbus";
import * as authService from "../services/authorizationService";
import { routerRedux } from "dva/router";

export default {
  namespace: "account",

  state: {
    type: null,
    id: null,
    status: null,
    createdAt: null,
    updatedAt: null
  },

  subscriptions: {
    setup({ dispatch, history }) {
      eventBus.on(WS.CONNECTED, () => {
        authService.subscribeEvent((message) => {
          let event = JSON.parse(message.body);

          if (event.name === "AccountSignUpFailedEvent" || event.name === "AccountSignInFailedEvent") {
            dispatch({
              type: "happenError",
              payload: event.data
            });
          }

          if (event.name === "AccountSignUpSucceededEvent") {
            dispatch({
              type: "signUpSucceeded",
              payload: event.data
            });
          }
        });
      });
    }
  },

  effects: {
    * signupWithPassword({ payload }, { call, put }) {
      yield put({ type: "error/clear" });
      let userId = localStorage.getItem("userId");
      yield call(authService.signUpWithPassword, userId, payload["accountId"], payload["password"]);
    },

    * signInWithPassword({ payload }, { call, put }) {
      yield put({ type: "error/clear" });
      let userId = localStorage.getItem("userId");
      yield call(authService.signInWithPassword, userId, payload["accountId"], payload["password"]);
    },

    * signUpSucceeded({ payload }, { put }) {
      let accountId = payload.accountId;
      yield put({ type: "save", payload: { ...accountId } });
      yield put(routerRedux.push("/sign-up-success"));
    },

    * happenError({ payload }, { call, put }) {
      yield put({ type: "error/add", payload: { model: "account", error: payload } });
    }
  },

  reducers: {
    save(state, action) {
      return { ...state, ...action.payload };
    }
  }

};
