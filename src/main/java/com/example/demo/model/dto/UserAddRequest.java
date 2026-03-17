package com.example.demo.model.dto;

import lombok.Data;
import java.time.LocalDate;

@Data

public class UserAddRequest {

    private String nomorKtp;
    private String namaLengkap;
    private String alamat;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
}