package com.puxiansheng.logic.data.system;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.puxiansheng.logic.bean.SystemConfig;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SystemDao_Impl implements SystemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SystemConfig> __insertionAdapterOfSystemConfig;

  public SystemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSystemConfig = new EntityInsertionAdapter<SystemConfig>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `table_sys_cfg` (`_id`,`_max_upload_file_size`,`_max_upload_file`,`_network_connect_read_timeout`,`_network_connect_write_timeout`,`_network_connect_timeout`,`_def_user_icon`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SystemConfig value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getMaxUploadFileLength());
        stmt.bindLong(3, value.getMaxUploadFile());
        stmt.bindLong(4, value.getNetworkConnectReadTimeout());
        stmt.bindLong(5, value.getNetworkConnectWriteTimeout());
        stmt.bindLong(6, value.getNetworkConnectTimeout());
        if (value.getDefaultUserIcon() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDefaultUserIcon());
        }
      }
    };
  }

  @Override
  public void insertOrUpdateLocalSystemConfig(final SystemConfig systemConfig) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSystemConfig.insert(systemConfig);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public SystemConfig getLocalSystemConfig() {
    final String _sql = "select * from table_sys_cfg where _id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
      final int _cursorIndexOfMaxUploadFileLength = CursorUtil.getColumnIndexOrThrow(_cursor, "_max_upload_file_size");
      final int _cursorIndexOfMaxUploadFile = CursorUtil.getColumnIndexOrThrow(_cursor, "_max_upload_file");
      final int _cursorIndexOfNetworkConnectReadTimeout = CursorUtil.getColumnIndexOrThrow(_cursor, "_network_connect_read_timeout");
      final int _cursorIndexOfNetworkConnectWriteTimeout = CursorUtil.getColumnIndexOrThrow(_cursor, "_network_connect_write_timeout");
      final int _cursorIndexOfNetworkConnectTimeout = CursorUtil.getColumnIndexOrThrow(_cursor, "_network_connect_timeout");
      final int _cursorIndexOfDefaultUserIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "_def_user_icon");
      final SystemConfig _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final long _tmpMaxUploadFileLength;
        _tmpMaxUploadFileLength = _cursor.getLong(_cursorIndexOfMaxUploadFileLength);
        final int _tmpMaxUploadFile;
        _tmpMaxUploadFile = _cursor.getInt(_cursorIndexOfMaxUploadFile);
        final int _tmpNetworkConnectReadTimeout;
        _tmpNetworkConnectReadTimeout = _cursor.getInt(_cursorIndexOfNetworkConnectReadTimeout);
        final int _tmpNetworkConnectWriteTimeout;
        _tmpNetworkConnectWriteTimeout = _cursor.getInt(_cursorIndexOfNetworkConnectWriteTimeout);
        final int _tmpNetworkConnectTimeout;
        _tmpNetworkConnectTimeout = _cursor.getInt(_cursorIndexOfNetworkConnectTimeout);
        final String _tmpDefaultUserIcon;
        _tmpDefaultUserIcon = _cursor.getString(_cursorIndexOfDefaultUserIcon);
        _result = new SystemConfig(_tmpId,_tmpMaxUploadFileLength,_tmpMaxUploadFile,_tmpNetworkConnectReadTimeout,_tmpNetworkConnectWriteTimeout,_tmpNetworkConnectTimeout,_tmpDefaultUserIcon);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
