{
  mode: 'development',
  resolve: {
    modules: [
      'node_modules'
    ]
  },
  plugins: [],
  module: {
    rules: [
      {
        test: /\.js$/,
        use: [
          'kotlin-source-map-loader'
        ],
        enforce: 'pre'
      }
    ]
  },
  entry: {
    main: [
      '/Users/michaeloverman/Documents/Android Projects/Learning Kotlin/fractaltree/build/js/packages/fractaltree/kotlin/fractaltree.js'
    ]
  },
  output: {
    path: '/Users/michaeloverman/Documents/Android Projects/Learning Kotlin/fractaltree/build/distributions',
    filename: [Function: filename],
    library: 'fractaltree',
    libraryTarget: 'umd'
  },
  devtool: 'eval-source-map',
  devServer: {
    inline: true,
    lazy: false,
    noInfo: true,
    open: true,
    overlay: false,
    port: 8080,
    contentBase: [
      '/Users/michaeloverman/Documents/Android Projects/Learning Kotlin/fractaltree/build/processedResources/Js/main'
    ]
  }
}