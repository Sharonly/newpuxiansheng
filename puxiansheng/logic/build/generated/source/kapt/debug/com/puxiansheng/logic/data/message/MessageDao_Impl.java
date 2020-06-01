package com.puxiansheng.logic.data.message;

import android.database.Cursor;
import androidx.paging.DataSource;
import androidx.paging.DataSource.Factory;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.puxiansheng.logic.bean.MenuItem;
import com.puxiansheng.logic.bean.MessageItem;
import com.puxiansheng.logic.bean.converter.MenuListConverter;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MessageDao_Impl implements MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MessageItem> __insertionAdapterOfMessageItem;

  private final MenuListConverter __menuListConverter = new MenuListConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllFromRoom;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByCategoryFromRoom;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByIdFromRoom;

  public MessageDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessageItem = new EntityInsertionAdapter<MessageItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `table_message` (`_id`,`_message_id`,`title`,`content`,`_category`,`view_time`,`read_log_id`,`buttonList`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MessageItem value) {
        stmt.bindLong(1, value.getItemID());
        stmt.bindLong(2, value.getMessageID());
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        stmt.bindLong(5, value.getCategory());
        if (value.getView_time() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getView_time());
        }
        stmt.bindLong(7, value.getRead_log_id());
        final String _tmp;
        _tmp = __menuListConverter.toString(value.getButtonList());
        if (_tmp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, _tmp);
        }
      }
    };
    this.__preparedStmtOfDeleteAllFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_message";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByCategoryFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_message where _category = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByIdFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_message where _message_id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertOrUpdateIntoRoom(final MessageItem... info) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessageItem.insert(info);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllFromRoom() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllFromRoom.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllFromRoom.release(_stmt);
    }
  }

  @Override
  public void deleteByCategoryFromRoom(final int category) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByCategoryFromRoom.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, category);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByCategoryFromRoom.release(_stmt);
    }
  }

  @Override
  public void deleteByIdFromRoom(final int infoId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByIdFromRoom.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, infoId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByIdFromRoom.release(_stmt);
    }
  }

  @Override
  public DataSource.Factory<Integer, MessageItem> getInfoByCategoryFromRoom(final int category) {
    final String _sql = "select * from table_message where _category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, category);
    return new DataSource.Factory<Integer, MessageItem>() {
      @Override
      public LimitOffsetDataSource<MessageItem> create() {
        return new LimitOffsetDataSource<MessageItem>(__db, _statement, false , "table_message") {
          @Override
          protected List<MessageItem> convertRows(Cursor cursor) {
            final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(cursor, "_id");
            final int _cursorIndexOfMessageID = CursorUtil.getColumnIndexOrThrow(cursor, "_message_id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
            final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(cursor, "content");
            final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(cursor, "_category");
            final int _cursorIndexOfViewTime = CursorUtil.getColumnIndexOrThrow(cursor, "view_time");
            final int _cursorIndexOfReadLogId = CursorUtil.getColumnIndexOrThrow(cursor, "read_log_id");
            final int _cursorIndexOfButtonList = CursorUtil.getColumnIndexOrThrow(cursor, "buttonList");
            final List<MessageItem> _res = new ArrayList<MessageItem>(cursor.getCount());
            while(cursor.moveToNext()) {
              final MessageItem _item;
              final long _tmpItemID;
              _tmpItemID = cursor.getLong(_cursorIndexOfItemID);
              final int _tmpMessageID;
              _tmpMessageID = cursor.getInt(_cursorIndexOfMessageID);
              final String _tmpTitle;
              _tmpTitle = cursor.getString(_cursorIndexOfTitle);
              final String _tmpContent;
              _tmpContent = cursor.getString(_cursorIndexOfContent);
              final int _tmpCategory;
              _tmpCategory = cursor.getInt(_cursorIndexOfCategory);
              final String _tmpView_time;
              _tmpView_time = cursor.getString(_cursorIndexOfViewTime);
              final int _tmpRead_log_id;
              _tmpRead_log_id = cursor.getInt(_cursorIndexOfReadLogId);
              final List<MenuItem> _tmpButtonList;
              final String _tmp;
              _tmp = cursor.getString(_cursorIndexOfButtonList);
              _tmpButtonList = __menuListConverter.toObject(_tmp);
              _item = new MessageItem(_tmpItemID,_tmpMessageID,_tmpTitle,_tmpContent,_tmpCategory,_tmpView_time,_tmpRead_log_id,_tmpButtonList);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public DataSource.Factory<Integer, MessageItem> getFavorInfoFromRoom() {
    final String _sql = "select * from table_message";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, MessageItem>() {
      @Override
      public LimitOffsetDataSource<MessageItem> create() {
        return new LimitOffsetDataSource<MessageItem>(__db, _statement, false , "table_message") {
          @Override
          protected List<MessageItem> convertRows(Cursor cursor) {
            final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(cursor, "_id");
            final int _cursorIndexOfMessageID = CursorUtil.getColumnIndexOrThrow(cursor, "_message_id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
            final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(cursor, "content");
            final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(cursor, "_category");
            final int _cursorIndexOfViewTime = CursorUtil.getColumnIndexOrThrow(cursor, "view_time");
            final int _cursorIndexOfReadLogId = CursorUtil.getColumnIndexOrThrow(cursor, "read_log_id");
            final int _cursorIndexOfButtonList = CursorUtil.getColumnIndexOrThrow(cursor, "buttonList");
            final List<MessageItem> _res = new ArrayList<MessageItem>(cursor.getCount());
            while(cursor.moveToNext()) {
              final MessageItem _item;
              final long _tmpItemID;
              _tmpItemID = cursor.getLong(_cursorIndexOfItemID);
              final int _tmpMessageID;
              _tmpMessageID = cursor.getInt(_cursorIndexOfMessageID);
              final String _tmpTitle;
              _tmpTitle = cursor.getString(_cursorIndexOfTitle);
              final String _tmpContent;
              _tmpContent = cursor.getString(_cursorIndexOfContent);
              final int _tmpCategory;
              _tmpCategory = cursor.getInt(_cursorIndexOfCategory);
              final String _tmpView_time;
              _tmpView_time = cursor.getString(_cursorIndexOfViewTime);
              final int _tmpRead_log_id;
              _tmpRead_log_id = cursor.getInt(_cursorIndexOfReadLogId);
              final List<MenuItem> _tmpButtonList;
              final String _tmp;
              _tmp = cursor.getString(_cursorIndexOfButtonList);
              _tmpButtonList = __menuListConverter.toObject(_tmp);
              _item = new MessageItem(_tmpItemID,_tmpMessageID,_tmpTitle,_tmpContent,_tmpCategory,_tmpView_time,_tmpRead_log_id,_tmpButtonList);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }
}
