//Space Cadets Challenge 1
//Try usernames "dem", "srg", "km2" for example
import java.net.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        //Get a valid username
        String nameLine = null;
        do {
            nameLine = fetchUsernameInfo();
        } while (nameLine == null);
        //Extract substring from line containing name
        int nameStart = nameLine.indexOf("\"og:title\" content=\"") + 20;
        int nameEnd = nameLine.indexOf("\"", nameStart);
        String name = nameLine.substring(nameStart, nameEnd);
        //Output name
        System.out.println(name);
    }
    private static String fetchUsernameInfo() throws IOException {
        System.out.println("Enter username:");
        //Variable declaration
        BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
        String username = input.readLine();
        String website = "https://www.ecs.soton.ac.uk/people/" + username;
        URL websiteURL = new URL(website);
        BufferedReader websiteReader = new BufferedReader (new InputStreamReader(websiteURL.openStream()));
        String inputLine;
        //Search website for name
        try {
            do {
                inputLine = websiteReader.readLine();
            } while (!(inputLine.contains("og:title")));
        } catch (Exception e) {
            //Catch exception caused by invalid name
            inputLine = null;
            System.out.println("Username is not found");
        }
        //Return line containing name
        return inputLine;
    }
}