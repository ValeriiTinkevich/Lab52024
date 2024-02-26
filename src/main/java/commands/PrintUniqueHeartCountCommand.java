package commands;

import collections.SpaceMarine;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Shows space marines with unique heart count.
 */
public class PrintUniqueHeartCountCommand extends  AbstractCommand{
    CollectionManager collectionManager;

    /**
     * Print_unique_heart_count command constructor.
     * @param collectionManager Collection manager for print_unique_heart_count command.
     */
    public PrintUniqueHeartCountCommand(CollectionManager collectionManager) {
        super("print_unique_heart_count", "Shows space marines with unique heart count");
        this.collectionManager = collectionManager;
    }
    /**
     * Shows space marines with unique heart count.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            if(collectionManager.getSpaceMarineCollection().isEmpty()){
                Console.println("Collection is empty!");
                return false;
            }
            for (int i = 1; i <= 3; i++ ) {
                int finalI = i;
                if (collectionManager.getSpaceMarineCollection()
                        .stream()
                        .filter(n -> n.getHeartCount() == finalI).count() == 1) {
                    SpaceMarine spaceMarine = collectionManager.getSpaceMarineCollection()
                            .stream()
                            .filter(n -> n.getHeartCount() == finalI).findFirst().orElseThrow(UnknownError::new);
                    Console.println(spaceMarine.toString() + "\n===============");
                }
            }

            return true;

        } catch (WrongAmountOfArgumentsException e) {
            Console.printError(e.getMessage());
            return false;
        }
    }
}
