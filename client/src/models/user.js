import { getUserDetails } from "../services/userService";
import { WS } from "../constants/eventConstants";
import eventBus from "../utils/eventbus";

export default {
  namespace: "user",

  state: {
    id: null,
    nickname: null,
    accountList: [],
    userSessionId: null,
    userSessionExpiredAt: null,
    userPasswordId: null,
    createdAt: null,
    updatedAt: null
  },

  subscriptions: {
    setup({ dispatch, history }) {
      eventBus.on(WS.CONNECTED, () => {

      });
    }
  },

  effects: {
    * getUserDetails({ payload }, { call, put }) {  // eslint-disable-line
      let response = yield call(getUserDetails, localStorage.getItem("userId"));
      yield put({ type: "save", payload: response.data });
    }
  },

  reducers: {
    save(state, action) {
      return { ...state, ...action.payload };
    }
  }

};
