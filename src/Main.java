import java.io.File;
import java.util.ArrayList;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {


        String[] list = new String[2];
        ArrayList<String> textFiles = new ArrayList<>();
//        String list2= new String();
        ArrayList<String> stopWordsList = new ArrayList<>();

  /*      for (int i = 1; i <= 314; i++) {
            //System.out.println(i + "\t" + fileRead(i));
            String address = "E:\\SearchingData_Database\\";

            //File f = new File(address + String.valueOf(index) + ".txt");
            if(new File(address + String.valueOf(i) + ".txt").isFile()) {
                list = fileRead(i);
                 System.out.println(list);
                for (int j = 0; j < stopWordsList.size(); j++) {
                    list[0] = list[0].replace(stopWordsList.get(j)," ");
                }
                // System.out.println(list1);
                textFiles.add(list[1]);
                //System.out.println(list1);

            }
        }
*/


        String address = "E:\\SearchingData_Database\\";

        //File f = new File(address + String.valueOf(index) + ".txt");
        if(new File(address + String.valueOf(1) + ".txt").isFile()) {
            list = fileRead(1);
            //System.out.println(list);
   /*         for (int j = 0; j < stopWordsList.size(); j++) {
                list[0] = list[0].replace(stopWordsList.get(j), " ");
            }*/
            // System.out.println(list1);
            textFiles.add(list[1]);
            //System.out.println(list1);
        }

        for (int i = 0; i < textFiles.size(); i++) {
            System.out.println(textFiles.get(i));
        }

        



    }



    public static String[] fileRead(int index) throws IOException {
        // File path is passed as parameter

        String address = "E:\\SearchingData_Database\\";

        File file = new File(address + String.valueOf(index) + ".txt");

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br = null;

        if(new FileReader(file) != null) {
            br = new BufferedReader(new FileReader(file));
        }

        // Declaring a string variable
        String st;
        String contain="";
        String contain2="";
        int flag = 0, lineZero = 0;
        // Consition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
            // Print the string
            if(st.contains("###")){
                flag++;
            }

            if(flag == 1 && lineZero != 0) {
//                PorterStemmer stem = new PorterStemmer();
//                stem.setCurrent(contain);
//                stem.stem();
//                contain = stem.getCurrent();

                contain2 = contain2 + st;
                String[] words = st.split(" ");
                ArrayList<String> wordsLine = new ArrayList<>();
                for (int i = 0; i < words.length; i++) {
                    if(words[i].length() > 5){
                        String temp = "";
                        for (int j = 0; j < 5; j++) {
                            temp = temp + words[i].charAt(j);
                        }
                        wordsLine.add(temp);
                    }else{
                        wordsLine.add(words[i]);
                    }
                }
                for (int i = 0; i < wordsLine.size(); i++) {
                    contain = contain + wordsLine.get(i).toLowerCase() + " ";
                }
            }
            lineZero++;
        }

        String[] a = new String[2];
        a[0] = contain;
        a[1] = contain2;
        return a;

    }

}


class Document{

    public int wordCount = 0;



}
