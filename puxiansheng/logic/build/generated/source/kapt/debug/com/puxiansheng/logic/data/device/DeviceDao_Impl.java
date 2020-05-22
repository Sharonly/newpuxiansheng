package com.puxiansheng.logic.data.device;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.puxiansheng.logic.bean.Device;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DeviceDao_Impl implements DeviceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Device> __insertionAdapterOfDevice;

  private final EntityDeletionOrUpdateAdapter<Device> __deletionAdapterOfDevice;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public DeviceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDevice = new EntityInsertionAdapter<Device>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `table_device` (`_id`,`_uid`,`_manufacturer`,`_brand`,`_hardware`,`_model`,`_sdk`,`_board`,`_host`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Device value) {
        stmt.bindLong(1, value.getId());
        if (value.getUid() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUid());
        }
        if (value.getManufacturer() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getManufacturer());
        }
        if (value.getBrand() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBrand());
        }
        if (value.getHardware() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getHardware());
        }
        if (value.getModel() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getModel());
        }
        stmt.bindLong(7, value.getSdk());
        if (value.getBoard() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getBoard());
        }
        if (value.getHost() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getHost());
        }
      }
    };
    this.__deletionAdapterOfDevice = new EntityDeletionOrUpdateAdapter<Device>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `table_device` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Device value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_device where _id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertOrUpdate(final Device device) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDevice.insert(device);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Device device) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDevice.handle(device);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public LiveData<Device> get() {
    final String _sql = "select * from table_device where _id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"table_device"}, false, new Callable<Device>() {
      @Override
      public Device call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "_uid");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "_manufacturer");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "_brand");
          final int _cursorIndexOfHardware = CursorUtil.getColumnIndexOrThrow(_cursor, "_hardware");
          final int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "_model");
          final int _cursorIndexOfSdk = CursorUtil.getColumnIndexOrThrow(_cursor, "_sdk");
          final int _cursorIndexOfBoard = CursorUtil.getColumnIndexOrThrow(_cursor, "_board");
          final int _cursorIndexOfHost = CursorUtil.getColumnIndexOrThrow(_cursor, "_host");
          final Device _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUid;
            _tmpUid = _cursor.getString(_cursorIndexOfUid);
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            final String _tmpBrand;
            _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            final String _tmpHardware;
            _tmpHardware = _cursor.getString(_cursorIndexOfHardware);
            final String _tmpModel;
            _tmpModel = _cursor.getString(_cursorIndexOfModel);
            final int _tmpSdk;
            _tmpSdk = _cursor.getInt(_cursorIndexOfSdk);
            final String _tmpBoard;
            _tmpBoard = _cursor.getString(_cursorIndexOfBoard);
            final String _tmpHost;
            _tmpHost = _cursor.getString(_cursorIndexOfHost);
            _result = new Device(_tmpId,_tmpUid,_tmpManufacturer,_tmpBrand,_tmpHardware,_tmpModel,_tmpSdk,_tmpBoard,_tmpHost);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
