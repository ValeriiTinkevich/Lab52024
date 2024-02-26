package commands;

import exceptions.MustNotBeEmptyException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;

/**
 * Command that removes element from collection with given id.
 */
public class RemoveByIdCommand extends AbstractCommand {
    CollectionManager collectionManager;

    /**
     * Remove_by_id command constructor.
     * @param collectionManager Collection manager for remove_by_id command.
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "Removes element by id");
        this.collectionManager = collectionManager;
    }

    /**
     * Removes element by id from collection.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            int id = Integer.parseInt(argument);
            if (collectionManager.getById(id) == null) throw new MustNotBeEmptyException();
            collectionManager.removeByIDFromCollection(id);
            Console.println("Successfully removed the element");
            return true;

        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
        } catch (MustNotBeEmptyException e) {
            Console.printError("No space marine with this id");
        } catch (NumberFormatException e) {
            Console.printError("The id value must be int!");
        }
        return false;
    }
}
