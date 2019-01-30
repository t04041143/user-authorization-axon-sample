"use strict";

import baseConfig from "./base";

let config = {
  appEnv: "dev",
  service: {
    baseUrl: "http://localhost:9580"
  }
};

export default Object.freeze(Object.assign({}, baseConfig, config));
