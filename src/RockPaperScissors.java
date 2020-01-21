import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private int winstreak = 0;
    private int highestWinstreak = winstreak;

    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        boolean gameLoop = true;
        while(gameLoop){
            System.out.println("======================");
            System.out.println("ROCK, PAPER, SCISSORS!");
            System.out.println("Highest winning streak in current session: " + highestWinstreak);
            System.out.println("======================");
            System.out.println("1. How to play?");
            System.out.println("2. Play!");
            System.out.println("3. Exit.");
            switch (scanner.next().toUpperCase()){
                case "1":
                case "HOW TO PLAY":
                    System.out.println(
                            "To start game go to main menu and type '1' or 'play'. Next you will be asked about your pick.\n" +
                                    "choose pick by typing number from 1 to 3 or 'paper', 'rock', 'scissors'.\n" +
                                    "After each round program will give you option to begin new round. Good luck!");
                    break;
                case "2":
                case "PLAY":
                    boolean isPlaying = true;
                    while(isPlaying) isPlaying = newRound(scanner);
                    break;
                case "3":
                case "EXIT":
                default:
                    gameLoop = false;
                    break;
            }
        }
    }

    private boolean newRound(Scanner scanner){
        Option computer = computerMove();
        Option human = humanMove(scanner);
        if(computer.equals(human)) System.out.println("Tie!");
        else {
            if(checkWinner(computer, human)){
                System.out.println("You won! (Computer picked " + computer + ")");
                winstreak++;
            }
            else{
                System.out.println("You lost! (Computer picked " + computer + ")");
                winstreak = 0;
            }
        }
        if(winstreak > highestWinstreak) highestWinstreak = winstreak;
        System.out.println("Play again? Y/N");
        return scanner.next().toUpperCase().equals("Y");
    }

    private Option computerMove(){
        Random random = new Random();
        return Option.values()[random.nextInt(3)];
    }

    private Option humanMove(Scanner scanner){
        System.out.println("Pick:");
        System.out.println("1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");
        switch(scanner.next().toUpperCase()){
            case "2":
            case "PAPER":
                return Option.PAPER;
            case "3":
            case "SCISSORS":
                return Option.SCISSORS;
            case "1":
            case "ROCK":
            default:
                return Option.ROCK;
        }
    }

    private boolean checkWinner(Option computerOption, Option humanOption){
        switch(humanOption){
            case PAPER:
                return computerOption.equals(Option.ROCK);
            case ROCK:
                return computerOption.equals(Option.SCISSORS);
            case SCISSORS:
                return computerOption.equals(Option.PAPER);
            default:
                return false;
        }
    }
}