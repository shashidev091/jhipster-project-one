package com.shashi.firstproject.jhipster;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.shashi.firstproject.jhipster");

        noClasses()
            .that()
            .resideInAnyPackage("com.shashi.firstproject.jhipster.service..")
            .or()
            .resideInAnyPackage("com.shashi.firstproject.jhipster.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.shashi.firstproject.jhipster.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
