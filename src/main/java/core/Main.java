package core;

import core.queue.Queue;

/**
 * @author azozello
 */
public class Main {
    public static void main(String[] args){

        Queue<String> test = new Queue<String>(3,String.class);

        testPrint(test);

        String[] strings = {"a","b","c"};

        test = new Queue<String>(strings);

        testPrint(test);
    }

    private static void testPrint(Queue test){
        System.out.println(test);
        test.insert("sad");
        System.out.println(test);
        test.insert("but");
        System.out.println(test);
        test.insert("true");
        System.out.println(test);
        test.insert("Twenty");
        System.out.println(test);

        test.remove();
        System.out.println(test);
        test.remove();
        System.out.println(test);
        test.remove();
        System.out.println(test);
        test.remove();
        System.out.println(test);
    }
}

