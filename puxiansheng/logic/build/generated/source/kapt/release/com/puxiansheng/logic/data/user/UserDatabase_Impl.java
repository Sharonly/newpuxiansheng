package com.puxiansheng.logic.data.user;

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
public final class UserDatabase_Impl extends UserDatabase {
  private volatile UserDao _userDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user_table` (`_user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `_account` TEXT NOT NULL, `_token` TEXT NOT NULL, `_nickname` TEXT NOT NULL, `nickName` TEXT NOT NULL, `userSex` INTEGER NOT NULL, `_icon` TEXT NOT NULL, `_login_timestamp` INTEGER NOT NULL, `tipsMsg` TEXT NOT NULL, `_login_state` INTEGER NOT NULL, `_user_contact_name` TEXT NOT NULL, `_user_contact_phone` TEXT NOT NULL, `cityPathId` TEXT NOT NULL, `cityId` INTEGER NOT NULL, `cityName` TEXT NOT NULL, `userCityPath` TEXT NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_user_table__user_contact_phone` ON `user_table` (`_user_contact_phone`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '78d9929e1ad478c072c2a8bfbb16bb9e')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `user_table`");
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
        final HashMap<String, TableInfo.Column> _columnsUserTable = new HashMap<String, TableInfo.Column>(16);
        _columnsUserTable.put("_user_id", new TableInfo.Column("_user_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("_account", new TableInfo.Column("_account", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("_token", new TableInfo.Column("_token", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("_nickname", new TableInfo.Column("_nickname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("nickName", new TableInfo.Column("nickName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("userSex", new TableInfo.Column("userSex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("_icon", new TableInfo.Column("_icon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("_login_timestamp", new TableInfo.Column("_login_timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("tipsMsg", new TableInfo.Column("tipsMsg", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("_login_state", new TableInfo.Column("_login_state", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("_user_contact_name", new TableInfo.Column("_user_contact_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("_user_contact_phone", new TableInfo.Column("_user_contact_phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("cityPathId", new TableInfo.Column("cityPathId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("cityId", new TableInfo.Column("cityId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("cityName", new TableInfo.Column("cityName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTable.put("userCityPath", new TableInfo.Column("userCityPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTable = new HashSet<TableInfo.Index>(1);
        _indicesUserTable.add(new TableInfo.Index("index_user_table__user_contact_phone", true, Arrays.asList("_user_contact_phone")));
        final TableInfo _infoUserTable = new TableInfo("user_table", _columnsUserTable, _foreignKeysUserTable, _indicesUserTable);
        final TableInfo _existingUserTable = TableInfo.read(_db, "user_table");
        if (! _infoUserTable.equals(_existingUserTable)) {
          return new RoomOpenHelper.ValidationResult(false, "user_table(com.puxiansheng.logic.bean.User).\n"
                  + " Expected:\n" + _infoUserTable + "\n"
                  + " Found:\n" + _existingUserTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "78d9929e1ad478c072c2a8bfbb16bb9e", "be4e82cefe5c8f27d9c2ee7c44b764ed");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `user_table`");
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
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
