package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.entity.UserEntity;
import org.ssor.boss.core.entity.UserType;
import java.io.Serializable;

/**
 * Provides details about a user in a safe manner.
 *
 * <p>
 * Because vendors can get information about a user, we must provide a secure way of giving them that information. The
 * information is generally limited. The results of getting a secure user is mostly for checking that a user exists,
 * more so than getting that user's information.
 *
 * @author John Christman
 */
// TODO: fix this.
public class SecureUserDetails implements Serializable {
	private int id;
	private UserType type;
	private int branchId;
	private String username;
	private long created;
	private Long deleted;

	/**
	 * Create {@link SecureUserDetails} from a {@link UserEntity}.
	 *
	 * @param user The user to create from.
	 */
	public SecureUserDetails(UserEntity user) {
//		id = user.getId();
//		type = user.getType();
//		branchId = user.getBranchId();
//		username = user.getUsername();
//		created = user.getCreated();
//		deleted = user.getDeleted();
	}
}
