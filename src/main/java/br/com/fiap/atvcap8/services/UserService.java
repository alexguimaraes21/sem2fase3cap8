package br.com.fiap.atvcap8.services;

import br.com.fiap.atvcap8.exceptions.RegisteredUserException;
import br.com.fiap.atvcap8.exceptions.UserPasswordNotMatchException;
import br.com.fiap.atvcap8.libs.DefaultMessage;
import br.com.fiap.atvcap8.libs.PasswordCrypto;
import br.com.fiap.atvcap8.models.UserModel;
import br.com.fiap.atvcap8.repositories.UserRepository;
import br.com.fiap.atvcap8.responsemodels.UserResponseModel;
import br.com.fiap.atvcap8.viewmodels.UserUpdatePasswordViewModel;
import br.com.fiap.atvcap8.viewmodels.UserViewModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseModel add(UserViewModel userViewModel) {
        Optional<UserModel> userFound = userRepository.findModelByEmail(userViewModel.email());
        if(userFound.isPresent()) {
            throw new RegisteredUserException(DefaultMessage.USER_REGISTERED);
        }
        String encryptedPassword = PasswordCrypto.encode(userViewModel.password());
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userViewModel, userModel);
        userModel.setPassword(encryptedPassword);
        return new UserResponseModel(userRepository.save(userModel));
    }

    public UserResponseModel findById(long id) {
        return userRepository.findById(id).map(UserResponseModel::new).orElseThrow(
                () -> new EntityNotFoundException(DefaultMessage.USER_NOT_FOUND)
        );
    }

    public UserResponseModel findByEmail(String email) {
        return userRepository.findModelByEmail(email).map(UserResponseModel::new).orElseThrow(
                () -> new EntityNotFoundException(DefaultMessage.USER_NOT_FOUND)
        );
    }

    public Page<UserResponseModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserResponseModel::new);
    }

    public void delete(Long id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isPresent()) {
            userRepository.delete(userModel.get());
        } else {
            throw new EntityNotFoundException(DefaultMessage.USER_NOT_FOUND);
        }
    }

    public UserResponseModel update(UserViewModel userViewModel) {
        Optional<UserModel> userModelOriginal = userRepository.findById(userViewModel.id());
        if (userModelOriginal.isPresent()) {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(userViewModel, userModel);
            String encryptedPassword = PasswordCrypto.encode(userViewModel.password());
            if(null == userModel.getEnabled()) {
                userModel.setEnabled(userModelOriginal.get().isEnabled());
            }
            if(null == userModel.getAccountNonExpired()) {
                userModel.setAccountNonExpired(userModelOriginal.get().getAccountNonExpired());
            }
            if(null == userModel.getCredentialsNonExpired()) {
                userModel.setCredentialsNonExpired(userModelOriginal.get().getCredentialsNonExpired());
            }
            if(null == userModel.getAccountNonLocked()) {
                userModel.setAccountNonLocked(userModelOriginal.get().getAccountNonLocked());
            }
            userModel.setPassword(encryptedPassword);
            return new UserResponseModel(userRepository.save(userModel));
        } else {
            throw new EntityNotFoundException(DefaultMessage.USER_NOT_FOUND);
        }
    }

    public UserResponseModel updatePassword(UserUpdatePasswordViewModel userUpdPassVM) {
        Optional<UserModel> userModelOriginal = userRepository.findById(userUpdPassVM.id());
        if (userModelOriginal.isEmpty()) {
            throw new EntityNotFoundException(DefaultMessage.USER_NOT_FOUND);
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModelOriginal.get(), userModel);

        if (!PasswordCrypto.validatePassword(userUpdPassVM.oldPassword(), userModelOriginal.get().getPassword())) {
            throw new UserPasswordNotMatchException(DefaultMessage.PASSWORD_NOT_MATCH);
        }

        String encryptedPassword = PasswordCrypto.encode(userUpdPassVM.newPassword());
        userModel.setPassword(encryptedPassword);
        return new UserResponseModel(userRepository.save(userModel));
    }
}
