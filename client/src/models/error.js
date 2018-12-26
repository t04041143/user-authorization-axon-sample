import ERROR_MESSAGE from "../constants/errorMessage";

export default {
  namespace: "error",

  /**
   * 内容为 模块名: 错误列表
   */
  state: {},

  subscriptions: {},

  effects: {},

  reducers: {
    clear(state, action) {
      let modelName = action.payload;
      if (modelName) {
        return { ...state, [modelName]: {} };
      } else {
        return {};
      }
    },

    add(state, action) {
      let modelName = action.payload.model;
      let errorCode = action.payload.error.code;

      let newError = { [modelName]: { [errorCode]: ERROR_MESSAGE[errorCode] } };

      return { ...state, ...newError };
    }
  }
};
