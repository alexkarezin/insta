package io.cryptex.ms.template.db.repository;

import io.cryptex.ms.template.db.model.Template;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends MongoRepository<Template, ObjectId> {
    Optional<Template> findByValue(String value);
}
