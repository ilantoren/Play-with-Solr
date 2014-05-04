
class PlaceService

    @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
    @defaultConfig = { headers: @headers }

    constructor: (@$log, @$http, @$q) ->
        @$log.debug "constructing PlaceService"

    getPlace: (id) ->
        @$log.debug "service getPlace(#{id})"
        deferred = @$q.defer()

        @$http.get("/show?q=#{id}")
        .success((data, status, headers) =>
                @$log.info("Successfully listed Places - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to list Places - status #{status}")
                deferred.reject(data);
            )
        deferred.promise


servicesModule.service('PlaceService', PlaceService)