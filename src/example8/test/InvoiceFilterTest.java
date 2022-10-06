package example8.test;

import example8.main.Invoice;
import example8.main.InvoiceFilter;
import example8.main.IssuedInvoices;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class InvoiceFilterTest {

    @Test
    void filterInvoices() {
        IssuedInvoices issuedInvoices = Mockito.mock(IssuedInvoices.class);

        Invoice mauricio = new Invoice("Maurício", 20);
        Invoice steve = new Invoice("Steve", 99);
        Invoice frank = new Invoice("Frank", 100);
        List<Invoice> invoiceList = Arrays.asList(mauricio, steve, frank);

        Mockito.when(issuedInvoices.all()).thenReturn(invoiceList);

        InvoiceFilter filter = new InvoiceFilter(issuedInvoices);

        Assertions.assertThat(filter.lowValueInvoices()).containsExactlyInAnyOrder(mauricio, steve);
    }
}
