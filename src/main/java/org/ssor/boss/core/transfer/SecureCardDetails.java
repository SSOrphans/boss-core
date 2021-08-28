package org.ssor.boss.core.transfer;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.NonNull;
import org.ssor.boss.core.entity.CardEntity;
import org.ssor.boss.core.entity.CardType;
import org.ssor.boss.core.entity.LogicalCardInfo;
import org.ssor.boss.core.entity.PhysicalCardInfo;
import java.io.Serializable;

/**
 * Provides details about a user's card in a safe manner.
 *
 * <p>
 * Because vendors can get information about a user, we must provide a secure way of getting information about a card.
 * The information is generally limited. The results of getting a secure card is mostly for checking that a card exists,
 * more so than getting that user's information.
 *
 * @author John Christman
 */
@Data
public class SecureCardDetails implements Serializable {
	private long id;
	private String type;

	@JsonUnwrapped
	private PhysicalCardInfo physicalInfo;

	@JsonUnwrapped
	private LogicalCardInfo logicalInfo;

	/**
	 * Default constructs the secure card details.
	 */
	public SecureCardDetails() {
		id = 0;
		type = CardType.UNDEFINED.toString();
		physicalInfo = null;
		logicalInfo = null;
	}

	/**
	 * Create {@link SecureCardDetails} from a {@link CardEntity}.
	 *
	 * @param card The card to create from.
	 * @throws NullPointerException If the card entity is null.
	 */
	public SecureCardDetails(@NonNull CardEntity card) throws NullPointerException {
		id = card.getId();
		type = card.getType().toString();
		physicalInfo = card.getPhysicalInfo();
		logicalInfo = card.getLogicalInfo();
	}
}
