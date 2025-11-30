public class StringBufferBuilderDemo1
{

    public static void main(String[] args)
    {
        String word = "Hello World!";
        String word2 = "Hello World!";

        StringBuffer sb = new StringBuffer(word);
        StringBuilder sb2 = new StringBuilder(word2);
        System.out.println(sb);
        System.out.println(sb2);

        sb.append(" John");
        sb2.append(" John");
        System.out.println(sb);
        System.out.println(sb2);

        sb.insert(2, "John");
        sb2.insert(2, "John");
        System.out.println(sb);
        System.out.println(sb2);

        sb.deleteCharAt(4);
        sb2.deleteCharAt(4);
        System.out.println(sb);
        System.out.println(sb2);


        sb.setCharAt(4, 'H');
        sb2.setCharAt(4, 'H');
        System.out.println(sb);
        System.out.println(sb2);

        sb.substring(0, 3);
        sb2.substring(0, 3);
        System.out.println(sb);
        System.out.println(sb2);

    }

}
