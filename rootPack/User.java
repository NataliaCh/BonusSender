package rootPack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {

    public final String email;

    private final List<String> args = new ArrayList<String>();

    public User(String email, ResultSet rs, int count) throws SQLException {
        this.email = email;

        for (int index = 1; index < count; index++)
            args.add(rs.getString(index));
    }

    public ArrayList<String> toArgs() {
        return new ArrayList<String>(args);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", args=").append(args);
        sb.append('}');
        return sb.toString();
    }
}