package org.ssor.boss.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.ssor.boss.core.entity.UserEntity;

/**
 * A service for working with {@link UserEntity}s.
 * <p>
 *   The user service is a middleware for performing specific operations on users. It's paramount in the security system
 *   for providing a user that can be logged in if they exist. It's capable of performing CRUD operations on users with
 *   extra logic for validation and extra steps if more than one thing needs to be updated from the service.
 * </p>
 *
 * @author John Christman
 */
// TODO: fix all of this.
@Slf4j
@Service
@Validated
@AllArgsConstructor
public class UserService /*implements UserDetailsService*/ {
//  private final UserEntityRepository userEntityRepository;
//  private final PasswordEncoder passwordEncoder;
//
//  @Override
//  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
//  {
//    // Try to find user with username or email.
//    final var result = userEntityRepository.getUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
//    if (result.isEmpty())
//      throw new UsernameNotFoundException("User with username or email not found");
//
//    final var user = result.get();
//    if (user.getDeleted() != null)
//      throw new UsernameNotFoundException("User with username or email not found");
//
//    log.debug("Loaded user: " + user);
//    return result.get();
//  }
//
//  /**
//   * Obtains all users in an unsecure format.
//   * <p>
//   *   Gets all the users from the repository without converting them to a {@link SecureUserDetails}. This function is
//   *   designed to be used by an administration request and not a vendor request.
//   * </p>
//   *
//   * @return All the current users from the repository.
//   */
//  public List<User> getAllUsersUnsecure()
//  {
//    return userEntityRepository.findAll();
//  }
//
//  /**
//   * Obtains all users in a secure format.
//   * <p>
//   *   Gets all the users from the repository and converts them to a {@link SecureUserDetails}. This function allows
//   *   non-administrative users to get information about other users.
//   * </p>
//   *
//   * @return All the current users from the repository converted to {@link SecureUserDetails}.
//   */
//  public List<SecureUserDetails> getAllUsersSecure()
//  {
//    final var users = userEntityRepository.findAll();
//    return users.stream().map(SecureUserDetails::new).collect(Collectors.toUnmodifiableList());
//  }
//
//  /**
//   * Registers a new user given the register user input.
//   *
//   * @param registerUserInput The input necessary for registering a new user.
//   * @param created The time the user was registered at.
//   * @return The result of registering a new user.
//   */
//  public RegisterUserOutput registerNewUser(@Valid @NotNull RegisterUserInput registerUserInput,
//                                            @NotNull Instant created) throws UserAlreadyExistsException
//  {
//    final var username = registerUserInput.getUsername();
//    final var email = registerUserInput.getEmail();
//    if (userEntityRepository.existsUserByUsernameOrEmail(username, email))
//      throw new UserAlreadyExistsException();
//
//    final var password = passwordEncoder.encode(registerUserInput.getPassword());
//    final var user = new User(null, UserType.USER_DEFAULT, 1, username, email, password, created.toEpochMilli(), null, false, false, new UserEntity());
//    final var result = userEntityRepository.save(user);
//    final var output = new RegisterUserOutput();
//    log.debug("Created new User: " + user);
//
//    output.setId(result.getId());
//    output.setTypeId(result.getType().index());
//    output.setBranchId(result.getBranchId());
//    output.setUsername(result.getUsername());
//    output.setEmail(result.getEmail());
//    output.setCreated(result.getCreated());
//    return output;
//  }
//
//  /**
//   * Confirms a user with the provided user id.
//   *
//   * @param userId The id of the user to confirm.
//   */
//  public void confirmUserWithId(int userId)
//  {
//    final var optionalUser = userEntityRepository.findById(userId);
//    if (optionalUser.isEmpty())
//      throw new NoSuchUserException(userId);
//
//    final var user = optionalUser.get();
//    if (user.getDeleted() != null)
//      throw new NoSuchUserException(userId);
//
//    log.debug("Confirmed user: " + user);
//    user.setEnabled(true);
//    userEntityRepository.save(user);
//  }
//
//  /**
//   * Updates a user profile with the new information.
//   *
//   * @param updateUserInput The new information for the user profile.
//   * @return An string detailing the transaction result.
//   */
//  public String updateUserProfile(@Valid @NotNull UpdateUserInput updateUserInput)
//    throws NoSuchUserException
//  {
//    final var userId = updateUserInput.getUserId();
//    final var optionalUser = userEntityRepository.findById(userId);
//    if (optionalUser.isEmpty())
//      throw new NoSuchUserException(userId);
//
//    final var user = optionalUser.get();
//    if (user.getDeleted() != null)
//      throw new NoSuchUserException(userId);
//
//    user.setUsername(updateUserInput.getUsername());
//    user.setEmail(updateUserInput.getEmail());
//    user.setPassword(updateUserInput.getPassword());
//    userEntityRepository.save(user);
//    return "User profile updated";
//  }
//
//  /**
//   * Gets the details of an unsecured user.
//   * <p>
//   *   This function is provided for getting all details of a user through the gateway by a Administrator or the client
//   *   user. The {@link User} contains all information about the user's profile. It can be used to get the holder
//   *   information as well.
//   * </p>
//   *
//   * @param id The id of the user.
//   * @return The user stored in an optional if successful, otherwise an empty optional.
//   */
//  public Optional<User> getUnsecureUserDetailsWithId(int id)
//  {
//    return userEntityRepository.findById(id);
//  }
//
//  /**
//   * Gets the details of a secured user.
//   * <p>
//   *   This function is provided for getting limited details of a user through the gateway by a vendor application. The
//   *   {@link SecureUserDetails} contains only necessary information. It lacks the ability to expose things like: email,
//   *   password, whether the user profile is enabled, and whether the user profile is locked. It's more of a manner to
//   *   check if a user exists rather than provide anymore information about them.
//   * </p>
//   *
//   * @param id The id of the user.
//   * @return The user stored in an optional if successful, otherwise an empty optional.
//   */
//  public Optional<SecureUserDetails> getSecureUserDetailsWithId(int id)
//  {
//    final var possibleUser = userEntityRepository.findById(id);
//    if (possibleUser.isEmpty())
//      return Optional.empty();
//
//    final var user = possibleUser.get();
//    if (user.getDeleted() != null)
//      return Optional.empty();
//
//    final var secureUserDetails = new SecureUserDetails(user);
//    return Optional.of(secureUserDetails);
//  }
//
//  /**
//   * Deletes a user with the given id.
//   * <p>
//   *   This function should only be available to administrators as it completely deletes the user. That is, it will
//   *   remove the row in the database containing the user. By default, users cannot be permanently deleted in our
//   *   application. Instead, their <code>deleted</code> property is set.
//   * </p>
//   *
//   * @param id The id of the user to delete.
//   */
//  public void permanentlyDeleteUserWithId(int id)
//  {
//    userEntityRepository.deleteById(id);
//  }
//
//  /**
//   * Deletes a user with a given id.
//   * <p>
//   *   Because of how our application works, this merely sets the <code>deleted</code> property of the user to a new
//   *   datetime. This way the user can be recovered if deleted accidentally.
//   * </p>
//   *
//   * @param id The id of the user to delete.
//   */
//  public void deleteUserWithId(int id)
//  {
//    // Prep delete time.
//    final var deleted = Instant.now().toEpochMilli();
//    final var possibleUser = userEntityRepository.findById(id);
//    if (possibleUser.isEmpty())
//      return;
//
//    // Get user, update deleted property.
//    final var user = possibleUser.get();
//    if (user.getDeleted() != null)
//      return;
//    user.setDeleted(deleted);
//    userEntityRepository.save(user);
//  }
}
