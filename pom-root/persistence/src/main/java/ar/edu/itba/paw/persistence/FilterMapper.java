package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.FilterType;
import ar.edu.itba.paw.models.User;
import org.omg.CORBA.INTERNAL;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

final class FilterMapper implements ResultSetExtractor<Map<Integer, Map<Integer, String>>> {

	public Map<Integer, Map<Integer, String>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Integer, Map<Integer, String>> map = new HashMap<Integer, Map<Integer, String>>();
        for (int i = 0; i < FilterType.values().length ; i++){
            map.put(i, new TreeMap<Integer, String>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            }));
        }
        while (resultSet.next()) {
            map.get(resultSet.getInt("type")).put(resultSet.getInt("count"), resultSet.getString("name"));
        }
        return map;
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