package com.puxiansheng.logic.data.menu;

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
public final class MenuDatabase_Impl extends MenuDatabase {
  private volatile MenuDao _menuDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `table_menu` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `_menu_id` INTEGER NOT NULL, `_type` INTEGER NOT NULL, `_parent_id` INTEGER NOT NULL, `_text` TEXT NOT NULL, `_color` TEXT NOT NULL, `_value` INTEGER NOT NULL, `_icon_enable` TEXT NOT NULL, `_icon_disable` TEXT NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_table_menu__menu_id__type` ON `table_menu` (`_menu_id`, `_type`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '13c86e80e52c7139fa057af801bbf2dc')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `table_menu`");
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
        final HashMap<String, TableInfo.Column> _columnsTableMenu = new HashMap<String, TableInfo.Column>(9);
        _columnsTableMenu.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableMenu.put("_menu_id", new TableInfo.Column("_menu_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableMenu.put("_type", new TableInfo.Column("_type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableMenu.put("_parent_id", new TableInfo.Column("_parent_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableMenu.put("_text", new TableInfo.Column("_text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableMenu.put("_color", new TableInfo.Column("_color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableMenu.put("_value", new TableInfo.Column("_value", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableMenu.put("_icon_enable", new TableInfo.Column("_icon_enable", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTableMenu.put("_icon_disable", new TableInfo.Column("_icon_disable", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTableMenu = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTableMenu = new HashSet<TableInfo.Index>(1);
        _indicesTableMenu.add(new TableInfo.Index("index_table_menu__menu_id__type", true, Arrays.asList("_menu_id","_type")));
        final TableInfo _infoTableMenu = new TableInfo("table_menu", _columnsTableMenu, _foreignKeysTableMenu, _indicesTableMenu);
        final TableInfo _existingTableMenu = TableInfo.read(_db, "table_menu");
        if (! _infoTableMenu.equals(_existingTableMenu)) {
          return new RoomOpenHelper.ValidationResult(false, "table_menu(com.puxiansheng.logic.bean.MenuItem).\n"
                  + " Expected:\n" + _infoTableMenu + "\n"
                  + " Found:\n" + _existingTableMenu);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "13c86e80e52c7139fa057af801bbf2dc", "88bad3708b567f69abc1dfe1be3c374a");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "table_menu");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `table_menu`");
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
  public MenuDao menuDao() {
    if (_menuDao != null) {
      return _menuDao;
    } else {
      synchronized(this) {
        if(_menuDao == null) {
          _menuDao = new MenuDao_Impl(this);
        }
        return _menuDao;
      }
    }
  }
}
