package com.puxiansheng.logic.data.order;

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
import com.puxiansheng.logic.bean.Address;
import com.puxiansheng.logic.bean.Coordinates;
import com.puxiansheng.logic.bean.LocationNode;
import com.puxiansheng.logic.bean.MenuItem;
import com.puxiansheng.logic.bean.Order;
import com.puxiansheng.logic.bean.Shop;
import com.puxiansheng.logic.bean.User;
import com.puxiansheng.logic.bean.converter.LocationNodeListConverter;
import com.puxiansheng.logic.bean.converter.MenuListConverter;
import com.puxiansheng.logic.bean.converter.StringListConverter;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class OrderDao_Impl implements OrderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Order> __insertionAdapterOfOrder;

  private final StringListConverter __stringListConverter = new StringListConverter();

  private final MenuListConverter __menuListConverter = new MenuListConverter();

  private final LocationNodeListConverter __locationNodeListConverter = new LocationNodeListConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllByTransferType;

  public OrderDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOrder = new EntityInsertionAdapter<Order>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `table_orders` (`_order_id`,`_order_type`,`_favorite`,`_shop_id`,`_title`,`_size`,`_rent`,`_fee`,`_lng`,`_lat`,`_new_lng`,`_new_lat`,`_industry`,`_running_state`,`_exclusive`,`_image`,`_images`,`_floor`,`_labels`,`_facilities`,`_allfacilities`,`_description`,`_description_url`,`_environment`,`_reason`,`_transfer_type`,`_is_success`,`_formatted_is_success`,`_formatted_date`,`_formatted_size`,`_formatted_rent`,`_formatted_page_views`,`_formatted_fee`,`_formatted_location_nodes`,`_formatted_industry`,`_formatted_final_industry`,`_formatted_final_location_node`,`formattedFacilities`,`_rent_view`,`_view_opening`,`_view_can_empty`,`_is_top`,`_is_hot`,`_is_recommend`,`_is_large_order`,`_large_order_img`,`_category_acreage`,`_formatted_area`,`_is_vip`,`_data_type`,`_jump_type`,`_jump_view`,`_jump_param`,`_location_nodes`,`_address_description`,`_post_code`,`_latitude`,`_longitude`,`_user_id`,`_account`,`_token`,`_name`,`_nickname`,`_user_sex`,`_icon`,`_login_timestamp`,`_tips_msg`,`_login_state`,`_user_contact_name`,`_user_contact_phone`,`_city_path_id`,`_city_id`,`_view_city_name`,`_view_city_path`,`_state_text`,`_state_color`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Order value) {
        stmt.bindLong(1, value.getOrderID());
        stmt.bindLong(2, value.getOrderType());
        stmt.bindLong(3, value.getFavorite());
        final Shop _tmpShop = value.getShop();
        if(_tmpShop != null) {
          stmt.bindLong(4, _tmpShop.getShopID());
          if (_tmpShop.getTitle() == null) {
            stmt.bindNull(5);
          } else {
            stmt.bindString(5, _tmpShop.getTitle());
          }
          stmt.bindDouble(6, _tmpShop.getSize());
          stmt.bindDouble(7, _tmpShop.getRent());
          stmt.bindDouble(8, _tmpShop.getFee());
          if (_tmpShop.getLng() == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindString(9, _tmpShop.getLng());
          }
          if (_tmpShop.getLat() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, _tmpShop.getLat());
          }
          stmt.bindDouble(11, _tmpShop.getNewLng());
          stmt.bindDouble(12, _tmpShop.getNewLat());
          if (_tmpShop.getIndustry() == null) {
            stmt.bindNull(13);
          } else {
            stmt.bindString(13, _tmpShop.getIndustry());
          }
          stmt.bindLong(14, _tmpShop.getRunningState());
          stmt.bindLong(15, _tmpShop.getIncludeFacilities());
          if (_tmpShop.getImage() == null) {
            stmt.bindNull(16);
          } else {
            stmt.bindString(16, _tmpShop.getImage());
          }
          final String _tmp;
          _tmp = __stringListConverter.fromArrayList(_tmpShop.getImages());
          if (_tmp == null) {
            stmt.bindNull(17);
          } else {
            stmt.bindString(17, _tmp);
          }
          stmt.bindLong(18, _tmpShop.getFloor());
          final String _tmp_1;
          _tmp_1 = __menuListConverter.toString(_tmpShop.getLabels());
          if (_tmp_1 == null) {
            stmt.bindNull(19);
          } else {
            stmt.bindString(19, _tmp_1);
          }
          final String _tmp_2;
          _tmp_2 = __menuListConverter.toString(_tmpShop.getFacilities());
          if (_tmp_2 == null) {
            stmt.bindNull(20);
          } else {
            stmt.bindString(20, _tmp_2);
          }
          final String _tmp_3;
          _tmp_3 = __menuListConverter.toString(_tmpShop.getAllFacilities());
          if (_tmp_3 == null) {
            stmt.bindNull(21);
          } else {
            stmt.bindString(21, _tmp_3);
          }
          if (_tmpShop.getDescription() == null) {
            stmt.bindNull(22);
          } else {
            stmt.bindString(22, _tmpShop.getDescription());
          }
          if (_tmpShop.getDescriptionUrl() == null) {
            stmt.bindNull(23);
          } else {
            stmt.bindString(23, _tmpShop.getDescriptionUrl());
          }
          if (_tmpShop.getEnvironment() == null) {
            stmt.bindNull(24);
          } else {
            stmt.bindString(24, _tmpShop.getEnvironment());
          }
          if (_tmpShop.getReason() == null) {
            stmt.bindNull(25);
          } else {
            stmt.bindString(25, _tmpShop.getReason());
          }
          stmt.bindLong(26, _tmpShop.getTransferType());
          stmt.bindLong(27, _tmpShop.isSuccess());
          stmt.bindLong(28, _tmpShop.getFormatted_is_success());
          if (_tmpShop.getFormattedDate() == null) {
            stmt.bindNull(29);
          } else {
            stmt.bindString(29, _tmpShop.getFormattedDate());
          }
          if (_tmpShop.getFormattedSize() == null) {
            stmt.bindNull(30);
          } else {
            stmt.bindString(30, _tmpShop.getFormattedSize());
          }
          if (_tmpShop.getFormattedRent() == null) {
            stmt.bindNull(31);
          } else {
            stmt.bindString(31, _tmpShop.getFormattedRent());
          }
          stmt.bindLong(32, _tmpShop.getFormattedPageViews());
          if (_tmpShop.getFormattedFee() == null) {
            stmt.bindNull(33);
          } else {
            stmt.bindString(33, _tmpShop.getFormattedFee());
          }
          if (_tmpShop.getFormattedLocationNodes() == null) {
            stmt.bindNull(34);
          } else {
            stmt.bindString(34, _tmpShop.getFormattedLocationNodes());
          }
          if (_tmpShop.getFormattedIndustry() == null) {
            stmt.bindNull(35);
          } else {
            stmt.bindString(35, _tmpShop.getFormattedIndustry());
          }
          if (_tmpShop.getFormattedFinalIndustry() == null) {
            stmt.bindNull(36);
          } else {
            stmt.bindString(36, _tmpShop.getFormattedFinalIndustry());
          }
          if (_tmpShop.getFormattedFinalLocationNode() == null) {
            stmt.bindNull(37);
          } else {
            stmt.bindString(37, _tmpShop.getFormattedFinalLocationNode());
          }
          final String _tmp_4;
          _tmp_4 = __menuListConverter.toString(_tmpShop.getFormattedFacilities());
          if (_tmp_4 == null) {
            stmt.bindNull(38);
          } else {
            stmt.bindString(38, _tmp_4);
          }
          if (_tmpShop.getRentView() == null) {
            stmt.bindNull(39);
          } else {
            stmt.bindString(39, _tmpShop.getRentView());
          }
          if (_tmpShop.getViewOpening() == null) {
            stmt.bindNull(40);
          } else {
            stmt.bindString(40, _tmpShop.getViewOpening());
          }
          if (_tmpShop.getViewCanEmpty() == null) {
            stmt.bindNull(41);
          } else {
            stmt.bindString(41, _tmpShop.getViewCanEmpty());
          }
          stmt.bindLong(42, _tmpShop.isTop());
          stmt.bindLong(43, _tmpShop.isHot());
          stmt.bindLong(44, _tmpShop.isRecommend());
          stmt.bindLong(45, _tmpShop.isLargeOrder());
          if (_tmpShop.getLargeOrderImg() == null) {
            stmt.bindNull(46);
          } else {
            stmt.bindString(46, _tmpShop.getLargeOrderImg());
          }
          if (_tmpShop.getCategory_acreage() == null) {
            stmt.bindNull(47);
          } else {
            stmt.bindString(47, _tmpShop.getCategory_acreage());
          }
          if (_tmpShop.getFormattedArea() == null) {
            stmt.bindNull(48);
          } else {
            stmt.bindString(48, _tmpShop.getFormattedArea());
          }
          stmt.bindLong(49, _tmpShop.isVip());
          if (_tmpShop.getData_type() == null) {
            stmt.bindNull(50);
          } else {
            stmt.bindString(50, _tmpShop.getData_type());
          }
          stmt.bindLong(51, _tmpShop.getJump_type());
          if (_tmpShop.getJump_view() == null) {
            stmt.bindNull(52);
          } else {
            stmt.bindString(52, _tmpShop.getJump_view());
          }
          if (_tmpShop.getJump_param() == null) {
            stmt.bindNull(53);
          } else {
            stmt.bindString(53, _tmpShop.getJump_param());
          }
          final Address _tmpAddress = _tmpShop.getAddress();
          if(_tmpAddress != null) {
            final String _tmp_5;
            _tmp_5 = __locationNodeListConverter.toString(_tmpAddress.getLocationNodes());
            if (_tmp_5 == null) {
              stmt.bindNull(54);
            } else {
              stmt.bindString(54, _tmp_5);
            }
            if (_tmpAddress.getAddressDetail() == null) {
              stmt.bindNull(55);
            } else {
              stmt.bindString(55, _tmpAddress.getAddressDetail());
            }
            stmt.bindLong(56, _tmpAddress.getPostCode());
            final Coordinates _tmpCoordinates = _tmpAddress.getCoordinates();
            if(_tmpCoordinates != null) {
              stmt.bindDouble(57, _tmpCoordinates.getLatitude());
              stmt.bindDouble(58, _tmpCoordinates.getLongitude());
            } else {
              stmt.bindNull(57);
              stmt.bindNull(58);
            }
          } else {
            stmt.bindNull(54);
            stmt.bindNull(55);
            stmt.bindNull(56);
            stmt.bindNull(57);
            stmt.bindNull(58);
          }
        } else {
          stmt.bindNull(4);
          stmt.bindNull(5);
          stmt.bindNull(6);
          stmt.bindNull(7);
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
          stmt.bindNull(11);
          stmt.bindNull(12);
          stmt.bindNull(13);
          stmt.bindNull(14);
          stmt.bindNull(15);
          stmt.bindNull(16);
          stmt.bindNull(17);
          stmt.bindNull(18);
          stmt.bindNull(19);
          stmt.bindNull(20);
          stmt.bindNull(21);
          stmt.bindNull(22);
          stmt.bindNull(23);
          stmt.bindNull(24);
          stmt.bindNull(25);
          stmt.bindNull(26);
          stmt.bindNull(27);
          stmt.bindNull(28);
          stmt.bindNull(29);
          stmt.bindNull(30);
          stmt.bindNull(31);
          stmt.bindNull(32);
          stmt.bindNull(33);
          stmt.bindNull(34);
          stmt.bindNull(35);
          stmt.bindNull(36);
          stmt.bindNull(37);
          stmt.bindNull(38);
          stmt.bindNull(39);
          stmt.bindNull(40);
          stmt.bindNull(41);
          stmt.bindNull(42);
          stmt.bindNull(43);
          stmt.bindNull(44);
          stmt.bindNull(45);
          stmt.bindNull(46);
          stmt.bindNull(47);
          stmt.bindNull(48);
          stmt.bindNull(49);
          stmt.bindNull(50);
          stmt.bindNull(51);
          stmt.bindNull(52);
          stmt.bindNull(53);
          stmt.bindNull(54);
          stmt.bindNull(55);
          stmt.bindNull(56);
          stmt.bindNull(57);
          stmt.bindNull(58);
        }
        final User _tmpShopOwner = value.getShopOwner();
        if(_tmpShopOwner != null) {
          stmt.bindLong(59, _tmpShopOwner.getUserID());
          if (_tmpShopOwner.getAccount() == null) {
            stmt.bindNull(60);
          } else {
            stmt.bindString(60, _tmpShopOwner.getAccount());
          }
          if (_tmpShopOwner.getToken() == null) {
            stmt.bindNull(61);
          } else {
            stmt.bindString(61, _tmpShopOwner.getToken());
          }
          if (_tmpShopOwner.getName() == null) {
            stmt.bindNull(62);
          } else {
            stmt.bindString(62, _tmpShopOwner.getName());
          }
          if (_tmpShopOwner.getNickName() == null) {
            stmt.bindNull(63);
          } else {
            stmt.bindString(63, _tmpShopOwner.getNickName());
          }
          stmt.bindLong(64, _tmpShopOwner.getUserSex());
          if (_tmpShopOwner.getIcon() == null) {
            stmt.bindNull(65);
          } else {
            stmt.bindString(65, _tmpShopOwner.getIcon());
          }
          stmt.bindLong(66, _tmpShopOwner.getLoginTimestamp());
          if (_tmpShopOwner.getTipsMsg() == null) {
            stmt.bindNull(67);
          } else {
            stmt.bindString(67, _tmpShopOwner.getTipsMsg());
          }
          stmt.bindLong(68, _tmpShopOwner.getLoginState());
          if (_tmpShopOwner.getActualName() == null) {
            stmt.bindNull(69);
          } else {
            stmt.bindString(69, _tmpShopOwner.getActualName());
          }
          if (_tmpShopOwner.getUserPhoneNumber() == null) {
            stmt.bindNull(70);
          } else {
            stmt.bindString(70, _tmpShopOwner.getUserPhoneNumber());
          }
          if (_tmpShopOwner.getCityPathId() == null) {
            stmt.bindNull(71);
          } else {
            stmt.bindString(71, _tmpShopOwner.getCityPathId());
          }
          stmt.bindLong(72, _tmpShopOwner.getCityId());
          if (_tmpShopOwner.getCityName() == null) {
            stmt.bindNull(73);
          } else {
            stmt.bindString(73, _tmpShopOwner.getCityName());
          }
          if (_tmpShopOwner.getUserCityPath() == null) {
            stmt.bindNull(74);
          } else {
            stmt.bindString(74, _tmpShopOwner.getUserCityPath());
          }
        } else {
          stmt.bindNull(59);
          stmt.bindNull(60);
          stmt.bindNull(61);
          stmt.bindNull(62);
          stmt.bindNull(63);
          stmt.bindNull(64);
          stmt.bindNull(65);
          stmt.bindNull(66);
          stmt.bindNull(67);
          stmt.bindNull(68);
          stmt.bindNull(69);
          stmt.bindNull(70);
          stmt.bindNull(71);
          stmt.bindNull(72);
          stmt.bindNull(73);
          stmt.bindNull(74);
        }
        final Order.Companion.State _tmpState = value.getState();
        if(_tmpState != null) {
          if (_tmpState.getText() == null) {
            stmt.bindNull(75);
          } else {
            stmt.bindString(75, _tmpState.getText());
          }
          if (_tmpState.getColor() == null) {
            stmt.bindNull(76);
          } else {
            stmt.bindString(76, _tmpState.getColor());
          }
        } else {
          stmt.bindNull(75);
          stmt.bindNull(76);
        }
      }
    };
    this.__preparedStmtOfDeleteAllByTransferType = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from table_orders where _transfer_type = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertOrUpdate(final Order... order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrder.insert(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllByTransferType(final int type) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllByTransferType.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, type);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllByTransferType.release(_stmt);
    }
  }

  @Override
  public DataSource.Factory<Integer, Order> getLocalOrdersByTransferType(final int type) {
    final String _sql = "select * from table_orders where _transfer_type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, type);
    return new DataSource.Factory<Integer, Order>() {
      @Override
      public LimitOffsetDataSource<Order> create() {
        return new LimitOffsetDataSource<Order>(__db, _statement, false , "table_orders") {
          @Override
          protected List<Order> convertRows(Cursor cursor) {
            final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(cursor, "_order_id");
            final int _cursorIndexOfOrderType = CursorUtil.getColumnIndexOrThrow(cursor, "_order_type");
            final int _cursorIndexOfFavorite = CursorUtil.getColumnIndexOrThrow(cursor, "_favorite");
            final int _cursorIndexOfShopID = CursorUtil.getColumnIndexOrThrow(cursor, "_shop_id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "_title");
            final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(cursor, "_size");
            final int _cursorIndexOfRent = CursorUtil.getColumnIndexOrThrow(cursor, "_rent");
            final int _cursorIndexOfFee = CursorUtil.getColumnIndexOrThrow(cursor, "_fee");
            final int _cursorIndexOfLng = CursorUtil.getColumnIndexOrThrow(cursor, "_lng");
            final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(cursor, "_lat");
            final int _cursorIndexOfNewLng = CursorUtil.getColumnIndexOrThrow(cursor, "_new_lng");
            final int _cursorIndexOfNewLat = CursorUtil.getColumnIndexOrThrow(cursor, "_new_lat");
            final int _cursorIndexOfIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_industry");
            final int _cursorIndexOfRunningState = CursorUtil.getColumnIndexOrThrow(cursor, "_running_state");
            final int _cursorIndexOfIncludeFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_exclusive");
            final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(cursor, "_image");
            final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(cursor, "_images");
            final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(cursor, "_floor");
            final int _cursorIndexOfLabels = CursorUtil.getColumnIndexOrThrow(cursor, "_labels");
            final int _cursorIndexOfFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_facilities");
            final int _cursorIndexOfAllFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_allfacilities");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "_description");
            final int _cursorIndexOfDescriptionUrl = CursorUtil.getColumnIndexOrThrow(cursor, "_description_url");
            final int _cursorIndexOfEnvironment = CursorUtil.getColumnIndexOrThrow(cursor, "_environment");
            final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(cursor, "_reason");
            final int _cursorIndexOfTransferType = CursorUtil.getColumnIndexOrThrow(cursor, "_transfer_type");
            final int _cursorIndexOfIsSuccess = CursorUtil.getColumnIndexOrThrow(cursor, "_is_success");
            final int _cursorIndexOfFormattedIsSuccess = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_is_success");
            final int _cursorIndexOfFormattedDate = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_date");
            final int _cursorIndexOfFormattedSize = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_size");
            final int _cursorIndexOfFormattedRent = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_rent");
            final int _cursorIndexOfFormattedPageViews = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_page_views");
            final int _cursorIndexOfFormattedFee = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_fee");
            final int _cursorIndexOfFormattedLocationNodes = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_location_nodes");
            final int _cursorIndexOfFormattedIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_industry");
            final int _cursorIndexOfFormattedFinalIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_final_industry");
            final int _cursorIndexOfFormattedFinalLocationNode = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_final_location_node");
            final int _cursorIndexOfFormattedFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "formattedFacilities");
            final int _cursorIndexOfRentView = CursorUtil.getColumnIndexOrThrow(cursor, "_rent_view");
            final int _cursorIndexOfViewOpening = CursorUtil.getColumnIndexOrThrow(cursor, "_view_opening");
            final int _cursorIndexOfViewCanEmpty = CursorUtil.getColumnIndexOrThrow(cursor, "_view_can_empty");
            final int _cursorIndexOfIsTop = CursorUtil.getColumnIndexOrThrow(cursor, "_is_top");
            final int _cursorIndexOfIsHot = CursorUtil.getColumnIndexOrThrow(cursor, "_is_hot");
            final int _cursorIndexOfIsRecommend = CursorUtil.getColumnIndexOrThrow(cursor, "_is_recommend");
            final int _cursorIndexOfIsLargeOrder = CursorUtil.getColumnIndexOrThrow(cursor, "_is_large_order");
            final int _cursorIndexOfLargeOrderImg = CursorUtil.getColumnIndexOrThrow(cursor, "_large_order_img");
            final int _cursorIndexOfCategoryAcreage = CursorUtil.getColumnIndexOrThrow(cursor, "_category_acreage");
            final int _cursorIndexOfFormattedArea = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_area");
            final int _cursorIndexOfIsVip = CursorUtil.getColumnIndexOrThrow(cursor, "_is_vip");
            final int _cursorIndexOfDataType = CursorUtil.getColumnIndexOrThrow(cursor, "_data_type");
            final int _cursorIndexOfJumpType = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_type");
            final int _cursorIndexOfJumpView = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_view");
            final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_param");
            final int _cursorIndexOfLocationNodes = CursorUtil.getColumnIndexOrThrow(cursor, "_location_nodes");
            final int _cursorIndexOfAddressDetail = CursorUtil.getColumnIndexOrThrow(cursor, "_address_description");
            final int _cursorIndexOfPostCode = CursorUtil.getColumnIndexOrThrow(cursor, "_post_code");
            final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(cursor, "_latitude");
            final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(cursor, "_longitude");
            final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(cursor, "_user_id");
            final int _cursorIndexOfAccount = CursorUtil.getColumnIndexOrThrow(cursor, "_account");
            final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(cursor, "_token");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(cursor, "_name");
            final int _cursorIndexOfNickName = CursorUtil.getColumnIndexOrThrow(cursor, "_nickname");
            final int _cursorIndexOfUserSex = CursorUtil.getColumnIndexOrThrow(cursor, "_user_sex");
            final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(cursor, "_icon");
            final int _cursorIndexOfLoginTimestamp = CursorUtil.getColumnIndexOrThrow(cursor, "_login_timestamp");
            final int _cursorIndexOfTipsMsg = CursorUtil.getColumnIndexOrThrow(cursor, "_tips_msg");
            final int _cursorIndexOfLoginState = CursorUtil.getColumnIndexOrThrow(cursor, "_login_state");
            final int _cursorIndexOfActualName = CursorUtil.getColumnIndexOrThrow(cursor, "_user_contact_name");
            final int _cursorIndexOfUserPhoneNumber = CursorUtil.getColumnIndexOrThrow(cursor, "_user_contact_phone");
            final int _cursorIndexOfCityPathId = CursorUtil.getColumnIndexOrThrow(cursor, "_city_path_id");
            final int _cursorIndexOfCityId = CursorUtil.getColumnIndexOrThrow(cursor, "_city_id");
            final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(cursor, "_view_city_name");
            final int _cursorIndexOfUserCityPath = CursorUtil.getColumnIndexOrThrow(cursor, "_view_city_path");
            final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(cursor, "_state_text");
            final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(cursor, "_state_color");
            final List<Order> _res = new ArrayList<Order>(cursor.getCount());
            while(cursor.moveToNext()) {
              final Order _item;
              final Shop _tmpShop;
              if (! (cursor.isNull(_cursorIndexOfShopID) && cursor.isNull(_cursorIndexOfTitle) && cursor.isNull(_cursorIndexOfSize) && cursor.isNull(_cursorIndexOfRent) && cursor.isNull(_cursorIndexOfFee) && cursor.isNull(_cursorIndexOfLng) && cursor.isNull(_cursorIndexOfLat) && cursor.isNull(_cursorIndexOfNewLng) && cursor.isNull(_cursorIndexOfNewLat) && cursor.isNull(_cursorIndexOfIndustry) && cursor.isNull(_cursorIndexOfRunningState) && cursor.isNull(_cursorIndexOfIncludeFacilities) && cursor.isNull(_cursorIndexOfImage) && cursor.isNull(_cursorIndexOfImages) && cursor.isNull(_cursorIndexOfFloor) && cursor.isNull(_cursorIndexOfLabels) && cursor.isNull(_cursorIndexOfFacilities) && cursor.isNull(_cursorIndexOfAllFacilities) && cursor.isNull(_cursorIndexOfDescription) && cursor.isNull(_cursorIndexOfDescriptionUrl) && cursor.isNull(_cursorIndexOfEnvironment) && cursor.isNull(_cursorIndexOfReason) && cursor.isNull(_cursorIndexOfTransferType) && cursor.isNull(_cursorIndexOfIsSuccess) && cursor.isNull(_cursorIndexOfFormattedIsSuccess) && cursor.isNull(_cursorIndexOfFormattedDate) && cursor.isNull(_cursorIndexOfFormattedSize) && cursor.isNull(_cursorIndexOfFormattedRent) && cursor.isNull(_cursorIndexOfFormattedPageViews) && cursor.isNull(_cursorIndexOfFormattedFee) && cursor.isNull(_cursorIndexOfFormattedLocationNodes) && cursor.isNull(_cursorIndexOfFormattedIndustry) && cursor.isNull(_cursorIndexOfFormattedFinalIndustry) && cursor.isNull(_cursorIndexOfFormattedFinalLocationNode) && cursor.isNull(_cursorIndexOfFormattedFacilities) && cursor.isNull(_cursorIndexOfRentView) && cursor.isNull(_cursorIndexOfViewOpening) && cursor.isNull(_cursorIndexOfViewCanEmpty) && cursor.isNull(_cursorIndexOfIsTop) && cursor.isNull(_cursorIndexOfIsHot) && cursor.isNull(_cursorIndexOfIsRecommend) && cursor.isNull(_cursorIndexOfIsLargeOrder) && cursor.isNull(_cursorIndexOfLargeOrderImg) && cursor.isNull(_cursorIndexOfCategoryAcreage) && cursor.isNull(_cursorIndexOfFormattedArea) && cursor.isNull(_cursorIndexOfIsVip) && cursor.isNull(_cursorIndexOfDataType) && cursor.isNull(_cursorIndexOfJumpType) && cursor.isNull(_cursorIndexOfJumpView) && cursor.isNull(_cursorIndexOfJumpParam) && cursor.isNull(_cursorIndexOfLocationNodes) && cursor.isNull(_cursorIndexOfAddressDetail) && cursor.isNull(_cursorIndexOfPostCode) && cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                final long _tmpShopID;
                _tmpShopID = cursor.getLong(_cursorIndexOfShopID);
                final String _tmpTitle;
                _tmpTitle = cursor.getString(_cursorIndexOfTitle);
                final double _tmpSize;
                _tmpSize = cursor.getDouble(_cursorIndexOfSize);
                final double _tmpRent;
                _tmpRent = cursor.getDouble(_cursorIndexOfRent);
                final double _tmpFee;
                _tmpFee = cursor.getDouble(_cursorIndexOfFee);
                final String _tmpLng;
                _tmpLng = cursor.getString(_cursorIndexOfLng);
                final String _tmpLat;
                _tmpLat = cursor.getString(_cursorIndexOfLat);
                final double _tmpNewLng;
                _tmpNewLng = cursor.getDouble(_cursorIndexOfNewLng);
                final double _tmpNewLat;
                _tmpNewLat = cursor.getDouble(_cursorIndexOfNewLat);
                final String _tmpIndustry;
                _tmpIndustry = cursor.getString(_cursorIndexOfIndustry);
                final int _tmpRunningState;
                _tmpRunningState = cursor.getInt(_cursorIndexOfRunningState);
                final int _tmpIncludeFacilities;
                _tmpIncludeFacilities = cursor.getInt(_cursorIndexOfIncludeFacilities);
                final String _tmpImage;
                _tmpImage = cursor.getString(_cursorIndexOfImage);
                final List<String> _tmpImages;
                final String _tmp;
                _tmp = cursor.getString(_cursorIndexOfImages);
                _tmpImages = __stringListConverter.fromString(_tmp);
                final int _tmpFloor;
                _tmpFloor = cursor.getInt(_cursorIndexOfFloor);
                final List<MenuItem> _tmpLabels;
                final String _tmp_1;
                _tmp_1 = cursor.getString(_cursorIndexOfLabels);
                _tmpLabels = __menuListConverter.toObject(_tmp_1);
                final List<MenuItem> _tmpFacilities;
                final String _tmp_2;
                _tmp_2 = cursor.getString(_cursorIndexOfFacilities);
                _tmpFacilities = __menuListConverter.toObject(_tmp_2);
                final List<MenuItem> _tmpAllFacilities;
                final String _tmp_3;
                _tmp_3 = cursor.getString(_cursorIndexOfAllFacilities);
                _tmpAllFacilities = __menuListConverter.toObject(_tmp_3);
                final String _tmpDescription;
                _tmpDescription = cursor.getString(_cursorIndexOfDescription);
                final String _tmpDescriptionUrl;
                _tmpDescriptionUrl = cursor.getString(_cursorIndexOfDescriptionUrl);
                final String _tmpEnvironment;
                _tmpEnvironment = cursor.getString(_cursorIndexOfEnvironment);
                final String _tmpReason;
                _tmpReason = cursor.getString(_cursorIndexOfReason);
                final int _tmpTransferType;
                _tmpTransferType = cursor.getInt(_cursorIndexOfTransferType);
                final int _tmpIsSuccess;
                _tmpIsSuccess = cursor.getInt(_cursorIndexOfIsSuccess);
                final int _tmpFormatted_is_success;
                _tmpFormatted_is_success = cursor.getInt(_cursorIndexOfFormattedIsSuccess);
                final String _tmpFormattedDate;
                _tmpFormattedDate = cursor.getString(_cursorIndexOfFormattedDate);
                final String _tmpFormattedSize;
                _tmpFormattedSize = cursor.getString(_cursorIndexOfFormattedSize);
                final String _tmpFormattedRent;
                _tmpFormattedRent = cursor.getString(_cursorIndexOfFormattedRent);
                final int _tmpFormattedPageViews;
                _tmpFormattedPageViews = cursor.getInt(_cursorIndexOfFormattedPageViews);
                final String _tmpFormattedFee;
                _tmpFormattedFee = cursor.getString(_cursorIndexOfFormattedFee);
                final String _tmpFormattedLocationNodes;
                _tmpFormattedLocationNodes = cursor.getString(_cursorIndexOfFormattedLocationNodes);
                final String _tmpFormattedIndustry;
                _tmpFormattedIndustry = cursor.getString(_cursorIndexOfFormattedIndustry);
                final String _tmpFormattedFinalIndustry;
                _tmpFormattedFinalIndustry = cursor.getString(_cursorIndexOfFormattedFinalIndustry);
                final String _tmpFormattedFinalLocationNode;
                _tmpFormattedFinalLocationNode = cursor.getString(_cursorIndexOfFormattedFinalLocationNode);
                final List<MenuItem> _tmpFormattedFacilities;
                final String _tmp_4;
                _tmp_4 = cursor.getString(_cursorIndexOfFormattedFacilities);
                _tmpFormattedFacilities = __menuListConverter.toObject(_tmp_4);
                final String _tmpRentView;
                _tmpRentView = cursor.getString(_cursorIndexOfRentView);
                final String _tmpViewOpening;
                _tmpViewOpening = cursor.getString(_cursorIndexOfViewOpening);
                final String _tmpViewCanEmpty;
                _tmpViewCanEmpty = cursor.getString(_cursorIndexOfViewCanEmpty);
                final int _tmpIsTop;
                _tmpIsTop = cursor.getInt(_cursorIndexOfIsTop);
                final int _tmpIsHot;
                _tmpIsHot = cursor.getInt(_cursorIndexOfIsHot);
                final int _tmpIsRecommend;
                _tmpIsRecommend = cursor.getInt(_cursorIndexOfIsRecommend);
                final int _tmpIsLargeOrder;
                _tmpIsLargeOrder = cursor.getInt(_cursorIndexOfIsLargeOrder);
                final String _tmpLargeOrderImg;
                _tmpLargeOrderImg = cursor.getString(_cursorIndexOfLargeOrderImg);
                final String _tmpCategory_acreage;
                _tmpCategory_acreage = cursor.getString(_cursorIndexOfCategoryAcreage);
                final String _tmpFormattedArea;
                _tmpFormattedArea = cursor.getString(_cursorIndexOfFormattedArea);
                final int _tmpIsVip;
                _tmpIsVip = cursor.getInt(_cursorIndexOfIsVip);
                final String _tmpData_type;
                _tmpData_type = cursor.getString(_cursorIndexOfDataType);
                final int _tmpJump_type;
                _tmpJump_type = cursor.getInt(_cursorIndexOfJumpType);
                final String _tmpJump_view;
                _tmpJump_view = cursor.getString(_cursorIndexOfJumpView);
                final String _tmpJump_param;
                _tmpJump_param = cursor.getString(_cursorIndexOfJumpParam);
                final Address _tmpAddress;
                if (! (cursor.isNull(_cursorIndexOfLocationNodes) && cursor.isNull(_cursorIndexOfAddressDetail) && cursor.isNull(_cursorIndexOfPostCode) && cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                  final List<LocationNode> _tmpLocationNodes;
                  final String _tmp_5;
                  _tmp_5 = cursor.getString(_cursorIndexOfLocationNodes);
                  _tmpLocationNodes = __locationNodeListConverter.toObject(_tmp_5);
                  final String _tmpAddressDetail;
                  _tmpAddressDetail = cursor.getString(_cursorIndexOfAddressDetail);
                  final int _tmpPostCode;
                  _tmpPostCode = cursor.getInt(_cursorIndexOfPostCode);
                  final Coordinates _tmpCoordinates;
                  if (! (cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                    final double _tmpLatitude;
                    _tmpLatitude = cursor.getDouble(_cursorIndexOfLatitude);
                    final double _tmpLongitude;
                    _tmpLongitude = cursor.getDouble(_cursorIndexOfLongitude);
                    _tmpCoordinates = new Coordinates(_tmpLatitude,_tmpLongitude);
                  }  else  {
                    _tmpCoordinates = null;
                  }
                  _tmpAddress = new Address(_tmpLocationNodes,_tmpAddressDetail,_tmpPostCode,_tmpCoordinates);
                }  else  {
                  _tmpAddress = null;
                }
                _tmpShop = new Shop(_tmpShopID,_tmpTitle,_tmpSize,_tmpRent,_tmpFee,_tmpAddress,_tmpLng,_tmpLat,_tmpNewLng,_tmpNewLat,_tmpIndustry,_tmpRunningState,_tmpIncludeFacilities,_tmpImage,_tmpImages,_tmpFloor,_tmpLabels,_tmpFacilities,_tmpAllFacilities,_tmpDescription,_tmpDescriptionUrl,_tmpEnvironment,_tmpReason,_tmpTransferType,_tmpIsSuccess,_tmpFormatted_is_success,_tmpFormattedDate,_tmpFormattedSize,_tmpFormattedRent,_tmpFormattedPageViews,_tmpFormattedFee,_tmpFormattedLocationNodes,_tmpFormattedIndustry,_tmpFormattedFinalIndustry,_tmpFormattedFinalLocationNode,_tmpFormattedFacilities,_tmpRentView,_tmpViewOpening,_tmpViewCanEmpty,_tmpIsTop,_tmpIsHot,_tmpIsRecommend,_tmpIsLargeOrder,_tmpLargeOrderImg,_tmpCategory_acreage,_tmpFormattedArea,_tmpIsVip,_tmpData_type,_tmpJump_type,_tmpJump_view,_tmpJump_param);
              }  else  {
                _tmpShop = null;
              }
              final User _tmpShopOwner;
              if (! (cursor.isNull(_cursorIndexOfUserID) && cursor.isNull(_cursorIndexOfAccount) && cursor.isNull(_cursorIndexOfToken) && cursor.isNull(_cursorIndexOfName) && cursor.isNull(_cursorIndexOfNickName) && cursor.isNull(_cursorIndexOfUserSex) && cursor.isNull(_cursorIndexOfIcon) && cursor.isNull(_cursorIndexOfLoginTimestamp) && cursor.isNull(_cursorIndexOfTipsMsg) && cursor.isNull(_cursorIndexOfLoginState) && cursor.isNull(_cursorIndexOfActualName) && cursor.isNull(_cursorIndexOfUserPhoneNumber) && cursor.isNull(_cursorIndexOfCityPathId) && cursor.isNull(_cursorIndexOfCityId) && cursor.isNull(_cursorIndexOfCityName) && cursor.isNull(_cursorIndexOfUserCityPath))) {
                _tmpShopOwner = new User();
                final int _tmpUserID;
                _tmpUserID = cursor.getInt(_cursorIndexOfUserID);
                _tmpShopOwner.setUserID(_tmpUserID);
                final String _tmpAccount;
                _tmpAccount = cursor.getString(_cursorIndexOfAccount);
                _tmpShopOwner.setAccount(_tmpAccount);
                final String _tmpToken;
                _tmpToken = cursor.getString(_cursorIndexOfToken);
                _tmpShopOwner.setToken(_tmpToken);
                final String _tmpName;
                _tmpName = cursor.getString(_cursorIndexOfName);
                _tmpShopOwner.setName(_tmpName);
                final String _tmpNickName;
                _tmpNickName = cursor.getString(_cursorIndexOfNickName);
                _tmpShopOwner.setNickName(_tmpNickName);
                final int _tmpUserSex;
                _tmpUserSex = cursor.getInt(_cursorIndexOfUserSex);
                _tmpShopOwner.setUserSex(_tmpUserSex);
                final String _tmpIcon;
                _tmpIcon = cursor.getString(_cursorIndexOfIcon);
                _tmpShopOwner.setIcon(_tmpIcon);
                final long _tmpLoginTimestamp;
                _tmpLoginTimestamp = cursor.getLong(_cursorIndexOfLoginTimestamp);
                _tmpShopOwner.setLoginTimestamp(_tmpLoginTimestamp);
                final String _tmpTipsMsg;
                _tmpTipsMsg = cursor.getString(_cursorIndexOfTipsMsg);
                _tmpShopOwner.setTipsMsg(_tmpTipsMsg);
                final int _tmpLoginState;
                _tmpLoginState = cursor.getInt(_cursorIndexOfLoginState);
                _tmpShopOwner.setLoginState(_tmpLoginState);
                final String _tmpActualName;
                _tmpActualName = cursor.getString(_cursorIndexOfActualName);
                _tmpShopOwner.setActualName(_tmpActualName);
                final String _tmpUserPhoneNumber;
                _tmpUserPhoneNumber = cursor.getString(_cursorIndexOfUserPhoneNumber);
                _tmpShopOwner.setUserPhoneNumber(_tmpUserPhoneNumber);
                final String _tmpCityPathId;
                _tmpCityPathId = cursor.getString(_cursorIndexOfCityPathId);
                _tmpShopOwner.setCityPathId(_tmpCityPathId);
                final int _tmpCityId;
                _tmpCityId = cursor.getInt(_cursorIndexOfCityId);
                _tmpShopOwner.setCityId(_tmpCityId);
                final String _tmpCityName;
                _tmpCityName = cursor.getString(_cursorIndexOfCityName);
                _tmpShopOwner.setCityName(_tmpCityName);
                final String _tmpUserCityPath;
                _tmpUserCityPath = cursor.getString(_cursorIndexOfUserCityPath);
                _tmpShopOwner.setUserCityPath(_tmpUserCityPath);
              }  else  {
                _tmpShopOwner = null;
              }
              final Order.Companion.State _tmpState;
              if (! (cursor.isNull(_cursorIndexOfText) && cursor.isNull(_cursorIndexOfColor))) {
                final String _tmpText;
                _tmpText = cursor.getString(_cursorIndexOfText);
                final String _tmpColor;
                _tmpColor = cursor.getString(_cursorIndexOfColor);
                _tmpState = new Order.Companion.State(_tmpText,_tmpColor);
              }  else  {
                _tmpState = null;
              }
              _item = new Order();
              final long _tmpOrderID;
              _tmpOrderID = cursor.getLong(_cursorIndexOfOrderID);
              _item.setOrderID(_tmpOrderID);
              final int _tmpOrderType;
              _tmpOrderType = cursor.getInt(_cursorIndexOfOrderType);
              _item.setOrderType(_tmpOrderType);
              final int _tmpFavorite;
              _tmpFavorite = cursor.getInt(_cursorIndexOfFavorite);
              _item.setFavorite(_tmpFavorite);
              _item.setShop(_tmpShop);
              _item.setShopOwner(_tmpShopOwner);
              _item.setState(_tmpState);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public DataSource.Factory<Integer, Order> getLocalOrdersByTransferTypeWithLimit(final int type,
      final int limit) {
    final String _sql = "select * from table_orders where _transfer_type = ? limit ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, type);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    return new DataSource.Factory<Integer, Order>() {
      @Override
      public LimitOffsetDataSource<Order> create() {
        return new LimitOffsetDataSource<Order>(__db, _statement, false , "table_orders") {
          @Override
          protected List<Order> convertRows(Cursor cursor) {
            final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(cursor, "_order_id");
            final int _cursorIndexOfOrderType = CursorUtil.getColumnIndexOrThrow(cursor, "_order_type");
            final int _cursorIndexOfFavorite = CursorUtil.getColumnIndexOrThrow(cursor, "_favorite");
            final int _cursorIndexOfShopID = CursorUtil.getColumnIndexOrThrow(cursor, "_shop_id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "_title");
            final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(cursor, "_size");
            final int _cursorIndexOfRent = CursorUtil.getColumnIndexOrThrow(cursor, "_rent");
            final int _cursorIndexOfFee = CursorUtil.getColumnIndexOrThrow(cursor, "_fee");
            final int _cursorIndexOfLng = CursorUtil.getColumnIndexOrThrow(cursor, "_lng");
            final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(cursor, "_lat");
            final int _cursorIndexOfNewLng = CursorUtil.getColumnIndexOrThrow(cursor, "_new_lng");
            final int _cursorIndexOfNewLat = CursorUtil.getColumnIndexOrThrow(cursor, "_new_lat");
            final int _cursorIndexOfIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_industry");
            final int _cursorIndexOfRunningState = CursorUtil.getColumnIndexOrThrow(cursor, "_running_state");
            final int _cursorIndexOfIncludeFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_exclusive");
            final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(cursor, "_image");
            final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(cursor, "_images");
            final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(cursor, "_floor");
            final int _cursorIndexOfLabels = CursorUtil.getColumnIndexOrThrow(cursor, "_labels");
            final int _cursorIndexOfFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_facilities");
            final int _cursorIndexOfAllFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_allfacilities");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "_description");
            final int _cursorIndexOfDescriptionUrl = CursorUtil.getColumnIndexOrThrow(cursor, "_description_url");
            final int _cursorIndexOfEnvironment = CursorUtil.getColumnIndexOrThrow(cursor, "_environment");
            final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(cursor, "_reason");
            final int _cursorIndexOfTransferType = CursorUtil.getColumnIndexOrThrow(cursor, "_transfer_type");
            final int _cursorIndexOfIsSuccess = CursorUtil.getColumnIndexOrThrow(cursor, "_is_success");
            final int _cursorIndexOfFormattedIsSuccess = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_is_success");
            final int _cursorIndexOfFormattedDate = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_date");
            final int _cursorIndexOfFormattedSize = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_size");
            final int _cursorIndexOfFormattedRent = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_rent");
            final int _cursorIndexOfFormattedPageViews = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_page_views");
            final int _cursorIndexOfFormattedFee = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_fee");
            final int _cursorIndexOfFormattedLocationNodes = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_location_nodes");
            final int _cursorIndexOfFormattedIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_industry");
            final int _cursorIndexOfFormattedFinalIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_final_industry");
            final int _cursorIndexOfFormattedFinalLocationNode = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_final_location_node");
            final int _cursorIndexOfFormattedFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "formattedFacilities");
            final int _cursorIndexOfRentView = CursorUtil.getColumnIndexOrThrow(cursor, "_rent_view");
            final int _cursorIndexOfViewOpening = CursorUtil.getColumnIndexOrThrow(cursor, "_view_opening");
            final int _cursorIndexOfViewCanEmpty = CursorUtil.getColumnIndexOrThrow(cursor, "_view_can_empty");
            final int _cursorIndexOfIsTop = CursorUtil.getColumnIndexOrThrow(cursor, "_is_top");
            final int _cursorIndexOfIsHot = CursorUtil.getColumnIndexOrThrow(cursor, "_is_hot");
            final int _cursorIndexOfIsRecommend = CursorUtil.getColumnIndexOrThrow(cursor, "_is_recommend");
            final int _cursorIndexOfIsLargeOrder = CursorUtil.getColumnIndexOrThrow(cursor, "_is_large_order");
            final int _cursorIndexOfLargeOrderImg = CursorUtil.getColumnIndexOrThrow(cursor, "_large_order_img");
            final int _cursorIndexOfCategoryAcreage = CursorUtil.getColumnIndexOrThrow(cursor, "_category_acreage");
            final int _cursorIndexOfFormattedArea = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_area");
            final int _cursorIndexOfIsVip = CursorUtil.getColumnIndexOrThrow(cursor, "_is_vip");
            final int _cursorIndexOfDataType = CursorUtil.getColumnIndexOrThrow(cursor, "_data_type");
            final int _cursorIndexOfJumpType = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_type");
            final int _cursorIndexOfJumpView = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_view");
            final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_param");
            final int _cursorIndexOfLocationNodes = CursorUtil.getColumnIndexOrThrow(cursor, "_location_nodes");
            final int _cursorIndexOfAddressDetail = CursorUtil.getColumnIndexOrThrow(cursor, "_address_description");
            final int _cursorIndexOfPostCode = CursorUtil.getColumnIndexOrThrow(cursor, "_post_code");
            final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(cursor, "_latitude");
            final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(cursor, "_longitude");
            final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(cursor, "_user_id");
            final int _cursorIndexOfAccount = CursorUtil.getColumnIndexOrThrow(cursor, "_account");
            final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(cursor, "_token");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(cursor, "_name");
            final int _cursorIndexOfNickName = CursorUtil.getColumnIndexOrThrow(cursor, "_nickname");
            final int _cursorIndexOfUserSex = CursorUtil.getColumnIndexOrThrow(cursor, "_user_sex");
            final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(cursor, "_icon");
            final int _cursorIndexOfLoginTimestamp = CursorUtil.getColumnIndexOrThrow(cursor, "_login_timestamp");
            final int _cursorIndexOfTipsMsg = CursorUtil.getColumnIndexOrThrow(cursor, "_tips_msg");
            final int _cursorIndexOfLoginState = CursorUtil.getColumnIndexOrThrow(cursor, "_login_state");
            final int _cursorIndexOfActualName = CursorUtil.getColumnIndexOrThrow(cursor, "_user_contact_name");
            final int _cursorIndexOfUserPhoneNumber = CursorUtil.getColumnIndexOrThrow(cursor, "_user_contact_phone");
            final int _cursorIndexOfCityPathId = CursorUtil.getColumnIndexOrThrow(cursor, "_city_path_id");
            final int _cursorIndexOfCityId = CursorUtil.getColumnIndexOrThrow(cursor, "_city_id");
            final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(cursor, "_view_city_name");
            final int _cursorIndexOfUserCityPath = CursorUtil.getColumnIndexOrThrow(cursor, "_view_city_path");
            final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(cursor, "_state_text");
            final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(cursor, "_state_color");
            final List<Order> _res = new ArrayList<Order>(cursor.getCount());
            while(cursor.moveToNext()) {
              final Order _item;
              final Shop _tmpShop;
              if (! (cursor.isNull(_cursorIndexOfShopID) && cursor.isNull(_cursorIndexOfTitle) && cursor.isNull(_cursorIndexOfSize) && cursor.isNull(_cursorIndexOfRent) && cursor.isNull(_cursorIndexOfFee) && cursor.isNull(_cursorIndexOfLng) && cursor.isNull(_cursorIndexOfLat) && cursor.isNull(_cursorIndexOfNewLng) && cursor.isNull(_cursorIndexOfNewLat) && cursor.isNull(_cursorIndexOfIndustry) && cursor.isNull(_cursorIndexOfRunningState) && cursor.isNull(_cursorIndexOfIncludeFacilities) && cursor.isNull(_cursorIndexOfImage) && cursor.isNull(_cursorIndexOfImages) && cursor.isNull(_cursorIndexOfFloor) && cursor.isNull(_cursorIndexOfLabels) && cursor.isNull(_cursorIndexOfFacilities) && cursor.isNull(_cursorIndexOfAllFacilities) && cursor.isNull(_cursorIndexOfDescription) && cursor.isNull(_cursorIndexOfDescriptionUrl) && cursor.isNull(_cursorIndexOfEnvironment) && cursor.isNull(_cursorIndexOfReason) && cursor.isNull(_cursorIndexOfTransferType) && cursor.isNull(_cursorIndexOfIsSuccess) && cursor.isNull(_cursorIndexOfFormattedIsSuccess) && cursor.isNull(_cursorIndexOfFormattedDate) && cursor.isNull(_cursorIndexOfFormattedSize) && cursor.isNull(_cursorIndexOfFormattedRent) && cursor.isNull(_cursorIndexOfFormattedPageViews) && cursor.isNull(_cursorIndexOfFormattedFee) && cursor.isNull(_cursorIndexOfFormattedLocationNodes) && cursor.isNull(_cursorIndexOfFormattedIndustry) && cursor.isNull(_cursorIndexOfFormattedFinalIndustry) && cursor.isNull(_cursorIndexOfFormattedFinalLocationNode) && cursor.isNull(_cursorIndexOfFormattedFacilities) && cursor.isNull(_cursorIndexOfRentView) && cursor.isNull(_cursorIndexOfViewOpening) && cursor.isNull(_cursorIndexOfViewCanEmpty) && cursor.isNull(_cursorIndexOfIsTop) && cursor.isNull(_cursorIndexOfIsHot) && cursor.isNull(_cursorIndexOfIsRecommend) && cursor.isNull(_cursorIndexOfIsLargeOrder) && cursor.isNull(_cursorIndexOfLargeOrderImg) && cursor.isNull(_cursorIndexOfCategoryAcreage) && cursor.isNull(_cursorIndexOfFormattedArea) && cursor.isNull(_cursorIndexOfIsVip) && cursor.isNull(_cursorIndexOfDataType) && cursor.isNull(_cursorIndexOfJumpType) && cursor.isNull(_cursorIndexOfJumpView) && cursor.isNull(_cursorIndexOfJumpParam) && cursor.isNull(_cursorIndexOfLocationNodes) && cursor.isNull(_cursorIndexOfAddressDetail) && cursor.isNull(_cursorIndexOfPostCode) && cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                final long _tmpShopID;
                _tmpShopID = cursor.getLong(_cursorIndexOfShopID);
                final String _tmpTitle;
                _tmpTitle = cursor.getString(_cursorIndexOfTitle);
                final double _tmpSize;
                _tmpSize = cursor.getDouble(_cursorIndexOfSize);
                final double _tmpRent;
                _tmpRent = cursor.getDouble(_cursorIndexOfRent);
                final double _tmpFee;
                _tmpFee = cursor.getDouble(_cursorIndexOfFee);
                final String _tmpLng;
                _tmpLng = cursor.getString(_cursorIndexOfLng);
                final String _tmpLat;
                _tmpLat = cursor.getString(_cursorIndexOfLat);
                final double _tmpNewLng;
                _tmpNewLng = cursor.getDouble(_cursorIndexOfNewLng);
                final double _tmpNewLat;
                _tmpNewLat = cursor.getDouble(_cursorIndexOfNewLat);
                final String _tmpIndustry;
                _tmpIndustry = cursor.getString(_cursorIndexOfIndustry);
                final int _tmpRunningState;
                _tmpRunningState = cursor.getInt(_cursorIndexOfRunningState);
                final int _tmpIncludeFacilities;
                _tmpIncludeFacilities = cursor.getInt(_cursorIndexOfIncludeFacilities);
                final String _tmpImage;
                _tmpImage = cursor.getString(_cursorIndexOfImage);
                final List<String> _tmpImages;
                final String _tmp;
                _tmp = cursor.getString(_cursorIndexOfImages);
                _tmpImages = __stringListConverter.fromString(_tmp);
                final int _tmpFloor;
                _tmpFloor = cursor.getInt(_cursorIndexOfFloor);
                final List<MenuItem> _tmpLabels;
                final String _tmp_1;
                _tmp_1 = cursor.getString(_cursorIndexOfLabels);
                _tmpLabels = __menuListConverter.toObject(_tmp_1);
                final List<MenuItem> _tmpFacilities;
                final String _tmp_2;
                _tmp_2 = cursor.getString(_cursorIndexOfFacilities);
                _tmpFacilities = __menuListConverter.toObject(_tmp_2);
                final List<MenuItem> _tmpAllFacilities;
                final String _tmp_3;
                _tmp_3 = cursor.getString(_cursorIndexOfAllFacilities);
                _tmpAllFacilities = __menuListConverter.toObject(_tmp_3);
                final String _tmpDescription;
                _tmpDescription = cursor.getString(_cursorIndexOfDescription);
                final String _tmpDescriptionUrl;
                _tmpDescriptionUrl = cursor.getString(_cursorIndexOfDescriptionUrl);
                final String _tmpEnvironment;
                _tmpEnvironment = cursor.getString(_cursorIndexOfEnvironment);
                final String _tmpReason;
                _tmpReason = cursor.getString(_cursorIndexOfReason);
                final int _tmpTransferType;
                _tmpTransferType = cursor.getInt(_cursorIndexOfTransferType);
                final int _tmpIsSuccess;
                _tmpIsSuccess = cursor.getInt(_cursorIndexOfIsSuccess);
                final int _tmpFormatted_is_success;
                _tmpFormatted_is_success = cursor.getInt(_cursorIndexOfFormattedIsSuccess);
                final String _tmpFormattedDate;
                _tmpFormattedDate = cursor.getString(_cursorIndexOfFormattedDate);
                final String _tmpFormattedSize;
                _tmpFormattedSize = cursor.getString(_cursorIndexOfFormattedSize);
                final String _tmpFormattedRent;
                _tmpFormattedRent = cursor.getString(_cursorIndexOfFormattedRent);
                final int _tmpFormattedPageViews;
                _tmpFormattedPageViews = cursor.getInt(_cursorIndexOfFormattedPageViews);
                final String _tmpFormattedFee;
                _tmpFormattedFee = cursor.getString(_cursorIndexOfFormattedFee);
                final String _tmpFormattedLocationNodes;
                _tmpFormattedLocationNodes = cursor.getString(_cursorIndexOfFormattedLocationNodes);
                final String _tmpFormattedIndustry;
                _tmpFormattedIndustry = cursor.getString(_cursorIndexOfFormattedIndustry);
                final String _tmpFormattedFinalIndustry;
                _tmpFormattedFinalIndustry = cursor.getString(_cursorIndexOfFormattedFinalIndustry);
                final String _tmpFormattedFinalLocationNode;
                _tmpFormattedFinalLocationNode = cursor.getString(_cursorIndexOfFormattedFinalLocationNode);
                final List<MenuItem> _tmpFormattedFacilities;
                final String _tmp_4;
                _tmp_4 = cursor.getString(_cursorIndexOfFormattedFacilities);
                _tmpFormattedFacilities = __menuListConverter.toObject(_tmp_4);
                final String _tmpRentView;
                _tmpRentView = cursor.getString(_cursorIndexOfRentView);
                final String _tmpViewOpening;
                _tmpViewOpening = cursor.getString(_cursorIndexOfViewOpening);
                final String _tmpViewCanEmpty;
                _tmpViewCanEmpty = cursor.getString(_cursorIndexOfViewCanEmpty);
                final int _tmpIsTop;
                _tmpIsTop = cursor.getInt(_cursorIndexOfIsTop);
                final int _tmpIsHot;
                _tmpIsHot = cursor.getInt(_cursorIndexOfIsHot);
                final int _tmpIsRecommend;
                _tmpIsRecommend = cursor.getInt(_cursorIndexOfIsRecommend);
                final int _tmpIsLargeOrder;
                _tmpIsLargeOrder = cursor.getInt(_cursorIndexOfIsLargeOrder);
                final String _tmpLargeOrderImg;
                _tmpLargeOrderImg = cursor.getString(_cursorIndexOfLargeOrderImg);
                final String _tmpCategory_acreage;
                _tmpCategory_acreage = cursor.getString(_cursorIndexOfCategoryAcreage);
                final String _tmpFormattedArea;
                _tmpFormattedArea = cursor.getString(_cursorIndexOfFormattedArea);
                final int _tmpIsVip;
                _tmpIsVip = cursor.getInt(_cursorIndexOfIsVip);
                final String _tmpData_type;
                _tmpData_type = cursor.getString(_cursorIndexOfDataType);
                final int _tmpJump_type;
                _tmpJump_type = cursor.getInt(_cursorIndexOfJumpType);
                final String _tmpJump_view;
                _tmpJump_view = cursor.getString(_cursorIndexOfJumpView);
                final String _tmpJump_param;
                _tmpJump_param = cursor.getString(_cursorIndexOfJumpParam);
                final Address _tmpAddress;
                if (! (cursor.isNull(_cursorIndexOfLocationNodes) && cursor.isNull(_cursorIndexOfAddressDetail) && cursor.isNull(_cursorIndexOfPostCode) && cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                  final List<LocationNode> _tmpLocationNodes;
                  final String _tmp_5;
                  _tmp_5 = cursor.getString(_cursorIndexOfLocationNodes);
                  _tmpLocationNodes = __locationNodeListConverter.toObject(_tmp_5);
                  final String _tmpAddressDetail;
                  _tmpAddressDetail = cursor.getString(_cursorIndexOfAddressDetail);
                  final int _tmpPostCode;
                  _tmpPostCode = cursor.getInt(_cursorIndexOfPostCode);
                  final Coordinates _tmpCoordinates;
                  if (! (cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                    final double _tmpLatitude;
                    _tmpLatitude = cursor.getDouble(_cursorIndexOfLatitude);
                    final double _tmpLongitude;
                    _tmpLongitude = cursor.getDouble(_cursorIndexOfLongitude);
                    _tmpCoordinates = new Coordinates(_tmpLatitude,_tmpLongitude);
                  }  else  {
                    _tmpCoordinates = null;
                  }
                  _tmpAddress = new Address(_tmpLocationNodes,_tmpAddressDetail,_tmpPostCode,_tmpCoordinates);
                }  else  {
                  _tmpAddress = null;
                }
                _tmpShop = new Shop(_tmpShopID,_tmpTitle,_tmpSize,_tmpRent,_tmpFee,_tmpAddress,_tmpLng,_tmpLat,_tmpNewLng,_tmpNewLat,_tmpIndustry,_tmpRunningState,_tmpIncludeFacilities,_tmpImage,_tmpImages,_tmpFloor,_tmpLabels,_tmpFacilities,_tmpAllFacilities,_tmpDescription,_tmpDescriptionUrl,_tmpEnvironment,_tmpReason,_tmpTransferType,_tmpIsSuccess,_tmpFormatted_is_success,_tmpFormattedDate,_tmpFormattedSize,_tmpFormattedRent,_tmpFormattedPageViews,_tmpFormattedFee,_tmpFormattedLocationNodes,_tmpFormattedIndustry,_tmpFormattedFinalIndustry,_tmpFormattedFinalLocationNode,_tmpFormattedFacilities,_tmpRentView,_tmpViewOpening,_tmpViewCanEmpty,_tmpIsTop,_tmpIsHot,_tmpIsRecommend,_tmpIsLargeOrder,_tmpLargeOrderImg,_tmpCategory_acreage,_tmpFormattedArea,_tmpIsVip,_tmpData_type,_tmpJump_type,_tmpJump_view,_tmpJump_param);
              }  else  {
                _tmpShop = null;
              }
              final User _tmpShopOwner;
              if (! (cursor.isNull(_cursorIndexOfUserID) && cursor.isNull(_cursorIndexOfAccount) && cursor.isNull(_cursorIndexOfToken) && cursor.isNull(_cursorIndexOfName) && cursor.isNull(_cursorIndexOfNickName) && cursor.isNull(_cursorIndexOfUserSex) && cursor.isNull(_cursorIndexOfIcon) && cursor.isNull(_cursorIndexOfLoginTimestamp) && cursor.isNull(_cursorIndexOfTipsMsg) && cursor.isNull(_cursorIndexOfLoginState) && cursor.isNull(_cursorIndexOfActualName) && cursor.isNull(_cursorIndexOfUserPhoneNumber) && cursor.isNull(_cursorIndexOfCityPathId) && cursor.isNull(_cursorIndexOfCityId) && cursor.isNull(_cursorIndexOfCityName) && cursor.isNull(_cursorIndexOfUserCityPath))) {
                _tmpShopOwner = new User();
                final int _tmpUserID;
                _tmpUserID = cursor.getInt(_cursorIndexOfUserID);
                _tmpShopOwner.setUserID(_tmpUserID);
                final String _tmpAccount;
                _tmpAccount = cursor.getString(_cursorIndexOfAccount);
                _tmpShopOwner.setAccount(_tmpAccount);
                final String _tmpToken;
                _tmpToken = cursor.getString(_cursorIndexOfToken);
                _tmpShopOwner.setToken(_tmpToken);
                final String _tmpName;
                _tmpName = cursor.getString(_cursorIndexOfName);
                _tmpShopOwner.setName(_tmpName);
                final String _tmpNickName;
                _tmpNickName = cursor.getString(_cursorIndexOfNickName);
                _tmpShopOwner.setNickName(_tmpNickName);
                final int _tmpUserSex;
                _tmpUserSex = cursor.getInt(_cursorIndexOfUserSex);
                _tmpShopOwner.setUserSex(_tmpUserSex);
                final String _tmpIcon;
                _tmpIcon = cursor.getString(_cursorIndexOfIcon);
                _tmpShopOwner.setIcon(_tmpIcon);
                final long _tmpLoginTimestamp;
                _tmpLoginTimestamp = cursor.getLong(_cursorIndexOfLoginTimestamp);
                _tmpShopOwner.setLoginTimestamp(_tmpLoginTimestamp);
                final String _tmpTipsMsg;
                _tmpTipsMsg = cursor.getString(_cursorIndexOfTipsMsg);
                _tmpShopOwner.setTipsMsg(_tmpTipsMsg);
                final int _tmpLoginState;
                _tmpLoginState = cursor.getInt(_cursorIndexOfLoginState);
                _tmpShopOwner.setLoginState(_tmpLoginState);
                final String _tmpActualName;
                _tmpActualName = cursor.getString(_cursorIndexOfActualName);
                _tmpShopOwner.setActualName(_tmpActualName);
                final String _tmpUserPhoneNumber;
                _tmpUserPhoneNumber = cursor.getString(_cursorIndexOfUserPhoneNumber);
                _tmpShopOwner.setUserPhoneNumber(_tmpUserPhoneNumber);
                final String _tmpCityPathId;
                _tmpCityPathId = cursor.getString(_cursorIndexOfCityPathId);
                _tmpShopOwner.setCityPathId(_tmpCityPathId);
                final int _tmpCityId;
                _tmpCityId = cursor.getInt(_cursorIndexOfCityId);
                _tmpShopOwner.setCityId(_tmpCityId);
                final String _tmpCityName;
                _tmpCityName = cursor.getString(_cursorIndexOfCityName);
                _tmpShopOwner.setCityName(_tmpCityName);
                final String _tmpUserCityPath;
                _tmpUserCityPath = cursor.getString(_cursorIndexOfUserCityPath);
                _tmpShopOwner.setUserCityPath(_tmpUserCityPath);
              }  else  {
                _tmpShopOwner = null;
              }
              final Order.Companion.State _tmpState;
              if (! (cursor.isNull(_cursorIndexOfText) && cursor.isNull(_cursorIndexOfColor))) {
                final String _tmpText;
                _tmpText = cursor.getString(_cursorIndexOfText);
                final String _tmpColor;
                _tmpColor = cursor.getString(_cursorIndexOfColor);
                _tmpState = new Order.Companion.State(_tmpText,_tmpColor);
              }  else  {
                _tmpState = null;
              }
              _item = new Order();
              final long _tmpOrderID;
              _tmpOrderID = cursor.getLong(_cursorIndexOfOrderID);
              _item.setOrderID(_tmpOrderID);
              final int _tmpOrderType;
              _tmpOrderType = cursor.getInt(_cursorIndexOfOrderType);
              _item.setOrderType(_tmpOrderType);
              final int _tmpFavorite;
              _tmpFavorite = cursor.getInt(_cursorIndexOfFavorite);
              _item.setFavorite(_tmpFavorite);
              _item.setShop(_tmpShop);
              _item.setShopOwner(_tmpShopOwner);
              _item.setState(_tmpState);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public DataSource.Factory<Integer, Order> getLocalOrdersByOrderType(final int orderType) {
    final String _sql = "select * from table_orders where _order_type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderType);
    return new DataSource.Factory<Integer, Order>() {
      @Override
      public LimitOffsetDataSource<Order> create() {
        return new LimitOffsetDataSource<Order>(__db, _statement, false , "table_orders") {
          @Override
          protected List<Order> convertRows(Cursor cursor) {
            final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(cursor, "_order_id");
            final int _cursorIndexOfOrderType = CursorUtil.getColumnIndexOrThrow(cursor, "_order_type");
            final int _cursorIndexOfFavorite = CursorUtil.getColumnIndexOrThrow(cursor, "_favorite");
            final int _cursorIndexOfShopID = CursorUtil.getColumnIndexOrThrow(cursor, "_shop_id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "_title");
            final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(cursor, "_size");
            final int _cursorIndexOfRent = CursorUtil.getColumnIndexOrThrow(cursor, "_rent");
            final int _cursorIndexOfFee = CursorUtil.getColumnIndexOrThrow(cursor, "_fee");
            final int _cursorIndexOfLng = CursorUtil.getColumnIndexOrThrow(cursor, "_lng");
            final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(cursor, "_lat");
            final int _cursorIndexOfNewLng = CursorUtil.getColumnIndexOrThrow(cursor, "_new_lng");
            final int _cursorIndexOfNewLat = CursorUtil.getColumnIndexOrThrow(cursor, "_new_lat");
            final int _cursorIndexOfIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_industry");
            final int _cursorIndexOfRunningState = CursorUtil.getColumnIndexOrThrow(cursor, "_running_state");
            final int _cursorIndexOfIncludeFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_exclusive");
            final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(cursor, "_image");
            final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(cursor, "_images");
            final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(cursor, "_floor");
            final int _cursorIndexOfLabels = CursorUtil.getColumnIndexOrThrow(cursor, "_labels");
            final int _cursorIndexOfFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_facilities");
            final int _cursorIndexOfAllFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "_allfacilities");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "_description");
            final int _cursorIndexOfDescriptionUrl = CursorUtil.getColumnIndexOrThrow(cursor, "_description_url");
            final int _cursorIndexOfEnvironment = CursorUtil.getColumnIndexOrThrow(cursor, "_environment");
            final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(cursor, "_reason");
            final int _cursorIndexOfTransferType = CursorUtil.getColumnIndexOrThrow(cursor, "_transfer_type");
            final int _cursorIndexOfIsSuccess = CursorUtil.getColumnIndexOrThrow(cursor, "_is_success");
            final int _cursorIndexOfFormattedIsSuccess = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_is_success");
            final int _cursorIndexOfFormattedDate = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_date");
            final int _cursorIndexOfFormattedSize = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_size");
            final int _cursorIndexOfFormattedRent = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_rent");
            final int _cursorIndexOfFormattedPageViews = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_page_views");
            final int _cursorIndexOfFormattedFee = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_fee");
            final int _cursorIndexOfFormattedLocationNodes = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_location_nodes");
            final int _cursorIndexOfFormattedIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_industry");
            final int _cursorIndexOfFormattedFinalIndustry = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_final_industry");
            final int _cursorIndexOfFormattedFinalLocationNode = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_final_location_node");
            final int _cursorIndexOfFormattedFacilities = CursorUtil.getColumnIndexOrThrow(cursor, "formattedFacilities");
            final int _cursorIndexOfRentView = CursorUtil.getColumnIndexOrThrow(cursor, "_rent_view");
            final int _cursorIndexOfViewOpening = CursorUtil.getColumnIndexOrThrow(cursor, "_view_opening");
            final int _cursorIndexOfViewCanEmpty = CursorUtil.getColumnIndexOrThrow(cursor, "_view_can_empty");
            final int _cursorIndexOfIsTop = CursorUtil.getColumnIndexOrThrow(cursor, "_is_top");
            final int _cursorIndexOfIsHot = CursorUtil.getColumnIndexOrThrow(cursor, "_is_hot");
            final int _cursorIndexOfIsRecommend = CursorUtil.getColumnIndexOrThrow(cursor, "_is_recommend");
            final int _cursorIndexOfIsLargeOrder = CursorUtil.getColumnIndexOrThrow(cursor, "_is_large_order");
            final int _cursorIndexOfLargeOrderImg = CursorUtil.getColumnIndexOrThrow(cursor, "_large_order_img");
            final int _cursorIndexOfCategoryAcreage = CursorUtil.getColumnIndexOrThrow(cursor, "_category_acreage");
            final int _cursorIndexOfFormattedArea = CursorUtil.getColumnIndexOrThrow(cursor, "_formatted_area");
            final int _cursorIndexOfIsVip = CursorUtil.getColumnIndexOrThrow(cursor, "_is_vip");
            final int _cursorIndexOfDataType = CursorUtil.getColumnIndexOrThrow(cursor, "_data_type");
            final int _cursorIndexOfJumpType = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_type");
            final int _cursorIndexOfJumpView = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_view");
            final int _cursorIndexOfJumpParam = CursorUtil.getColumnIndexOrThrow(cursor, "_jump_param");
            final int _cursorIndexOfLocationNodes = CursorUtil.getColumnIndexOrThrow(cursor, "_location_nodes");
            final int _cursorIndexOfAddressDetail = CursorUtil.getColumnIndexOrThrow(cursor, "_address_description");
            final int _cursorIndexOfPostCode = CursorUtil.getColumnIndexOrThrow(cursor, "_post_code");
            final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(cursor, "_latitude");
            final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(cursor, "_longitude");
            final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(cursor, "_user_id");
            final int _cursorIndexOfAccount = CursorUtil.getColumnIndexOrThrow(cursor, "_account");
            final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(cursor, "_token");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(cursor, "_name");
            final int _cursorIndexOfNickName = CursorUtil.getColumnIndexOrThrow(cursor, "_nickname");
            final int _cursorIndexOfUserSex = CursorUtil.getColumnIndexOrThrow(cursor, "_user_sex");
            final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(cursor, "_icon");
            final int _cursorIndexOfLoginTimestamp = CursorUtil.getColumnIndexOrThrow(cursor, "_login_timestamp");
            final int _cursorIndexOfTipsMsg = CursorUtil.getColumnIndexOrThrow(cursor, "_tips_msg");
            final int _cursorIndexOfLoginState = CursorUtil.getColumnIndexOrThrow(cursor, "_login_state");
            final int _cursorIndexOfActualName = CursorUtil.getColumnIndexOrThrow(cursor, "_user_contact_name");
            final int _cursorIndexOfUserPhoneNumber = CursorUtil.getColumnIndexOrThrow(cursor, "_user_contact_phone");
            final int _cursorIndexOfCityPathId = CursorUtil.getColumnIndexOrThrow(cursor, "_city_path_id");
            final int _cursorIndexOfCityId = CursorUtil.getColumnIndexOrThrow(cursor, "_city_id");
            final int _cursorIndexOfCityName = CursorUtil.getColumnIndexOrThrow(cursor, "_view_city_name");
            final int _cursorIndexOfUserCityPath = CursorUtil.getColumnIndexOrThrow(cursor, "_view_city_path");
            final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(cursor, "_state_text");
            final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(cursor, "_state_color");
            final List<Order> _res = new ArrayList<Order>(cursor.getCount());
            while(cursor.moveToNext()) {
              final Order _item;
              final Shop _tmpShop;
              if (! (cursor.isNull(_cursorIndexOfShopID) && cursor.isNull(_cursorIndexOfTitle) && cursor.isNull(_cursorIndexOfSize) && cursor.isNull(_cursorIndexOfRent) && cursor.isNull(_cursorIndexOfFee) && cursor.isNull(_cursorIndexOfLng) && cursor.isNull(_cursorIndexOfLat) && cursor.isNull(_cursorIndexOfNewLng) && cursor.isNull(_cursorIndexOfNewLat) && cursor.isNull(_cursorIndexOfIndustry) && cursor.isNull(_cursorIndexOfRunningState) && cursor.isNull(_cursorIndexOfIncludeFacilities) && cursor.isNull(_cursorIndexOfImage) && cursor.isNull(_cursorIndexOfImages) && cursor.isNull(_cursorIndexOfFloor) && cursor.isNull(_cursorIndexOfLabels) && cursor.isNull(_cursorIndexOfFacilities) && cursor.isNull(_cursorIndexOfAllFacilities) && cursor.isNull(_cursorIndexOfDescription) && cursor.isNull(_cursorIndexOfDescriptionUrl) && cursor.isNull(_cursorIndexOfEnvironment) && cursor.isNull(_cursorIndexOfReason) && cursor.isNull(_cursorIndexOfTransferType) && cursor.isNull(_cursorIndexOfIsSuccess) && cursor.isNull(_cursorIndexOfFormattedIsSuccess) && cursor.isNull(_cursorIndexOfFormattedDate) && cursor.isNull(_cursorIndexOfFormattedSize) && cursor.isNull(_cursorIndexOfFormattedRent) && cursor.isNull(_cursorIndexOfFormattedPageViews) && cursor.isNull(_cursorIndexOfFormattedFee) && cursor.isNull(_cursorIndexOfFormattedLocationNodes) && cursor.isNull(_cursorIndexOfFormattedIndustry) && cursor.isNull(_cursorIndexOfFormattedFinalIndustry) && cursor.isNull(_cursorIndexOfFormattedFinalLocationNode) && cursor.isNull(_cursorIndexOfFormattedFacilities) && cursor.isNull(_cursorIndexOfRentView) && cursor.isNull(_cursorIndexOfViewOpening) && cursor.isNull(_cursorIndexOfViewCanEmpty) && cursor.isNull(_cursorIndexOfIsTop) && cursor.isNull(_cursorIndexOfIsHot) && cursor.isNull(_cursorIndexOfIsRecommend) && cursor.isNull(_cursorIndexOfIsLargeOrder) && cursor.isNull(_cursorIndexOfLargeOrderImg) && cursor.isNull(_cursorIndexOfCategoryAcreage) && cursor.isNull(_cursorIndexOfFormattedArea) && cursor.isNull(_cursorIndexOfIsVip) && cursor.isNull(_cursorIndexOfDataType) && cursor.isNull(_cursorIndexOfJumpType) && cursor.isNull(_cursorIndexOfJumpView) && cursor.isNull(_cursorIndexOfJumpParam) && cursor.isNull(_cursorIndexOfLocationNodes) && cursor.isNull(_cursorIndexOfAddressDetail) && cursor.isNull(_cursorIndexOfPostCode) && cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                final long _tmpShopID;
                _tmpShopID = cursor.getLong(_cursorIndexOfShopID);
                final String _tmpTitle;
                _tmpTitle = cursor.getString(_cursorIndexOfTitle);
                final double _tmpSize;
                _tmpSize = cursor.getDouble(_cursorIndexOfSize);
                final double _tmpRent;
                _tmpRent = cursor.getDouble(_cursorIndexOfRent);
                final double _tmpFee;
                _tmpFee = cursor.getDouble(_cursorIndexOfFee);
                final String _tmpLng;
                _tmpLng = cursor.getString(_cursorIndexOfLng);
                final String _tmpLat;
                _tmpLat = cursor.getString(_cursorIndexOfLat);
                final double _tmpNewLng;
                _tmpNewLng = cursor.getDouble(_cursorIndexOfNewLng);
                final double _tmpNewLat;
                _tmpNewLat = cursor.getDouble(_cursorIndexOfNewLat);
                final String _tmpIndustry;
                _tmpIndustry = cursor.getString(_cursorIndexOfIndustry);
                final int _tmpRunningState;
                _tmpRunningState = cursor.getInt(_cursorIndexOfRunningState);
                final int _tmpIncludeFacilities;
                _tmpIncludeFacilities = cursor.getInt(_cursorIndexOfIncludeFacilities);
                final String _tmpImage;
                _tmpImage = cursor.getString(_cursorIndexOfImage);
                final List<String> _tmpImages;
                final String _tmp;
                _tmp = cursor.getString(_cursorIndexOfImages);
                _tmpImages = __stringListConverter.fromString(_tmp);
                final int _tmpFloor;
                _tmpFloor = cursor.getInt(_cursorIndexOfFloor);
                final List<MenuItem> _tmpLabels;
                final String _tmp_1;
                _tmp_1 = cursor.getString(_cursorIndexOfLabels);
                _tmpLabels = __menuListConverter.toObject(_tmp_1);
                final List<MenuItem> _tmpFacilities;
                final String _tmp_2;
                _tmp_2 = cursor.getString(_cursorIndexOfFacilities);
                _tmpFacilities = __menuListConverter.toObject(_tmp_2);
                final List<MenuItem> _tmpAllFacilities;
                final String _tmp_3;
                _tmp_3 = cursor.getString(_cursorIndexOfAllFacilities);
                _tmpAllFacilities = __menuListConverter.toObject(_tmp_3);
                final String _tmpDescription;
                _tmpDescription = cursor.getString(_cursorIndexOfDescription);
                final String _tmpDescriptionUrl;
                _tmpDescriptionUrl = cursor.getString(_cursorIndexOfDescriptionUrl);
                final String _tmpEnvironment;
                _tmpEnvironment = cursor.getString(_cursorIndexOfEnvironment);
                final String _tmpReason;
                _tmpReason = cursor.getString(_cursorIndexOfReason);
                final int _tmpTransferType;
                _tmpTransferType = cursor.getInt(_cursorIndexOfTransferType);
                final int _tmpIsSuccess;
                _tmpIsSuccess = cursor.getInt(_cursorIndexOfIsSuccess);
                final int _tmpFormatted_is_success;
                _tmpFormatted_is_success = cursor.getInt(_cursorIndexOfFormattedIsSuccess);
                final String _tmpFormattedDate;
                _tmpFormattedDate = cursor.getString(_cursorIndexOfFormattedDate);
                final String _tmpFormattedSize;
                _tmpFormattedSize = cursor.getString(_cursorIndexOfFormattedSize);
                final String _tmpFormattedRent;
                _tmpFormattedRent = cursor.getString(_cursorIndexOfFormattedRent);
                final int _tmpFormattedPageViews;
                _tmpFormattedPageViews = cursor.getInt(_cursorIndexOfFormattedPageViews);
                final String _tmpFormattedFee;
                _tmpFormattedFee = cursor.getString(_cursorIndexOfFormattedFee);
                final String _tmpFormattedLocationNodes;
                _tmpFormattedLocationNodes = cursor.getString(_cursorIndexOfFormattedLocationNodes);
                final String _tmpFormattedIndustry;
                _tmpFormattedIndustry = cursor.getString(_cursorIndexOfFormattedIndustry);
                final String _tmpFormattedFinalIndustry;
                _tmpFormattedFinalIndustry = cursor.getString(_cursorIndexOfFormattedFinalIndustry);
                final String _tmpFormattedFinalLocationNode;
                _tmpFormattedFinalLocationNode = cursor.getString(_cursorIndexOfFormattedFinalLocationNode);
                final List<MenuItem> _tmpFormattedFacilities;
                final String _tmp_4;
                _tmp_4 = cursor.getString(_cursorIndexOfFormattedFacilities);
                _tmpFormattedFacilities = __menuListConverter.toObject(_tmp_4);
                final String _tmpRentView;
                _tmpRentView = cursor.getString(_cursorIndexOfRentView);
                final String _tmpViewOpening;
                _tmpViewOpening = cursor.getString(_cursorIndexOfViewOpening);
                final String _tmpViewCanEmpty;
                _tmpViewCanEmpty = cursor.getString(_cursorIndexOfViewCanEmpty);
                final int _tmpIsTop;
                _tmpIsTop = cursor.getInt(_cursorIndexOfIsTop);
                final int _tmpIsHot;
                _tmpIsHot = cursor.getInt(_cursorIndexOfIsHot);
                final int _tmpIsRecommend;
                _tmpIsRecommend = cursor.getInt(_cursorIndexOfIsRecommend);
                final int _tmpIsLargeOrder;
                _tmpIsLargeOrder = cursor.getInt(_cursorIndexOfIsLargeOrder);
                final String _tmpLargeOrderImg;
                _tmpLargeOrderImg = cursor.getString(_cursorIndexOfLargeOrderImg);
                final String _tmpCategory_acreage;
                _tmpCategory_acreage = cursor.getString(_cursorIndexOfCategoryAcreage);
                final String _tmpFormattedArea;
                _tmpFormattedArea = cursor.getString(_cursorIndexOfFormattedArea);
                final int _tmpIsVip;
                _tmpIsVip = cursor.getInt(_cursorIndexOfIsVip);
                final String _tmpData_type;
                _tmpData_type = cursor.getString(_cursorIndexOfDataType);
                final int _tmpJump_type;
                _tmpJump_type = cursor.getInt(_cursorIndexOfJumpType);
                final String _tmpJump_view;
                _tmpJump_view = cursor.getString(_cursorIndexOfJumpView);
                final String _tmpJump_param;
                _tmpJump_param = cursor.getString(_cursorIndexOfJumpParam);
                final Address _tmpAddress;
                if (! (cursor.isNull(_cursorIndexOfLocationNodes) && cursor.isNull(_cursorIndexOfAddressDetail) && cursor.isNull(_cursorIndexOfPostCode) && cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                  final List<LocationNode> _tmpLocationNodes;
                  final String _tmp_5;
                  _tmp_5 = cursor.getString(_cursorIndexOfLocationNodes);
                  _tmpLocationNodes = __locationNodeListConverter.toObject(_tmp_5);
                  final String _tmpAddressDetail;
                  _tmpAddressDetail = cursor.getString(_cursorIndexOfAddressDetail);
                  final int _tmpPostCode;
                  _tmpPostCode = cursor.getInt(_cursorIndexOfPostCode);
                  final Coordinates _tmpCoordinates;
                  if (! (cursor.isNull(_cursorIndexOfLatitude) && cursor.isNull(_cursorIndexOfLongitude))) {
                    final double _tmpLatitude;
                    _tmpLatitude = cursor.getDouble(_cursorIndexOfLatitude);
                    final double _tmpLongitude;
                    _tmpLongitude = cursor.getDouble(_cursorIndexOfLongitude);
                    _tmpCoordinates = new Coordinates(_tmpLatitude,_tmpLongitude);
                  }  else  {
                    _tmpCoordinates = null;
                  }
                  _tmpAddress = new Address(_tmpLocationNodes,_tmpAddressDetail,_tmpPostCode,_tmpCoordinates);
                }  else  {
                  _tmpAddress = null;
                }
                _tmpShop = new Shop(_tmpShopID,_tmpTitle,_tmpSize,_tmpRent,_tmpFee,_tmpAddress,_tmpLng,_tmpLat,_tmpNewLng,_tmpNewLat,_tmpIndustry,_tmpRunningState,_tmpIncludeFacilities,_tmpImage,_tmpImages,_tmpFloor,_tmpLabels,_tmpFacilities,_tmpAllFacilities,_tmpDescription,_tmpDescriptionUrl,_tmpEnvironment,_tmpReason,_tmpTransferType,_tmpIsSuccess,_tmpFormatted_is_success,_tmpFormattedDate,_tmpFormattedSize,_tmpFormattedRent,_tmpFormattedPageViews,_tmpFormattedFee,_tmpFormattedLocationNodes,_tmpFormattedIndustry,_tmpFormattedFinalIndustry,_tmpFormattedFinalLocationNode,_tmpFormattedFacilities,_tmpRentView,_tmpViewOpening,_tmpViewCanEmpty,_tmpIsTop,_tmpIsHot,_tmpIsRecommend,_tmpIsLargeOrder,_tmpLargeOrderImg,_tmpCategory_acreage,_tmpFormattedArea,_tmpIsVip,_tmpData_type,_tmpJump_type,_tmpJump_view,_tmpJump_param);
              }  else  {
                _tmpShop = null;
              }
              final User _tmpShopOwner;
              if (! (cursor.isNull(_cursorIndexOfUserID) && cursor.isNull(_cursorIndexOfAccount) && cursor.isNull(_cursorIndexOfToken) && cursor.isNull(_cursorIndexOfName) && cursor.isNull(_cursorIndexOfNickName) && cursor.isNull(_cursorIndexOfUserSex) && cursor.isNull(_cursorIndexOfIcon) && cursor.isNull(_cursorIndexOfLoginTimestamp) && cursor.isNull(_cursorIndexOfTipsMsg) && cursor.isNull(_cursorIndexOfLoginState) && cursor.isNull(_cursorIndexOfActualName) && cursor.isNull(_cursorIndexOfUserPhoneNumber) && cursor.isNull(_cursorIndexOfCityPathId) && cursor.isNull(_cursorIndexOfCityId) && cursor.isNull(_cursorIndexOfCityName) && cursor.isNull(_cursorIndexOfUserCityPath))) {
                _tmpShopOwner = new User();
                final int _tmpUserID;
                _tmpUserID = cursor.getInt(_cursorIndexOfUserID);
                _tmpShopOwner.setUserID(_tmpUserID);
                final String _tmpAccount;
                _tmpAccount = cursor.getString(_cursorIndexOfAccount);
                _tmpShopOwner.setAccount(_tmpAccount);
                final String _tmpToken;
                _tmpToken = cursor.getString(_cursorIndexOfToken);
                _tmpShopOwner.setToken(_tmpToken);
                final String _tmpName;
                _tmpName = cursor.getString(_cursorIndexOfName);
                _tmpShopOwner.setName(_tmpName);
                final String _tmpNickName;
                _tmpNickName = cursor.getString(_cursorIndexOfNickName);
                _tmpShopOwner.setNickName(_tmpNickName);
                final int _tmpUserSex;
                _tmpUserSex = cursor.getInt(_cursorIndexOfUserSex);
                _tmpShopOwner.setUserSex(_tmpUserSex);
                final String _tmpIcon;
                _tmpIcon = cursor.getString(_cursorIndexOfIcon);
                _tmpShopOwner.setIcon(_tmpIcon);
                final long _tmpLoginTimestamp;
                _tmpLoginTimestamp = cursor.getLong(_cursorIndexOfLoginTimestamp);
                _tmpShopOwner.setLoginTimestamp(_tmpLoginTimestamp);
                final String _tmpTipsMsg;
                _tmpTipsMsg = cursor.getString(_cursorIndexOfTipsMsg);
                _tmpShopOwner.setTipsMsg(_tmpTipsMsg);
                final int _tmpLoginState;
                _tmpLoginState = cursor.getInt(_cursorIndexOfLoginState);
                _tmpShopOwner.setLoginState(_tmpLoginState);
                final String _tmpActualName;
                _tmpActualName = cursor.getString(_cursorIndexOfActualName);
                _tmpShopOwner.setActualName(_tmpActualName);
                final String _tmpUserPhoneNumber;
                _tmpUserPhoneNumber = cursor.getString(_cursorIndexOfUserPhoneNumber);
                _tmpShopOwner.setUserPhoneNumber(_tmpUserPhoneNumber);
                final String _tmpCityPathId;
                _tmpCityPathId = cursor.getString(_cursorIndexOfCityPathId);
                _tmpShopOwner.setCityPathId(_tmpCityPathId);
                final int _tmpCityId;
                _tmpCityId = cursor.getInt(_cursorIndexOfCityId);
                _tmpShopOwner.setCityId(_tmpCityId);
                final String _tmpCityName;
                _tmpCityName = cursor.getString(_cursorIndexOfCityName);
                _tmpShopOwner.setCityName(_tmpCityName);
                final String _tmpUserCityPath;
                _tmpUserCityPath = cursor.getString(_cursorIndexOfUserCityPath);
                _tmpShopOwner.setUserCityPath(_tmpUserCityPath);
              }  else  {
                _tmpShopOwner = null;
              }
              final Order.Companion.State _tmpState;
              if (! (cursor.isNull(_cursorIndexOfText) && cursor.isNull(_cursorIndexOfColor))) {
                final String _tmpText;
                _tmpText = cursor.getString(_cursorIndexOfText);
                final String _tmpColor;
                _tmpColor = cursor.getString(_cursorIndexOfColor);
                _tmpState = new Order.Companion.State(_tmpText,_tmpColor);
              }  else  {
                _tmpState = null;
              }
              _item = new Order();
              final long _tmpOrderID;
              _tmpOrderID = cursor.getLong(_cursorIndexOfOrderID);
              _item.setOrderID(_tmpOrderID);
              final int _tmpOrderType;
              _tmpOrderType = cursor.getInt(_cursorIndexOfOrderType);
              _item.setOrderType(_tmpOrderType);
              final int _tmpFavorite;
              _tmpFavorite = cursor.getInt(_cursorIndexOfFavorite);
              _item.setFavorite(_tmpFavorite);
              _item.setShop(_tmpShop);
              _item.setShopOwner(_tmpShopOwner);
              _item.setState(_tmpState);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }
}
