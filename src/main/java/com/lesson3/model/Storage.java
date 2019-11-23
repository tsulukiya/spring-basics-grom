package com.lesson3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "STORAGE_ONE")
public class Storage {
    @JsonProperty("id")
    private long id;
    @JsonProperty("formatSupported")
    private String formatsSupported;
    @JsonProperty("storageCountry")
    private String storageCountry;
    @JsonProperty("storageSize")
    private long storageSize;


    public Storage() {
    }

    public Storage(long id) {
        this.id = id;
    }


    @Id
    @SequenceGenerator(name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FORMAT_SUPPORTED")
    public String getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    @Column(name = "STORAGE_COUNTRY")
    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    @Column(name = "STORAGE_SIZE")
    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported='" + formatsSupported + '\'' +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
