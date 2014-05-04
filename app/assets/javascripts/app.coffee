
dependencies = [
    'ngRoute',
    'ui.bootstrap',
    'myApp.filters',
    'myApp.services',
    'myApp.controllers',
    'myApp.directives',
    'myApp.common',
    'myApp.routeConfig'
]

app = angular.module('myApp', dependencies)

angular.module('myApp.routeConfig', ['ngRoute'])
    .config ($routeProvider) ->
        $routeProvider
            .when('/', {
                templateUrl: '/assets/partials/search.html'
            })
            .when('/browse', {
                templateUrl: '/assets/partials/search.html'
            })
            .when('/showPlace/:loc_id', {
                 templateUrl: '/assets/partials/viewPlace.html'
            })
            .otherwise({redirectTo: '/'})

angular.module('myApp.services', ['ngResource'])
    .factory  'suggest', ($resource) ->
        return $resource('/suggest', {})






@commonModule = angular.module('myApp.common', [])
@controllersModule = angular.module('myApp.controllers', [])
@servicesModule = angular.module('myApp.services', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])