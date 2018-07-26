/**
 * @author MarkHuang
 * @version <ul>
 * <li>2018/7/25, MarkHuang,new
 * </ul>
 * @since 2018/7/25
 */
class ConvertUtil {
    static String toCamelCase(String text) {
        String[] textParts = text.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder(200);
        sb.append(textParts[0]);
        for (int i = 1; i < textParts.length; i++) {
            sb.append(capitalize(textParts[i]));
        }
        return sb.toString();
    }

    static String toPascalCase(String text) {
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder(200);
        for (char aChar : chars) {
            if (((int) aChar >= 65 && (int) aChar <= 90)||
                    ((int) aChar >= 48 && (int) aChar <= 57)) {
                sb.append("_");
            }
            sb.append(aChar);
        }
        return capitalize(sb.toString());
    }

    static boolean isPascalCase(String text){
        return text.contains("_");
    }

    private static String capitalize(String text) {
        if (text.length() == 1) {
            return text.toUpperCase();
        }
        return String.valueOf(text.charAt(0)).toUpperCase() + text.substring(1, text.length());
    }

}
