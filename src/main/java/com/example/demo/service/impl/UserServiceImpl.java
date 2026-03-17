package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.UserAddRequest;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Ktp;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public UserDto AddUser(UserAddRequest request) {
        validationUtil.validate(request);

        Ktp saveUser = Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        userRepository.save(saveUser);

        return UserMapper.MAPPER.toUserDtoData(saveUser);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.MAPPER::toUserDtoData)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {
        Ktp user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.MAPPER.toUserDtoData(user);
    }


    @Override
    public UserDto UpdateUser(Integer id, UserAddRequest request) {
        validationUtil.validate(request);

        Ktp existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setNomorKtp(request.getNomorKtp());
        existingUser.setNamaLengkap(request.getNamaLengkap());
        existingUser.setAlamat(request.getAlamat());
        existingUser.setTanggalLahir(request.getTanggalLahir());
        existingUser.setJenisKelamin(request.getJenisKelamin());

        userRepository.save(existingUser);

        return UserMapper.MAPPER.toUserDtoData(existingUser);
    }

    @Override
    public void DeleteUser(Integer id) {
        Ktp user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }
}