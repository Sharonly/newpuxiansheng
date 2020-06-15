package com.puxiansheng.logic.data.device;

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
public final class DeviceDatabase_Impl extends DeviceDatabase {
  private volatile DeviceDao _deviceDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `table_device` (`_id` INTEGER NOT NULL, `_uid` TEXT NOT NULL, `_manufacturer` TEXT NOT NULL, `_brand` TEXT NOT NULL, `_hardware` TEXT NOT NULL, `_model` TEXT NOT NULL, `_sdk` INTEGER NOT NULL, `_board` TEXT NOT NULL, `_host` TEXT NOT NULL, PRIMARY KEY(`_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '039873d89c3c323bd494181682edb4e5')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `table_device`");
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
        final HashMap<String, TableInfo.Column> _columnsTableDevice = new HashMap<String, TableInfo.Column>(9);
        _columnsTableDevice.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableDevice.put("_uid", new TableInfo.Column("_uid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableDevice.put("_manufacturer", new TableInfo.Column("_manufacturer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableDevice.put("_brand", new TableInfo.Column("_brand", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableDevice.put("_hardware", new TableInfo.Column("_hardware", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableDevice.put("_model", new TableInfo.Column("_model", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableDevice.put("_sdk", new TableInfo.Column("_sdk", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableDevice.put("_board", new TableInfo.Column("_board", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableDevice.put("_host", new TableInfo.Column("_host", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTableDevice = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTableDevice = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTableDevice = new TableInfo("table_device", _columnsTableDevice, _foreignKeysTableDevice, _indicesTableDevice);
        final TableInfo _existingTableDevice = TableInfo.read(_db, "table_device");
        if (! _infoTableDevice.equals(_existingTableDevice)) {
          return new RoomOpenHelper.ValidationResult(false, "table_device(com.puxiansheng.logic.bean.Device).\n"
                  + " Expected:\n" + _infoTableDevice + "\n"
                  + " Found:\n" + _existingTableDevice);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "039873d89c3c323bd494181682edb4e5", "3dd97bd543ba74ef1d9172dd3f3fe349");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "table_device");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `table_device`");
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
  public DeviceDao deviceDao() {
    if (_deviceDao != null) {
      return _deviceDao;
    } else {
      synchronized(this) {
        if(_deviceDao == null) {
          _deviceDao = new DeviceDao_Impl(this);
        }
        return _deviceDao;
      }
    }
  }
}
