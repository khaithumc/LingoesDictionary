/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author USER
 */
public enum LanguageEnum {
    Afrikaans("af"),
    Albanian("sq"),
    Amharic("am"),
    Arabic("ar"),
    Armenian("hy"),
    Azerbaijani("az"),
    Basque("eu"),
    Belarusian("be"),
    Bengali("bn"),
    Bosnian("bs"),
    Bulgarian("bg"),
    Catalan("ca"),
    Cebuano("ceb"),
    Chinese("zh"),
    Corsican("co"),
    Croatian("hr"),
    Czech("cs"),
    Danish("da"),
    Dutch("nl"),
    English("en"),
    Esperanto("eo"),
    Estonian("et"),
    Finnish("fi"),
    French("fr"),
    Frisian("fy"),
    Galician("gl"),
    Georgian("ka"),
    German("de"),
    Greek("el"),
    Gujarati("gu"),
    HaitianCreole("ht"),
    Hausa("ha"),
    Hawaiian("haw"),
    Hebrew("he"),
    Hindi("hi"),
    Hmong("hmn"),
    Hungarian("hu"),
    Icelandic("is"),
    Igbo("ig"),
    Indonesian("id"),
    Irish("ga"),
    Italian("it"),
    Japanese("ja"),
    Javanese("jv"),
    Kannada("kn"),
    Kazakh("kk"),
    Khmer("km"),
    Korean("ko"),
    Kurdish("ku"),
    Kyrgyz("ky"),
    Lao("lo"),
    Latin("la"),
    Latvian("lv"),
    Lithuanian("lt"),
    Luxembourgish("lb"),
    Macedonian("mk"),
    Malagasy("mg"),
    Malay("ms"),
    Malayalam("ml"),
    Maltese("mt"),
    Maori("mi"),
    Marathi("mr"),
    Mongolian("mn"),
    Myanmar("my"),
    Nepali("ne"),
    Norwegian("no"),
    Nyanja("ny"),
    Pashto("ps"),
    Persian("fa"),
    Polish("pl"),
    Portuguese("pt"),
    Punjabi("pa"),
    Romanian("ro"),
    Russian("ru"),
    Samoan("sm"),
    ScotsGaelic("gd"),
    Serbian("sr"),
    Sesotho("st"),
    Shona("sn"),
    Sindhi("sd"),
    Sinhala("si"),
    Slovak("sk"),
    Slovenian("sl"),
    Somali("so"),
    Spanish("es"),
    Sundanese("su"),
    Swahili("sw"),
    Swedish("sv"),
    Tagalog("tl"),
    Tajik("tg"),
    Tamil("ta"),
    Telugu("te"),
    Thai("th"),
    Turkish("tr"),
    Ukrainian("uk"),
    Urdu("ur"),
    Uzbek("uz"),
    Vietnamese("vi"),
    Welsh("cy"),
    Xhosa("xh"),
    Yiddish("yi"),
    Yoruba("yo"),
    Zulu("zu");

    private final String value;

    LanguageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
