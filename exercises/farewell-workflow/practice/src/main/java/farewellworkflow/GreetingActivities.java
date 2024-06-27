package farewellworkflow;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface GreetingActivities {

    String greetInSpanish(String name);

    // DONE
    String farewellInSpanish(String name);
    
    /* TODO (DONE): Define a method signature for a method that will get a farewell
     * message in Spanish. You can name your method whatever you like, but remember
     * the name as you'll need to implement it in a later step.
    */
}
