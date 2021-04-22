package org.ssor.boss.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.ssor.boss.transfer.ApiRequestResponse;
import org.ssor.boss.transfer.RegisterUserInput;
import org.ssor.boss.transfer.RegisterUserOutput;
import org.ssor.boss.transfer.SecureUserDetails;
import org.ssor.boss.transfer.UpdateUserInput;
import org.ssor.boss.entity.User;
import org.ssor.boss.repository.UserRepository;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.ssor.boss.entity.UserType.USER_DEFAULT;

/**
 * A service for working with {@link User}s.
 * <p>
 *   The user service is a middleware for performing specific operations on users. It's paramount in the security system
 *   for providing a user that can be logged in if they exist. It's capable of performing CRUD operations on users with
 *   extra logic for validation and extra steps if more than one thing needs to be updated from the service.
 * </p>
 */
@Service
@Validated
@AllArgsConstructor
public class UserService implements UserDetailsService
{
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
  {
    // Try to find user with username or email.
    final var result = userRepository.getUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    if (result.isEmpty())
      throw new UsernameNotFoundException("User with username or email not found");
    return result.get();
  }

  /**
   * Registers a new user given the register user input.
   *
   * @param registerUserInput The input necessary for registering a new user.
   * @param created The time the user was registered at.
   * @return The result of registering a new user.
   */
  public RegisterUserOutput registerNewUser(@Valid @NotNull RegisterUserInput registerUserInput,
                                            @NotNull LocalDateTime created)
  {
    final var username = registerUserInput.getUsername();
    final var email = registerUserInput.getEmail();
    final var password = registerUserInput.getPassword();
    final var user = new User(null, USER_DEFAULT, 1, username, email, password, created, null, false, false);
    final var result = userRepository.save(user);
    final var output = new RegisterUserOutput();

    output.setId(result.getId());
    output.setTypeId(result.getType().index());
    output.setBranchId(result.getBranchId());
    output.setUsername(result.getUsername());
    output.setEmail(result.getEmail());
    output.setCreated(result.getCreated());
    return output;
  }

  /**
   * Updates a user profile with the new information.
   *
   * @param updateUserInput The new information for the user profile.
   * @return
   */
  public ApiRequestResponse updateUserProfile(@Valid @NotNull UpdateUserInput updateUserInput)
  {
    return null;
  }

  /**
   * Gets the details of an unsecured user.
   * <p>
   *   This function is provided for getting all details of a user through the gateway by a Administrator or the client
   *   user. The {@link User} contains all information about the user's profile. It can be used to get the holder
   *   information as well.
   * </p>
   *
   * @param id The id of the user.
   * @return The user stored in an optional if successful, otherwise an empty optional.
   */
  public Optional<User> getUnsecureUserDetailsWithId(int id)
  {
    return userRepository.findById(id);
  }

  /**
   * Gets the details of a secured user.
   * <p>
   *   This function is provided for getting limited details of a user through the gateway by a vendor application. The
   *   {@link SecureUserDetails} contains only necessary information. It lacks the ability to expose things like: email,
   *   password, whether the user profile is enabled, and whether the user profile is locked. It's more of a manner to
   *   check if a user exists rather than provide anymore information about them.
   * </p>
   *
   * @param id The id of the user.
   * @return The user stored in an optional if successful, otherwise an empty optional.
   */
  public Optional<SecureUserDetails> getSecureUserDetailsWithId(int id)
  {
    final var possibleUser = userRepository.findById(id);
    if (possibleUser.isEmpty())
      return Optional.empty();

    final var secureUserDetails = new SecureUserDetails(possibleUser.get());
    return Optional.of(secureUserDetails);
  }

  /**
   * Deletes a user with the given id.
   * <p>
   *   This function should only be available to administrators as it completely deletes the user. That is, it will
   *   remove the row in the database containing the user. By default, users cannot be permanently deleted in our
   *   application. Instead, their <code>deleted</code> property is set.
   * </p>
   *
   * @param id The id of the user to delete.
   */
  public void permanentlyDeleteUserWithId(int id)
  {
    userRepository.deleteById(id);
  }

  /**
   * Deletes a user with a given id.
   * <p>
   *   Because of how our application works, this merely sets the <code>deleted</code> property of the user to a new
   *   datetime. This way the user can be recovered if deleted accidentally.
   * </p>
   *
   * @param id The id of the user to delete.
   */
  public void deleteUserWithId(int id)
  {
    // Prep delete time.
    final var deleted = LocalDateTime.now();
    final var possibleUser = userRepository.findById(id);
    if (possibleUser.isEmpty())
      return;

    // Get user, update deleted property.
    final var user = possibleUser.get();
    user.setDeleted(deleted);
    userRepository.save(user);
  }
}
