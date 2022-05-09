package me.gsycl2004.data


@kotlinx.serialization.Serializable
data class Sender(
    val name:String,
    val avatar:String,
    val id:Long,
    val title:String,
    val titleColor:String,
)