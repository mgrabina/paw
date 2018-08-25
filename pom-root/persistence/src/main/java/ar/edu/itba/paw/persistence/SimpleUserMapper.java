package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

final class SimpleUserMapper implements ResultSetExtractor<List<User>> {

    public List<User> extractData(ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<User>();
        int aux=-1;
        User user=null;
        while (rs.next()) {
            int id = rs.getInt("id");
            if(aux!=id) {
                user= User.create(id, rs.getString("name"), rs.getString("mail"), rs.getString("hash_psw"));
                aux=id;
                userList.add(user);
            }
        }

        return userList;
    }
}