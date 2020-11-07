package de.schauseil.examples.springboot;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SliceAssignment;
import com.tngtech.archunit.library.dependencies.SliceIdentifier;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packagesOf = Application.class)
public class CyclicDependencyRulesTest {

    @ArchTest
    static final ArchRule noCyclicMethodCallsBetweenSlices =
            slices().matching("..(simplecycle).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule noCyclesByConstructorCallsBetweenSlices =
            slices().matching("..(constructorcycle).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule noCyclesByInheritanceBetweenSlices =
            slices().matching("..(inheritancecycle).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule noCyclesByFieldAccessBetweenSlices =
            slices().matching("..(fieldaccesscycle).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule noCyclesByMemberDependenciesBetweenSlices =
            slices().matching("..(membercycle).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule noCyclesInSimpleScenario =
            slices().matching("..simplescenario.(*)..").namingSlices("$1").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule noCyclesInComplexScenario =
            slices().matching("..(complexcycles).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule noCyclesInFreelyCustomizedSlices =
            slices().assignedFrom(inComplexSliceOneOrTwo())
                    .namingSlices("$1[$2]")
                    .should().beFreeOfCycles();

    private static SliceAssignment inComplexSliceOneOrTwo() {
        return new SliceAssignment() {
            @Override
            public String getDescription() {
                return "complex slice one or two";
            }

            @Override
            public SliceIdentifier getIdentifierOf(JavaClass javaClass) {
                if (javaClass.getPackageName().contains("complexcycles.slice1")) {
                    return SliceIdentifier.of("Complex-Cycle", "One");
                }
                if (javaClass.getPackageName().contains("complexcycles.slice2")) {
                    return SliceIdentifier.of("Complex-Cycle", "Two");
                }
                return SliceIdentifier.ignore();
            }
        };
    }
}
