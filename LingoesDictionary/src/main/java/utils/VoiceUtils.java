/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 *
 * @author USER
 */
public class VoiceUtils {

    private static final String VOICE_NAME = "kevin16";

    private VoiceUtils() {

    }

    public static void ConvertTextToSpeech(String text) {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICE_NAME);
        voice.allocate();
        voice.speak(text);
    }

}
