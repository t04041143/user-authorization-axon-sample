import * as accountService from "../services/accountService";
import { routerRedux } from "dva/router";
import { TYPE as ACCOUNT_TYPE } from "../constants/accountConstants";

export default {
  namespace: "account",

  state: {
    type: null,
    id: null,
    status: null,
    createdAt: null,
    updatedAt: null
  },

  subscriptions: {},

  effects: {
    * signupUserName({ payload }, { call, put }) {
      yield put({ type: "error/clear" });
      try {
        yield call(accountService.userSignUpUserName, payload["username"], payload["password"]);
        yield put({ type: "save", payload: { id: payload["username"], type: ACCOUNT_TYPE.USERNAME } });
        yield put(routerRedux.push("/sign-up-success"));
      } catch (err) {
        yield put({ type: "error/add", payload: { model: "account", error: err.response.data } });
      }
    },

    * signInWithPassword({ payload }, { call, put }) {
      yield put({ type: "error/clear" });
      try {
        const response = yield call(accountService.signInWithPassword,
          payload["idInAccountType"], ACCOUNT_TYPE.USERNAME, payload["password"]);
        localStorage.setItem("userId", response.data.userId);
        localStorage.setItem("token", response.data.token);
        yield put(routerRedux.push("/sign-in-success"));
      } catch (err) {
        yield put({ type: "error/add", payload: { model: "account", error: err.response.data } });
      }
    }
  },

  reducers: {
    save(state, action) {
      return { ...state, ...action.payload };
    }
  }

};
