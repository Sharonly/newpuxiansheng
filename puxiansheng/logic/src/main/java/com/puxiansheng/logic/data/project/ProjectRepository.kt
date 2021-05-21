package com.puxiansheng.logic.data.project

class ProjectRepository {
    private val remoteProjectRepository = RemoteProjectRepository()
    fun requestHomeProject() = remoteProjectRepository.requestHomeProject()
    fun requestProjectList(
        industry: String = "",
        view_count: String = "",
        page: Int,
        city: String?
    ) = remoteProjectRepository.requestProjectList(
        industry = industry,
        page = page,
        city = city,
        view_count = view_count
    )

    fun getProjectDetailFromRemote(
        shopID: String
    ) = remoteProjectRepository.getProjectDetailFromRemote(
        shopID = shopID
    )

    fun getFavorProjectListFromRemote(page: Int) =
        remoteProjectRepository.requestFavorProject(page = page)


}