package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.models.Property.PropertyBuilder;
import ar.edu.itba.paw.models.User;

import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

final class PropertyMapper implements ResultSetExtractor<List<Property>> {

    public List<Property> extractData(ResultSet rs) throws SQLException {
    	
        List<Property> propertyList = new ArrayList<Property>();
        
        int auxId = -1;
        Property p = null;
        
        while (rs.next()) {
            int propertyId = rs.getInt("id");
            
            if(auxId != propertyId) {
            	
            	User publisher = User.CreateUser(rs.getLong("user_id"), rs.getString("name"), rs.getString("password"), rs.getString("phone"), rs.getString("mail"), rs.getString("imagesrc"));
            	
            	PropertyBuilder pb = new PropertyBuilder(publisher,
						rs.getString("street"),
						rs.getInt("number"),
						rs.getLong("price"),
						OperationType.valueOf(rs.getString("operation_type"))
				);
            	p = pb.id(rs.getInt("id"))
                  .floor(rs.getInt("floor"))
                  .apartment(rs.getString("apartment"))
                    .Neighborhood(rs.getString("neighborhood"))
                  .type(PropertyType.valueOf(rs.getString("type")))
                  .coveredArea(rs.getInt("covered_area"))
                  .totalArea(rs.getInt("total_area"))
                  .rooms(rs.getInt("rooms"))
                  .baths(rs.getInt("baths"))
                  .garage(rs.getBoolean("garage"))
                  .taxPrice(rs.getInt("tax_price"))
                    .AdMessage(rs.getString("ad_message"))
                    .AdDescription(rs.getString("ad_description"))
                    .AdDate(rs.getDate("ad_date"))
                    .InmediateDelivery(rs.getBoolean("inmediate_delivery"))
                  .build();
                
            	propertyList.add(p);
                
            	auxId = propertyId;
            }
        }

        return propertyList;
    }
    
}