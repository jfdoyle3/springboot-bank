package com.jfdeveloper.springbootbank.controllers;

import com.jfdeveloper.springbootbank.entities.Checking;
import com.jfdeveloper.springbootbank.repositories.AccountRepository;
import com.jfdeveloper.springbootbank.repositories.CheckingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


// Generate Account Numbers
// int accountNumberChecking=Math.abs((1 + randNumber.nextInt(2)) * (int)Math.pow(10,9) + randNumber.nextInt((int)Math.pow(10,9)));
// int accountNumberSavings=Math.abs(accountNumberChecking+((1 + randNumber.nextInt(2)) * (int)Math.pow(10,5) + randNumber.nextInt((int)Math.pow(10,5))));

@RestController
@CrossOrigin
@RequestMapping("/api/accounts/checking")
public class CheckingAccountController {
    private final long millis=System.currentTimeMillis();

    private final Random randNumber = new Random(millis);
    // private Date date=new Date(millis);


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;


    @GetMapping("/showAllAccounts")
    public @ResponseBody List<Checking> getAllAccounts() {
        return checkingRepository.findAll();
    }

//    @GetMapping("/showCheckingAccount/{id}")
//    public ResponseEntity<Checking> getAccountId(){
//
//    }

    @PostMapping("/create")
    public ResponseEntity<Checking> checkingAccount(@RequestBody Checking account){


        int accountNumberChecking=Math.abs((1 + randNumber.nextInt(2)) * (int)Math.pow(10,9) + randNumber.nextInt((int)Math.pow(10,9)));
        //       int accountNumberSavings=Math.abs(accountNumberChecking+((1 + randNumber.nextInt(2)) * (int)Math.pow(10,5) + randNumber.nextInt((int)Math.pow(10,5))));

        Checking checking= new Checking(account.getName(),accountNumberChecking);

        checkingRepository.save(checking);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }





    // ex of Post need to create a Checking and Savings accounts
//    @PostMapping("/rate/{trackerId}/{cId}")
//    public ResponseEntity<CryptocurrencyInfo> rateById(@PathVariable Long trackerId,@PathVariable Long cId,
//                                                       @RequestBody SetRating setRating) {
//        Optional<CryptocurrencyInfo> currency = infoRepository.findById(cId);
//        Optional<Tracker> tracker = trackerRepository.findById(trackerId);
//
//        if (currency.isEmpty() || tracker.isEmpty())
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//
//        if (repository.existsByCurrency_id(cId)) {
//            Rating updateRating=repository.findByCurrencyId(cId);
//            updateRating.setRate(setRating.getRate());
//            repository.save(updateRating);
//            return new ResponseEntity<>(currency.get(), HttpStatus.CREATED);
//        }
//
//        Rating newRating = new Rating(tracker.get(), currency.get(), setRating.getRate());
//        repository.save(newRating);
//        return new ResponseEntity<>(currency.get(), HttpStatus.CREATED);
//    }
}
