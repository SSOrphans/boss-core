package org.ssor.boss.core.utility;

/**
 * Defines the masks for the different bit flags of an entity.
 *
 * <p>
 * This is designed to provide ease of use of the entity flags and provide a system for checking various information
 * about an entity based on its flags. This information relates to state of the entity. For example, if the entity is
 * locked, the 3rd bit will be set in the flags. This class is able to determine and set that.
 *
 * @author John Christman
 */
public final class EntityFlags {
	public static final byte CONFIRMED_MASK = 1 << 1;
	public static final byte ENABLED_MASK = 1 << 2;
	public static final byte LOCKED_MASK = 1 << 3;

	/**
	 * Checks that the flags has the 'confirmed' bit set.
	 *
	 * @param flags The flags to mask.
	 * @return True if the confirmed bit is set, false otherwise.
	 */
	public static boolean isConfirmed(byte flags) {
		return (flags & CONFIRMED_MASK) != 0;
	}

	/**
	 * Checks that the flags has the 'enabled' bit set.
	 *
	 * @param flags The flags to mask.
	 * @return True if the enabled bit is set, false otherwise.
	 */
	public static boolean isEnabled(byte flags) {
		return (flags & ENABLED_MASK) != 0;
	}

	/**
	 * Checks that the flags has the 'locked' bit set.
	 *
	 * @param flags The flags to mask.
	 * @return True if the locked bit is set, false otherwise.
	 */
	public static boolean isLocked(byte flags) {
		return (flags & LOCKED_MASK) != 0;
	}

	/**
	 * Sets the 'confirmed' bit in the user flags.
	 *
	 * @param flags The flags to manipulate.
	 * @return The adjusted flags.
	 */
	public static byte setConfirmed(byte flags) {
		return (byte)(flags | CONFIRMED_MASK);
	}

	/**
	 * Sets the 'enabled' bit in the user flags.
	 *
	 * @param flags The flags to manipulate.
	 * @return The adjusted flags.
	 */
	public static byte setEnabled(byte flags) {
		return (byte)(flags | ENABLED_MASK);
	}

	/**
	 * Sets the 'locked' bit in the user flags.
	 *
	 * @param flags The flags to manipulate.
	 * @return The adjusted flags.
	 */
	public static byte setLocked(byte flags) {
		return (byte)(flags | LOCKED_MASK);
	}
}
