package commands;

import collections.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;
import managers.SpaceMarineInputManager;

/**
 * Command that updates element by id from user input.
 */
public class UpdateByIdCommand extends AbstractCommand {
    CollectionManager collectionManager;
    SpaceMarineInputManager spaceMarineInputManager;

    /**
     * Update command constructor
     * @param collectionManager Collection manager for update command.
     * @param spaceMarineInputManager Input manager for update command.
     */
    public UpdateByIdCommand(CollectionManager collectionManager, SpaceMarineInputManager spaceMarineInputManager) {
        super("update", "Updates element by id");
        this.collectionManager = collectionManager;
        this.spaceMarineInputManager = spaceMarineInputManager;

    }

    /**
     * Updates element by id from user input.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            long id = Integer.parseInt(argument);
            collectionManager.update(id, new SpaceMarine(
                    id,
                    spaceMarineInputManager.askName(),
                    spaceMarineInputManager.askCoordinates(),
                    spaceMarineInputManager.askCreationDate(),
                    spaceMarineInputManager.askHealth(),
                    spaceMarineInputManager.askHeartCount(),
                    spaceMarineInputManager.askHeight(),
                    spaceMarineInputManager.askMeleeWeapon(),
                    spaceMarineInputManager.askChapter()
            ));
            Console.println("Space marine updated successfully!");
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
        } catch (NumberFormatException e) {
            Console.printError("id must be Integer!");
        } catch (IncorrectInputInScriptException ignore) {
        } catch (IndexOutOfBoundsException e) {
            Console.printError("Element with this id does not exist!");
        }
        return false;
    }

}
