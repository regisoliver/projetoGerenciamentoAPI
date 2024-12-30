plugins {
	java
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.projeto"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	testCompileOnly("org.projectlombok:lombok:1.18.30")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

	implementation("jakarta.validation:jakarta.validation-api:3.0.0")
	implementation("org.hibernate.validator:hibernate-validator:7.0.0.Final")

	implementation("org.postgresql:postgresql")

	implementation("io.springfox:springfox-boot-starter:3.0.0")

	testImplementation("org.mockito:mockito-core:4.8.0")
	testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")
	testImplementation("org.assertj:assertj-core:3.23.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
