package services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import models.User;
import repositories.user.UserRepository;

public class UserService {

	@Inject
	private UserRepository repository;
	
	public User getUser(int userId) {
		return repository.getUser(userId);
	}
	
	public void changeUserStatus(int userId) {
		repository.changeUserStatus(userId);
	}
	
	public User getUserByEmail(String email) {
		return repository.getUserByEmail(email);
	}
	
	public List<User> getUsers(int page) {
		return repository.getUsers(page);
	}
	
	public User createUser(String email, String name, String surname, String password, boolean isAdmin) {
		return repository.createUser(email, name, surname, password, isAdmin);
	}
	
	public User updateUser(int userId, String email, String name, String surname, boolean isAdmin) {
		return repository.updateUser(userId, email, name, surname, isAdmin);
	}
	
    public String login(String email, String password) {
        User user = repository.getUserByEmail(email);
        
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");


        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("isAdmin", user.isAdmin())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        
        String email = jwt.getSubject();
        User user = repository.getUserByEmail(email);

        return user != null;
    }
}
