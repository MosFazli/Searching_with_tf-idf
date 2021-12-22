import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }


    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }


    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }

    public static void main(String[] args) throws IOException {
/*
        List<String> doc1 = Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum");
        List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
        List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);
 */

        String[] list = new String[2];
        ArrayList<String> textFiles = new ArrayList<>();
        ArrayList<String> stopWordsList = new ArrayList<>();

        List<List<String>> documents = new ArrayList<>();

        for (int i = 1; i <= 314; i++) {
                   String address = "E:\\SearchingData_Database\\";
            //String address = "E:\\test\\";
            //File f = new File(address + String.valueOf(index) + ".txt");
            if (new File(address + String.valueOf(i) + ".txt").isFile()) {
                list = fileRead(i);
                //System.out.println(list);
                System.out.println("File number " + i + " added successfully !");
        /*        for (int j = 0; j < stopWordsList.size(); j++) {
                    list[0] = list[0].replace(stopWordsList.get(j)," ");
                }
         */

                String[] strings = list[1].split(" ");
                List<String> doc = Arrays.asList(strings);
                documents.add(doc);
            }
        }

        Main calculator = new Main();

        List<Double> tfidfs = new ArrayList<>();


        String query = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your query: ");
        query = scanner.nextLine();

        String[] queryString = query.split(" ");
        List<String> queryList = Arrays.asList(queryString);

        LinkedList<Double> querytfidfs = new LinkedList<>();
        List<List<String>> queries = Arrays.asList(queryList);
        Main calculator2 = new Main();

        for (int i = 0; i < queryList.size(); i++) {
            //double querytfidf = calculator2.tf(queryList,queryList.get(i));
            double querytfidf = calculator.tfIdf(queryList, documents, queryList.get(i));
            querytfidfs.add(querytfidf);
            //  System.out.println(querytfidf);
        }

        System.out.println("\n**************************************\n");
        LinkedList<Double> ans = new LinkedList<>();
        LinkedList<Integer> answers = new LinkedList<>();

        for (int i = 0; i < documents.size(); i++) {
            for (int j = 0; j < queryList.size(); j++) {
                double tfidf = calculator.tfIdf(documents.get(i), documents, queryList.get(j));
                tfidfs.add(tfidf);
                // System.out.println(tfidf);
            }
        }


        for (int i = 0; i < documents.size(); i++) {
            double temp = 0;
            for (int j = 0; j < querytfidfs.size(); j++) {
                temp += (querytfidfs.get(j) * tfidfs.get(i));
            }
            // System.out.println(temp);
            ans.add(temp);
        }


        LinkedList<Integer> indexes = new LinkedList<>();
        for (int j = 0; j < ans.size(); j++) {
            double max = -1;
            int index = 0;
            for (int i = 0; i < ans.size(); i++) {
                if (ans.get(i) >= max && !indexes.contains(i)) {
                    max = ans.get(i);
                    index = i;
                }
            }
            indexes.add(index);
            answers.add(index);
        }

        if (answers.size() > 10) {
            for (int i = 0; i < 10; i++) {
                System.out.println(documents.get(answers.get(i)));
            }
        }else {
            for (int i = 0; i < answers.size(); i++) {
                System.out.println(documents.get(answers.get(i)));
            }
        }



        //    System.out.println("TF-IDF (ipsum) = " + tfidf);


    }


    public static String[] fileRead(int index) throws IOException {
        // File path is passed as parameter

        String address = "E:\\SearchingData_Database\\";

        File file = new File(address + String.valueOf(index) + ".txt");

        // Creating an object of BufferedReader class
        BufferedReader br = null;

        if (new FileReader(file) != null) {
            br = new BufferedReader(new FileReader(file));
        }

        // Declaring a string variable
        String st;
        String contain = "";
        String contain2 = "";
        int flag = 0, lineZero = 0;
        // Consition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
            // Print the string
            if (st.contains("###")) {
                flag++;
            }

            if (flag == 1 && lineZero != 0) {
//                PorterStemmer stem = new PorterStemmer();
//                stem.setCurrent(contain);
//                stem.stem();
//                contain = stem.getCurrent();

                contain2 = contain2 + st;
                String[] words = st.split(" ");
                ArrayList<String> wordsLine = new ArrayList<>();
                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() > 5) {
                        String temp = "";
                        for (int j = 0; j < 5; j++) {
                            temp = temp + words[i].charAt(j);
                        }
                        wordsLine.add(temp);
                    } else {
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