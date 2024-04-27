package com.hoangtien2k3.inventoryservice.infrastructure.message.log;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MessageLog {

    @Id
    private UUID id;

    @Column(nullable = false)
    private Timestamp receivedAt;
}
