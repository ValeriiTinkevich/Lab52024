package commands;

import managers.CollectionManager;
import managers.FileManager;

/**
 * Command that saves collection to a csv file.
 */
public class SaveCommand extends AbstractCommand{
    FileManager fileManager;
    CollectionManager collectionManager;

    /**
     * Save command constructor.
     * @param collectionManager Collection manager for save command.
     * @param fileManager File manager for save command.
     */
    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        super("save", "Saves collection to a file.");
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }
    /**
     * Saves collection to a csv file.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        fileManager.saveCollection(collectionManager.getSpaceMarineCollection());
        return true;
    }
}
