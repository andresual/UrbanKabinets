package com.andresual.dev.urbankabinet.Model;

import com.andresual.dev.urbankabinet.Activity.SignUpActivity;

/**
 * Created by andresual on 10/18/2017.
 */

public class CustomerModel {
    String nama;
    String login_source;
    String email;
    String password;
    String alamat;
    String telp;
    String referral_daftar;
    String tanggal_lahir;
    String social_media_id;
    String token;
    String otp;
    String function;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public CustomerModel() {
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setLogin_source(String login_source) {
        this.login_source = login_source;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setReferral_daftar(String referral_daftar) {
        this.referral_daftar = referral_daftar;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public void setSocial_media_id(String social_media_id) {
        this.social_media_id = social_media_id;
    }

    public String getNama() {
        return nama;
    }

    public String getLogin_source() {
        return login_source;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelp() {
        return telp;
    }

    public String getReferral_daftar() {
        return referral_daftar;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getSocial_media_id() {
        return social_media_id;
    }
}
