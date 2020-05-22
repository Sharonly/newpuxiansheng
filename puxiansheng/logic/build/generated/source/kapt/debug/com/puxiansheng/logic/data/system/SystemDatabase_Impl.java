package com.puxiansheng.logic.data.system;

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
public final class SystemDatabase_Impl extends SystemDatabase {
  private volatile SystemDao _systemDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `table_sys_cfg` (`_id` INTEGER NOT NULL, `_max_upload_file_size` INTEGER NOT NULL, `_max_upload_file` INTEGER NOT NULL, `_network_connect_read_timeout` INTEGER NOT NULL, `_network_connect_write_timeout` INTEGER NOT NULL, `_network_connect_timeout` INTEGER NOT NULL, `_def_user_icon` TEXT NOT NULL, PRIMARY KEY(`_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '182a5b881834b969c9d3ef2bb471f689')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `table_sys_cfg`");
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
        final HashMap<String, TableInfo.Column> _columnsTableSysCfg = new HashMap<String, TableInfo.Column>(7);
        _columnsTableSysCfg.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSysCfg.put("_max_upload_file_size", new TableInfo.Column("_max_upload_file_size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSysCfg.put("_max_upload_file", new TableInfo.Column("_max_upload_file", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSysCfg.put("_network_connect_read_timeout", new TableInfo.Column("_network_connect_read_timeout", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSysCfg.put("_network_connect_write_timeout", new TableInfo.Column("_network_connect_write_timeout", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSysCfg.put("_network_connect_timeout", new TableInfo.Column("_network_connect_timeout", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableSysCfg.put("_def_user_icon", new TableInfo.Column("_def_user_icon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTableSysCfg = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTableSysCfg = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTableSysCfg = new TableInfo("table_sys_cfg", _columnsTableSysCfg, _foreignKeysTableSysCfg, _indicesTableSysCfg);
        final TableInfo _existingTableSysCfg = TableInfo.read(_db, "table_sys_cfg");
        if (! _infoTableSysCfg.equals(_existingTableSysCfg)) {
          return new RoomOpenHelper.ValidationResult(false, "table_sys_cfg(com.puxiansheng.logic.bean.SystemConfig).\n"
                  + " Expected:\n" + _infoTableSysCfg + "\n"
                  + " Found:\n" + _existingTableSysCfg);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "182a5b881834b969c9d3ef2bb471f689", "533fcd270557c691407259e7f51296cf");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "table_sys_cfg");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `table_sys_cfg`");
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
  public SystemDao systemConfigDao() {
    if (_systemDao != null) {
      return _systemDao;
    } else {
      synchronized(this) {
        if(_systemDao == null) {
          _systemDao = new SystemDao_Impl(this);
        }
        return _systemDao;
      }
    }
  }
}
