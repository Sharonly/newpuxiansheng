/*
package com.puxiansheng.util.ext

import android.content.Context
import android.content.SharedPreferences
import com.puxiansheng.util.BaseApplication
import java.io.*
import android.util.Base64;


class SharedPreferencesUtil {
 companion object {


     private val FILE_NAME = "share_data"

     fun put(key: String?, value: Any) {
         val sp: SharedPreferences =
             BaseApplication.getAppContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
         val editor = sp.edit()
         if (value is String) {
             editor.putString(key, value)
         } else if (value is Int) {
             editor.putInt(key, value)
         } else if (value is Boolean) {
             editor.putBoolean(key, value)
         } else if (value is Float) {
             editor.putFloat(key, value)
         } else if (value is Long) {
             editor.putLong(key, value)
         } else {
             editor.putString(key, value.toString())
         }
         editor.apply()
     }

      fun get(key: String?, defaultValue: Any?): Any? {
         val sp: SharedPreferences =
             BaseApplication.getAppContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
         if (defaultValue is String) {
             return sp.getString(key, defaultValue as String?)
         } else if (defaultValue is Int) {
             return sp.getInt(key, (defaultValue as Int?)!!)
         } else if (defaultValue is Boolean) {
             return sp.getBoolean(key, (defaultValue as Boolean?)!!)
         } else if (defaultValue is Float) {
             return sp.getFloat(key, (defaultValue as Float?)!!)
         } else if (defaultValue is Long) {
             return sp.getLong(key, (defaultValue as Long?)!!)
         }
         return null
     }

     */
/**
      * 存储对象
      *//*

     fun putObject(key: String?, `object`: Any?) {
         val sp: SharedPreferences =
             BaseApplication.getAppContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
         //创建字节输出流
         val baos = ByteArrayOutputStream()
         //创建字节对象输出流
         var out: ObjectOutputStream? = null
         try { //然后通过将字对象进行64转码，写入key值为key的sp中
             out = ObjectOutputStream(baos)
             out.writeObject(`object`)
             val objectVal = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
             val editor = sp.edit()
             editor.putString(key, objectVal)
             editor.apply()
         } catch (e: IOException) {
             e.printStackTrace()
         } finally {
             try {
                 baos.close()
                 out?.close()
             } catch (e: IOException) {
                 e.printStackTrace()
             }
         }
     }

     */
/**
      * 读取对象
      *//*

     fun <T> getObject(key: String?, clazz: Class<T>?): T? {
         val sp: SharedPreferences =
             BaseApplication.getAppContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
         if (!sp.contains(key)) {
             return null
         }
         val objectVal = sp.getString(key, null)
         val buffer: ByteArray = Base64.decode(objectVal, Base64.DEFAULT)
         //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
         val bais = ByteArrayInputStream(buffer)
         var ois: ObjectInputStream? = null
         try {
             ois = ObjectInputStream(bais)
             return ois.readObject() as T?
         } catch (e: IOException) {
             e.printStackTrace()
         } catch (e: ClassNotFoundException) {
             e.printStackTrace()
         } finally {
             try {
                 bais.close()
                 ois?.close()
             } catch (e: IOException) {
                 e.printStackTrace()
             }
         }
         return null
     }

     fun saveSpStringData(key: String, info: String) {
         val sharedPreferences = BaseApplication.getAppContext().getSharedPreferences(key, Context.MODE_PRIVATE)
         val editor = sharedPreferences.edit()
         editor.putString(key, info)
         editor.apply()
     }

     fun saveSpStringData(context: Context, key: String, info: String) {
         val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
         val editor = sharedPreferences.edit()
         editor.putString(key, info)
         editor.apply()
     }

     fun getSpStringData(context: Context, key: String): String {
         val result = context.getSharedPreferences(key, Context.MODE_PRIVATE).getString(key, "")
         return result ?: ""
     }

     fun saveSpBooleanData(context: Context, key: String, info: Boolean) {
         val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
         val editor = sharedPreferences.edit()
         editor.putBoolean(key, info)
         editor.apply()
     }


     fun getSpBooleanData(context: Context, key: String): Boolean {
         return context.getSharedPreferences(key, Context.MODE_PRIVATE).getBoolean(key, true)
     }


     fun saveSpIntData(context: Context, key: String, info: Int) {
         val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
         val editor = sharedPreferences.edit()
         editor.putInt(key, info)
         editor.apply()
     }

     fun getSpIntData(context: Context, key: String): Int {
         return context.getSharedPreferences(key, Context.MODE_PRIVATE).getInt(key, 0)
     }
 }
}*/
