import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

public class Scenes {

    HashMap<String, String> response= new HashMap<>();
    private int loc;

    public int score;

    private List<String> scenes=new ArrayList<>(); //array is needed to load the scenes from .csv file to it


    public Scenes(){
        loc=0;
        loadScenes();
        Scene();
    }

    private void Scene(){

        System.out.println(scenes.get(loc)); //prints out current location after moving to it
    }

    private void loadScenes(){ //loads info from .csv file to the array
        String file="src/Scenes.csv";
        String respFile="src/response.txt";
        String line;

        try{
            BufferedReader reader=new BufferedReader(new FileReader(respFile));
            while ((line=reader.readLine())!=null){
                String[] parts=line.split(":", 2);
                if (parts.length>=2){
                    String key=parts[0];
                    String value=parts[1];
                    response.put(key,value);
                }
            }
            reader.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }


        try{
            BufferedReader br=new BufferedReader((new FileReader(file)));
            while((line=br.readLine())!=null){
                scenes.add(line);
            }
        }catch(IOException e){
            System.out.println(e);
        }

    }

    public void Move(String input){




        Character value=input.toLowerCase().charAt(0);

        switch (value){
            case 'n': //whenever user type n, N, north, North etc. (checks for the first character)
                if(loc==scenes.size()-1){
                    System.out.println("Can't go any further North.");
                }else{
                    loc+=1;
                }
                break;
            case 's':
                if(loc==0){
                    System.out.println("Can't go any further South.");
                }else{
                    loc-=1;
                }

                break;

            default:
                System.out.println("What did you mean? Please try again.");
                return;
        }

        Scene();
        if(loc==2){ //on the 3 scene user can fight a dragon
            System.out.println("You see a Dragon!");
            String symbol="";
            try{
                BufferedReader drg=new BufferedReader(new FileReader("src/dragon.txt"));
                while((symbol= drg.readLine())!=null){
                    System.out.println(symbol);
                }

            }catch(IOException e){
                System.out.println(e.getMessage());
            }
            System.out.println();

            System.out.println("Fight the Dragon? yes or no?");
            Scanner drgn=new Scanner(System.in);
            String answ= drgn.nextLine();

            if(answ.equals("yes")) {
                int win;
                System.out.println("You are fighting a dragon...");
                Random rand=new Random();
                win=rand.nextInt(10); //randomizer check whether user wins or looses by picking a number up to 10
                try {
                    Thread.sleep(1000); //just to give some time before prompting anything
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                //System.out.println(win);
                if(win<5){ //user looses, program will exit and will show the score before exiting
                    System.out.println("You lost!!! END OF GAME!");
                    System.out.println((score>0?"You beat "+score+" dragon(s)!":"You didn't beat any dragons"));
                    exit(0);
                }
                else{ //score will grow
                    System.out.println("Congrats!!! You just beat the dragon! Now you can keep moving!");
                    score+=1;
                }


            }else if(answ.equals("no")){
                AtomicInteger i= new AtomicInteger();
                do {

                    System.out.println("What would you like to do? (run, hide, fly)");
                    Scanner resp = new Scanner(System.in);
                    String answr = resp.nextLine();
                    response.forEach((k, v) -> {
                        if (answr.equals(k)) {
                            if(k.equals("fly")){
                                i.set(1);
                            }
                            System.out.println(v);
                            System.out.println();
                        }
                    });
                } while(i.get() <1);

            }
        }
    }



}




