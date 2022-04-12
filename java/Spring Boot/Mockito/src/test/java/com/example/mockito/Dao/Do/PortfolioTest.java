package com.example.mockito.Dao.Do;

import com.example.mockito.AbstractClass;
import com.example.mockito.Service.IStockService;
import com.example.mockito.util.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PortfolioTest {
    //    @InjectMocks
    private Portfolio portfolio;
    @Mock
    private IStockService stockService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        //Create a portfolio object which is to be tested
        portfolio = new Portfolio();

        //Create the mock object of stock service
        stockService = mock(IStockService.class);

        //set the stockService to the portfolio
        portfolio.setStockService(stockService);
    }

    @Test
    @DisplayName(" 簡單範例 ")
    public void testMarketValue() {
        //Creates a list of stocks to be added to the portfolio
        List<Stock> stocks = new ArrayList<Stock>();

        Stock googleStock = new Stock("1", "Google", 10);
        Stock microsoftStock = new Stock("2", "Microsoft", 100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        //mock the behavior of stock service to return the value of various stocks
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        Assertions.assertEquals(100500.0, portfolio.getMarketValue());

        Map<Stock, Double> stockDoubleMap = stockService.getStockPriceMap();
        System.out.println("====");
    }

    @Test
    @DisplayName(" 多次呼叫 ")
    public void testMarketValue2() {

        //Creates a list of stocks to be added to the portfolio
        List<Stock> stocks = new ArrayList<Stock>();

        Stock googleStock = new Stock("1", "Google", 10);
        Stock microsoftStock = new Stock("2", "Microsoft", 100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);
        stocks.add(microsoftStock);
        stocks.add(microsoftStock);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        //mock the behavior of stock service to return the value of various stocks
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock))
                .thenReturn(1000.00)
                .thenThrow(new RuntimeException("Wait for me search"));

        Assertions.assertThrows(RuntimeException.class, () -> portfolio.getMarketValue());
    }

    @Test
    @DisplayName("  Argument matchers ")
    public void testMarketValue3() {
        //Creates a list of stocks to be added to the portfolio
        List<Stock> stocks = new ArrayList<Stock>();

        Stock googleStock = new Stock("1", "Google", 10);
        Stock microsoftStock = new Stock("2", "Microsoft", 100);
        Stock nonExist = new Stock("", "other", 100);
        Stock nonExist2 = new Stock("AAAAAA", "other", 100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);
        stocks.add(nonExist);
        stocks.add(nonExist2);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        //mock the behavior of stock service to return the value of various stocks
        when(stockService.getPrice(argThat(stock -> stock.getStockId().length() > 5 || stock.getStockId().length() == 0)))
                .thenThrow(new RuntimeException("stock is not exist."));
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        Assertions.assertThrows(RuntimeException.class, () -> portfolio.getMarketValue());
    }

    @Test
    @DisplayName("Verification in order")
    public void testVerificationOrder() {
        List singleMock = mock(List.class);
        singleMock.add("was added first");
        singleMock.add("was added second");

        InOrder inOrder = inOrder(singleMock);

        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("was called first");
        secondMock.add("was called second");

        InOrder inOrder2 = inOrder(firstMock, secondMock);

        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");

    }

    @Test
    @DisplayName("doAnswer doThrow doReturn")
    public void test() throws IOException {
        Long[] values = {1L, 2L, 3L, 4L, 5L};
        InputStream inputStream = mock(InputStream.class);

        doNothing()
                .doThrow(new IOException("this is a message."))
                .when(inputStream).close();
        Assertions.assertAll(() -> inputStream.close());
        verify(inputStream).close();
        Assertions.assertThrows(IOException.class, () -> inputStream.close());
        verify(inputStream, Mockito.times(2)).close();

        for (Long value : values) {
            doReturn(0L)
                    .doAnswer((invocation) ->
                            (Long) invocation.getArgument(0) * 1023
                    ).when(inputStream).skip(value);

            inputStream.skip(value);

            verify(inputStream).skip(value);
        }

        verify(inputStream, times(values.length)).skip(anyLong());
    }

    @Test
    @DisplayName("Spy")
    public void testSpy() {
        List<String> spy = spy(new LinkedList<>());
        List<String> mock = mock(List.class);

        doAnswer(invocation -> invocation.getArgument(0).toString())
                .doReturn("test")
                .when(spy)
                .get(anyInt());

        doAnswer(invocation -> invocation.getArgument(0).toString())
                .doReturn("test")
                .when(mock)
                .get(anyInt());

        spy.add("add1");
        mock.add("add1");
        spy.add("add2");
        mock.add("add2");
        spy.add("add3");
        mock.add("add3");

        for (int i = 0; i < spy.size(); i++) {
            if (i == 0) {
                Assertions.assertEquals(String.valueOf(i), spy.get(i));
            } else {
//            spy.get(i);
                Assertions.assertEquals("test", spy.get(i));
            }
        }
        Assertions.assertEquals(3, spy.size());
        verify(spy, times(3)).get(anyInt());

        mock.get(0);
        mock.get(0);
        verify(mock, times(2)).get(anyInt());
        Assertions.assertEquals(0, mock.size());
    }

    @Test
    @DisplayName("Captor")
    public void testCaptor() {
        List<Object> spy = spy(new LinkedList<>());

        spy.add("test");
        spy.add(12345);

        ArgumentCaptor<Object> arg = ArgumentCaptor.forClass(Object.class);
        verify(spy, times(2)).add(arg.capture());
        Assertions.assertEquals("test", arg.getAllValues().get(0));
        Assertions.assertEquals(12345, arg.getAllValues().get(1));
    }

    @Test
    @DisplayName("static method")
    public void testStaticMethod() {
        assertEquals("foo", Foo.method());

        try (MockedStatic<Foo> mockMethod = mockStatic(Foo.class)) {
            mockMethod.when(Foo::method).thenReturn("bar");
            assertEquals("bar", Foo.method());
            mockMethod.verify(Foo::method);
        }

        assertEquals("foo", Foo.method());
    }

    @Test
    @DisplayName("abstract class")
    public void testAbstractClass() {
        Function<String, String> function = spy(Function.class);

        AbstractClass<String> abstractClassSpy = spy(AbstractClass.class);

        AbstractClass<String> spy = mock(AbstractClass.class, withSettings()
                .useConstructor().defaultAnswer(CALLS_REAL_METHODS));

        AbstractClass<String> hasArgSpy = mock(AbstractClass.class, withSettings()
                .useConstructor("arg1", 123).defaultAnswer(CALLS_REAL_METHODS));
    }
}