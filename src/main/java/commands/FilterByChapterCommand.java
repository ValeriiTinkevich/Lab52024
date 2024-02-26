package commands;

import collections.Chapter;
import collections.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;
import managers.SpaceMarineInputManager;

/**
 * Shows filtered collection by chapter.
 */
public class FilterByChapterCommand extends AbstractCommand{
    CollectionManager collectionManager;
    SpaceMarineInputManager spaceMarineInputManager;

    /**
     * Filter_by_chapter command constructor.
     * @param collectionManager Collection manager for filter_by_chapter command.
     * @param spaceMarineInputManager Input manager for filter_by_chapter command.
     */
    public FilterByChapterCommand(CollectionManager collectionManager, SpaceMarineInputManager spaceMarineInputManager) {
        super("filter_by_chapter", "Shows elements with chapter == input");
        this.collectionManager = collectionManager;
        this.spaceMarineInputManager = spaceMarineInputManager;
    }


    /**
     * Show filtered collection by chapter.
     *
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            Chapter askedChapter = spaceMarineInputManager.askChapter();
            for(SpaceMarine spaceMarine: collectionManager.getSpaceMarineCollection()) {
                if (spaceMarine.getChapter().equals(askedChapter)) {
                    Console.println(spaceMarine.toString() + "\n===============");
                }
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.printError(e.getMessage());
            return false;
        } catch (IncorrectInputInScriptException ignore) {

        }
        return false;
    }
}
