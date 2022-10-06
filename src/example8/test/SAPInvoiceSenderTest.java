package example8.test;

import example8.main.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SAPInvoiceSenderTest {
    private InvoiceFilter filter = mock(InvoiceFilter.class);
    private SAP sap = mock(SAP.class);
    private SAPInvoiceSender sender = new SAPInvoiceSender(filter, sap);

    @Test
    void sendToSap() {
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 99);

        // Stub
        List<Invoice> invoices = Arrays.asList(mauricio, frank);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        // Mock
        sender.sendLowValuedInvoices();

//        verify(sap).send(mauricio);
//        verify(sap).send(frank);

//        verify(sap, times(2)).send(any(Invoice.class));
//
//        verify(sap, times(1)).send(mauricio);
//        verify(sap, times(1)).send(frank);
    }

    @Test
    void noLowValueInvoices() {
        List<Invoice> invoices = emptyList();

        when(filter.lowValueInvoices()).thenReturn(invoices);

        sender.sendLowValuedInvoices();

//        verify(sap, never()).send(any(Invoice.class));
    }

    @ParameterizedTest
    @CsvSource({
            "Mauricio,Ma",
            "M,X"
    })
    void sendToSapWithTheGeneratedId(String customer, String initialId) {
        Invoice mauricio = new Invoice(customer, 20);

        List<Invoice> invoices = Arrays.asList(mauricio);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        sender.sendLowValuedInvoices();

        ArgumentCaptor<SapInvoice> captor = ArgumentCaptor.forClass(SapInvoice.class);

        verify(sap).send(captor.capture());

        SapInvoice generatedSapInvoice = captor.getValue();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));

        Assertions.assertThat(generatedSapInvoice).isEqualTo(
                new SapInvoice(customer, 20, date + initialId)
        );
    }

    @Test
    void returnFailedInvoices() {
        Invoice mauricio = new Invoice("Mauricio", 20);
        Invoice frank = new Invoice("Frank", 25);
        Invoice steve = new Invoice("Steve", 48);

        List<Invoice> invoices = Arrays.asList(mauricio, frank, steve);
        when(filter.lowValueInvoices()).thenReturn(invoices);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
        SapInvoice franksInvoice = new SapInvoice("Frank", 25, date + "Fr");

        doThrow(new SAPException()).when(sap).send(franksInvoice);

        List<Invoice> failedInvoices = sender.sendLowValuedInvoices();
        Assertions.assertThat(failedInvoices).containsExactly(frank);

        SapInvoice mauriciosInvoice = new SapInvoice("Mauricio", 20, date + "Ma");
        verify(sap).send(mauriciosInvoice);

        SapInvoice stevesInvoice = new SapInvoice("Steve", 48, date + "St");
        verify(sap).send(stevesInvoice);
    }

}
