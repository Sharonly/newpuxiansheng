package com.puxiansheng.logic.data.user;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.puxiansheng.logic.bean.User;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `user_table` (`_user_id`,`_account`,`_token`,`_nickname`,`_sex`,`_icon`,`_login_timestamp`,`_login_state`,`_user_contact_name`,`_user_contact_phone`,`_city_path_id`,`cityId`,`_view_path_city`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getUserID());
        if (value.getAccount() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAccount());
        }
        if (value.getToken() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getToken());
        }
        if (value.getNickname() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNickname());
        }
        stmt.bindLong(5, value.getUserSex());
        if (value.getIcon() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getIcon());
        }
        stmt.bindLong(7, value.getLoginTimestamp());
        stmt.bindLong(8, value.getLoginState());
        if (value.getActualName() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getActualName());
        }
        if (value.getUserPhoneNumber() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getUserPhoneNumber());
        }
        if (value.getCityPathId() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCityPathId());
        }
        stmt.bindLong(12, value.getCityId());
        if (value.getCityName() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCityName());
        }
      }
    };
  }

  @Override
  public Object insert(final User user, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUser.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object isUserExist(final String userAccount, final Continuation<? super User> p1) {
    final String _sql = "select * from user_table where _account = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userAccount == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userAccount);
    }
    return CoroutinesRoom.execute(__db, false, new Callable<User>() {
      @Override
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(_cursor, "_user_id");
          final int _cursorIndexOfAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "_account");
          final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(_cursor, "_token");
          final int _cursorIndexOfNickname = CursorUtil.getColumnIndexOrThrow(_cursor, "_nickname");
          final int _cursorIndexOfUserSex = CursorUtil.getColumnIndexOrThrow(_cursor, "_sex");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon");
          final int _cursorIndexOfLoginTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "_login_timestamp");
          final int _cursorIndexOfLoginState = CursorUtil.getColumnIndexOrThrow(_cursor, "_login_state");
          final int _cursorIndexOfActualName = CursorUtil.getColumnIndexOrThrow(_cursor, "_user_contact_name");
          final int _cursorIndexOfUserPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "_user_contact_phone");
          final int _cursorIndexOfCityPathId = CursorUtil.getColumnIndexOrThrow(_cursor, "_city_path_id");
          final int _cursorIndexOfCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "cityId");
          final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(_cursor, "_view_path_city");
          final User _result;
          if(_cursor.moveToFirst()) {
            _result = new User();
            final int _tmpUserID;
            _tmpUserID = _cursor.getInt(_cursorIndexOfUserID);
            _result.setUserID(_tmpUserID);
            final String _tmpAccount;
            _tmpAccount = _cursor.getString(_cursorIndexOfAccount);
            _result.setAccount(_tmpAccount);
            final String _tmpToken;
            _tmpToken = _cursor.getString(_cursorIndexOfToken);
            _result.setToken(_tmpToken);
            final String _tmpNickname;
            _tmpNickname = _cursor.getString(_cursorIndexOfNickname);
            _result.setNickname(_tmpNickname);
            final int _tmpUserSex;
            _tmpUserSex = _cursor.getInt(_cursorIndexOfUserSex);
            _result.setUserSex(_tmpUserSex);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            _result.setIcon(_tmpIcon);
            final long _tmpLoginTimestamp;
            _tmpLoginTimestamp = _cursor.getLong(_cursorIndexOfLoginTimestamp);
            _result.setLoginTimestamp(_tmpLoginTimestamp);
            final int _tmpLoginState;
            _tmpLoginState = _cursor.getInt(_cursorIndexOfLoginState);
            _result.setLoginState(_tmpLoginState);
            final String _tmpActualName;
            _tmpActualName = _cursor.getString(_cursorIndexOfActualName);
            _result.setActualName(_tmpActualName);
            final String _tmpUserPhoneNumber;
            _tmpUserPhoneNumber = _cursor.getString(_cursorIndexOfUserPhoneNumber);
            _result.setUserPhoneNumber(_tmpUserPhoneNumber);
            final String _tmpCityPathId;
            _tmpCityPathId = _cursor.getString(_cursorIndexOfCityPathId);
            _result.setCityPathId(_tmpCityPathId);
            final int _tmpCityId;
            _tmpCityId = _cursor.getInt(_cursorIndexOfCityId);
            _result.setCityId(_tmpCityId);
            final String _tmpCityName;
            _tmpCityName = _cursor.getString(_cursorIndexOfCityName);
            _result.setCityName(_tmpCityName);
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

  @Override
  public Object requestLastUser(final Continuation<? super User> p0) {
    final String _sql = "select * from user_table order by _login_timestamp desc limit 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.execute(__db, false, new Callable<User>() {
      @Override
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(_cursor, "_user_id");
          final int _cursorIndexOfAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "_account");
          final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(_cursor, "_token");
          final int _cursorIndexOfNickname = CursorUtil.getColumnIndexOrThrow(_cursor, "_nickname");
          final int _cursorIndexOfUserSex = CursorUtil.getColumnIndexOrThrow(_cursor, "_sex");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "_icon");
          final int _cursorIndexOfLoginTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "_login_timestamp");
          final int _cursorIndexOfLoginState = CursorUtil.getColumnIndexOrThrow(_cursor, "_login_state");
          final int _cursorIndexOfActualName = CursorUtil.getColumnIndexOrThrow(_cursor, "_user_contact_name");
          final int _cursorIndexOfUserPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "_user_contact_phone");
          final int _cursorIndexOfCityPathId = CursorUtil.getColumnIndexOrThrow(_cursor, "_city_path_id");
          final int _cursorIndexOfCityId = CursorUtil.getColumnIndexOrThrow(_cursor, "cityId");
          final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(_cursor, "_view_path_city");
          final User _result;
          if(_cursor.moveToFirst()) {
            _result = new User();
            final int _tmpUserID;
            _tmpUserID = _cursor.getInt(_cursorIndexOfUserID);
            _result.setUserID(_tmpUserID);
            final String _tmpAccount;
            _tmpAccount = _cursor.getString(_cursorIndexOfAccount);
            _result.setAccount(_tmpAccount);
            final String _tmpToken;
            _tmpToken = _cursor.getString(_cursorIndexOfToken);
            _result.setToken(_tmpToken);
            final String _tmpNickname;
            _tmpNickname = _cursor.getString(_cursorIndexOfNickname);
            _result.setNickname(_tmpNickname);
            final int _tmpUserSex;
            _tmpUserSex = _cursor.getInt(_cursorIndexOfUserSex);
            _result.setUserSex(_tmpUserSex);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            _result.setIcon(_tmpIcon);
            final long _tmpLoginTimestamp;
            _tmpLoginTimestamp = _cursor.getLong(_cursorIndexOfLoginTimestamp);
            _result.setLoginTimestamp(_tmpLoginTimestamp);
            final int _tmpLoginState;
            _tmpLoginState = _cursor.getInt(_cursorIndexOfLoginState);
            _result.setLoginState(_tmpLoginState);
            final String _tmpActualName;
            _tmpActualName = _cursor.getString(_cursorIndexOfActualName);
            _result.setActualName(_tmpActualName);
            final String _tmpUserPhoneNumber;
            _tmpUserPhoneNumber = _cursor.getString(_cursorIndexOfUserPhoneNumber);
            _result.setUserPhoneNumber(_tmpUserPhoneNumber);
            final String _tmpCityPathId;
            _tmpCityPathId = _cursor.getString(_cursorIndexOfCityPathId);
            _result.setCityPathId(_tmpCityPathId);
            final int _tmpCityId;
            _tmpCityId = _cursor.getInt(_cursorIndexOfCityId);
            _result.setCityId(_tmpCityId);
            final String _tmpCityName;
            _tmpCityName = _cursor.getString(_cursorIndexOfCityName);
            _result.setCityName(_tmpCityName);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }
}
