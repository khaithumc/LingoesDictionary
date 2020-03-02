/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 *
 * @author USER
 */
public class VoiceTest {
    
    private static final String VOICE_NAME = "kevin16";
    
    public static void main(String[] args) {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICE_NAME);
        voice.allocate();
        voice.speak("Fuck you");
    }
    
}
