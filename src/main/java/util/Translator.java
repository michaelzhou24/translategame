package util;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class Translator {
    public Translator() {}

    public String translateTo(String str, String sourceLang, String targetLang) {
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        Translation translation =
                translate.translate(
                        str,
                        TranslateOption.sourceLanguage(sourceLang),
                        TranslateOption.targetLanguage(getLanguageCode(targetLang)));
        return translation.getTranslatedText();
    }

    private String getLanguageCode(String lang) {
        switch (lang) {
            case "Spanish":
                return "es";
            case "Chinese":
                return "zh-CN";
            case "Japanese":
                return "ja";
            case "French":
                return "fr";
            case "Russian":
                return "ru";
        }
        return null;
    }
}