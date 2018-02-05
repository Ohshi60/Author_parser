import java.util.*;

/**
 *
 *
 * Created by Michael on 03-02-2018.
 */
class StringSplitter {


    //Here we feed the names and execute the functions
    public static void main(String[] args){
        String name = "H-C Jensen, Peter Hans Kristensen, John Doe";
        parse_Names(name);
    }

    //The purpose of this method is to determine whether a string has a comma in it in order to identify the cases where
    //the order of the names is reversed(e.g Doe, John)
    private static boolean is_Reversed(String s) {
        return s.contains(",");
    }

    //Our main method: The idea is to use string split on the spaces and then depending on the information from the
    //is_List and is_Reversed functions determine the case we in order to deduce the first and last names. First we
    //check for a list since multiple commas results in a false positive for the is_Reversed function.
    private static void parse_Names(String s) {


        String[] listOfNames = s.split(" ");
        List<Author> authors = new ArrayList<>();
        int lengthOfNames = listOfNames.length;

        //The logic behind this loop is to first split on the commas and then split each result into its own then do
        //an OrderedParse
        if(is_List(listOfNames)){
            String[] names = s.split(",");
            for (String name1 : names) {
                String[] name = name1.split(" ");
                authors.add(OrderedParse(name));
            }
        }
        //Note we purge the comma directly here, again under the assumption that a person can only have one lastname
        else if(is_Reversed(s)){
            Author a = new Author();
            StringBuilder fn = new StringBuilder();
            StringBuilder sb = new StringBuilder(listOfNames[0].replace(",",""));
            for(int i = 1;i < lengthOfNames;i++){
                fn.append(" ").append(listOfNames[i]);
            }
            a.firstname = fn.toString();
            a.surname = sb.toString();
            authors.add(a);
        }
        else{
            authors.add(OrderedParse(listOfNames));
        }
        //This loop iterates over the authors array and prints the name therein
        for (Author a:authors) {
            System.out.println("First name: "+a.firstname+","+" Last name: "+a.surname);
        }

    }
    //The idea behind this function is to search all entries of the listOfNames and if more than 1 comma is found
    //we hereby know that its a list
    private static boolean is_List(String[] s){
        int counter = 0;
        for (String element:s) {
            if (element.contains(",")){
                counter++;
            }
        }
        return counter > 1;

    }
    //A helper since i can use this code in 2 cases. The logic behind it assumes that the last index is the surname
    private static Author OrderedParse(String[] names){
        Author a = new Author();
        StringBuilder sb = new StringBuilder(names[0]);
        for(int i = 1; i < names.length; i++){
            if (i == names.length-1){
                a.surname = names[i];
            }
            else sb.append(" ").append(names[i]);
        }
        a.firstname = sb.toString();
        return a;
    }
}
