
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;


public class Game {


    public Game(String character){
        Hero hero=new Hero(character); //starts the game by printing about the hero from the Hero class
        StartGame();
    }

    private void StartGame(){
        Scenes start=new Scenes(); //refers to Scenes class
        Scanner input=new Scanner(System.in); //inquires users input
        String value="";


        do{
            System.out.println("\nWhere would you like to move? North or South?");
            value=input.nextLine();
            if(!value.toLowerCase().equals("exit")){
                start.Move(value);
            }



        }while(!value.toLowerCase().equals("exit")); //if user types exit program will quit




    }

}
