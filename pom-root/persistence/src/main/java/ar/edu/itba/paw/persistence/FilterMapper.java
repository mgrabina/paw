package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.FilterType;
import ar.edu.itba.paw.models.User;
import org.omg.CORBA.INTERNAL;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

final class FilterMapper implements ResultSetExtractor<Map<Integer, Map<String, Integer>>>{

	public Map<Integer, Map<String, Integer>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Integer, Map<String, Integer>> map = new HashMap<Integer, Map<String, Integer>>();
        for (int i = 0; i < FilterType.values().length ; i++){
            map.put(i, new HashMap<String, Integer>());
        }
        while (resultSet.next()) {
            map.get(resultSet.getInt("type")).put(resultSet.getString("name"), resultSet.getInt("count"));
        }
        map.entrySet().stream().forEach(integerMapEntry -> integerMapEntry.setValue(
                integerMapEntry.getValue().entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                        collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (integer, integer2) -> integer2,
                        LinkedHashMap::new))
        ));
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