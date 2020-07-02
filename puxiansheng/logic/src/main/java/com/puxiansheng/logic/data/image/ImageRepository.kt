package com.puxiansheng.logic.data.image

class ImageRepository {
    private val remoteImageRepository = RemoteImageRepository()

    fun requestMenuImages() = remoteImageRepository.requestMenuImages()
    fun requestRemoteImages(position: String) = remoteImageRepository.requestRemoteImages(position)
    fun requestRemoteImage(position: String) = remoteImageRepository.requestRemoteBanner(position)
    fun requestAdvertImages(position: String) = remoteImageRepository.requestAdvertImages(position)
    fun SubmitAdvertImages(position: String) = remoteImageRepository.submitAdvertImages(position)
}