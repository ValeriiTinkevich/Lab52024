package collections;

/**
 * Melee weapon enum.
 */
public enum MeleeWeapon {
    MANREAPER,
    LIGHTNING_CLAW,
    POWER_FIST;

    /**
     * Method that shows enum list.
     * @return enum list in string format.
     */
    public static String nameList() {
    StringBuilder nameList = new StringBuilder();
    for (MeleeWeapon category : values()) {
        nameList.append(category.name()).append(", ");
    }
    return nameList.substring(0, nameList.length() - 2);
}
}
