package com.puxiansheng.logic.data.location

class LocationRepository {
    private val remoteLocationRepository = RemoteLocationRepository()

    fun requestRemoteCitiesByParentID(
        parentID: String
    ) = remoteLocationRepository.requestRemoteCitiesByParentID(parentID)

    fun getRemoteSupportedCities(

    ) = remoteLocationRepository.getRemoteSupportedCities()

    fun getCurrentLocationFromRemote(
        location: String? = null
    ) = remoteLocationRepository.getCurrentLocationFromRemote(location = location)
}