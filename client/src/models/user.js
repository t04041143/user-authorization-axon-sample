import { WS } from "../constants/eventConstants";
import eventBus from "../utils/eventbus";

export default {
  namespace: "user",

  state: {
    id: null,
    nickname: null,
    status: null,
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
    * fetch({ payload }, { call, put }) {  // eslint-disable-line
      yield put({ type: "save" });
    }
  },

  reducers: {
    save(state, action) {
      return { ...state, ...action.payload };
    }
  }

};
