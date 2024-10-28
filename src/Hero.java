import java.util.Random;

public class Hero {
    private String hero;
    private int dragons;

    public Hero(String name){
        Random rnd=new Random();
        hero=(name.trim().length()>0?name:"Rhaenyra Targaryen"); //defines default hero name
        dragons=rnd.nextInt(5)+5; //randomizes quantity of dragons from 5 up to 10

        System.out.println("Welcome "+ hero+"!");
        System.out.println("You have "+ dragons+ " dragons");
        System.out.println("You are  "+ (dragons>7?"worthy of the iron throne!":"not worthy of the iron throne!"));

    }
}
