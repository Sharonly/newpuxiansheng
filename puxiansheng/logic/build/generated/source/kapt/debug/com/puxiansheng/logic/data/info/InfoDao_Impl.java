package com.puxiansheng.logic.data.info;

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
import com.puxiansheng.logic.bean.InfoItem;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class InfoDao_Impl implements InfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<InfoItem> __insertionAdapterOfInfoItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllFromRoom;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByCategoryFromRoom;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByIdFromRoom;

  public InfoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInfoItem = new EntityInsertionAdapter<InfoItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `table_info` (`_id`,`_info_id`,`_title`,`_release_date`,`_image`,`_category`,`_author`,`_url`,`_page_views`,`_param`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, InfoItem value) {
        stmt.bindLong(1, value.getItemID());
        stmt.bindLong(2, value.getInfoID());
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
        if (value.getImage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getImage());
        }
        stmt.bindLong(6, value.getCategory());
        if (value.getAuthor() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAuthor());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUrl());
        }
        stmt.bindLong(9, value.getPageViews());
        if (value.getJump_param() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getJump_param());
        }
      }
    };
    this.__preparedStmtOfDeleteAllFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_info";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByCategoryFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_info where _category = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByIdFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_info where _info_id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertOrUpdateIntoRoom(final InfoItem... info) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfInfoItem.insert(info);
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
  public DataSource.Factory<Integer, InfoItem> getInfoByCategoryFromRoom(final int category) {
    final String _sql = "select * from table_info where _category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, category);
    return new DataSource.Factory<Integer, InfoItem>() {
      @Override
      public LimitOffsetDataSource<InfoItem> create() {
        return new LimitOffsetDataSource<InfoItem>(__db, _statement, false , "table_info") {
          @Override
          protected List<InfoItem> convertRows(Cursor cursor) {
            final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(cursor, "_id");
            final int _cursorIndexOfInfoID = CursorUtil.getColumnIndexOrThrow(cursor, "_info_id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "_title");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(cursor, "_release_date");
            final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(cursor, "_image");
            final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(cursor, "_category");
            final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(cursor, "_author");
            final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(cursor, "_url");
            final int _cursorIndexOfPageViews = CursorUtil.getColumnIndexOrThrow(cursor, "_page_views");
            final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(cursor, "_param");
            final List<InfoItem> _res = new ArrayList<InfoItem>(cursor.getCount());
            while(cursor.moveToNext()) {
              final InfoItem _item;
              final long _tmpItemID;
              _tmpItemID = cursor.getLong(_cursorIndexOfItemID);
              final int _tmpInfoID;
              _tmpInfoID = cursor.getInt(_cursorIndexOfInfoID);
              final String _tmpTitle;
              _tmpTitle = cursor.getString(_cursorIndexOfTitle);
              final String _tmpDate;
              _tmpDate = cursor.getString(_cursorIndexOfDate);
              final String _tmpImage;
              _tmpImage = cursor.getString(_cursorIndexOfImage);
              final int _tmpCategory;
              _tmpCategory = cursor.getInt(_cursorIndexOfCategory);
              final String _tmpAuthor;
              _tmpAuthor = cursor.getString(_cursorIndexOfAuthor);
              final String _tmpUrl;
              _tmpUrl = cursor.getString(_cursorIndexOfUrl);
              final int _tmpPageViews;
              _tmpPageViews = cursor.getInt(_cursorIndexOfPageViews);
              final String _tmpJump_param;
              _tmpJump_param = cursor.getString(_cursorIndexOfJumpParam);
              _item = new InfoItem(_tmpItemID,_tmpInfoID,_tmpTitle,_tmpDate,_tmpImage,_tmpCategory,_tmpAuthor,_tmpUrl,_tmpPageViews,_tmpJump_param);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public DataSource.Factory<Integer, InfoItem> getFavorInfoFromRoom() {
    final String _sql = "select * from table_info";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, InfoItem>() {
      @Override
      public LimitOffsetDataSource<InfoItem> create() {
        return new LimitOffsetDataSource<InfoItem>(__db, _statement, false , "table_info") {
          @Override
          protected List<InfoItem> convertRows(Cursor cursor) {
            final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(cursor, "_id");
            final int _cursorIndexOfInfoID = CursorUtil.getColumnIndexOrThrow(cursor, "_info_id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "_title");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(cursor, "_release_date");
            final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(cursor, "_image");
            final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(cursor, "_category");
            final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(cursor, "_author");
            final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(cursor, "_url");
            final int _cursorIndexOfPageViews = CursorUtil.getColumnIndexOrThrow(cursor, "_page_views");
            final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(cursor, "_param");
            final List<InfoItem> _res = new ArrayList<InfoItem>(cursor.getCount());
            while(cursor.moveToNext()) {
              final InfoItem _item;
              final long _tmpItemID;
              _tmpItemID = cursor.getLong(_cursorIndexOfItemID);
              final int _tmpInfoID;
              _tmpInfoID = cursor.getInt(_cursorIndexOfInfoID);
              final String _tmpTitle;
              _tmpTitle = cursor.getString(_cursorIndexOfTitle);
              final String _tmpDate;
              _tmpDate = cursor.getString(_cursorIndexOfDate);
              final String _tmpImage;
              _tmpImage = cursor.getString(_cursorIndexOfImage);
              final int _tmpCategory;
              _tmpCategory = cursor.getInt(_cursorIndexOfCategory);
              final String _tmpAuthor;
              _tmpAuthor = cursor.getString(_cursorIndexOfAuthor);
              final String _tmpUrl;
              _tmpUrl = cursor.getString(_cursorIndexOfUrl);
              final int _tmpPageViews;
              _tmpPageViews = cursor.getInt(_cursorIndexOfPageViews);
              final String _tmpJump_param;
              _tmpJump_param = cursor.getString(_cursorIndexOfJumpParam);
              _item = new InfoItem(_tmpItemID,_tmpInfoID,_tmpTitle,_tmpDate,_tmpImage,_tmpCategory,_tmpAuthor,_tmpUrl,_tmpPageViews,_tmpJump_param);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }
}
