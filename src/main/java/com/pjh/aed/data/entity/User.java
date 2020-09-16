package com.pjh.aed.data.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pjh.aed.data.response.Response;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@JsonFilter("UserFilter")
public class User extends Response {
    @Id
    @GeneratedValue
    @NotNull
    @JsonIgnore
    Long userCode;

    @NotNull
    String name;

    @NotNull
    String id;
}
