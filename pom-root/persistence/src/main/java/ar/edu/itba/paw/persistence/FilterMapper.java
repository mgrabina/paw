package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.FilterType;
import ar.edu.itba.paw.models.User;
import org.omg.CORBA.INTERNAL;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class FilterMapper implements ResultSetExtractor<ArrayList<Map<String, Integer>>> {

	public ArrayList<Map<String, Integer>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        ArrayList<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
        for (int i = 0; i < FilterType.values().length ; i++){
            list.add(i, new HashMap<String, Integer>());
        }
        while (resultSet.next()) {
            list.get(resultSet.getInt("type")).put(resultSet.getString("name"), resultSet.getInt("count"));
        }
        return list;
    }
//
//    public List<User> extractData(ResultSet rs) throws SQLException {
//        List<User> userList = new ArrayList<User>();
//        int aux=-1;
//        User user=null;
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            if(aux!=id) {
//                user= User.create(id, rs.getString("name"), rs.getString("mail"), rs.getString("hash_psw"));
//                aux=id;
//                userList.add(user);
//            }
//        }
//
//        return userList;
//    }
}