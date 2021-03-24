package com.hello.scala.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
 * Actor之间的通讯
 */
object ActorGame extends App {
  /*
  1. 两个Actor通讯机制和Actor自身发消息机制基本一样
  2. 如果A Actor在需要给B Actor发消息，则需要持有B Actor的ActorRef，可以通过创建时，传入B Actor的代理对象（ActorRef）
  3. 当B Actor在receive方法中接收到消息，需要回复时，可以通过sender获取到发送Actor的代理对象
   */
  val actorFactory: ActorSystem = ActorSystem("actorFactory")
  val bActorRef: ActorRef = actorFactory.actorOf(Props[BActor], "bActorRef")
  val aActorRef: ActorRef = actorFactory.actorOf(Props(new AActor(bActorRef)), "aActorRef")

  aActorRef ! "start"

}

class AActor(actorRef: ActorRef) extends Actor{
  val bActorRef = actorRef
  override def receive: Receive = {
    case "start" =>{
      println("a actor start ...")
      self ! "send"
    }
    case "send" => {
      println("a actor send to b actor")
      Thread.sleep(1000)
      bActorRef ! "send"
    }
  }
}

class BActor extends Actor{
  override def receive: Receive = {
    case "send" => {
      println("b actor send to a actor")
      Thread.sleep(1000)
      //通过sender()可以获取发送消息的ActorRef
      sender() ! "send"
    }
  }
}
