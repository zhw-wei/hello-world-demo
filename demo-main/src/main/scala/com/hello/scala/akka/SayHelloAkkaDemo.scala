package com.hello.scala.akka

import akka.actor.{Actor, ActorSystem, Props}

/**
 * actor自我通讯
 */
object SayHelloAkkaDemo extends App {

  //创建ActorSystem，用于创建Actor
  val actorFactory = ActorSystem("actorFactory")

  /*
  创建Actor的同时，返回Actor的ActorRef，创建的Actor会被ActorSystem接管
  说明：
  1. Props[SayHelloAkka]创建了一个SayHelloAkka实例，使用反射
  2. "sayHelloActor"给Actor取名
   */
  val sayHelloAkkaRef = actorFactory.actorOf(Props[SayHelloAkka], "sayHelloActor")

  sayHelloAkkaRef ! "hello"
  sayHelloAkkaRef ! "ok"
  sayHelloAkkaRef ! ""
  sayHelloAkkaRef ! "exit"
}

class SayHelloAkka extends Actor {
  /*
  1. receive方法会被该Actor的MailBox调用，MailBox实现了Runnable接口
  2. 当该Actor的MailBox接收到消息，就会调用receive
  3. type Receive = PartialFunction[Any, Unit]
   */
  override def receive: Receive = {
    case "hello" => println("收到hello，回应hello too :)")
    case "ok" => println("收到ok，回应ok too :)")
    case "exit" => { //默认情况下，不会停止，因为Actor的MailBox实现了Runnable接口
      context.stop(self) //停止ActorRef
      context.system.terminate() //停止，退出ActorSystem
    }
    case _ => println("匹配不到")
  }
}
