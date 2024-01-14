package dev.norton.librarymanager.Tools;

public class StrT {

    // region QUOT
    public static String SingleQuote = "'";
    public static String DoubleQuote = "\"";

    public static String SingleQuotStr(String s) {
        return SingleQuote + s + SingleQuote;
    }

    public static String DoubleQuotStr(String s) {
        return DoubleQuote + s + DoubleQuote;
    }

    // endregion QUOT

    public static String Empty;
    public static boolean IsNullOrEmpty(String s) {
        return s.isEmpty() || s.isBlank();
    }
}
