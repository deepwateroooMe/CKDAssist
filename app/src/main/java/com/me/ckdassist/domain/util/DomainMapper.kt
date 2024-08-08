package com.me.ckdassist.domain.util

 // 声明：接口类：它是个帮助类，帮助双向转化的，所以放 Util 包里
interface DomainMapper <T, DomainModel> { // DomainModel 是什么意思？像是【泛型】的写法，随便起名的。。把某个域Domain 里的ViewModel 声称为 DomainModel
    // DomainModel, 这里就是， domain/model/ 下定义过的，都算是 DomainModel 的实例
    fun mapToDomainModel(model: T): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel): T
}