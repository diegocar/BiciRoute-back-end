package edu.eci.BiciRoute.services.impl;

import edu.eci.BiciRoute.Models.User;
import edu.eci.BiciRoute.Repositories.IBicicleRepository;
import edu.eci.BiciRoute.Repositories.IUserRepository;
import edu.eci.BiciRoute.services.IUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "userImplService")
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IBicicleRepository bicicleRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public void removeUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(User user) {
        if(user.getBicicle() !=  null ){
            if(user.getBicicle().get_id() ==  null) user.getBicicle().set_id(ObjectId.get());
            bicicleRepository.save(user.getBicicle());
        }
        //userRepository.deleteById(user.get_id().toString());
        userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        user.set_id(ObjectId.get());
        userRepository.insert(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserBy_id(String _id) {
        return userRepository.findBy_id(_id);
    }
}
