package com.example.demo.services;


import com.example.demo.models.Transaction;
import com.example.demo.repositiries.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceImplTest {

    @InjectMocks
    ConversionServiceImpl mockConversionService;
    @Mock
    TransactionRepository transactionRepository;


    @Test
    public void getByIdShould_CallRepository(){
        //arrange
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        Mockito.when(transactionRepository.getOne(1)).thenReturn(transaction);
        //act
        Assert.assertNotNull(mockConversionService.getById(1));
        //assert
        Mockito.verify(transactionRepository,Mockito.times(1)).getOne(1);

    }

    
}
