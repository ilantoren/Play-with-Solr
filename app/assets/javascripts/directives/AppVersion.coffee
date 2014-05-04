'use strict'
app_name = "myApp"
app = angular.module "#{app_name}.directives", []

# Directive to include the version number of my project
app.directive 'appVersion', ['version', (version) ->
    (scope, element, attrs) ->
    element.text version
]

# Hello world directive
app.directive 'hello', () ->
    restict: 'E'
    template: '<div>Hello World</div>'

app.directive "searchResults", ->
  scope:
    solrUrl: "@"
    displayField: "@"
    query: "@"
    results: "&"

  restrict: "E"
  controller: ($scope, $http) ->
    $scope.$watch "query", ->
      $http(
        method: 'GET'
        url: $scope.solrUrl
        params:
          q: $scope.query
          wt: 'json'
          indent: 'true'
          fl: $scope.displayField
      ).success((data) ->
        docs = data
        $scope.results.docs = docs
        return
      ).error ->

      return

    return

  template: "<input ng-model=\"query\" name=\"Search\"></input>" + "<h2>Search Results for {{query}}</h2>" + "<span ng-repeat=\"doc in results.docs\">" + "  <p><a href=\"/#/showPlace/{{doc.id}}\">{{doc.textsuggest}}</a></p>" + "</span>"


