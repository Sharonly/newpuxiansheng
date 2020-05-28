package com.puxiansheng.logic.data.business;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BusinessDatabase_Impl extends BusinessDatabase {
  private volatile BusinessDao _businessDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `table_business` (`_shop_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `_id` INTEGER NOT NULL, `_name` TEXT NOT NULL, `_trades` TEXT NOT NULL, `_contact_phone` TEXT NOT NULL, `_investment` TEXT NOT NULL, `_item_keywords` TEXT, `_large_img` TEXT NOT NULL, `_large_img_alt` TEXT NOT NULL, `_large_img_title` TEXT NOT NULL, `_thumb_img` TEXT NOT NULL, `_thumb_img_alt` TEXT NOT NULL, `_thumb_img_title` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '18ec644783ffeb73f109ac105109b2ba')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `table_business`");
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
        final HashMap<String, TableInfo.Column> _columnsTableBusiness = new HashMap<String, TableInfo.Column>(13);
        _columnsTableBusiness.put("_shop_id", new TableInfo.Column("_shop_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_id", new TableInfo.Column("_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_name", new TableInfo.Column("_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_trades", new TableInfo.Column("_trades", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_contact_phone", new TableInfo.Column("_contact_phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_investment", new TableInfo.Column("_investment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_item_keywords", new TableInfo.Column("_item_keywords", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_large_img", new TableInfo.Column("_large_img", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_large_img_alt", new TableInfo.Column("_large_img_alt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_large_img_title", new TableInfo.Column("_large_img_title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_thumb_img", new TableInfo.Column("_thumb_img", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_thumb_img_alt", new TableInfo.Column("_thumb_img_alt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableBusiness.put("_thumb_img_title", new TableInfo.Column("_thumb_img_title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTableBusiness = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTableBusiness = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTableBusiness = new TableInfo("table_business", _columnsTableBusiness, _foreignKeysTableBusiness, _indicesTableBusiness);
        final TableInfo _existingTableBusiness = TableInfo.read(_db, "table_business");
        if (! _infoTableBusiness.equals(_existingTableBusiness)) {
          return new RoomOpenHelper.ValidationResult(false, "table_business(com.puxiansheng.logic.bean.BusinessBean).\n"
                  + " Expected:\n" + _infoTableBusiness + "\n"
                  + " Found:\n" + _existingTableBusiness);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "18ec644783ffeb73f109ac105109b2ba", "d13c5eef47bf8c3e3c79bda05d73b139");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "table_business");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `table_business`");
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
  public BusinessDao businessDao() {
    if (_businessDao != null) {
      return _businessDao;
    } else {
      synchronized(this) {
        if(_businessDao == null) {
          _businessDao = new BusinessDao_Impl(this);
        }
        return _businessDao;
      }
    }
  }
}
