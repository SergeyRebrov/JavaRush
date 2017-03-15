package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.model.service.UserService;
import com.javarush.test.level36.lesson04.big01.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by Sergey on 15.03.2017.
 */
public class MainModel implements Model
{
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData()
    {
        return modelData;
    }

    @Override
    public void loadUsers()
    {
        loadOnlyActiveUsers();
    }

    @Override
    public void loadDeletedUsers()
    {
        List<User> users = userService.getAllDeletedUsers();
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers(users);
    }

    @Override
    public void loadUserById(long userId)
    {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    @Override
    public void deleteUserById(long id)
    {
        userService.deleteUser(id);
    }

    @Override
    public void changeUserData(String name, long id, int level)
    {
        userService.createOrUpdateUser(name, id, level);
    }

    private void loadOnlyActiveUsers()
    {
        List<User> users = userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100));
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(users);
    }
}
