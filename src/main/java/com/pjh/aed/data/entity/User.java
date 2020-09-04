package com.pjh.aed.data.entity;

import com.pjh.aed.data.response.Response;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class User extends Response {
    @Id
    @GeneratedValue
    Long id;
}
