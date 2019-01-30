export default {
  namespace: "userSession",

  state: {
    id: null,
    userId: null
  },

  subscriptions: {},

  effects: {},

  reducers: {
    save(state, action) {
      return { ...state, ...action.payload };
    }
  }

};
