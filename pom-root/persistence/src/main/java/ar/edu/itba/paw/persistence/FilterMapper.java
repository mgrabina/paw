package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.FilterType;
import ar.edu.itba.paw.models.User;
import org.omg.CORBA.INTERNAL;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

final class FilterMapper implements ResultSetExtractor<Map<Integer, TreeSet<Map.Entry<String, Integer>>>>{

	public Map<Integer, TreeSet<Map.Entry<String, Integer>>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Integer, TreeSet<Map.Entry<String, Integer>>> map = new HashMap<Integer, TreeSet<Map.Entry<String, Integer>>>();
        for (int i = 0; i < FilterType.values().length ; i++){
            map.put(i, new TreeSet<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            }));
        }
        while (resultSet.next()) {
            map.get(resultSet.getInt("type")).add(new TreeMap.SimpleEntry<String, Integer>(resultSet.getString("name"), resultSet.getInt("count")));
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