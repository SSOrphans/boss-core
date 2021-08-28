package org.ssor.boss.core.entity;

/**
 * An enumeration to describe the different presentation types of things.
 *
 * <p>
 * The presentation type enumeration is mostly used for presenting phone numbers in their correct format for consistency
 * whilst only needing to store the actual numbers in the database.
 *
 * @author John Christman
 */
public enum PresentationType {
	GENERIC,
	US_STANDARD
}
