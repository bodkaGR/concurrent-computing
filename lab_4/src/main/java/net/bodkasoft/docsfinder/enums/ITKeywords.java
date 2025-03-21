package net.bodkasoft.docsfinder.enums;

public enum ITKeywords {

    JAVA, PYTHON, JAVASCRIPT, SQL, HTML, CSS, REACT, ANGULAR, NODEJS;

    public static boolean contains(String keyword) {
        for (ITKeywords itKeyword : values()) {
            if (itKeyword.name().equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
}
