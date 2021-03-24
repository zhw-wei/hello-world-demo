package com.hello.demo.designpattern.facade;

/**
 * 影院
 */
public class HomeTheaterFacade {

    private TheaterLight theaterLight;
    private Popcorn popcorn;
    private Screen screen;
    private Projector projector;
    private DVDPlayer dvdPlayer;
    private Stereo stereo;

    public HomeTheaterFacade(){
        this.theaterLight = TheaterLight.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.screen = Screen.getInstance();
        this.projector = Projector.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
        this.stereo = Stereo.getInstance();
    }

    public void ready(){
        popcorn.on();
        popcorn.pop();
        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
    }

    public void play(){
        dvdPlayer.play();
    }

    public void pause(){
        dvdPlayer.pause();
    }

    public void end(){
        popcorn.off();
        theaterLight.bright();
        screen.up();
        projector.off();
        stereo.off();
        dvdPlayer.off();
    }
}
