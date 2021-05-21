package org.example;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private String name;
    private String position;
    private Double totalSalary;
}
