public class StringDemo1
{
    public static void main(String[] args)
    {
        String word = "Hello World!";
        String word2 = "Dog";

        String upper = word.toUpperCase();
        System.out.println(upper);

        String lower = word.toLowerCase();
        System.out.println(lower);

        char charAt = word.charAt(5);
        System.out.println(charAt);

        String conCat = word.concat(word2);
        System.out.println(conCat);

        boolean equalString = word.equals(word2);
        System.out.println(equalString);

        boolean equalStringIgnoreCase = word.equalsIgnoreCase(word);
        System.out.println(equalStringIgnoreCase);

        int wordLen = word.length();
        System.out.println(wordLen);

        String replaceWord = word.replace("H", "N");
        System.out.println(replaceWord);

        String trimWord = word.trim();
        System.out.println(trimWord);
    }
}
