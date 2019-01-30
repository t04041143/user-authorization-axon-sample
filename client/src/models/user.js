import * as userService from "../services/userService";

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
    }
  },

  effects: {
    * getUserDetails({ payload }, { call, put }) {  // eslint-disable-line
      let response = yield call(userService.getSelfDetails);
      yield put({ type: "save", payload: response.data });
    }
  },

  reducers: {
    save(state, action) {
      return { ...state, ...action.payload };
    }
  }

};
