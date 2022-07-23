package com.github.commands

import com.github.XXYan
import com.github.YanConfig
import com.github.YanData
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.event.events.MessageEvent

object YanCommands : CompositeCommand(
    XXYan,
    "yan",
    parentPermission = XXYan.permission
) {
    @SubCommand
    suspend fun CommandSender.length(userId: Long) {
        val sequence = YanData.getSequence(userId)
        this.sendMessage("目前该用户的yan数量为:${sequence.rowSet.size()}")
    }

    @SubCommand
    suspend fun CommandSender.unsetYan(name: String) {
        val value = YanConfig.cares.remove(name)
        this.sendMessage("已成功移除${name} -> $value")
    }

    @SubCommand
    suspend fun CommandSenderOnMessage<MessageEvent>.makeYan(name: String, member: Member) {
        makeYan(name, member.id)
    }

    @SubCommand
    suspend fun CommandSender.makeYan(name: String, userId: Long) {
        YanConfig.cares[name] = userId
        this.sendMessage("已成功添加${name} -> $userId")
    }

    @SubCommand
    suspend fun CommandSender.stars() {
        val sequence = YanConfig.cares
        val ids = sequence.values.toSet()
        this.sendMessage("目前关注的人有:\n${ids.joinToString("\n")}")
    }


}

