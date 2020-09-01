package com.puxiansheng.logic.data.homeinfo


class InfoMarqueeRepository {
    private val remoteInfoMarqueeRepository = RemoteInfoMarqueeRepository()

    fun requestRemoteInfoMarquee(page: String,city:String) = remoteInfoMarqueeRepository.requestRemoteMarqueeInfos(page,city)
}