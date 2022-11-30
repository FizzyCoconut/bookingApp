package com.example.bookingApp.service;

import com.example.bookingApp.DTO.BookingsDTO;
import com.example.bookingApp.DTO.UserRegisteredDTO;
import com.example.bookingApp.beans.Bookings;
import com.example.bookingApp.beans.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DefaultUserService extends UserDetailsService {
    User save(UserRegisteredDTO userRegisteredDTO);

    Bookings updateBookings(BookingsDTO bookingDTO, UserDetails user);

    void sendEmail(BookingsDTO bookingDTO, User users, String nameGenrator);

}
