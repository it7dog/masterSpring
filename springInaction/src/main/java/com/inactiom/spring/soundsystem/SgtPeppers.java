package com.inactiom.spring.soundsystem;

import org.springframework.stereotype.Component;

@Component("lonelyHeartsClub") //对bean进行命名
public class SgtPeppers implements CompactDisc {
  private String title = "Sgt. Pepper's Lonely Hearts Club Band";
  private String artist = "The Beatles";

  @Override
  public void play() {
    System.out.println("Playing " + title + " By " + artist);
  }
}
