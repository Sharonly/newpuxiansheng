package com.puxiansheng.logic.data.menu;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.puxiansheng.logic.bean.MenuItem;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MenuDao_Impl implements MenuDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MenuItem> __insertionAdapterOfMenuItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMenuByTypeFromRoom;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllFromRoom;

  public MenuDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMenuItem = new EntityInsertionAdapter<MenuItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `table_menu` (`_id`,`_menu_id`,`_type`,`_parent_id`,`_text`,`_value`,`jump_type`,`jump_view`,`jump_param`,`_icon_enable`,`_icon_disable`,`btText`,`_color`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MenuItem value) {
        stmt.bindLong(1, value.getItemID());
        stmt.bindLong(2, value.getMenuID());
        stmt.bindLong(3, value.getType());
        stmt.bindLong(4, value.getParentID());
        if (value.getText() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getText());
        }
        stmt.bindLong(6, value.getValue());
        stmt.bindLong(7, value.getJump_type());
        if (value.getJump_view() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getJump_view());
        }
        if (value.getJump_param() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getJump_param());
        }
        if (value.getIcon_enable() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getIcon_enable());
        }
        if (value.getIcon_disable() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getIcon_disable());
        }
        if (value.getBtText() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getBtText());
        }
        if (value.getColor() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getColor());
        }
      }
    };
    this.__preparedStmtOfDeleteMenuByTypeFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_menu where _type = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_menu";
        return _query;
      }
    };
  }

  @Override
  public Object insertOrUpdateIntoRoom(final MenuItem[] menu, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMenuItem.insert(menu);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deleteMenuByTypeFromRoom(final int type, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMenuByTypeFromRoom.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, type);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteMenuByTypeFromRoom.release(_stmt);
        }
      }
    }, p1);
  }

  @Override
  public Object deleteAllFromRoom(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllFromRoom.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllFromRoom.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Object getMenuByTypeFromRoom(final int type,
      final Continuation<? super List<MenuItem>> p1) {
    final String _sql = "select * from table_menu where _type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, type);
    return CoroutinesRoom.execute(__db, false, new Callable<List<MenuItem>>() {
      @Override
      public List<MenuItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfMenuID = CursorUtil.getColumnIndexOrThrow(_cursor, "_menu_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "_type");
          final int _cursorIndexOfParentID = CursorUtil.getColumnIndexOrThrow(_cursor, "_parent_id");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "_text");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "_value");
          final int _cursorIndexOfJumpType = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_type");
          final int _cursorIndexOfJumpView = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_view");
          final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_param");
          final int _cursorIndexOfIconEnable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_enable");
          final int _cursorIndexOfIconDisable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_disable");
          final int _cursorIndexOfBtText = CursorUtil.getColumnIndexOrThrow(_cursor, "btText");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "_color");
          final List<MenuItem> _result = new ArrayList<MenuItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MenuItem _item;
            final long _tmpMenuID;
            _tmpMenuID = _cursor.getLong(_cursorIndexOfMenuID);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            final long _tmpParentID;
            _tmpParentID = _cursor.getLong(_cursorIndexOfParentID);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            final int _tmpValue;
            _tmpValue = _cursor.getInt(_cursorIndexOfValue);
            final int _tmpJump_type;
            _tmpJump_type = _cursor.getInt(_cursorIndexOfJumpType);
            final String _tmpJump_view;
            _tmpJump_view = _cursor.getString(_cursorIndexOfJumpView);
            final String _tmpJump_param;
            _tmpJump_param = _cursor.getString(_cursorIndexOfJumpParam);
            final String _tmpIcon_enable;
            _tmpIcon_enable = _cursor.getString(_cursorIndexOfIconEnable);
            final String _tmpIcon_disable;
            _tmpIcon_disable = _cursor.getString(_cursorIndexOfIconDisable);
            final String _tmpBtText;
            _tmpBtText = _cursor.getString(_cursorIndexOfBtText);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            _item = new MenuItem(_tmpMenuID,_tmpType,_tmpParentID,_tmpText,_tmpValue,_tmpJump_type,_tmpJump_view,_tmpJump_param,_tmpIcon_enable,_tmpIcon_disable,_tmpBtText,_tmpColor);
            final long _tmpItemID;
            _tmpItemID = _cursor.getLong(_cursorIndexOfItemID);
            _item.setItemID(_tmpItemID);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }

  @Override
  public Object getMenuByParentIDFromRoom(final int parentID,
      final Continuation<? super List<MenuItem>> p1) {
    final String _sql = "select * from table_menu where _parent_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, parentID);
    return CoroutinesRoom.execute(__db, false, new Callable<List<MenuItem>>() {
      @Override
      public List<MenuItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfMenuID = CursorUtil.getColumnIndexOrThrow(_cursor, "_menu_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "_type");
          final int _cursorIndexOfParentID = CursorUtil.getColumnIndexOrThrow(_cursor, "_parent_id");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "_text");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "_value");
          final int _cursorIndexOfJumpType = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_type");
          final int _cursorIndexOfJumpView = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_view");
          final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_param");
          final int _cursorIndexOfIconEnable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_enable");
          final int _cursorIndexOfIconDisable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_disable");
          final int _cursorIndexOfBtText = CursorUtil.getColumnIndexOrThrow(_cursor, "btText");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "_color");
          final List<MenuItem> _result = new ArrayList<MenuItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MenuItem _item;
            final long _tmpMenuID;
            _tmpMenuID = _cursor.getLong(_cursorIndexOfMenuID);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            final long _tmpParentID;
            _tmpParentID = _cursor.getLong(_cursorIndexOfParentID);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            final int _tmpValue;
            _tmpValue = _cursor.getInt(_cursorIndexOfValue);
            final int _tmpJump_type;
            _tmpJump_type = _cursor.getInt(_cursorIndexOfJumpType);
            final String _tmpJump_view;
            _tmpJump_view = _cursor.getString(_cursorIndexOfJumpView);
            final String _tmpJump_param;
            _tmpJump_param = _cursor.getString(_cursorIndexOfJumpParam);
            final String _tmpIcon_enable;
            _tmpIcon_enable = _cursor.getString(_cursorIndexOfIconEnable);
            final String _tmpIcon_disable;
            _tmpIcon_disable = _cursor.getString(_cursorIndexOfIconDisable);
            final String _tmpBtText;
            _tmpBtText = _cursor.getString(_cursorIndexOfBtText);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            _item = new MenuItem(_tmpMenuID,_tmpType,_tmpParentID,_tmpText,_tmpValue,_tmpJump_type,_tmpJump_view,_tmpJump_param,_tmpIcon_enable,_tmpIcon_disable,_tmpBtText,_tmpColor);
            final long _tmpItemID;
            _tmpItemID = _cursor.getLong(_cursorIndexOfItemID);
            _item.setItemID(_tmpItemID);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }

  @Override
  public Object getMenuByTypeAndParentIDFromRoom(final int type, final int parentID,
      final Continuation<? super List<MenuItem>> p2) {
    final String _sql = "select * from table_menu where _type = ? and _parent_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, type);
    _argIndex = 2;
    _statement.bindLong(_argIndex, parentID);
    return CoroutinesRoom.execute(__db, false, new Callable<List<MenuItem>>() {
      @Override
      public List<MenuItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfMenuID = CursorUtil.getColumnIndexOrThrow(_cursor, "_menu_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "_type");
          final int _cursorIndexOfParentID = CursorUtil.getColumnIndexOrThrow(_cursor, "_parent_id");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "_text");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "_value");
          final int _cursorIndexOfJumpType = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_type");
          final int _cursorIndexOfJumpView = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_view");
          final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_param");
          final int _cursorIndexOfIconEnable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_enable");
          final int _cursorIndexOfIconDisable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_disable");
          final int _cursorIndexOfBtText = CursorUtil.getColumnIndexOrThrow(_cursor, "btText");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "_color");
          final List<MenuItem> _result = new ArrayList<MenuItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MenuItem _item;
            final long _tmpMenuID;
            _tmpMenuID = _cursor.getLong(_cursorIndexOfMenuID);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            final long _tmpParentID;
            _tmpParentID = _cursor.getLong(_cursorIndexOfParentID);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            final int _tmpValue;
            _tmpValue = _cursor.getInt(_cursorIndexOfValue);
            final int _tmpJump_type;
            _tmpJump_type = _cursor.getInt(_cursorIndexOfJumpType);
            final String _tmpJump_view;
            _tmpJump_view = _cursor.getString(_cursorIndexOfJumpView);
            final String _tmpJump_param;
            _tmpJump_param = _cursor.getString(_cursorIndexOfJumpParam);
            final String _tmpIcon_enable;
            _tmpIcon_enable = _cursor.getString(_cursorIndexOfIconEnable);
            final String _tmpIcon_disable;
            _tmpIcon_disable = _cursor.getString(_cursorIndexOfIconDisable);
            final String _tmpBtText;
            _tmpBtText = _cursor.getString(_cursorIndexOfBtText);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            _item = new MenuItem(_tmpMenuID,_tmpType,_tmpParentID,_tmpText,_tmpValue,_tmpJump_type,_tmpJump_view,_tmpJump_param,_tmpIcon_enable,_tmpIcon_disable,_tmpBtText,_tmpColor);
            final long _tmpItemID;
            _tmpItemID = _cursor.getLong(_cursorIndexOfItemID);
            _item.setItemID(_tmpItemID);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p2);
  }

  @Override
  public Object getAllMenuFromRoom(final Continuation<? super List<MenuItem>> p0) {
    final String _sql = "select * from table_menu";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.execute(__db, false, new Callable<List<MenuItem>>() {
      @Override
      public List<MenuItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfMenuID = CursorUtil.getColumnIndexOrThrow(_cursor, "_menu_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "_type");
          final int _cursorIndexOfParentID = CursorUtil.getColumnIndexOrThrow(_cursor, "_parent_id");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "_text");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "_value");
          final int _cursorIndexOfJumpType = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_type");
          final int _cursorIndexOfJumpView = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_view");
          final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_param");
          final int _cursorIndexOfIconEnable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_enable");
          final int _cursorIndexOfIconDisable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_disable");
          final int _cursorIndexOfBtText = CursorUtil.getColumnIndexOrThrow(_cursor, "btText");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "_color");
          final List<MenuItem> _result = new ArrayList<MenuItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MenuItem _item;
            final long _tmpMenuID;
            _tmpMenuID = _cursor.getLong(_cursorIndexOfMenuID);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            final long _tmpParentID;
            _tmpParentID = _cursor.getLong(_cursorIndexOfParentID);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            final int _tmpValue;
            _tmpValue = _cursor.getInt(_cursorIndexOfValue);
            final int _tmpJump_type;
            _tmpJump_type = _cursor.getInt(_cursorIndexOfJumpType);
            final String _tmpJump_view;
            _tmpJump_view = _cursor.getString(_cursorIndexOfJumpView);
            final String _tmpJump_param;
            _tmpJump_param = _cursor.getString(_cursorIndexOfJumpParam);
            final String _tmpIcon_enable;
            _tmpIcon_enable = _cursor.getString(_cursorIndexOfIconEnable);
            final String _tmpIcon_disable;
            _tmpIcon_disable = _cursor.getString(_cursorIndexOfIconDisable);
            final String _tmpBtText;
            _tmpBtText = _cursor.getString(_cursorIndexOfBtText);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            _item = new MenuItem(_tmpMenuID,_tmpType,_tmpParentID,_tmpText,_tmpValue,_tmpJump_type,_tmpJump_view,_tmpJump_param,_tmpIcon_enable,_tmpIcon_disable,_tmpBtText,_tmpColor);
            final long _tmpItemID;
            _tmpItemID = _cursor.getLong(_cursorIndexOfItemID);
            _item.setItemID(_tmpItemID);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }

  @Override
  public Object getMenuByIDFromRoom(final long id, final Continuation<? super MenuItem> p1) {
    final String _sql = "select * from table_menu where _menu_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.execute(__db, false, new Callable<MenuItem>() {
      @Override
      public MenuItem call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfMenuID = CursorUtil.getColumnIndexOrThrow(_cursor, "_menu_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "_type");
          final int _cursorIndexOfParentID = CursorUtil.getColumnIndexOrThrow(_cursor, "_parent_id");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "_text");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "_value");
          final int _cursorIndexOfJumpType = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_type");
          final int _cursorIndexOfJumpView = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_view");
          final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(_cursor, "jump_param");
          final int _cursorIndexOfIconEnable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_enable");
          final int _cursorIndexOfIconDisable = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon_disable");
          final int _cursorIndexOfBtText = CursorUtil.getColumnIndexOrThrow(_cursor, "btText");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "_color");
          final MenuItem _result;
          if(_cursor.moveToFirst()) {
            final long _tmpMenuID;
            _tmpMenuID = _cursor.getLong(_cursorIndexOfMenuID);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            final long _tmpParentID;
            _tmpParentID = _cursor.getLong(_cursorIndexOfParentID);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            final int _tmpValue;
            _tmpValue = _cursor.getInt(_cursorIndexOfValue);
            final int _tmpJump_type;
            _tmpJump_type = _cursor.getInt(_cursorIndexOfJumpType);
            final String _tmpJump_view;
            _tmpJump_view = _cursor.getString(_cursorIndexOfJumpView);
            final String _tmpJump_param;
            _tmpJump_param = _cursor.getString(_cursorIndexOfJumpParam);
            final String _tmpIcon_enable;
            _tmpIcon_enable = _cursor.getString(_cursorIndexOfIconEnable);
            final String _tmpIcon_disable;
            _tmpIcon_disable = _cursor.getString(_cursorIndexOfIconDisable);
            final String _tmpBtText;
            _tmpBtText = _cursor.getString(_cursorIndexOfBtText);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            _result = new MenuItem(_tmpMenuID,_tmpType,_tmpParentID,_tmpText,_tmpValue,_tmpJump_type,_tmpJump_view,_tmpJump_param,_tmpIcon_enable,_tmpIcon_disable,_tmpBtText,_tmpColor);
            final long _tmpItemID;
            _tmpItemID = _cursor.getLong(_cursorIndexOfItemID);
            _result.setItemID(_tmpItemID);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }
}
