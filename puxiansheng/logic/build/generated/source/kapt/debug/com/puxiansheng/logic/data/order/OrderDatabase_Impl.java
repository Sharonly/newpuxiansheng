package com.puxiansheng.logic.data.order;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class OrderDatabase_Impl extends OrderDatabase {
  private volatile OrderDao _orderDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `table_orders` (`_order_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `_order_type` INTEGER NOT NULL, `_favorite` INTEGER NOT NULL, `_shop_id` INTEGER, `_title` TEXT, `_size` REAL, `_rent` REAL, `_rent_view` TEXT, `_fee` REAL, `_lng` REAL, `_lat` REAL, `_industry` TEXT, `view_opening` TEXT, `view_can_empty` TEXT, `_running_state` INTEGER, `_exclusive` INTEGER, `_image` TEXT, `_images` TEXT, `_floor` INTEGER, `_labels` TEXT, `_facilities` TEXT, `_allfacilities` TEXT, `_description` TEXT, `_description_url` TEXT, `_environment` TEXT, `_reason` TEXT, `_transfer_type` INTEGER, `_is_success` INTEGER, `_formatted_area` TEXT, `_formatted_date` TEXT, `_formatted_size` TEXT, `_formatted_rent` TEXT, `_formatted_page_views` INTEGER, `_formatted_fee` TEXT, `_formatted_location_nodes` TEXT, `_formatted_industry` TEXT, `_formatted_final_industry` TEXT, `_formatted_final_location_node` TEXT, `_view_demand_ids` TEXT, `_data_type` TEXT, `_jump_type` INTEGER, `_jump_view` TEXT, `_jump_param` TEXT, `_article_list` TEXT, `_location_nodes` TEXT, `_address_description` TEXT, `_post_code` INTEGER, `_latitude` REAL, `_longitude` REAL, `_user_id` INTEGER, `_account` TEXT, `_token` TEXT, `_nickname` TEXT, `_sex` INTEGER, `_icon` TEXT, `_login_timestamp` INTEGER, `_login_state` INTEGER, `_user_contact_name` TEXT, `_user_contact_phone` TEXT, `_city_path_id` TEXT, `cityId` INTEGER, `_view_path_city` TEXT, `_state_text` TEXT, `_state_color` TEXT, `_status_name` TEXT, `_status_color` TEXT)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_table_orders__shop_id__transfer_type` ON `table_orders` (`_shop_id`, `_transfer_type`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0a174e5cae33e3e5a3e1916ba08ba4f9')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `table_orders`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTableOrders = new HashMap<String, TableInfo.Column>(66);
        _columnsTableOrders.put("_order_id", new TableInfo.Column("_order_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_order_type", new TableInfo.Column("_order_type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_favorite", new TableInfo.Column("_favorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_shop_id", new TableInfo.Column("_shop_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_title", new TableInfo.Column("_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_size", new TableInfo.Column("_size", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_rent", new TableInfo.Column("_rent", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_rent_view", new TableInfo.Column("_rent_view", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_fee", new TableInfo.Column("_fee", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_lng", new TableInfo.Column("_lng", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_lat", new TableInfo.Column("_lat", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_industry", new TableInfo.Column("_industry", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("view_opening", new TableInfo.Column("view_opening", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("view_can_empty", new TableInfo.Column("view_can_empty", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_running_state", new TableInfo.Column("_running_state", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_exclusive", new TableInfo.Column("_exclusive", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_image", new TableInfo.Column("_image", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_images", new TableInfo.Column("_images", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_floor", new TableInfo.Column("_floor", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_labels", new TableInfo.Column("_labels", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_facilities", new TableInfo.Column("_facilities", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_allfacilities", new TableInfo.Column("_allfacilities", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_description", new TableInfo.Column("_description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_description_url", new TableInfo.Column("_description_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_environment", new TableInfo.Column("_environment", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_reason", new TableInfo.Column("_reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_transfer_type", new TableInfo.Column("_transfer_type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_is_success", new TableInfo.Column("_is_success", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_area", new TableInfo.Column("_formatted_area", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_date", new TableInfo.Column("_formatted_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_size", new TableInfo.Column("_formatted_size", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_rent", new TableInfo.Column("_formatted_rent", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_page_views", new TableInfo.Column("_formatted_page_views", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_fee", new TableInfo.Column("_formatted_fee", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_location_nodes", new TableInfo.Column("_formatted_location_nodes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_industry", new TableInfo.Column("_formatted_industry", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_final_industry", new TableInfo.Column("_formatted_final_industry", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_formatted_final_location_node", new TableInfo.Column("_formatted_final_location_node", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_view_demand_ids", new TableInfo.Column("_view_demand_ids", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_data_type", new TableInfo.Column("_data_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_jump_type", new TableInfo.Column("_jump_type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_jump_view", new TableInfo.Column("_jump_view", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_jump_param", new TableInfo.Column("_jump_param", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_article_list", new TableInfo.Column("_article_list", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_location_nodes", new TableInfo.Column("_location_nodes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_address_description", new TableInfo.Column("_address_description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_post_code", new TableInfo.Column("_post_code", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_latitude", new TableInfo.Column("_latitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_longitude", new TableInfo.Column("_longitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_user_id", new TableInfo.Column("_user_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_account", new TableInfo.Column("_account", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_token", new TableInfo.Column("_token", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_nickname", new TableInfo.Column("_nickname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_sex", new TableInfo.Column("_sex", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_icon", new TableInfo.Column("_icon", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_login_timestamp", new TableInfo.Column("_login_timestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_login_state", new TableInfo.Column("_login_state", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_user_contact_name", new TableInfo.Column("_user_contact_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_user_contact_phone", new TableInfo.Column("_user_contact_phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_city_path_id", new TableInfo.Column("_city_path_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("cityId", new TableInfo.Column("cityId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_view_path_city", new TableInfo.Column("_view_path_city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_state_text", new TableInfo.Column("_state_text", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_state_color", new TableInfo.Column("_state_color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_status_name", new TableInfo.Column("_status_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableOrders.put("_status_color", new TableInfo.Column("_status_color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTableOrders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTableOrders = new HashSet<TableInfo.Index>(1);
        _indicesTableOrders.add(new TableInfo.Index("index_table_orders__shop_id__transfer_type", true, Arrays.asList("_shop_id","_transfer_type")));
        final TableInfo _infoTableOrders = new TableInfo("table_orders", _columnsTableOrders, _foreignKeysTableOrders, _indicesTableOrders);
        final TableInfo _existingTableOrders = TableInfo.read(_db, "table_orders");
        if (! _infoTableOrders.equals(_existingTableOrders)) {
          return new RoomOpenHelper.ValidationResult(false, "table_orders(com.puxiansheng.logic.bean.Order).\n"
                  + " Expected:\n" + _infoTableOrders + "\n"
                  + " Found:\n" + _existingTableOrders);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "0a174e5cae33e3e5a3e1916ba08ba4f9", "c7f7926cf62214e08a2523140b58f523");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "table_orders");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `table_orders`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public OrderDao getOrderDao() {
    if (_orderDao != null) {
      return _orderDao;
    } else {
      synchronized(this) {
        if(_orderDao == null) {
          _orderDao = new OrderDao_Impl(this);
        }
        return _orderDao;
      }
    }
  }
}
