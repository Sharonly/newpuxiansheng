package com.puxiansheng.logic.data.image

class ImageRepository {
    private val remoteImageRepository = RemoteImageRepository()

    fun requestRemoteImages(position: String) = remoteImageRepository.requestRemoteImages(position)
}