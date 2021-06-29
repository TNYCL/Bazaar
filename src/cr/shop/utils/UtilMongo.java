package cr.shop.utils;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import cr.api.CRAPI;
import cr.shop.module.Order;
import cr.shop.module.PlayerSettings;

public class UtilMongo {
	
	public static String SHOP_COLLECTION_NAME = "skyblock_playerdata_shop";
	public static String SETTINGS_COLLECTION_NAME = "skyblock_playerdata_settings";
	
	public static PlayerSettings getSettingsData(Query query) {
		return CRAPI.getMongo().getTemp().findOne(query, PlayerSettings.class, SETTINGS_COLLECTION_NAME);
	}
	
	public static void updateSettingsData(Query query, Update update) {
		CRAPI.getMongo().getTemp().updateFirst(query, update, SETTINGS_COLLECTION_NAME);
	}
	
	public static void insertSettingsData(PlayerSettings settings) {
		CRAPI.getMongo().getTemp().insert(settings, SETTINGS_COLLECTION_NAME);
	}
	
	public static Order getShopData(Query query) {
		return CRAPI.getMongo().getTemp().findOne(query, Order.class, SHOP_COLLECTION_NAME);
	}
	
	public static List<Order> getShopData(String key, Object value) {
		return CRAPI.getMongo().getTemp().find(Query.query(Criteria.where(key).is(value)).limit(112), Order.class, SHOP_COLLECTION_NAME);
	}
	
	public static List<Order> getShopListData(Query query) {
		return CRAPI.getMongo().getTemp().find(query.limit(112), Order.class, SHOP_COLLECTION_NAME);
	}
	
	public static List<Order> getSortShopData(Query query, String key){
		return CRAPI.getMongo().getTemp().find(query.with(new Sort(Direction.ASC, key)).limit(112), Order.class, SHOP_COLLECTION_NAME);
	}
	
	public static long getShopDataCount(String key, Object value) {
		return CRAPI.getMongo().getCount(SHOP_COLLECTION_NAME, Query.query(Criteria.where(key).is(value)));
	}
	
	public static long getShopDataCount(Query query) {
		return CRAPI.getMongo().getCount(SHOP_COLLECTION_NAME, query);
	}
	
	public static void insertShopData(Order shop) {
		CRAPI.getMongo().getTemp().insert(shop, SHOP_COLLECTION_NAME);
	}
	
	public static void updateShopData(String where, Object is, String key, Object value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(where).is(is));
		Update update = new Update();
		update.set(key, value);
		CRAPI.getMongo().getTemp().updateFirst(query, update, SHOP_COLLECTION_NAME);
	}
	
	public static void updateShopData(Query query, String key, Object value) {
		Update update = new Update();
		update.set(key, value);
		CRAPI.getMongo().getTemp().updateFirst(query, update, SHOP_COLLECTION_NAME);
	}
	
	public static void updateShopData(Query query, Update update) {
		CRAPI.getMongo().getTemp().updateFirst(query, update, SHOP_COLLECTION_NAME);
	}
	
	public static void deleteShopData(String where, Object is, String key, Object value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(where).is(is));
		CRAPI.getMongo().getTemp().remove(query, Order.class, SHOP_COLLECTION_NAME);
	}
	
	public static void deleteShopData(Query query) {
		CRAPI.getMongo().getTemp().remove(query, Order.class, SHOP_COLLECTION_NAME);
	}

}
