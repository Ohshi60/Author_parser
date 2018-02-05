/**
 * Class created to hold the names of the parsed authors, nothing special here just two properties and 2 constructors
 * Created by Michael on 04-02-2018.
 */
class Author {
    String firstname;
    String surname;
    public Author(){
        this.firstname = "NN";
        this.surname = "NN";
    }
    public Author(String f, String s){
        this.firstname = f;
        this.surname = s;
    }
}
