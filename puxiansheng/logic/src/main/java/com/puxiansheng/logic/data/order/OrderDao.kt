package com.puxiansheng.logic.data.order

import androidx.paging.DataSource
import androidx.room.*
import com.puxiansheng.logic.bean.Order

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(vararg order: Order)

    @Query("delete from table_orders where _transfer_type = :type")
    fun deleteAllByTransferType(type: Int)

    @Query("select * from table_orders where _transfer_type = :type")
    fun getLocalOrdersByTransferType(type: Int): DataSource.Factory<Int, Order>

    @Query("select * from table_orders where _transfer_type = :type limit :limit")
    fun getLocalOrdersByTransferTypeWithLimit(type: Int, limit: Int): DataSource.Factory<Int, Order>

    @Query("select * from table_orders where _order_type = :orderType")
    fun getLocalOrdersByOrderType(orderType: Int): DataSource.Factory<Int, Order>
}