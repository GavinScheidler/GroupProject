public class IDS11{
    public static boolean isValidEmail(String email){
        if (email == null) {
            return false;
        }

        //remove white space from inputted string
        email = email.trim();

        //lowercase string
        email = email.toLowerCase();

        //all valid characters for email address
        String emailRegEx = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        //perform validation after modifications
        return email.matches(emailRegEx);
    }

    //method to print the results of the validation
    public static String message(String email){
        if(!isValidEmail(email)){
            return "'" + email + "'" + " is NOT valid";
        } else{
            return "'" + email + "'" + " is valid";
        }
    }

    public static void main(String[] args){
        String invalidEmail = "MyNameis*THis()@email.com";
        System.out.println(message(invalidEmail));

        System.out.println();

        String validEmail = "thisIsAvalidemail@email.com";
        System.out.println(message(validEmail));

    
    }
}