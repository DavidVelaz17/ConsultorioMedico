package com.iwa.iwaid.consultoriomedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "medicines")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medicine {

}
