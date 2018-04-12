package com.foodservice.mapper;

import java.util.List;

import com.foodservice.model.Restaurent;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface RestaurenDTO {
	@SqlQuery("SELECT * FROM restaurent")
	@Mapper(RestaurentMapper.class)
	List<Restaurent> getall();
	
	@SqlUpdate("insert into restaurent (restaurentName, contactNumber,cityName,stateName,pin, menuId)"
			+ " values (:rName,:cNo,:city,:state,:pin,:mId)") 
	void insertRestaurent(@Bind("rName") String name ,@Bind("cNo") String contactNumber,
			@Bind("city")String cityName , @Bind("state")String stateName ,
			@Bind("pin") String pin,@Bind("mId")String menuId );
}