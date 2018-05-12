package com.inaction.test;

import com.inactiom.spring.soundsystem.CDPlayerConfig;
import com.inactiom.spring.soundsystem.CompactDisc;
import com.inactiom.spring.soundsystem.MediaPlayer;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDplayerTest {
  @Autowired
  private CompactDisc cd;
  @Autowired
  private MediaPlayer player;

  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @Test
  public void cdShouldNotBeNull() {
    assertNotNull(cd);
  }

  @Test
  public void play(){
    player.play();
    assertEquals("Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles",log.getLog());
  }
}
