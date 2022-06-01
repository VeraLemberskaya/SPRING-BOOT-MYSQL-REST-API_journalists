package com.example.project.journalists.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Journalist extends BaseEntity{
    private String name;
    private String surname;
    private String photoURL;
    private String description;
}
