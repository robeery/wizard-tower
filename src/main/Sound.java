package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];



    public Sound()
    {
        //soundtrack joc
        soundURL[0]=getClass().getResource("/sound/035-Dungeon01.wav");
        //cheie
        soundURL[1]=getClass().getResource("/sound/032-Switch01.wav");
        //potiune
        soundURL[2]=getClass().getResource("/sound/105-Heal01.wav");
        //usa deschide
        soundURL[3]=getClass().getResource("/sound/024-Door01.wav");
        //obtine spell
        soundURL[4]=getClass().getResource("/sound/157-Skill01.wav");
        //menu soundtrack
        soundURL[5]=getClass().getResource("/sound/menu.wav");
        //minus viata
        soundURL[6]=getClass().getResource("/sound/hit_player.wav");
        //foc
        soundURL[7]=getClass().getResource("/sound/fire.wav");
        //gheata
        soundURL[8]=getClass().getResource("/sound/ice.wav");
        //boss
        soundURL[9]=getClass().getResource("/sound/boss.wav");
        //win
        soundURL[10]=getClass().getResource("/sound/victory.wav");



    }
    public void setFile(int i)
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e)
        {

        }
    }
    public void play()
    {
        clip.start();
    }
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop()
    {
        clip.stop();
    }

}
