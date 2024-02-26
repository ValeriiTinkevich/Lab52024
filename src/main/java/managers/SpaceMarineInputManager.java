package managers;

import collections.Chapter;
import collections.Coordinates;
import collections.MeleeWeapon;
import exceptions.EmptyArgumentException;
import exceptions.IncorrectInputInScriptException;
import exceptions.NotInDeclaredLimitsException;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SpaceMarineInputManager {
    CollectionManager collectionManager;
    Scanner userScanner;
    private boolean scriptMode;

    /**
     * User scanner getter.
     * @return User scanner
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * User scanner setter
     * @param userScanner scanner that needs to be set.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * Input manager constructor
     * @param collectionManager Collection manager for input manager.
     * @param userScanner User scanner for input manager.
     */
    public SpaceMarineInputManager(CollectionManager collectionManager, Scanner userScanner) {
        this.collectionManager = collectionManager;
        this.userScanner = userScanner;
        this.scriptMode = false;
    }

    /**
     * Sets script mode.
     * Script mode is an input manager mode that listens for SpaceMarine object input from script.
     */
    public void setScriptMode() {
        this.scriptMode = true;
    }

    /**
     * Sets User mode.
     * User mode is an input manager mod that listens for SpaceMarine object input from user.
     */
    public void setUserMode() {
        this.scriptMode = false;
    }

    /**
     * Set id for element.
     * @return Id for element
     */
    public long setId() {
        return collectionManager.generateNewIdForCollection();
    }

    /**
     * Ask space marine name.
     * @return Name for space marine
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            Console.print("Enter name: ");
            try {
                name = userScanner.nextLine().trim();
                if (scriptMode) Console.println(name);
                if (name.equals("")) throw new EmptyArgumentException();
                break;
            } catch (EmptyArgumentException e) {
                Console.printError("The name can't be empty!");
                Console.println(e.getMessage());
                if (scriptMode) throw new IncorrectInputInScriptException();

            } catch (NoSuchElementException e) {
                Console.printError("The name can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused exit!");
                    System.exit(2);
                }
            } catch (IllegalStateException e) {
                Console.printError("Unexpected error!");
            }
        }
        return name;

    }
    /**
     * Ask X Coordinate.
     * @return X coordinate.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */
    private float askCoordX() throws IncorrectInputInScriptException {
        float x;
        while (true) {
            Console.print("Enter Coordinate X: ");
            try {
                String s = userScanner.nextLine().trim();
                if (scriptMode) Console.println(s);
                x = Float.parseFloat(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The X coordinate can't be loaded or recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused exit!");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                Console.printError("The X coordinate must be Float!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printError("Unexpected error!");
                if (scriptMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            }
        }
        return x;
    }
    /**
     * Ask Y Coordinate.
     * @return Y coordinate.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */

    private Double askCoordY() throws IncorrectInputInScriptException {
        Double y;
        while (true) {
            Console.print("Enter Coordinate Y: ");
            try {
                String s = userScanner.nextLine().trim();
                if (scriptMode) Console.println(s);
                y = Double.parseDouble(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Y coordinate can't be loaded or recognized");
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused exit!");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                Console.printError("The Y coordinate must be Double");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(2);
            }
        }
        return y;
    }
    /**
     * Asks for coordinates class.
     * @return coordinates class.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        float x;
        double y;
        x = askCoordX();
        y = askCoordY();
        return new Coordinates(x, y);
    }
    /**
     * Asks for chapter name.
     * @return Chapter name.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */
    private String askChName() throws IncorrectInputInScriptException {
        String chName;
        while (true) {
            Console.print("Enter chapter name: ");
            try {
                String s = userScanner.nextLine().trim();
                if (scriptMode) Console.println(s);
                chName = s;
                if (chName == null || chName.isEmpty()) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Y coordinate can't be loaded or recognized");
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused exit!");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                Console.printError("The Y coordinate must be Double");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(2);
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("Not in declared limits or null!");
            }
        }
        return chName;
    }

    /**
     * Asks for chapter world.
     * @return Chapter world.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */
    private String askWorld() throws IncorrectInputInScriptException {
        String world;
        while (true) {
            Console.print("Enter chapter world: ");
            try {
                String s = userScanner.nextLine().trim();
                if (scriptMode) Console.println(s);
                world = s;
                if (world == null) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The world name coordinate can't be loaded or recognized");
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused exit!");
                    System.exit(2);
                }
            }
             catch (NullPointerException | IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(2);
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("Not in declared limits or null!");
            }
        }
        return world;
    }
    /**
     * Asks for chapter class.
     * @return Chapter class.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */

    public Chapter askChapter() throws IncorrectInputInScriptException {
        String name;
        String world;
        name = askChName();
        world = askWorld();
        return new Chapter(name, world);
    }
    /**
     * Asks for health value.
     * @return health value.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */

    public Integer askHealth() throws IncorrectInputInScriptException {
        int health;
        while (true) {
            Console.print("Enter Health value: ");
            try {
                String s = userScanner.nextLine().trim();
                if (scriptMode) Console.println(s);
                health = Integer.parseInt(s);
                if (health <= 0) {
                    throw new NotInDeclaredLimitsException();
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Health value or recognized");
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused exit!");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                Console.printError("The Health value must be Integer");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(2);
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("Not in declared limits or null! (Health > 0)");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        return health;
    }

    /**
     * Asks for heart count value.
     * @return heart count value.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */

    public Long askHeartCount() throws IncorrectInputInScriptException {
        long heartcount;
        while (true) {
            Console.print("Enter Heart count value: ");
            try {
                String s = userScanner.nextLine().trim();
                if (scriptMode) Console.println(s);
                heartcount = Long.parseLong(s);
                if (heartcount <= 0 || heartcount > 3) {
                    throw new NotInDeclaredLimitsException();
                }
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Heart count value not recognized");
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused exit!");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                Console.printError("The Heart count value must be Long");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(2);
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("Not in declared limits or null! (Heart count must be greater than 0, but lower than 3 )");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        return heartcount;
    }

    /**
     * Asks for height value.
     * @return height value.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */

    public Long askHeight() throws IncorrectInputInScriptException {
        long height;
        while (true) {
            Console.print("Enter Height value: ");
            try {
                String s = userScanner.nextLine().trim();
                if (scriptMode) Console.println(s);
                height = Long.parseLong(s);
                break;
            } catch (NoSuchElementException e) {
                Console.printError("The Height value not recognized");
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused exit!");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                Console.printError("The Height value must be Long");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException e) {
                Console.printError("Unexpected error!");
                System.exit(2);
            }
        }
        return height;
    }

    /**
     * Generates creation time.
     * @return creation time.
     */

    public ZonedDateTime askCreationDate() {
        while (true) {
            try {
                return ZonedDateTime.now();
            } catch (DateTimeException e) {
                Console.printError("Problem with date and time");
            }
        }
    }
    /**
     * Asks for MeleeWeapon value.
     * @return MeleeWeapon value.
     * @throws IncorrectInputInScriptException If input in script is incorrect
     */


    public MeleeWeapon askMeleeWeapon() throws IncorrectInputInScriptException {
        MeleeWeapon meleeWeapon;
        while (true) {
            try {
                Console.println("Melee weapons: " + MeleeWeapon.nameList());
                Console.print("Enter the Melee weapon: ");
                String type = userScanner.nextLine().trim();
                if (scriptMode) Console.println(type);
                meleeWeapon = MeleeWeapon.valueOf(type.toUpperCase());
                break;
            } catch (NoSuchElementException e) {
                Console.printError("Melee weapon can't be recognized");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    Console.printError("Ctrl + D Caused an exit!");
                    System.exit(2);
                }
            } catch (IllegalArgumentException e) {
                Console.printError("Melee weapon you entered is not a category");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        return meleeWeapon;
    }







}

