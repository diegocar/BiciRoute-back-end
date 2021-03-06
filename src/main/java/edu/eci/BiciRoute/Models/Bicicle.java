package edu.eci.BiciRoute.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Bicicle {
    @Id
    private ObjectId _id;
    private String frame;
    private String bars;
    private String forks;
    private String stem;
    private String brand;
    private String color;
}
