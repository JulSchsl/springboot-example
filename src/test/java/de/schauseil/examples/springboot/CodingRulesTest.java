package de.schauseil.examples.springboot;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.CompositeArchRule;

import java.util.logging.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME;

@AnalyzeClasses(packagesOf = Application.class)
public class CodingRulesTest {

    @ArchTest
    static final ArchRule noClassesShouldAccessStandardStreams = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    private void noAccessToStandardStreamsAsMethod(JavaClasses classes) {
        noClasses().should(ACCESS_STANDARD_STREAMS).check(classes);
    }

    @ArchTest
    static final ArchRule noClassesShouldThrowGenericExceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    static final ArchRule noClassesShouldUseJavaUtilLogging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    static final ArchRule loggersShouldBePrivateStaticFinal =
            fields().that().haveRawType(Logger.class)
                    .should().bePrivate()
                    .andShould().beStatic()
                    .andShould().beFinal()
                    .because("we agreed on this convention");

    @ArchTest
    static final ArchRule noClassesShouldUseJodatime = NO_CLASSES_SHOULD_USE_JODATIME;

    @ArchTest
    static final ArchRule noClassesShouldUseFieldInjection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    static final ArchRule noClassesShouldAccessStandardStreamsOrThrowGenericExceptions =
            CompositeArchRule.of(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS)
                    .and(NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS);

}
