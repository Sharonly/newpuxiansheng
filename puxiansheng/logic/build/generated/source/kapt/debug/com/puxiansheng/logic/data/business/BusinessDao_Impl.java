package com.puxiansheng.logic.data.business;

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
import com.puxiansheng.logic.bean.BusinessBean;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BusinessDao_Impl implements BusinessDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BusinessBean> __insertionAdapterOfBusinessBean;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllFromRoom;

  public BusinessDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBusinessBean = new EntityInsertionAdapter<BusinessBean>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `table_business` (`_shop_id`,`_id`,`_name`,`_trades`,`_contact_phone`,`_investment`,`_large_img`,`_large_img_alt`,`_large_img_title`,`_thumb_img`,`_thumb_img_alt`,`_thumb_img_title`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BusinessBean value) {
        stmt.bindLong(1, value.getShopID());
        stmt.bindLong(2, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getTrades() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTrades());
        }
        if (value.getContact_phone() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getContact_phone());
        }
        if (value.getInvestment() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getInvestment());
        }
        if (value.getLarge_img() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLarge_img());
        }
        if (value.getLarge_img_alt() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getLarge_img_alt());
        }
        if (value.getLarge_img_title() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getLarge_img_title());
        }
        if (value.getThumb_img() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getThumb_img());
        }
        if (value.getThumb_img_alt() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getThumb_img_alt());
        }
        if (value.getThumb_img_title() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getThumb_img_title());
        }
      }
    };
    this.__preparedStmtOfDeleteAllFromRoom = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_business";
        return _query;
      }
    };
  }

  @Override
  public void insertOrUpdateIntoRoom(final BusinessBean... info) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBusinessBean.insert(info);
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
  public DataSource.Factory<Integer, BusinessBean> getBusinessInfoFromRoom() {
    final String _sql = "select * from table_business";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, BusinessBean>() {
      @Override
      public LimitOffsetDataSource<BusinessBean> create() {
        return new LimitOffsetDataSource<BusinessBean>(__db, _statement, false , "table_business") {
          @Override
          protected List<BusinessBean> convertRows(Cursor cursor) {
            final int _cursorIndexOfShopID = CursorUtil.getColumnIndexOrThrow(cursor, "_shop_id");
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "_id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(cursor, "_name");
            final int _cursorIndexOfTrades = CursorUtil.getColumnIndexOrThrow(cursor, "_trades");
            final int _cursorIndexOfContactPhone = CursorUtil.getColumnIndexOrThrow(cursor, "_contact_phone");
            final int _cursorIndexOfInvestment = CursorUtil.getColumnIndexOrThrow(cursor, "_investment");
            final int _cursorIndexOfLargeImg = CursorUtil.getColumnIndexOrThrow(cursor, "_large_img");
            final int _cursorIndexOfLargeImgAlt = CursorUtil.getColumnIndexOrThrow(cursor, "_large_img_alt");
            final int _cursorIndexOfLargeImgTitle = CursorUtil.getColumnIndexOrThrow(cursor, "_large_img_title");
            final int _cursorIndexOfThumbImg = CursorUtil.getColumnIndexOrThrow(cursor, "_thumb_img");
            final int _cursorIndexOfThumbImgAlt = CursorUtil.getColumnIndexOrThrow(cursor, "_thumb_img_alt");
            final int _cursorIndexOfThumbImgTitle = CursorUtil.getColumnIndexOrThrow(cursor, "_thumb_img_title");
            final List<BusinessBean> _res = new ArrayList<BusinessBean>(cursor.getCount());
            while(cursor.moveToNext()) {
              final BusinessBean _item;
              final int _tmpShopID;
              _tmpShopID = cursor.getInt(_cursorIndexOfShopID);
              final int _tmpId;
              _tmpId = cursor.getInt(_cursorIndexOfId);
              final String _tmpName;
              _tmpName = cursor.getString(_cursorIndexOfName);
              final String _tmpTrades;
              _tmpTrades = cursor.getString(_cursorIndexOfTrades);
              final String _tmpContact_phone;
              _tmpContact_phone = cursor.getString(_cursorIndexOfContactPhone);
              final String _tmpInvestment;
              _tmpInvestment = cursor.getString(_cursorIndexOfInvestment);
              final String _tmpLarge_img;
              _tmpLarge_img = cursor.getString(_cursorIndexOfLargeImg);
              final String _tmpLarge_img_alt;
              _tmpLarge_img_alt = cursor.getString(_cursorIndexOfLargeImgAlt);
              final String _tmpLarge_img_title;
              _tmpLarge_img_title = cursor.getString(_cursorIndexOfLargeImgTitle);
              final String _tmpThumb_img;
              _tmpThumb_img = cursor.getString(_cursorIndexOfThumbImg);
              final String _tmpThumb_img_alt;
              _tmpThumb_img_alt = cursor.getString(_cursorIndexOfThumbImgAlt);
              final String _tmpThumb_img_title;
              _tmpThumb_img_title = cursor.getString(_cursorIndexOfThumbImgTitle);
              _item = new BusinessBean(_tmpShopID,_tmpId,_tmpName,_tmpTrades,_tmpContact_phone,_tmpInvestment,_tmpLarge_img,_tmpLarge_img_alt,_tmpLarge_img_title,_tmpThumb_img,_tmpThumb_img_alt,_tmpThumb_img_title);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }
}
