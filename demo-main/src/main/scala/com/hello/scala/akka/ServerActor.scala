package com.hello.scala.akka

import akka.actor.{Actor, ActorSystem, Props}

/**
 * akka-remote官方已不建议使用
 */
class ServerActor extends Actor{
  override def receive: Receive = {
    case "start" => println("server start ...")
  }
}

object ServerActorDemo extends App{
  val serverFactory = ActorSystem(ClientServerConfig.serverSystemName, ClientServerConfig.serverConfig)
  val serverRef = serverFactory.actorOf(Props[ServerActor], ClientServerConfig.serverRefName)
  serverRef ! "start"
}
