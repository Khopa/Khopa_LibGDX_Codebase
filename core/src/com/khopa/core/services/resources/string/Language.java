package com.khopa.core.services.resources.string;

/**
 * TODO : could be improved (redundant code)
 * Language must be registered manually here, not very practical
 */
public enum Language {
	FRENCH,
	ENGLISH,
    GERMAN,
    ITALIAN,
    SPANISH,
    PORTUGUESE,
    RUSSIAN,
    KOREAN,
    JAPANESE;

	public static Language fromString(String str) {
		if(str.equals("en")){
			return ENGLISH;
		}else if(str.equals("fr")){
			return FRENCH;
		}else if(str.equals("de")){
            return GERMAN;
        }else if(str.equals("it")){
            return ITALIAN;
        }else if(str.equals("ru")){
            return RUSSIAN;
        }else if(str.equals("ko")){
            return KOREAN;
        }else if(str.equals("jp")){
            return JAPANESE;
        }else if(str.equals("es")){
            return SPANISH;
        }else if(str.equals("pt")){
            return PORTUGUESE;
        }
		else return Language.getDefaultLanguage();
	}
	
	public static Language getDefaultLanguage(){
		return ENGLISH;
	}
	
	public static String toString(Language lang){
		switch(lang){
			case ENGLISH:
				return "en";
			case FRENCH:
				return "fr";
            case GERMAN:
                return "de";
            case ITALIAN:
                return "it";
            case RUSSIAN:
                return "it";
            case KOREAN:
                return "ko";
            case JAPANESE:
                return "jp";
            case SPANISH:
                return "sp";
            case PORTUGUESE:
                return "pt";
		}
		return "en";
	}
	
}
