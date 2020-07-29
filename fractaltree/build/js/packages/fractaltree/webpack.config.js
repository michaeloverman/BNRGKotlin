var config = {
  mode: 'development',
  resolve: {
    modules: [
      "node_modules"
    ]
  },
  plugins: [],
  module: {
    rules: []
  }
};

// entry
config.entry = {
    main: ["/Users/michaeloverman/Documents/Android Projects/Learning Kotlin/fractaltree/build/js/packages/fractaltree/kotlin/fractaltree.js"]
};

config.output = {
    path: "/Users/michaeloverman/Documents/Android Projects/Learning Kotlin/fractaltree/build/distributions",
    filename: (chunkData) => {
        return chunkData.chunk.name === 'main'
            ? "fractaltree.js"
            : "fractaltree-[name].js";
    },
    library: "fractaltree",
    libraryTarget: "umd",
};

// source maps
config.module.rules.push({
        test: /\.js$/,
        use: ["kotlin-source-map-loader"],
        enforce: "pre"
});
config.devtool = 'eval-source-map';

// dev server
config.devServer = {
  "inline": true,
  "lazy": false,
  "noInfo": true,
  "open": true,
  "overlay": false,
  "port": 8080,
  "contentBase": [
    "/Users/michaeloverman/Documents/Android Projects/Learning Kotlin/fractaltree/build/processedResources/Js/main"
  ]
};

// save evaluated config file
var util = require('util');
var fs = require("fs");
var evaluatedConfig = util.inspect(config, {showHidden: false, depth: null, compact: false});
fs.writeFile("/Users/michaeloverman/Documents/Android Projects/Learning Kotlin/fractaltree/build/reports/webpack/fractaltree/webpack.config.evaluated.js", evaluatedConfig, function (err) {});

module.exports = config
