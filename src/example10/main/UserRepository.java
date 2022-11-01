package example10.main;

public interface UserRepository {
    User findById(String id); //a finder to find a user by its identifier
}