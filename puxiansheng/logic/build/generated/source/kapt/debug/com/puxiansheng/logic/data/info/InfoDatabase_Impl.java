package com.puxiansheng.logic.data.info;

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
public final class InfoDatabase_Impl extends InfoDatabase {
  private volatile InfoDao _infoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `table_info` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `_info_id` INTEGER NOT NULL, `_title` TEXT NOT NULL, `micro` TEXT NOT NULL, `_release_date` TEXT NOT NULL, `_image` TEXT NOT NULL, `_category` INTEGER NOT NULL, `_author` TEXT NOT NULL, `_url` TEXT NOT NULL, `_page_views` INTEGER NOT NULL, `_param` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4b055f148fa8d718c3b73252ba7b6eaa')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `table_info`");
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
        final HashMap<String, TableInfo.Column> _columnsTableInfo = new HashMap<String, TableInfo.Column>(11);
        _columnsTableInfo.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_info_id", new TableInfo.Column("_info_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_title", new TableInfo.Column("_title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("micro", new TableInfo.Column("micro", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_release_date", new TableInfo.Column("_release_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_image", new TableInfo.Column("_image", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_category", new TableInfo.Column("_category", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_author", new TableInfo.Column("_author", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_url", new TableInfo.Column("_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_page_views", new TableInfo.Column("_page_views", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableInfo.put("_param", new TableInfo.Column("_param", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTableInfo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTableInfo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTableInfo = new TableInfo("table_info", _columnsTableInfo, _foreignKeysTableInfo, _indicesTableInfo);
        final TableInfo _existingTableInfo = TableInfo.read(_db, "table_info");
        if (! _infoTableInfo.equals(_existingTableInfo)) {
          return new RoomOpenHelper.ValidationResult(false, "table_info(com.puxiansheng.logic.bean.InfoItem).\n"
                  + " Expected:\n" + _infoTableInfo + "\n"
                  + " Found:\n" + _existingTableInfo);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "4b055f148fa8d718c3b73252ba7b6eaa", "6aac0fbe65a7a247fbb5a2bdda47c95d");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "table_info");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `table_info`");
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
  public InfoDao infoDao() {
    if (_infoDao != null) {
      return _infoDao;
    } else {
      synchronized(this) {
        if(_infoDao == null) {
          _infoDao = new InfoDao_Impl(this);
        }
        return _infoDao;
      }
    }
  }
}
