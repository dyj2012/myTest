package com.duyj2.work.algorithm;


public class UpperFirstLetter {

    private static String upper(String str) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (Character c : str.toCharArray()) {
            if (first) {
                sb.append(Character.toUpperCase(c));
                first = false;
            } else {
                sb.append(c);
            }
            if (Character.isWhitespace(c)) {
                first = true;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        char c = 20;
        System.out.println("[" + c + "]");

        String str = "am luang   Aeng  shs n nn nnn 13 !asd lls 5a d 3 frr fdd3 f3f f77f ";
        System.out.println(str);
        System.out.println(upper(str));
    }

}
