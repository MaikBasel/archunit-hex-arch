package com.maikbasel.hexarch.archunit;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.PackageMatcher;
import com.tngtech.archunit.core.domain.properties.HasName;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;
import static com.tngtech.archunit.core.domain.Formatters.joinSingleQuoted;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static java.lang.System.lineSeparator;

public final class Architectures {

    @PublicAPI(usage = ACCESS)
    public static HexagonalArchitecture hexagonalArchitecture() {
        return new HexagonalArchitecture();
    }

    public static final class HexagonalArchitecture implements ArchRule {

        private static final String CONFIGURATION_LAYER = "configuration";
        private static final String APPLICATION_SERVICE_LAYER = "application service";
        private static final String DRIVEN_PORT_LAYER = "driven port";
        private static final String DRIVING_PORT_LAYER = "driving port";
        private static final String DRIVEN_ADAPTER_LAYER = "driven adapter";
        private static final String DRIVING_ADAPTER_LAYER = "driving adapter";

        private final Optional<String> overriddenDescription;
        private boolean optionalLayers = false;
        private Optional<DescribedPredicate<? super JavaClass>> configurationPredicate = Optional.empty();
        private Optional<DescribedPredicate<? super JavaClass>> applicationServicePredicate = Optional.empty();
        private Optional<DescribedPredicate<? super JavaClass>> drivenPortPredicate = Optional.empty();
        private Optional<DescribedPredicate<? super JavaClass>> drivingPortPredicate = Optional.empty();

        private HexagonalArchitecture() {
            this.overriddenDescription = Optional.empty();
        }

        public HexagonalArchitecture(Optional<String> overriddenDescription) {
            this.overriddenDescription = overriddenDescription;
        }

        /**
         * Defines which classes belong to the configurations by matching them against {@link PackageMatcher package identifiers}.
         *
         * @param packageIdentifiers {@link PackageMatcher package identifiers} defining which classes belong to the configurations
         * @return The {@link HexagonalArchitecture} to be checked against classes or further customized
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture configurations(String... packageIdentifiers) {
            return configurations(byPackagePredicate(packageIdentifiers));
        }

        /**
         * Defines which classes belong to the configurations by matching them against the supplied {@link DescribedPredicate predicate}.
         * <br><br>
         * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
         * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
         * {@link JavaClass} can be found within {@link JavaClass.Predicates} or one of the respective ancestors like {@link HasName.Predicates}.
         *
         * @param predicate A {@link DescribedPredicate} defining which classes belong to domain models
         * @return The {@link HexagonalArchitecture} to be checked against classes or further customized
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture configurations(DescribedPredicate<? super JavaClass> predicate) {
            configurationPredicate = Optional.of(predicate);
            return this;
        }

        /**
         * Defines which classes belong to the applicationServices by matching them against {@link PackageMatcher package identifiers}.
         *
         * @param packageIdentifiers {@link PackageMatcher package identifiers} defining which classes belong to the applicationServices
         * @return The {@link HexagonalArchitecture} to be checked against classes or further customized
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture applicationServices(String... packageIdentifiers) {
            return applicationServices(byPackagePredicate(packageIdentifiers));
        }

        /**
         * Defines which classes belong to the applicationServices by matching them against the supplied {@link DescribedPredicate predicate}.
         * <br><br>
         * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
         * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
         * {@link JavaClass} can be found within {@link JavaClass.Predicates} or one of the respective ancestors like {@link HasName.Predicates}.
         *
         * @param predicate A {@link DescribedPredicate} defining which classes belong to domain models
         * @return The {@link HexagonalArchitecture} to be checked against classes or further customized
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture applicationServices(DescribedPredicate<? super JavaClass> predicate) {
            applicationServicePredicate = Optional.of(predicate);
            return this;
        }

        /**
         * Defines which classes belong to the drivenPorts by matching them against {@link PackageMatcher package identifiers}.
         *
         * @param packageIdentifiers {@link PackageMatcher package identifiers} defining which classes belong to the drivenPorts
         * @return The {@link HexagonalArchitecture} to be checked against classes or further customized
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture drivenPorts(String... packageIdentifiers) {
            return drivenPorts(byPackagePredicate(packageIdentifiers));
        }

        /**
         * Defines which classes belong to the drivenPorts by matching them against the supplied {@link DescribedPredicate predicate}.
         * <br><br>
         * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
         * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
         * {@link JavaClass} can be found within {@link JavaClass.Predicates} or one of the respective ancestors like {@link HasName.Predicates}.
         *
         * @param predicate A {@link DescribedPredicate} defining which classes belong to domain models
         * @return The {@link HexagonalArchitecture} to be checked against classes or further customized
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture drivenPorts(DescribedPredicate<? super JavaClass> predicate) {
            drivenPortPredicate = Optional.of(predicate);
            return this;
        }

        /**
         * Defines which classes belong to the drivingPorts by matching them against {@link PackageMatcher package identifiers}.
         *
         * @param packageIdentifiers {@link PackageMatcher package identifiers} defining which classes belong to the drivingPorts
         * @return The {@link HexagonalArchitecture} to be checked against classes or further customized
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture drivingPorts(String... packageIdentifiers) {
            return drivingPorts(byPackagePredicate(packageIdentifiers));
        }

        /**
         * Defines which classes belong to the drivingPorts by matching them against the supplied {@link DescribedPredicate predicate}.
         * <br><br>
         * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
         * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
         * {@link JavaClass} can be found within {@link JavaClass.Predicates} or one of the respective ancestors like {@link HasName.Predicates}.
         *
         * @param predicate A {@link DescribedPredicate} defining which classes belong to domain models
         * @return The {@link HexagonalArchitecture} to be checked against classes or further customized
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture drivingPorts(DescribedPredicate<? super JavaClass> predicate) {
            drivingPortPredicate = Optional.of(predicate);
            return this;
        }

        /**
         * @param optionalLayers Whether the different parts of the Hexagonal Architecture (api, domain, ...) should be allowed to be empty.
         *                       If set to {@code false} the {@link HexagonalArchitecture HexagonalArchitecture} will fail if any such layer does not contain any class.
         */
        @PublicAPI(usage = ACCESS)
        public HexagonalArchitecture withOptionalLayers(boolean optionalLayers) {
            this.optionalLayers = optionalLayers;
            return this;
        }

        @Override
        public void check(JavaClasses classes) {
            Assertions.check(this, classes);
        }

        @Override
        public ArchRule because(String reason) {
            return ArchRule.Factory.withBecause(this, reason);
        }

        /**
         * This method is equivalent to calling {@link #withOptionalLayers(boolean)}, which should be preferred in this context
         * as the meaning is easier to understand.
         */
        @Override
        public ArchRule allowEmptyShould(boolean allowEmptyShould) {
            return withOptionalLayers(allowEmptyShould);
        }

        @Override
        public ArchRule as(String newDescription) {
            return new HexagonalArchitecture(Optional.of(newDescription));
        }

        @Override
        public EvaluationResult evaluate(JavaClasses classes) {
            return null;
        }

        @Override
        public String getDescription() {
            if (overriddenDescription.isPresent()) {
                return overriddenDescription.get();
            }

            var lines = new ArrayList<String>();
            lines.add("Hexagonal architecture consisting of");

            configurationPredicate.ifPresent(
                    describedPredicate -> lines.add(String.format("configurations (%s)", describedPredicate.getDescription())));
            applicationServicePredicate.ifPresent(
                    describedPredicate -> lines.add(String.format("application services (%s)", describedPredicate.getDescription())));
            drivenPortPredicate.ifPresent(
                    describedPredicate -> lines.add(String.format("driven ports (%s)", describedPredicate.getDescription())));
            drivingPortPredicate.ifPresent(
                    describedPredicate -> lines.add(String.format("driving ports (%s)", describedPredicate.getDescription())));

            return lines.stream()
                    .collect(Collectors.joining(lineSeparator()));
        }

        private DescribedPredicate<JavaClass> byPackagePredicate(String[] packageIdentifiers) {
            return resideInAnyPackage(packageIdentifiers).as(joinSingleQuoted(packageIdentifiers));
        }
    }
}


