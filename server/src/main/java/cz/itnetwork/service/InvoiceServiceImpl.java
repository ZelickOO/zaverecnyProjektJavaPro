package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        PersonEntity seller = personRepository.getReferenceById(invoiceDTO.getSeller().getId());
        entity.setSeller(seller);
        PersonEntity buyer = personRepository.getReferenceById(invoiceDTO.getBuyer().getId());
        entity.setBuyer(buyer);
        entity = invoiceRepository.save(entity);
        return invoiceMapper.toDTO(entity);
    }

    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }
    @Override
    public List<InvoiceDTO> getInvoicesBySales(String Ic) {
        return personRepository.findByIdentificationNumber(Ic)
                .stream()
                .map(PersonEntity::getSales)
                .flatMap(List::stream)
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getInvoicesByPurchases(String Ic) {
        return personRepository.findByIdentificationNumber(Ic)
                .stream()
                .map(PersonEntity::getPurchases)
                .flatMap(List::stream)
                .map(item -> invoiceMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceDetail(long Id) {
        return invoiceMapper.toDTO(fetchInvoiceById(Id));
    }

    @Override
    public void deleteInvoice(long Id) {
        invoiceRepository.deleteById(Id);
    }
    @Override
    public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, long Id) {
        invoiceDTO.setId(Id);
        InvoiceEntity entity = fetchInvoiceById(Id);
        invoiceMapper.updateInvoiceEntity(invoiceDTO, entity);
        entity = invoiceRepository.save(entity);

        return invoiceMapper.toDTO(entity);
    }

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        return invoiceRepository.getInvoiceStatistics();
    }
}
