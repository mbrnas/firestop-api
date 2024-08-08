package org.onecode.firestopapi.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "incidents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {

    @Id
    private String id;

    @NotBlank
    @Size(max = 255)
    private String location;

    @NotNull
    @Indexed
    private Severity severity;

    @NotBlank
    @Size(max = 2000)
    private String description;

    @CreatedDate
    private LocalDateTime reportedAt;

    @NotBlank
    @Size(max = 100)
    private String reporterName;

    @NotBlank
    @Size(max = 100)
    private String reporterContact;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

}
