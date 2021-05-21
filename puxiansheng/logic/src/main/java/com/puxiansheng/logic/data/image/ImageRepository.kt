package com.puxiansheng.logic.data.image

class ImageRepository {
    private val remoteImageRepository = RemoteImageRepository()

    fun requestMenuImages() = remoteImageRepository.requestMenuImages()
    fun requestNewMenuImages() = remoteImageRepository.requestNewMenuImages()
    fun requestHomeVideo() = remoteImageRepository.requestHomeVideo()
    fun requestHomeProject() = remoteImageRepository.requestHomeProject()
    fun requestProjectList(industry: String = "",
                            view_count:String = "",
                            page: Int,
                            city: String?) = remoteImageRepository.requestProjectList( industry = industry,
        page = page,
        city = city,
        view_count = view_count)
    fun getProjectDetailFromRemote(
        shopID: String
    ) = remoteImageRepository.getProjectDetailFromRemote(
        shopID = shopID
    )


    fun requestRemoteImages(position: String) = remoteImageRepository.requestRemoteImages(position)
    fun requestRemoteImage(position: String) = remoteImageRepository.requestRemoteBanner(position)
    fun requestAdvertImages(position: String) = remoteImageRepository.requestAdvertImages(position)
    fun SubmitAdvertImages(position: String) = remoteImageRepository.submitAdvertImages(position)
}