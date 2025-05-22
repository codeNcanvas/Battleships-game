import java.util.ArrayList;

public class StartupBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<Startup>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        //first make some startups and give them locations
        Startup one = new Startup();
        one.setName("poniez");
        Startup two = new Startup();
        two.setName("hacqi");
        Startup three = new Startup();
        three.setName("cabista");
        startups.add(one);
        startups.add(two);
        startups.add(three);

        System.out.println("Your goal is to sink three startups");
        System.out.println("poniez,hacqi,cabista");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for (Startup startup : startups) {
            ArrayList<String> newLocation = helper.placeStartup(3);
            startup.setLocationCells(newLocation);
        }
    }

    private void startPlaying() {
        while (!startups.isEmpty()) {
            String userGuess = helper.getUserInput("enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "miss";
        for (Startup startupToTest : startups) {
            result = startupToTest.checkYourself(userGuess);
            if (result.equals("hit")) {
                break;
            }
            if (result.equals("kill")) {
                startups.remove(startupToTest);
                break;
            }
        }
        System.out.println(result);

    }
    
    private void finishGame() {
        System.out.println("all startups are dead! your stock is now worthless");
        if (numOfGuesses <= 18) {
            System.out.println("it only took you " + numOfGuesses + " guesses.");
            System.out.println("you got out before your options sank");
        } else {
            System.out.println("took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("fish are dacing with your options");
        }
    }

    public static void main(String[] args) {
        StartupBust game = new StartupBust();
        game.setUpGame();
        game.startPlaying();
    }

}
