package com.predatum.sbsla.listener;

import com.predatum.sbsla.entity.User;
import com.predatum.sbsla.repository.UserRepository;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

/**
 *
 * @author Marcos Peña
 */
public class UserRepositoryEventListener extends AbstractRepositoryEventListener<User>
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onBeforeSave(User user)
    {
        this.saveUserPassword(user);

    }

    @Override
    public void onAfterSave(User customer)
    {

    }

    private void saveUserPassword(User user)
    {
        this.generatePassword(user, user.getPassword());
        userRepository.save(user);
    }

    private void generatePassword(User user, String plainTextPassword)
    {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        Object salt = randomNumberGenerator.nextBytes();

        // Now hash the plain-text password with the random salt and multiple
        // iterations and then Base64-encode the value (requires less space than
        // Hex):
        String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt,
                1024).toBase64();

        user.setPassword(hashedPasswordBase64);
        user.setSalt(salt.toString());
    }
}
