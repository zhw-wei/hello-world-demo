package com.hello.scala.akka

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}

class ClientActor(serverHost: String, serverPort: Int, serverSystemName: String, serverRefName: String) extends Actor {

  var serverActorRef: ActorSelection = _

  override def preStart(): Unit = {
    serverActorRef = context.actorSelection(s"akka.tcp://$serverSystemName@$serverHost:$serverPort/user/$serverRefName")
  }

  override def receive: Receive = {
    case "start" => println("client start ...")
  }
}

object ClientActorDemo extends App {
  val clientFactory = ActorSystem(ClientServerConfig.clientSystemName, ClientServerConfig.clientConfig)
  val clientActorRef = clientFactory.actorOf(Props(new ClientActor(ClientServerConfig.serverHost, ClientServerConfig.serverPort,
    ClientServerConfig.serverSystemName, ClientServerConfig.serverRefName)), ClientServerConfig.clientRefName)

  clientActorRef ! "start"
}
