package com.enset;

import com.enset.entities.Patient;
import com.enset.entities.PatientRepository;
import com.enset.security.User;
import com.enset.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class Tp2Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Yassine",new Date(),451,false));
        patientRepository.save(new Patient(null,"Karim",new Date(),451,false));
        patientRepository.save(new Patient(null,"Marwa",new Date(),451,false));
        patientRepository.save(new Patient(null,"Yassmine",new Date(),451,false));
        patientRepository.save(new Patient(null,"Yassmine",new Date(),451,false));

        patientRepository.save(new Patient(null,"Yassmine",new Date(),451,false));

        patientRepository.save(new Patient(null,"Yassmine",new Date(),451,false));

        patientRepository.save(new Patient(null,"Yassmine",new Date(),451,false));

        patientRepository.save(new Patient(null,"Yassmine",new Date(),451,false));

        patientRepository.findAll().forEach(p->{
            System.out.println(p.toString());
        });

        userRepository.save(new User(1L,"admin",new BCryptPasswordEncoder().encode("1234"),true));

    }
}
