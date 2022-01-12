package com.tech.Registration.service;

import com.tech.Registration.exception.*;
import com.tech.Registration.model.User;
import com.tech.Registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public static final String ACCEPTED_COUNTRY="FRANCE";
    public static final int ADULT_AGE=18;
    private static final String PHONE_NUMBER_FORMAT="^((\\+)33|0)[1-9](\\d{2}){4}$";

    @Override
    public User saveUser(User user){

        if (user.getUsername()==null){
            throw new NullPointerException("Username should not be empty !");
        }

        if (user.getBirthdate()==null){
            throw new NullPointerException("Birthdate should not be empty !");
        }


        if (user.getCountry()==null){
            throw new NullPointerException("Country should not be empty !");
        }

        final Optional<User> optionalUser=userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new UserNotAllowedRegistration("This Username is taken, choose another !");
        }

       if(user.getPhoneNumber() !=null){
           if(!user.getPhoneNumber().matches(PHONE_NUMBER_FORMAT)) {
               throw new UserNotAllowedPhoneNumber(String.format("Phone number '%s' is not valid !", user.getPhoneNumber()));
           }
       }

        if(!user.getCountry().equalsIgnoreCase(ACCEPTED_COUNTRY)){
            throw new UserNotFrenchResident("Not Allowed registration - Only French resident are allowed !");
        }

        if((calculateAge(user.getBirthdate()) < ADULT_AGE)&&user.getCountry().equalsIgnoreCase(ACCEPTED_COUNTRY)){
            throw new UserNotAdult("Not Allowed registration - Only adult are allowed !");
        }

        if((calculateAge(user.getBirthdate()) < ADULT_AGE)){
            throw new UserNotAdult("Not Allowed registration - Only adult are allowed !");
        }

        if(user.getCountry().equalsIgnoreCase(ACCEPTED_COUNTRY) && calculateAge(user.getBirthdate())>=ADULT_AGE){
            return userRepository.save(user);
        } else{
            throw new UserNotAllowedRegistration("Not Allowed registration");
        }
    }

    @Override
    public Optional<User> findByUsername(String username)  {

        final Optional<User> optionalUser=userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            return optionalUser;
        }
        else {
            throw new UsernameNotFoundException(String.format("Username %s cannot be found.", username));
        }

    }

    public int calculateAge(Date birthdate){
        Calendar dateInCalendar = Calendar.getInstance();
        dateInCalendar.setTime(birthdate);
        return Calendar.getInstance().get(Calendar.YEAR) - dateInCalendar.get(Calendar.YEAR) ;
    }

}
