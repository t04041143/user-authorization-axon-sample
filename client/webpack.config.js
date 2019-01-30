const path = require("path");

module.exports = function(config, env) {
  let srcPath = path.join(__dirname, "src");
  let nodeEnvKey = process.env.NODE_ENV;

  if (nodeEnvKey === "production") {
    config.entry = {
      index: ["babel-polyfill", "./src/index.js"],
      common: ["react", "react-dom"]
    };
  }

  config.resolve["alias"]["config"] = `${srcPath}/config/${nodeEnvKey}.js`;

  return config;
};
