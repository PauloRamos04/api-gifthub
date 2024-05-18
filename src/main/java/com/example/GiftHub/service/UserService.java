package com.example.GiftHub.service;

import com.example.GiftHub.domain.user.User;
import com.example.GiftHub.domain.user.UserDTO;
import com.example.GiftHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() throws Exception {
        List<User> User = this.userRepository.findAll();

        if (User.isEmpty()){
            throw new Exception("Não há clientes cadastrados");
        }

        return User;
    }

    public User getUserById(Long id) throws Exception {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    public User createUser(UserDTO userDTO) throws Exception{
        if (userDTO == null){
            throw new Exception("Erro ao cadastrar o cliente, faltando informações necessarias");
        }
        User newUser = new User(userDTO);
        this.userRepository.save(newUser);
        return newUser;
    }

    public void deleteUser(Long id) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("Cliente não encontrado para exclusão");
        }
    }
}
