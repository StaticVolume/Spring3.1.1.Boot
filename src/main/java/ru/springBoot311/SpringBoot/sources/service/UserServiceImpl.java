package ru.springBoot311.SpringBoot.sources.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springBoot311.SpringBoot.sources.dao.UserDao;
import ru.springBoot311.SpringBoot.sources.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
/**перенёс @transactional в @service, потому что посмотрел, в основном реализуют тут*/
    private UserDao userDao;

    @Autowired // можно и не писать, Spring сам подцепит
    public UserServiceImpl(UserDao usersDao) {
        this.userDao = usersDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {

        userDao.addUserToDatabase(user);

    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {

        return userDao.getAllUsersFromDatabase();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userDao.getUserByIdFromDatabase(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
         userDao.updateUserInDatabase(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {

        userDao.deleteUserFromDatabase(id);
    }
}
