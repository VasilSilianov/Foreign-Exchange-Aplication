package com.example.demo.services;


import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.models.Transaction;
import com.example.demo.repositiries.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceImplTest {

    @InjectMocks
    ConversionServiceImpl mockConversionService;

    @Mock
    ClientServiceImpl clientService;

    @Mock
    TransactionRepository transactionRepository;


    @Test
    public void getByIdShould_CallRepository(){
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);;
        transaction.setAmount(100);
        Mockito.when(transactionRepository.getByTransactionId(1)).thenReturn(Optional.of(transaction));
        //act
        mockConversionService.getById(1);
        // Assert
        Mockito.verify(transactionRepository,Mockito.times(1)).getByTransactionId(1);
    }


    @Test public void getByDate_ShouldReturnListOfTransactionsFilteredByDate() throws ParseException {
        //Arrange
        Date date= new Date();
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();
        transaction1.setAmount(100);
        transaction1.setDate(date);
        transaction2.setAmount(100);
        transaction2.setDate(date);
        transaction3.setAmount(100);
        Date otherDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-27");
        transaction3.setDate(otherDate);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        List<Transaction> transactionsFiltered = new ArrayList<>();
        transactionsFiltered.add(transaction1);
        transactionsFiltered.add(transaction2);
        Mockito.when(transactionRepository.getTransactionsByDate(date)).thenReturn(transactionsFiltered);

        //Assert, Act
        Assert.assertNotEquals(transactions.size(),transactionRepository.getTransactionsByDate(date).size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByDateShould_ThrowException_IfListIsEmpty() throws ParseException {
        //Arrange
         Date date= new Date();
        //Act,Assert
        mockConversionService.getByDate(date);
    }
    @Test(expected = EntityNotFoundException.class)
    public void getByIdShould_ThrowException_IfThereIsNoTransactionWithThatId() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        //arrange,act,assert
        mockConversionService.getById(2);

    }

    @Test
    public void getByIDShould_ThrowExeption(){
        Assert.assertThrows(EntityNotFoundException.class,
                () -> mockConversionService.getById(2));


    }
}

