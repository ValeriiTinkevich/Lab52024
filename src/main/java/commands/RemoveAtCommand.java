package commands;

import exceptions.MustNotBeEmptyException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;

/**
 * Command that removes element at given index.
 */
public class RemoveAtCommand extends AbstractCommand{
    CollectionManager collectionManager;

    /**
     * Remove_at command constructor.
     * @param collectionManager Collection manager for remove_at command.
     */
    public RemoveAtCommand(CollectionManager collectionManager) {
        super("remove_at", "Removes an element by its index");
        this.collectionManager = collectionManager;
    }
    /**
     * Removes element at index from collection.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            int index = Integer.parseInt(argument);
            collectionManager.removeAtIndex(index);
            Console.println("Successfully removed the element");
            return true;

        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Console.printError("Index out of bounds!");
        } catch (NumberFormatException e) {
            Console.printError("The id value must be int!");
        }
        return false;
    }
}

