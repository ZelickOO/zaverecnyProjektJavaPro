package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;

import java.util.List;


public interface InvoiceService {
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAll();

    List<InvoiceDTO> getInvoicesBySales(String identificationNumber);
    // InvoiceDTO getInvoiceById(Long invoiceId);

    List<InvoiceDTO> getInvoicesByPurchases(String identificationNumber);

  //  InvoiceDTO getInvoiceById (long id);

    InvoiceDTO getInvoiceDetail(long id);

    void deleteInvoice(long Id);

    InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, long Id);

    InvoiceStatisticsDTO getInvoiceStatistics();

}
