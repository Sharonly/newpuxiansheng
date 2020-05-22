package com.puxiansheng.logic.data.device

import android.os.Build
import androidx.lifecycle.LiveData
import com.puxiansheng.logic.BuildConfig
import com.puxiansheng.logic.bean.Device

class DeviceRepository(private val deviceDao: DeviceDao) {

    fun getDevice(): LiveData<Device> {
        return deviceDao.get()
    }

    fun requireDevice() {
        deviceDao.insertOrUpdate(getLocalDevice())
    }

    fun deleteDevice(uid: Int = 1) {
        deviceDao.delete(uid)
    }

    fun deleteDevice(device: Device) {
        deviceDao.delete(device)
    }

    private fun getLocalDevice(): Device {
        return Device(
            uid = Build.ID,
            manufacturer = Build.MANUFACTURER,
            hardware = Build.HARDWARE,
            model = Build.MODEL,
            sdk = Build.VERSION.SDK_INT,
            board = Build.BOARD,
            brand = Build.BRAND,
            host = Build.HOST
        ).apply {
            if (BuildConfig.DEBUG) println("new device : $this")
        }
    }
}