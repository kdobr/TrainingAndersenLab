package shortWay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {

        System.out.println(shortway('R', 'D'));
    }

    public static String shortway(char one, char two) {

        String test = "JXVYJLZMEDAQYMZITLEIGIZUREREDLLXSPDFIWTTRZNOCVHPKWVECUREHBFAMJZDYTJSTHGZWBAMVGDVABQENKTNVGSXYQDST";

        List<Integer> oneList = new ArrayList<>();
        List<Integer> twoList = new ArrayList<>();
        char[] testChar = test.toCharArray();
        for (int i = 0; i < testChar.length; i++) {
            if (testChar[i] == one) {
                oneList.add(i);
            }
            if (testChar[i] == two) {
                twoList.add(i);
            }
        }
        System.out.println(oneList);
        System.out.println(twoList);
        int testOne = 0;
        int testTwo = 0;
        int min = Integer.MAX_VALUE;

        for (int m = 0; m < oneList.size(); m++) {
            for (int n = 0; n < twoList.size(); n++) {
                int next = Math.abs(oneList.get(m) - twoList.get(n));
                if (next < min) {
                    min = next;
                    testOne = oneList.get(m);
                    testTwo = twoList.get(n);
                }
            }
        }

        test = test.substring(testOne, testTwo + 1);
        System.out.println(test);

        //нашли кратчайшее расстояние
//=========================================

        byte[] strInbytes = test.getBytes();
        int[] strInInt = new int[strInbytes.length];
        for (int i = 0; i < strInbytes.length; i++) {
            strInInt[i] = strInbytes[i];
        }
        int maxValue = Arrays.stream(strInInt).max().getAsInt();
        System.out.println(Arrays.toString(strInInt));
        System.out.println(maxValue);
        int numCounts[] = new int[maxValue + 1];

        for (int num : strInInt) {
            numCounts[num]++;
        }

        char last = 0;
        for (int i = numCounts.length - 1; i >= 0; i--) {
            if (numCounts[i] > 1)
                last = (char) i;
            test = test.substring(0, test.indexOf(last) + 1) + test.substring(test.lastIndexOf(last) + 1);
        }
        if (last == 0) return test;
        return test;
        //.substring(test.lastIndexOf(last));
    }
}
