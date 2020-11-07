package de.schauseil.examples.springboot;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.reflections.Reflections;

import de.schauseil.examples.springboot.common.InternalPackage;

import java.util.List;
import java.util.stream.Collectors;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Evaluates {@link InternalPackage} annotations and checks that those packages are not accessed from the outside.
 */
@AnalyzeClasses(packagesOf = Application.class)
class InternalPackageTest {

    @ArchTest
    static void internalPackagesAreNotAccessedFromOutside(JavaClasses analyzedClasses) {

        String basePackage = Application.class.getPackageName();
        List<String> internalPackages = internalPackages(basePackage);

        for (String internalPackage : internalPackages) {
            assertPackageIsNotAccessedFromOutside(analyzedClasses, internalPackage, basePackage);
        }
    }

    /**
     * Finds all packages annotated with @{@link InternalPackage}.
     */
    private static List<String> internalPackages(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        return reflections.getTypesAnnotatedWith(InternalPackage.class).stream()
                .map(c -> c.getPackage().getName())
                .collect(Collectors.toList());
    }

    private static void assertPackageIsNotAccessedFromOutside(JavaClasses analyzedClasses, String internalPackage,
            String basePackage) {
        noClasses().that().resideInAPackage(basePackage)
                .and().resideOutsideOfPackage(packageMatcher(internalPackage))
                .should().dependOnClassesThat().resideInAPackage(packageMatcher(internalPackage))
                .check(analyzedClasses);
    }

    private static String packageMatcher(String fullyQualifiedPackage) {
        return fullyQualifiedPackage + "..";
    }
}