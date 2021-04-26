.PHONY: run
run:
	make build
	docker-compose up --build -d

.PHONY: build
build:
	./gradlew build

.PHONY: test/unit
test/unit:
	./gradlew clean test

.PHONY: test/integration
test/integration: 
	./gradlew integrationTest