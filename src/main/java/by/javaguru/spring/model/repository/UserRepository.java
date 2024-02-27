package by.javaguru.spring.model.repository;

import by.javaguru.spring.model.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class UserRepository implements CrudRepository<Integer, User> {
    private final static String FIND_BY_ID_SQL = """ 
            SELECT id, username, email
            FROM users
            WHERE id = ?""";

    private final static String SAVE_SQL = """
            INSERT INTO users (username, email)
            VALUES (?, ?)
            """;
    private final Connection connection;
    public UserRepository(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Optional<User> findById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setInt(1, id);
            var result = statement.executeQuery();
            User user = null;
            if (result.next()) {
                user = User.builder()
                        .id(result.getInt("id"))
                        .username(result.getString("username"))
                        .email(result.getString("email"))
                        .build();
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User save(User user) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            keys.next();
            user.setId(keys.getObject("id", Integer.class));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
