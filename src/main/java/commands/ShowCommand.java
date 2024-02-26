package commands;

import collections.SpaceMarine;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;

/**
 * Command that displays collection elements for user.
 */
public class ShowCommand extends AbstractCommand {
    CollectionManager collectionManager;

    /**
     * Show command constructor.
     * @param collectionManager Collection manager for show command.
     */
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "Displays collection elements as Strings");
        this.collectionManager = collectionManager;
    }
    /**
     * Displays collection.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            if (collectionManager.getSpaceMarineCollection().size() == 0) {
                Console.println("Collection is empty.");
            } else {
                for (SpaceMarine spaceMarine : collectionManager.getSpaceMarineCollection()) {
                    Console.println(spaceMarine.toString() + "\n===============");

                }
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
        }
        return false;
    }

}
