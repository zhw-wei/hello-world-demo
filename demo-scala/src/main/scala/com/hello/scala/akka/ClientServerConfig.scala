package com.hello.scala.akka

import com.typesafe.config.{Config, ConfigFactory}

object ClientServerConfig {

  val (clientHost, clientPort, serverHost, serverPort) = ("127.0.0.1", 9989, "127.0.0.1", 9998)

  val (serverSystemName, serverRefName, clientSystemName, clientRefName) =
    ("helloServerName", "helloServerRefName", "helloClientName", "helloClientRefName")

  val clientConfig = config(clientHost, clientPort)

  val serverConfig = config(serverHost, serverPort)

  def config(host: String, port: Int): Config = {
    ConfigFactory.parseString(
      s"""
         |akka.actor.provider="cluster"
         |akka.remote.artery.canonical.tcp.hostname=$host
         |akka.remote.artery.canonical.tcp.port=$port
         |"""
      .stripMargin
    )
  }
}
