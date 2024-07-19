package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return  invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices() {
        return invoiceService.getAll();
    }

    @GetMapping("/identification/{Ic}/sales")
    public List<InvoiceDTO> getSales(@PathVariable String Ic) {
        return invoiceService.getInvoicesBySales(Ic);
    }

    @GetMapping("/identification/{Ic}/purchases")
    public List<InvoiceDTO> getPurchases(@PathVariable String Ic) {
        return invoiceService.getInvoicesByPurchases(Ic);
    }

    @GetMapping("/invoices/{Id}")
    public InvoiceDTO getInvoiceDetail(@PathVariable long Id) {
        return invoiceService.getInvoiceDetail(Id);
    }

    @DeleteMapping("/invoices/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable long Id) {
        invoiceService.deleteInvoice(Id);
    }

    @PutMapping("/invoices/{Id}")
    public InvoiceDTO updateInvoice(@RequestBody InvoiceDTO invoiceDTO, @PathVariable long Id) {
        return invoiceService.updateInvoice(invoiceDTO, Id);
    }

    @GetMapping("/invoices/statistics")
    public InvoiceStatisticsDTO getInvoiceStatisctics(){
        return invoiceService.getInvoiceStatistics();}


}
