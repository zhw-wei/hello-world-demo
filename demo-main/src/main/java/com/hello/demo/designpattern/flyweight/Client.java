package com.hello.demo.designpattern.flyweight;

public class Client {
    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();

        WebSite webSite_0 = webSiteFactory.getWebSite("00");
        webSite_0.use(new User("aa"));

        WebSite webSite_1 = webSiteFactory.getWebSite("01");
        webSite_1.use(new User("bb"));

        System.out.println(webSiteFactory.getWebSiteCount());
    }
}
