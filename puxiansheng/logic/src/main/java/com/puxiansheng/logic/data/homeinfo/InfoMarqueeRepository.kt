package com.puxiansheng.logic.data.homeinfo


class InfoMarqueeRepository {
    private val remoteInfoMarqueeRepository = RemoteInfoMarqueeRepository()

    fun requestRemoteInfoMarquee(city:String) = remoteInfoMarqueeRepository.requestRemoteMarqueeInfos(city)
}