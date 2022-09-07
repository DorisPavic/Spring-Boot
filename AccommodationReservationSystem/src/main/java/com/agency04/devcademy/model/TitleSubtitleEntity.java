package com.agency04.devcademy.model;

import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class TitleSubtitleEntity extends Auditable<String> implements Serializable {
    @NotBlank
    @Size(max = 100)
    private String title;
    @Size(max = 150)
    private String subtitle;

    public TitleSubtitleEntity(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }
}
