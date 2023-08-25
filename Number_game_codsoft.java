import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

// Gues the number Game is a simple game where the computer will generate a random number between 1-100 and
// will get 5-10 chances to guess the number correctly , score will be alloted accordin to the number of
// attempts taken (I would be taking reference from one of my previous project)

public class Number_game_codsoft {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("------------------------------------------------");
            System.out.println("------- WELCOME to GUESS THE NUMBER GAME -------");
            System.out.println("------------------------------------------------");
            System.out.println("RULES\n 1) Computer will generate a random number between 1 to 100\n 2) You have to guess the number within 10 chances\n 3) Your scores will be evaluated according to the number of chances you take\n");
            Random rand = new Random();
            int random = rand.nextInt(99);
            //System.out.println(random);
            int chance = 10;
            while (true) {

                System.out.println("Guess the random number ");
                int answer = sc.nextInt();
                if (answer == random && chance > 0){
                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
                    System.out.println("CONGRATULATIONS !!! your answer is correct and your score is "+chance+" out of 10, Play again ? y/n");
                    System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
                    break;
                }

                else if (answer>random && chance > 0) {
                    System.out.println("> > > > > > > > > > > > > > > > > > > > > > > > ");
                    System.out.println("Your answer is bigger than the correct answer...");
                    System.out.println("> > > > > > > > > > > > > > > > > > > > > > > > ");
                    chance--;
                }

                else if (answer<random && chance > 0) {
                    System.out.println("< < < < < < < < < < < < < < < < < < < < < < < < <");
                    System.out.println("Your answer is smaller than the correct answer...");
                    System.out.println("< < < < < < < < < < < < < < < < < < < < < < < < <");
                    chance--;
                }

                else
                {
                    System.out.println("^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^");
                    System.out.println("Oops you ran out of chances, Play again ? y/n");
                    System.out.println("^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^");

                    break;
                }
            }
            System.out.println("Play Again ? y/n");
            sc.nextLine();
            String playagain = sc.nextLine();
            if (Objects.equals(playagain, "n")){
                break;
            }
        }
    }
}
