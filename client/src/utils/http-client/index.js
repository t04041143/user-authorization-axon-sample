import axios from "axios";
import config from "config";

let isInitialized = false;

function buildClient() {
  if (!isInitialized) {
    // 配置axios
    axios.defaults.baseURL = config.service.baseUrl;
    axios.defaults.headers.common["Content-Type"] = "application/json;charset=utf-8";

    // 响应的拦截器
    axios.interceptors.response.use(function(response) {
      // 如果是成功的消息，那么减少数据嵌套
      if (response.data && response.data.data) {
        response.data = response.data.data;
      }

      return response;
    }, function(error) {
      let response = error.response;
      if (response === null || response === undefined) {
        error.response = { data: { code: 10000 } };
      }

      return Promise.reject(error);
    });

    isInitialized = true;
  }

  return axios;
}

export default buildClient();
