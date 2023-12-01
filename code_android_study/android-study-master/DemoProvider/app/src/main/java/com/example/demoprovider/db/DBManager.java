package com.example.demoprovider.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demoprovider.Person;

/**
 * 数据库管理类
 */
public class DBManager {

    private static DBManager dbManager;

    private final String TAG = "DBManager";

    private final SQLiteDatabase db;

    private DBManager(Context context){
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();

    }

   public static DBManager getInstance(Context context) {
       if (dbManager == null ){
           synchronized (DBManager.class){
               if (dbManager == null ) {
                   dbManager = new DBManager(context);
               }
           }
       }
        return dbManager;
   }

    /**
     * 插入
     *
     * @param person Person对象
     * @return 成功0；失败-1
     */
   public int insertPerson(Person person){
       if (person == null) {
           return -1;
       }
       int ret = 0;
       try {
           String sql_insert = "INSERT INTO person (" +
                   "name," +
                   "phone," +
                   "sex," +
                   "age ) " +
                   "VALUES (?,?,?,?);";
           db.execSQL(sql_insert, new String[]{ person.getName(), person.getPhone()
                   , person.getSex(), person.getAge() + ""});
       } catch (Exception e) {
           Log.e(TAG, "insertPerson e="+e.getMessage());
           ret = -1;
       }
       return ret;
   }

    /**
     * 根据id删除
     *
     * @param id Person id
     */
    public void deletePerson(int id){
        String sql_delete = "DELETE FROM person WHERE _id=?";
        db.execSQL(sql_delete,new String[]{id+""});
    }

    /**
     * 更新Person
     *
     * @param person Person对象
     */
    public void updatePerson(Person person){
        String sql_update = "UPDATE person SET name=?,phone=?,sex=?,age=? WHERE _id=?";
        db.execSQL(sql_update,new String[]{ person.getName(), person.getPhone()
                , person.getSex(), person.getAge() + "",person.getId() + ""});
    }

    /**
     * 根据id查询Person
     *
     * @param id Person id
     * @return Person对象
     */
    @SuppressLint("Range")
    public Person queryPerson(int id){
        Person person = null;
        Cursor cursor = null;
        try {
            String sql_query="SELECT * FROM person WHERE _id=?";
            cursor = db.rawQuery(sql_query,new String[]{id+""});
            if (cursor != null && cursor.moveToFirst()){
                person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                person.setName(cursor.getString(cursor.getColumnIndex("name")));
                person.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                person.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            }
        }catch (Exception e){
            Log.e(TAG, "queryPerson e="+e.getMessage());
        }finally {
            if (cursor != null){
                cursor.close();
            }
        }
        return person;
    }


//    public void close(){
//        if (db != null && db.isOpen()){
//            db.close();
//            db = null;
//            dbManager = null;
//        }
//    }

}
