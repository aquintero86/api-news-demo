package com.applydigital.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponse {
    private List<NewsRecord> records;
}
