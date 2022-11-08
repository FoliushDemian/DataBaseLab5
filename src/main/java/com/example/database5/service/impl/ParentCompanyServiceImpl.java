package com.example.database5.service.impl;

import com.example.database5.domain.ParentCompany;
import com.example.database5.exception.ParentCompanyNotFoundException;
import com.example.database5.repository.ParentCompanyRepository;
import com.example.database5.service.ParentCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentCompanyServiceImpl implements ParentCompanyService {

    @Autowired
    private ParentCompanyRepository parentCompanyRepository;

    @Override
    public List<ParentCompany> findAll() {
        return parentCompanyRepository.findAll();
    }

    @Override
    public ParentCompany findById(Integer id) {
        return parentCompanyRepository.findById(id)
                .orElseThrow(() -> new ParentCompanyNotFoundException(id));
    }

    @Override
    public ParentCompany create(ParentCompany entity) {
        return parentCompanyRepository.save(entity);
    }

    @Override
    public void update(Integer id, ParentCompany newParentCompany) {
        ParentCompany parentCompany = parentCompanyRepository.findById(id)
                .orElseThrow(() -> new ParentCompanyNotFoundException(id));
        parentCompany.setName(parentCompany.getName());
        parentCompanyRepository.save(parentCompany);
    }

    @Override
    public void delete(Integer id) {
        ParentCompany parentCompany = parentCompanyRepository.findById(id)
                .orElseThrow(() -> new ParentCompanyNotFoundException(id));
        parentCompanyRepository.delete(parentCompany);
    }
}
