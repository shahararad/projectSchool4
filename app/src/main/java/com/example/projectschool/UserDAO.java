package com.example.projectschool;

import androidx.lifecycle.LiveData;
import androidx.room.Query;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
public interface UserDAO {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM users WHERE username = :username")
    LiveData<User> getUserByUsername(String username);
   // @Query("SELECT * FROM users WHERE username = :username")
   // User getUserByUsernameSync(String username); // פונקציה סינכרונית


    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);
}
